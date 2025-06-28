package com.sammy.malum.client.scarf;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.*;
import com.sammy.malum.registry.client.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.Component;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.client.event.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.*;

public class ScarfRenderHandler {
    public static final WeakHashMap<LivingEntity, ScarfRenderData> SCARF_DATA = new WeakHashMap<>();

    public static void tickScarfData(ClientTickEvent event) {
        ArrayList<LivingEntity> toRemove = new ArrayList<>();
        for (Map.Entry<LivingEntity, ScarfRenderData> entry : SCARF_DATA.entrySet()) {
            final ScarfRenderData data = entry.getValue();
            final LivingEntity entity = entry.getKey();
            data.tick(entity);
            if (!data.isValid(entity)) {
                toRemove.add(entity);
            }
        }
        toRemove.forEach(SCARF_DATA::remove);
    }
    public static void renderScarfData(RenderLevelStageEvent event) {
        PoseStack poseStack = event.getPoseStack();
        Camera camera = event.getCamera();
        Vec3 cameraPosition = camera.getPosition();
        poseStack.pushPose();
        poseStack.translate(-cameraPosition.x, -cameraPosition.y, -cameraPosition.z);
        for (Map.Entry<LivingEntity, ScarfRenderData> entry : SCARF_DATA.entrySet()) {
            final ScarfRenderData data = entry.getValue();
            final LivingEntity entity = entry.getKey();
            final float partialTicks = event.getPartialTick().getGameTimeDeltaPartialTick(true);
            var position = entity.getPosition(partialTicks);
            poseStack.pushPose();
            poseStack.translate(position.x, position.y, position.z);
            data.render(entity, poseStack, partialTicks);
            poseStack.popPose();
        }
        poseStack.popPose();
    }

    public static ScarfRenderData addScarfRenderer(LivingEntity living, Function<LivingEntity, ScarfRenderData> constructor) {
        return SCARF_DATA.computeIfAbsent(living, constructor);
    }

    public static class ScarfRenderData {
        public final RenderTypeToken token;
        public final TrailPointBuilder points;
        public Supplier<Boolean> isValid = () -> true;

        public Color primaryColor = Color.WHITE;
        public Color secondaryColor = Color.WHITE;


        public float scale = 1;
        public float alpha = 1;

        public ScarfRenderData(RenderTypeToken token, int trailLength) {
            this.token = token;
            this.points = new TrailPointBuilder(trailLength);
        }

        public boolean isValid(LivingEntity entity) {
            return isValid.get() && !entity.isRemoved() && !entity.isDeadOrDying();
        }

        public ScarfRenderData setPrimaryColor(Color primaryColor) {
            this.primaryColor = primaryColor;
            return this;
        }

        public ScarfRenderData setSecondaryColor(Color secondaryColor) {
            this.secondaryColor = secondaryColor;
            return this;
        }

        public ScarfRenderData setPredicate(Supplier<Boolean> isValid) {
            this.isValid = isValid;
            return this;
        }

        public ScarfRenderData setScale(float scale) {
            this.scale = scale;
            return this;
        }

        public ScarfRenderData setAlpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public void render(LivingEntity entity, PoseStack poseStack, float partialTicks) {
            BlockPos blockpos = entity.blockPosition().above(2);
            int light = entity.level().hasChunkAt(blockpos) ? LevelRenderer.getLightColor(entity.level(), blockpos) : 0;
            var renderType = LodestoneRenderTypes.TEXTURE_FADE.apply(token);
            var builder = VFXBuilders.createWorld().setRenderType(renderType).setLight(light).setAlpha(alpha);
            Vec3 scarfStart = getScarfStart(entity, partialTicks);
            points.setOrigin(scarfStart);
            poseStack.pushPose();
            float trailOffsetX = (float) Mth.lerp(partialTicks, entity.xOld, entity.getX());
            float trailOffsetY = (float) Mth.lerp(partialTicks, entity.yOld, entity.getY());
            float trailOffsetZ = (float) Mth.lerp(partialTicks, entity.zOld, entity.getZ());
            poseStack.translate(-trailOffsetX, -trailOffsetY, -trailOffsetZ);
            //TODO: actually giving it the partial tick makes it jitter when the player is stationary, but not doing so makes it jitter when the player is moving... for whatever reason
            builder.usePartialTicks(0).renderTrail(poseStack, points,
                    f -> scale * (2.5f - f * 1.75f),
                    f -> builder.setColor(ColorHelper.colorLerp(Easing.LINEAR, Mth.floor(f * 4) / 4f, secondaryColor, primaryColor))
            );
            poseStack.popPose();
        }

        public void tick(LivingEntity entity) {
            var movement = getScarfPointMovement(entity);
            points.addTrailPoint(new TrailPoint(getScarfStart(entity, 0)));
            points.run(t -> t.move(movement));
            final List<TrailPoint> list = points.getTrailPoints();
            if (list.size() > 2) {
                float age = points.getTrailLength();
                for (int i = 0; i < list.size() - 1; i++) {
                    var currentPoint = list.get(i);
                    var nextPoint = list.get(i + 1);
                    float delta = Mth.clamp(currentPoint.getAge() / age * 4, 0, 1);
                    float lerpX = (float) Mth.lerp(delta, currentPoint.getPosition().x, nextPoint.getPosition().x);
                    float lerpY = (float) Mth.lerp(delta, currentPoint.getPosition().y, nextPoint.getPosition().y);
                    float lerpZ = (float) Mth.lerp(delta, currentPoint.getPosition().z, nextPoint.getPosition().z);
                    currentPoint.setPosition(new Vec3(lerpX, lerpY, lerpZ));
                }
            }
            points.tickTrailPoints();
        }

        public Vec3 getScarfPointMovement(LivingEntity entity) {
            var lookDirection = entity.getLookAngle().scale(Mth.clamp(entity.getDeltaMovement().length(), 0, 1));
            double y = -0.02f;
            if (lookDirection.length() < 0.1f) {
                lookDirection = entity.getLookAngle().scale(0.3f);
                y = -0.08f;
            }
            double x = lookDirection.x * -0.1f;
            double z = lookDirection.z * -0.1f;
            return new Vec3(x, y, z);
        }

        public Vec3 getScarfStart(LivingEntity entity, float partialTicks) {
            var lookDirection = entity.getForward();
            final float upwardsOffset = entity.getBbHeight() * 0.8f;
            var eyePosition = entity.getPosition(partialTicks).add(0, upwardsOffset, 0);
            float yRot = ((float) (Mth.atan2(lookDirection.x, lookDirection.z) * (double) (180F / (float) Math.PI)));
            float yaw = (float) Math.toRadians(yRot);
            var left = new Vec3(-Math.cos(yaw), 0, Math.sin(yaw));
            final Vec3 offsetPosition = eyePosition.subtract(lookDirection.scale(0.2f).add(left.scale(-0.2f)));
            float angle = ((entity.level().getGameTime()+partialTicks) * 0.05f) % 6.28f;
            float offsetStrength = 0.01f;
            float xOffset = Mth.sin(angle * 4) * offsetStrength;
            float yOffset = Mth.sin(angle * 4) * offsetStrength;
            float zOffset = Mth.cos(angle * 4) * offsetStrength;
            return offsetPosition.add(xOffset, yOffset, zOffset);
        }
    }
}
