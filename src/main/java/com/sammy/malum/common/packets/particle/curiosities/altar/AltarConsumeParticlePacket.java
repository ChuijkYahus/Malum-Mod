package com.sammy.malum.common.packets.particle.curiosities.altar;

import com.sammy.malum.common.packets.particle.ItemAbsorbParticleEffectPacket;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import team.lodestar.lodestone.setup.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;

public class AltarConsumeParticlePacket extends ItemAbsorbParticleEffectPacket {

    private float alpha;

    public AltarConsumeParticlePacket(ItemStack stack, List<String> spirits, double posX, double posY, double posZ, double altarPosX, double altarPosY, double altarPosZ) {
        super(stack, spirits, posX, posY, posZ, altarPosX, altarPosY, altarPosZ);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void execute(Supplier<NetworkEvent.Context> context) {
        alpha = 0.1f / spirits.size();
        super.execute(context);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void execute(Supplier<NetworkEvent.Context> context, MalumSpiritType spiritType) {
        Level level = Minecraft.getInstance().level;
        Color color = spiritType.getPrimaryColor();
        Color endColor = spiritType.getSecondaryColor();
        WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
                .setTransparencyData(GenericParticleData.create(alpha * 2, 0f).build())
                .setScaleData(GenericParticleData.create(0.4f, 0).build())
                .setColorData(ColorParticleData.create(color, endColor).build())
                .setLifetime(60)
                .setRandomOffset(0.25f, 0.1f)
                .setRandomMotion(0.004f, 0.004f)
                .enableNoClip()
                .repeat(level, posX, posY, posZ, 12);

        WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                .setTransparencyData(GenericParticleData.create(alpha, 0f).build())
                .setScaleData(GenericParticleData.create(0.2f, 0).build())
                .setColorData(ColorParticleData.create(color, endColor).build())
                .setLifetime(30)
                .setRandomOffset(0.05f, 0.05f)
                .setRandomMotion(0.002f, 0.002f)
                .enableNoClip()
                .repeat(level, posX, posY, posZ, 8);

        Vec3 velocity = new Vec3(posX, posY, posZ).subtract(altarPosX, altarPosY, altarPosZ).normalize().scale(-0.05f);
        WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                .setTransparencyData(GenericParticleData.create(alpha, 0f).build())
                .setScaleData(GenericParticleData.create(0.3f, 0).build())
                .setColorData(ColorParticleData.create(color, color.darker()).build())
                .setLifetime(40)
                .setRandomOffset(0.15f)
                .setRandomMotion(0.005f, 0.005f)
                .addMotion(velocity.x, velocity.y, velocity.z)
                .enableNoClip()
                .repeat(level, posX, posY, posZ, 36);
    }


    public static void register(SimpleChannel instance, int index) {
        instance.registerMessage(index, AltarConsumeParticlePacket.class, AltarConsumeParticlePacket::encode, AltarConsumeParticlePacket::decode, AltarConsumeParticlePacket::handle);
    }

    public static AltarConsumeParticlePacket decode(FriendlyByteBuf buf) {
        return decode(AltarConsumeParticlePacket::new, buf);
    }
}
