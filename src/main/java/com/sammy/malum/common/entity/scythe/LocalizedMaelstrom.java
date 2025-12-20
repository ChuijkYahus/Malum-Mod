package com.sammy.malum.common.entity.scythe;

import com.sammy.malum.common.item.curiosities.curios.sets.scythe.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import team.lodestar.lodestone.helpers.*;

public class LocalizedMaelstrom extends AbstractScytheProjectileEntity {

    public LocalizedMaelstrom(Level level) {
        super(MalumEntities.SCYTHE_MAELSTROM.get(), level);
    }

    public LocalizedMaelstrom(Level level, double pX, double pY, double pZ) {
        super(MalumEntities.SCYTHE_MAELSTROM.get(), pX, pY, pZ, level);
    }

    @Override
    public void tick() {
        super.tick();
        var level = level();
        if (level instanceof ServerLevel serverLevel) {
            returnTimer--;
            if (returnTimer <= 0) {
                remove(RemovalReason.DISCARDED);
                return;
            }
            if (getOwner() instanceof LivingEntity scytheOwner) {
                CurioHowlingMaelstromRing.handleMaelstrom(serverLevel, scytheOwner, this);
                playSound();
            }
        } else {
            WeaponParticleEffects.spawnMaelstromParticles(this);
        }
    }

    public void playSound() {
        if (age % 2 == 0) {
            float pitch = (float) (0.8f + Math.sin(level().getGameTime() * 0.5f) * 0.2f);
            float volumeScalar = Mth.clamp(age / 12f, 0, 1f);
            if (isInWater()) {
                volumeScalar *= 0.2f;
                pitch *= 0.5f;
            }
            SoundHelper.playSound(this, MalumSoundEvents.SCYTHE_SWEEP.get(), 0.4f * volumeScalar, pitch);
        }
    }
}