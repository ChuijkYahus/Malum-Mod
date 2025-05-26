package com.sammy.malum.common.effect.gluttony;

import com.sammy.malum.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.compability.irons_spellbooks.IronsSpellsCompat;
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

    public static GluttonyEffectProperties applyGluttony(LivingEntity collector, Consumer<GluttonyEffectProperties> gluttonyBuilder) {
        var properties = getGluttonyEffectProperties(collector, gluttonyBuilder);
        var effectType = properties.getEffectType();
        var effectInstance = collector.getEffect(effectType);
        if (effectInstance == null) {
            if (properties.getInitialDuration() <= 0) {
                return properties;
            }
            collector.addEffect(new MobEffectInstance(effectType, properties.getInitialDuration(), properties.getInitialAmplifier(), true, true, true));
        } else {
            if (properties.getAmplifierGain() > 0) {
                EntityHelper.amplifyEffect(effectInstance, collector, properties.getAmplifierGain(), properties.getAmplifierLimit());
            }
            if (properties.getDurationGain() > 0) {
                EntityHelper.extendEffect(effectInstance, collector, properties.getDurationGain(), properties.getDurationLimit());
            }
        }
        return properties;
    }

    public static Holder<MobEffect> getGluttonyEffectType(LivingEntity collector) {
        final GluttonyEffectProperties properties = getGluttonyEffectProperties(collector, b -> {});
        return properties.effectType;
    }

    public static GluttonyEffectProperties getGluttonyEffectProperties(LivingEntity collector, Consumer<GluttonyEffectProperties> gluttonyBuilder) {
        var properties = createGluttony();
        gluttonyBuilder.accept(properties);
        var event = new ModifyGluttonyPropertiesEvent(collector, properties);
        ItemEventHandler.getEventResponders(collector).forEach(lookup -> lookup.run(IMalumEventResponder.class,
                (eventResponderItem, stack) -> eventResponderItem.modifyGluttonyPropertiesEvent(event, collector)));
        NeoForge.EVENT_BUS.post(event);
        return event.getProperties();
    }

    public static GluttonyEffectProperties createGluttony() {
        return new GluttonyEffectProperties();
    }

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

        public Holder<MobEffect> getEffectType() {
            return effectType;
        }

        public void replaceEffectType(Holder<MobEffect> effectType) {
            this.effectType = effectType;
        }

        public int getInitialDuration() {
            return initialDuration;
        }

        public int getInitialAmplifier() {
            return initialAmplifier;
        }

        public GluttonyEffectProperties scaleInitialDuration(float scalar) {
            this.initialDuration = (int) (initialDuration * scalar);
            return this;
        }

        public GluttonyEffectProperties scaleInitialAmplifier(float scalar) {
            this.initialAmplifier = (int) (initialAmplifier * scalar);
            return this;
        }

        public GluttonyEffectProperties setInitialData(int initialDuration, int initialAmplifier) {
            this.initialDuration = initialDuration;
            this.initialAmplifier = initialAmplifier;
            return this;
        }

        public int getDurationGain() {
            return durationGain;
        }

        public int getAmplifierGain() {
            return amplifierGain;
        }

        public GluttonyEffectProperties scaleDurationGain(float scalar) {
            this.durationGain = (int) (durationGain * scalar);
            return this;
        }

        public GluttonyEffectProperties scaleAmplifierGain(float scalar) {
            this.amplifierGain = (int) (amplifierGain * scalar);
            return this;
        }

        public GluttonyEffectProperties setStackingData(int durationGain, int amplifierGain) {
            this.durationGain = durationGain;
            this.amplifierGain = amplifierGain;
            return this;
        }

        public int getDurationLimit() {
            return durationLimit;
        }

        public int getAmplifierLimit() {
            return amplifierLimit;
        }

        public GluttonyEffectProperties scaleDurationLimit(float scalar) {
            this.durationLimit = (int) (durationLimit * scalar);
            return this;
        }

        public GluttonyEffectProperties scaleAmplifierLimit(float scalar) {
            this.amplifierLimit = (int) (amplifierLimit * scalar);
            return this;
        }

        public GluttonyEffectProperties setLimitData(int durationLimit, int amplifierLimit) {
            this.durationLimit = durationLimit;
            this.amplifierLimit = amplifierLimit;
            return this;
        }
    }
}