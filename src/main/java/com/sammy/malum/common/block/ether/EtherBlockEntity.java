package com.sammy.malum.common.block.ether;

import com.sammy.malum.common.item.ether.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.DataComponentRegistry;
import com.sammy.malum.visual_effects.*;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.helpers.block.*;
import team.lodestar.lodestone.registry.common.particle.*;
import team.lodestar.lodestone.systems.blockentity.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;

import java.awt.*;

public class EtherBlockEntity extends LodestoneBlockEntity {

    public DyedItemColor firstColor;
    public DyedItemColor secondColor;

    public EtherBlockEntity(BlockEntityType<? extends EtherBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.firstColor = AbstractEtherItem.DEFAULT_FIRST_COLOR;
        this.secondColor = state.getBlock().asItem() instanceof AbstractEtherItem etherItem && etherItem.iridescent
                ? AbstractEtherItem.DEFAULT_SECOND_COLOR
                : AbstractEtherItem.DEFAULT_FIRST_COLOR;
    }

    public EtherBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.ETHER.get(), pos, state);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.DYED_COLOR, firstColor);
        components.set(DataComponentRegistry.SECONDARY_DYE_COLOR, secondColor);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        firstColor = componentInput.get(DataComponents.DYED_COLOR);
        secondColor = componentInput.get(DataComponentRegistry.SECONDARY_DYE_COLOR);
    }
    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("firstColor");
        tag.remove("secondColor");
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        firstColor = DyedItemColor.CODEC.parse(NbtOps.INSTANCE, tag.get("firstColor")).result().orElse(firstColor);
        secondColor = DyedItemColor.CODEC.parse(NbtOps.INSTANCE, tag.get("secondColor")).result().orElse(secondColor);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("firstColor", DyedItemColor.CODEC.encodeStart(NbtOps.INSTANCE, firstColor).getOrThrow());
        tag.put("secondColor", DyedItemColor.CODEC.encodeStart(NbtOps.INSTANCE, secondColor).getOrThrow());
        super.saveAdditional(tag, registries);
    }

    @Override
    public void tick() {
        if (level.isClientSide) {
            if (firstColor == null) {
                return;
            }
            final RandomSource random = level.random;
            Block block = getBlockState().getBlock();
            Color start = new Color(firstColor.rgb());
            Color end = new Color(secondColor.rgb());
            double x = worldPosition.getX() + 0.5f;
            double y = worldPosition.getY() + 0.5f;
            double z = worldPosition.getZ() + 0.5f;

            switch (block) { //TODO: this sucks
                case EtherWallTorchBlock etherWallTorchBlock -> {
                    final float offset = 0.15f;
                    Direction direction = getBlockState().getValue(WallTorchBlock.FACING);
                    x -= direction.getNormal().getX() * offset;
                    y += 0.4f;
                    z -= direction.getNormal().getZ() * offset;
                }
                case EtherTorchBlock etherTorchBlock -> y += 0.3f;
                case EtherBrazierBlock etherBrazierBlock -> y -= 0.05f;
                default -> {
                }
            }

            final ColorParticleData colorData = ColorParticleData.create(start, end).setCoefficient(1.5f).setEasing(Easing.BOUNCE_IN_OUT).build();
            if (level.getGameTime() % 8L == 0) {
                int lifeTime = RandomHelper.randomBetween(random, 40, 60);
                float scale = RandomHelper.randomBetween(random, 0.6f, 0.7f);
                float velocity = RandomHelper.randomBetween(random, 0.02f, 0.03f);
                var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, new Vec3(x, y, z), colorData);
                lightSpecs.getBuilder()
                        .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                        .setLifetime(lifeTime)
                        .setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN_OUT).build())
                        .setTransparencyData(GenericParticleData.create(0.3f, 0.6f, 0).build())
                        .addMotion(0, velocity * 1.2f, 0);
                lightSpecs.spawnParticlesRaw();
            }

            if (level.getGameTime() % 2L == 0) {
                int lifeTime = RandomHelper.randomBetween(random, 12, 14);
                float scale = RandomHelper.randomBetween(random, 0.16f, 0.2f);
                float velocity = RandomHelper.randomBetween(random, 0.02f, 0.03f);
                WorldParticleBuilder.create(LodestoneParticleTypes.WISP_PARTICLE)
                        .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                        .setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN).build())
                        .setTransparencyData(GenericParticleData.create(0.4f, 0.8f, 0.2f).setEasing(Easing.QUAD_OUT).build())
                        .setColorData(colorData)
                        .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                        .setLifetime(lifeTime)
                        .addMotion(0, velocity * 1.5f, 0)
                        .enableNoClip()
                        .spawn(level, x, y, z);
                lifeTime = 20;
                scale = 0.4f;
                WorldParticleBuilder.create(LodestoneParticleTypes.TWINKLE_PARTICLE)
                        .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                        .setScaleData(GenericParticleData.create(scale, 0f).build())
                        .setTransparencyData(GenericParticleData.create(0.2f, 0.8f).build())
                        .setColorData(ColorParticleData.create(start, end).setEasing(Easing.SINE_IN).setCoefficient(0.5f).build())
                        .setSpinData(SpinParticleData.createRandomDirection(random, 0, 0.4f).setEasing(Easing.QUARTIC_IN).build())
                        .setLifetime(lifeTime)
                        .enableNoClip()
                        .spawn(level, x, y, z);
            }

            if (level.getGameTime() % 4L == 0) {
                final long gameTime = level.getGameTime();
                float scale = RandomHelper.randomBetween(random, 0.6f, 0.75f);
                float velocity = RandomHelper.randomBetween(random, 0f, 0.02f);
                float angle = ((gameTime % 24) / 24f) * (float) Math.PI * 2f;
                Vec3 offset = new Vec3(Math.sin(angle), 0, Math.cos(angle)).normalize();
                Vec3 offsetPosition = new Vec3(x + offset.x * 0.075f, y-0.05f, z + offset.z * 0.075f);
                WorldParticleBuilder.create(ParticleRegistry.SPIRIT_FLAME_PARTICLE)
                        .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                        .setScaleData(GenericParticleData.create(scale * 0.75f, scale, 0).build())
                        .setColorData(ColorParticleData.create(start, end).setEasing(Easing.CIRC_IN_OUT).setCoefficient(2.5f).build())
                        .setTransparencyData(GenericParticleData.create(0f, 1f, 0).setEasing(Easing.SINE_IN, Easing.QUAD_IN).setCoefficient(3.5f).build())
                        .addMotion(0, velocity, 0)
                        .addTickActor(p -> p.setParticleSpeed(p.getParticleSpeed().scale(1f - random.nextFloat() * 0f)))
                        .enableNoClip()
                        .setDiscardFunction(SimpleParticleOptions.ParticleDiscardFunctionType.ENDING_CURVE_INVISIBLE)
                        .spawn(level, offsetPosition.x, offsetPosition.y, offsetPosition.z);
            }
        }
    }
}