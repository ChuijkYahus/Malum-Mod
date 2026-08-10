package com.sammy.malum.datagen.sound;

import com.sammy.malum.common.entity.mob.cultist.*;
import com.sammy.malum.registry.common.sound.*;

import static team.lodestar.lodestone.modules.datagen.providers.sound.LodestoneSoundEventSystem.sound;

public class MalumMobSoundDatagen extends MalumSoundDatagenWrapper {

    public MalumMobSoundDatagen(MalumSoundDatagen soundDatagen) {
        super(soundDatagen);
    }

    @Override
    public void registerSounds() {
        //Cultist
        addCultistSounds(MalumCultistSoundEvents.ALTAR, 1.4f);
        addCultistSounds(MalumCultistSoundEvents.BELIEVER, 1.2f);
        addCultistSounds(MalumCultistSoundEvents.CHERUB, 1.6f);
        addCultistSounds(MalumCultistSoundEvents.CARDINAL, 0.8f);
        addCultistSounds(MalumCultistSoundEvents.EVANGELIST, 1.4f);


        //Altar
        add(MalumCultistSoundEvents.ALTAR_BLESSING_LAUNCH, s -> s.with(allSounds("mob/cultist/altar/shoot_bless")));
        add(MalumCultistSoundEvents.ALTAR_BLESSING_IMPACT, s -> s.with(allSounds("mob/cultist/altar/impact_bless")));
        add(MalumCultistSoundEvents.ALTAR_CURSE_LAUNCH, s -> s.with(allSounds("mob/cultist/altar/shoot_curse")));
        add(MalumCultistSoundEvents.ALTAR_CURSE_IMPACT, s -> s.with(allSounds("mob/cultist/altar/impact_curse")));
        add(MalumCultistSoundEvents.ALTAR_MELEE_ATTACK, s -> s.with(allSounds("mob/cultist/altar/attack")));
        add(MalumCultistSoundEvents.ALTAR_FLEE, s -> s.with(allSounds("mob/cultist/altar/flee")));

        //Cherub

        add(MalumCultistSoundEvents.CHERUB_PREPARE_SPELL, s -> s.with(allSounds("mob/cultist/cherub/prepare_spell")));
        add(MalumCultistSoundEvents.CHERUB_CAST_CURSE, s -> s.with(allSounds("mob/cultist/cherub/cast_curse")));
        add(MalumCultistSoundEvents.CHERUB_CAST_HEAL, s -> s.with(allSounds("mob/cultist/cherub/cast_heal")));


        //Cardinal
        add(MalumCultistSoundEvents.CARDINAL_ENTROPY_THROW, s -> s.with(allSounds("mob/cultist/cardinal/entropy_charge/throw")));
        add(MalumCultistSoundEvents.CARDINAL_ENTROPY_LOOP, s -> s.with(sound("mob/cultist/cardinal/entropy_charge/loop")));
        add(MalumCultistSoundEvents.CARDINAL_ENTROPY_PRIME, s -> s.with(allSounds("mob/cultist/cardinal/entropy_charge/prime")));
        add(MalumCultistSoundEvents.CARDINAL_ENTROPY_DETONATE, s -> s.with(allSounds("mob/cultist/cardinal/entropy_charge/detonate")));
        add(MalumCultistSoundEvents.CARDINAL_ENTROPY_IMMOLATE, s -> s.with(allSounds("mob/cultist/cardinal/entropy_charge/immolate")));

        add(MalumCultistSoundEvents.CARDINAL_KNOCKBACK_CHARGE, s -> s.with(allSounds("mob/cultist/cardinal/knockback_charge")));
        add(MalumCultistSoundEvents.CARDINAL_KNOCKBACK_FIRE, s -> s.with(allSounds("mob/cultist/cardinal/knockback_fire")));

        add(MalumCultistSoundEvents.CARDINAL_CANNON_CHARGE, s -> s.with(allSounds("mob/cultist/cardinal/cannon_charge")));
        add(MalumCultistSoundEvents.CARDINAL_CANNON_FIRE, s -> s.with(allSounds("mob/cultist/cardinal/cannon_fire")));

        add(MalumCultistSoundEvents.CARDINAL_IMMOLATION_CHARGE, s -> s.with(allSounds("mob/cultist/cardinal/cannon_charge", se -> se.pitch(0.7f))));
        add(MalumCultistSoundEvents.CARDINAL_IMMOLATION_FIRE, s -> s.with(allSounds("mob/cultist/cardinal/cannon_fire", se -> se.pitch(0.5f))));

    }

    public void addCultistSounds(CultistSoundDefinition definition, float pitch) {
        var rank = definition.profile.getName();
        var basePath = "mob/cultist/" + rank;
        add(definition.idleSound, s -> s.with(allSounds(basePath + "_idle", se -> se.pitch(pitch))));
        add(definition.hurtSound, s -> s.with(allSounds(basePath + "_hurt", se -> se.pitch(pitch))));
        add(definition.deathSound, s -> s.with(allSounds(basePath + "_death", se -> se.pitch(pitch))));
    }
}
