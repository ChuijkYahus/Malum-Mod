package com.sammy.malum.registry.common.sound;

import com.sammy.malum.common.entity.mob.cultist.*;
import com.sammy.malum.common.entity.mob.cultist.CultistSoundDefinition.*;
import net.minecraft.sounds.*;
import net.neoforged.neoforge.registries.*;

public class MalumCultistSoundEvents {

    public static void init() {

    }

    public static final CultistSoundDefinition ALTAR = new CultistSoundDefinition(CultistSoundProfile.MINOR, "altar");
    public static final CultistSoundDefinition BELIEVER = new CultistSoundDefinition(CultistSoundProfile.MINOR, "believer");
    public static final CultistSoundDefinition CHERUB = new CultistSoundDefinition(CultistSoundProfile.MINOR, "cherub");

    public static final CultistSoundDefinition CARDINAL = new CultistSoundDefinition(CultistSoundProfile.MINOR, "cardinal");
    public static final CultistSoundDefinition EVANGELIST = new CultistSoundDefinition(CultistSoundProfile.MINOR, "evangelist");

    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_ENTROPY_THROW = MalumSoundEvents.registerVariable("cardinal_entropy_throw");
    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_ENTROPY_LOOP = MalumSoundEvents.registerVariable("cardinal_entropy_loop");
    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_ENTROPY_PRIME = MalumSoundEvents.registerVariable("cardinal_entropy_prime");
    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_ENTROPY_DETONATE = MalumSoundEvents.registerVariable("cardinal_entropy_detonate");
    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_ENTROPY_IMMOLATE = MalumSoundEvents.registerVariable("cardinal_entropy_immolate");

    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_KNOCKBACK_CHARGE = MalumSoundEvents.registerVariable("cardinal_knockback_charge");
    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_CANNON_CHARGE = MalumSoundEvents.registerVariable("cardinal_cannon_charge");
    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_IMMOLATION_CHARGE = MalumSoundEvents.registerVariable("cardinal_immolation_charge");

    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_KNOCKBACK_FIRE = MalumSoundEvents.registerVariable("cardinal_knockback_fire");
    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_CANNON_FIRE = MalumSoundEvents.registerVariable("cardinal_cannon_fire");
    public static final DeferredHolder<SoundEvent, SoundEvent> CARDINAL_IMMOLATION_FIRE = MalumSoundEvents.registerVariable("cardinal_immolation_fire");

}
