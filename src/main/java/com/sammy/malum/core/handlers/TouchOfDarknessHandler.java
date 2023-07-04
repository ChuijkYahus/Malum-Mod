package com.sammy.malum.core.handlers;

import com.mojang.blaze3d.systems.*;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.common.block.curiosities.weeping_well.*;
import com.sammy.malum.common.capability.*;
import com.sammy.malum.common.packets.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.*;
import net.minecraft.nbt.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.block.state.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.network.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import java.util.function.*;

public class TouchOfDarknessHandler {

    public static final float MAX_AFFLICTION = 100f;

    public int expectedAffliction;
    public int afflictionDuration;
    public float currentAffliction;
    public int rejection;
    public int timeSpentInGoop;

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("expectedAffliction", expectedAffliction);
        tag.putInt("afflictionDuration", afflictionDuration);
        tag.putFloat("currentAffliction", currentAffliction);
        tag.putInt("rejection", rejection);
        tag.putInt("timeSpentInGoop", timeSpentInGoop);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        expectedAffliction = tag.getInt("expectedAffliction");
        afflictionDuration = tag.getInt("afflictionDuration");
        currentAffliction = tag.getFloat("currentAffliction");
        rejection = tag.getInt("rejection");
        timeSpentInGoop = tag.getInt("timeSpentInGoop");
    }

    public static void touchedByGoop(BlockState pState, LivingEntity livingEntity) {
        //while a living entity is in the primordial soup, we will bring their expected affliction to 100, slow their movement down.
        float intensity = 0.4f;
        TouchOfDarknessHandler touchOfDarknessHandler = MalumLivingEntityDataCapability.getCapability(livingEntity).touchOfDarknessHandler;
        if (touchOfDarknessHandler.isEntityRejected()) {
            return;
        }
        livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().multiply(intensity, intensity, intensity));
        touchOfDarknessHandler.afflict(100);
    }

    public static double updateEntityGravity(LivingEntity livingEntity, double value) {
        TouchOfDarknessHandler handler = MalumLivingEntityDataCapability.getCapability(livingEntity).touchOfDarknessHandler;
        if (handler.timeSpentInGoop > 0) {
            double intensity = 1- Math.min(60, handler.timeSpentInGoop) / 60f;
            return value * intensity;
        }
        return value;
    }

    public static void entityTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        TouchOfDarknessHandler handler = MalumLivingEntityDataCapability.getCapability(livingEntity).touchOfDarknessHandler;
        boolean isInTheGoop = livingEntity.level.getBlockState(livingEntity.blockPosition()).getBlock() instanceof PrimordialSoupBlock;
        if (handler.afflictionDuration > 0) { // tick down the duration of touch of darkness.
            handler.afflictionDuration--;
            if (handler.afflictionDuration == 0) { //if it reaches zero, set expectedAffliction to 0, eventually ending the effect
                handler.expectedAffliction = 0;
            }
        }
        if (handler.currentAffliction <= handler.expectedAffliction) { //increment the affliction until it reaches the limit value
            float increase = 1.55f;
            if (handler.afflictionDuration < 20) { //increase in affliction strength decreases if effect is about to run out
                increase *= handler.afflictionDuration / 20f;
            }
            handler.currentAffliction = Math.min(MAX_AFFLICTION, handler.currentAffliction+increase);
            //if the entity's affliction reached the max, and the entity isn't close to actively being rejected, and is in the goop, they are rejected
            //rejection is set to 15, and the entity starts rapidly ascending as a result
            //we do this only on the server, and then communicate the rejection to the client using a packet
            if (!livingEntity.level.isClientSide) {
                if (handler.currentAffliction >= MAX_AFFLICTION && (handler.rejection < 5 || handler.rejection > 25) && handler.timeSpentInGoop > 60) {
                    handler.reject(livingEntity);
                }
            }
        }
        //if the expected affliction is lower than the current affliction, it diminishes.
        //current affliction diminishes faster if the expected value is 0
        if (handler.currentAffliction > handler.expectedAffliction) {
            handler.currentAffliction = Math.max(handler.currentAffliction - (handler.expectedAffliction == 0 ? 1.5f : 0.75f), 0);
        }
        //if we in the goop, we in the goop
        if (isInTheGoop) {
            handler.timeSpentInGoop++;
            boolean isPlayer = livingEntity instanceof Player;
            if (isPlayer && livingEntity.level.getGameTime() % 6L == 0) {
                livingEntity.level.playSound(null, livingEntity.blockPosition(), SoundRegistry.SONG_OF_THE_VOID.get(), SoundSource.HOSTILE, 0.5f+handler.timeSpentInGoop*0.02f, 0.5f+handler.timeSpentInGoop*0.04f);
            }
            if (!isPlayer) {
                if (livingEntity.getDeltaMovement().y > 0) {
                    livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().multiply(1, 0.5f, 1));
                }
            }
        }
        //if we ain't in the goop, we ain't in the goop.
        if (!isInTheGoop || handler.isEntityRejected()) {
            handler.timeSpentInGoop = 0;
        }
        //rejection grows while at max affliction, and already rejected.
        if (handler.currentAffliction >= TouchOfDarknessHandler.MAX_AFFLICTION && handler.isEntityRejected()) {
            handler.rejection += 2;
        }
        //rejection diminishes naturally
        handler.rejection = Math.max(handler.rejection - 1, 0);
        //handles throwing the entity out, if the entity is being rejected it's Y velocity is forced into a diminishing value
        if (handler.isEntityRejected()) {
            float intensity = (40 - handler.rejection) / 30f;
            livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().x, Math.pow(1.1f * (intensity), 2), livingEntity.getDeltaMovement().z);
            if (handler.rejection >= 40) {
                handler.rejection = 0;
            }
        }
    }

    public void afflict(int expectedAffliction) {
        if (this.expectedAffliction > expectedAffliction) {
            return;
        }
        this.expectedAffliction = expectedAffliction;
        this.afflictionDuration = 60;
    }

    public void reject(LivingEntity livingEntity) {
        if (!(livingEntity instanceof Player)) {
            livingEntity.remove(Entity.RemovalReason.DISCARDED);
            return;
        }
        expectedAffliction = 0;
        currentAffliction += 40f;
        afflictionDuration = 0;
        rejection = 10;
        if (!livingEntity.level.isClientSide) {
            PacketRegistry.MALUM_CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> livingEntity), new VoidRejectionPacket(livingEntity.getId()));
            ParticleEffectTypeRegistry.WEEPING_WELL_REACTS.createEntityEffect(livingEntity);
            livingEntity.hurt(new DamageSource(DamageSourceRegistry.VOODOO_IDENTIFIER), 4);
            livingEntity.level.playSound(null, livingEntity.blockPosition(), SoundRegistry.VOID_REJECTION.get(), SoundSource.HOSTILE, 2f, Mth.nextFloat(livingEntity.getRandom(), 0.85f, 1.35f));
        }
        livingEntity.addEffect(new MobEffectInstance(MobEffectRegistry.REJECTED.get(), 400, 0));
    }

    public boolean isEntityRejected() {
        return rejection >= 10;
    }

    public static class ClientOnly {
        private static final Tesselator INSTANCE = new Tesselator();

        public static void renderDarknessVignette(PoseStack poseStack) {
            Minecraft minecraft = Minecraft.getInstance();
            Player player = minecraft.player;
            TouchOfDarknessHandler darknessHandler = MalumLivingEntityDataCapability.getCapability(player).touchOfDarknessHandler;
            if (darknessHandler.currentAffliction == 0f) {
                return;
            }
            int screenWidth = minecraft.getWindow().getGuiScaledWidth();
            int screenHeight = minecraft.getWindow().getGuiScaledHeight();

            float effectStrength = Easing.SINE_IN_OUT.ease(darknessHandler.currentAffliction / MAX_AFFLICTION, 0, 1, 1);
            float alpha = Math.min(1, effectStrength * 5);
            float zoom = 0.5f + Math.min(0.35f, effectStrength);
            float intensity = 1f + (effectStrength > 0.5f ? (effectStrength - 0.5f) * 2.5f : 0);

            ExtendedShaderInstance shaderInstance = (ExtendedShaderInstance) ShaderRegistry.TOUCH_OF_DARKNESS.getInstance().get();
            shaderInstance.safeGetUniform("Speed").set(1000f);
            Consumer<Float> setZoom = f -> shaderInstance.safeGetUniform("Zoom").set(f);
            Consumer<Float> setIntensity = f -> shaderInstance.safeGetUniform("Intensity").set(f);
            VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
                    .setPosColorTexDefaultFormat()
                    .setPositionWithWidth(0, 0, screenWidth, screenHeight)
                    .overrideBufferBuilder(INSTANCE.getBuilder())
                    .setColor(0, 0, 0)
                    .setAlpha(alpha)
                    .setShader(ShaderRegistry.TOUCH_OF_DARKNESS.getInstance());

            poseStack.pushPose();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            setZoom.accept(zoom);
            setIntensity.accept(intensity);
            builder.draw(poseStack);

            setZoom.accept(zoom * 1.25f + 0.15f);
            setIntensity.accept(intensity * 0.8f + 0.5f);
            builder.setAlpha(0.5f * alpha).draw(poseStack);

            RenderSystem.disableBlend();
            poseStack.popPose();

            shaderInstance.setUniformDefaults();
        }
    }
}