package com.sammy.malum.common.entity.mob.cultist;

import com.sammy.malum.registry.common.sound.*;
import net.minecraft.sounds.*;
import net.neoforged.neoforge.registries.*;

public class CultistSoundDefinition {

    public final CultistSoundProfile profile;
    public final String name;
    public final DeferredHolder<SoundEvent, SoundEvent> idleSound;
    public final DeferredHolder<SoundEvent, SoundEvent> hurtSound;
    public final DeferredHolder<SoundEvent, SoundEvent> deathSound;

    public CultistSoundDefinition(CultistSoundProfile profile, String name) {
        this.profile = profile;
        this.name = name;
        this.idleSound = MalumSoundEvents.registerVariable(name + "_idle");
        this.hurtSound = MalumSoundEvents.registerVariable(name + "_hurt");
        this.deathSound = MalumSoundEvents.registerVariable(name + "_death");
    }

    public enum CultistSoundProfile {
        MINOR("minor"),
        MAJOR("major");

        public final String name;

        CultistSoundProfile(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
