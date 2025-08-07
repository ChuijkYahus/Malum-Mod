package com.sammy.malum.common.effect.gluttony;

import com.sammy.malum.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.compat.irons_spellbooks.IronsSpellsCompat;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

import java.util.function.*;

import static net.neoforged.neoforge.event.entity.living.MobEffectEvent.Applicable.Result.DO_NOT_APPLY;

public class GluttonyEffect extends MobEffect {
    public GluttonyEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(88, 86, 60));
        addAttributeModifier(LodestoneAttributes.MAGIC_PROFICIENCY, MalumMod.malumPath("gluttony"), 0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        IronsSpellsCompat.addGluttonySpellPower(this);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn instanceof Player player) {
            player.causeFoodExhaustion(0.005f * (amplifier + 1));
        }
        return true;
    }

    public static void canApplyPotion(MobEffectEvent.Applicable event) {
        MobEffectInstance potionEffect = event.getEffectInstance();
        LivingEntity entityLiving = event.getEntity();
        if (potionEffect.getEffect().equals(MobEffects.HUNGER)) {
            final Holder<MobEffect> gluttony = getGluttonyEffectType(event.getEntity());
            if (entityLiving.hasEffect(gluttony)) {
                event.setResult(DO_NOT_APPLY);
            }
        }
    }

    public static void removeExistingHunger(MobEffectEvent.Added event) {
        MobEffectInstance potionEffect = event.getEffectInstance();
        if (potionEffect == null) {
            return;
        }
        final LivingEntity entity = event.getEntity();
        final Holder<MobEffect> gluttony = getGluttonyEffectType(entity);
        //TODO: this should instead just check if the applied effect is any of the gluttony effects instead of figuring out the active one, ideally with a tag but mob effect tags don't seem to exist?
        if (potionEffect.getEffect().equals(gluttony)) {
            if (entity.hasEffect(MobEffects.HUNGER)) {
                entity.removeEffect(MobEffects.HUNGER);
            }
        }
    }

    public static GluttonyEffectProperties applyGluttony(LivingEntity target, Consumer<GluttonyEffectProperties> gluttonyBuilder) {
        var properties = getGluttonyEffectProperties(target, gluttonyBuilder);
        properties.apply(target);
        return properties;
    }

    public static Holder<MobEffect> getGluttonyEffectType(LivingEntity target) {
        final GluttonyEffectProperties properties = getGluttonyEffectProperties(target, b -> {});
        return properties.effectType;
    }

    public static GluttonyEffectProperties getGluttonyEffectProperties(LivingEntity target, Consumer<GluttonyEffectProperties> gluttonyBuilder) {
        var properties = createGluttony();
        gluttonyBuilder.accept(properties);
        var event = new ModifyGluttonyPropertiesEvent(target, properties);
        ItemEventHandler.getEventResponders(target).forEach(lookup -> lookup.run(IMalumEventResponder.class,
                (eventResponderItem, stack) -> eventResponderItem.modifyGluttonyPropertiesEvent(event, target)));
        NeoForge.EVENT_BUS.post(event);
        return event.getProperties();
    }

    public static GluttonyEffectProperties createGluttony() {
        return new GluttonyEffectProperties();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class GluttonyEffectProperties {

        private Holder<MobEffect> effectType = MalumMobEffects.GLUTTONY;
        private int initialDuration;
        private int initialAmplifier;
        private int durationGain;
        private int amplifierGain;
        private int durationLimit = -1;
        private int amplifierLimit = -1;

        private GluttonyEffectProperties() {
        }

        public void apply(LivingEntity target) {
            var effectType = getEffectType();
            var effectInstance = target.getEffect(effectType);
            if (effectInstance == null) {
                int initialDuration = getInitialDuration();
                int initialAmplifier = getInitialAmplifier() - 1;
                if (initialDuration <= 0) {
                    return;
                }
                target.addEffect(new MobEffectInstance(effectType, initialDuration, initialAmplifier, true, true, true));
            } else {
                int amplifierLimit = getAmplifierLimit() - 1;
                int durationLimit = getDurationLimit();
                int amplifierGain = getAmplifierGain();
                int durationGain = getDurationGain();
                if (amplifierGain > 0) {
                    EntityHelper.amplifyEffect(effectInstance, target, amplifierGain, amplifierLimit);
                }
                if (durationGain > 0) {
                    EntityHelper.extendEffect(effectInstance, target, durationGain, durationLimit);
                }
            }
        }

        public Holder<MobEffect> getEffectType() {
            return effectType;
        }

        public GluttonyEffectProperties replaceEffectType(Holder<MobEffect> effectType) {
            this.effectType = effectType;
            return this;
        }

        public GluttonyEffectProperties setInitialDuration(int initialDuration) {
            this.initialDuration = initialDuration;
            return this;
        }

        public GluttonyEffectProperties setInitialAmplifier(int initialAmplifier) {
            this.initialAmplifier = initialAmplifier;
            return this;
        }

        public GluttonyEffectProperties setDurationGain(int durationGain) {
            this.durationGain = durationGain;
            return this;
        }

        public GluttonyEffectProperties setAmplifierGain(int amplifierGain) {
            this.amplifierGain = amplifierGain;
            return this;
        }

        public GluttonyEffectProperties setDurationLimit(int durationLimit) {
            this.durationLimit = durationLimit;
            return this;
        }

        public GluttonyEffectProperties setAmplifierLimit(int amplifierLimit) {
            this.amplifierLimit = amplifierLimit;
            return this;
        }

        public GluttonyEffectProperties scaleInitialDuration(float scalar) {
            this.initialDuration = (int) (initialDuration * scalar);
            return this;
        }

        public GluttonyEffectProperties scaleInitialAmplifier(float scalar) {
            this.initialAmplifier = (int) (initialAmplifier * scalar);
            return this;
        }

        public GluttonyEffectProperties scaleDurationGain(float scalar) {
            this.durationGain = (int) (durationGain * scalar);
            return this;
        }

        public GluttonyEffectProperties scaleAmplifierGain(float scalar) {
            this.amplifierGain = (int) (amplifierGain * scalar);
            return this;
        }

        public GluttonyEffectProperties scaleDurationLimit(float scalar) {
            this.durationLimit = (int) (durationLimit * scalar);
            return this;
        }

        public GluttonyEffectProperties scaleAmplifierLimit(float scalar) {
            this.amplifierLimit = (int) (amplifierLimit * scalar);
            return this;
        }

        public int getInitialDuration() {
            int limit = getDurationLimit();
            return limit == -1 ? initialDuration : Math.min(initialDuration, limit);
        }

        public int getInitialAmplifier() {
            int limit = getAmplifierLimit();
            return limit == -1 ? initialAmplifier : Math.min(initialAmplifier, limit);
        }

        public int getDurationGain() {
            return durationGain;
        }

        public int getAmplifierGain() {
            return amplifierGain;
        }

        public int getDurationLimit() {
            return durationLimit;
        }

        public int getAmplifierLimit() {
            return amplifierLimit;
        }
    }
}