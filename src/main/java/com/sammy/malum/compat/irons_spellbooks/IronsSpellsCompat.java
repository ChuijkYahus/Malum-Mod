package com.sammy.malum.compat.irons_spellbooks;

import com.google.common.collect.*;
import com.sammy.malum.*;
import com.sammy.malum.common.effect.*;
import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.config.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.enchantment.*;
import io.redspace.ironsspellbooks.api.events.*;
import io.redspace.ironsspellbooks.api.magic.*;
import io.redspace.ironsspellbooks.api.registry.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.neoforged.fml.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.event.entity.living.*;

import static com.sammy.malum.registry.common.enchantment.EnchantmentKeys.*;

public class IronsSpellsCompat {

    public static boolean LOADED;

    public static void init() {
        LOADED = ModList.get().isLoaded("irons_spellbooks");
        if (LOADED) {
            NeoForge.EVENT_BUS.addListener(LoadedOnly::spellDamage);
            NeoForge.EVENT_BUS.addListener(LoadedOnly::triggerReplenishing);
        }
    }

    public static void generateMana(ServerPlayer collector, double amount) {
        generateMana(collector, (float) amount);
    }

    public static void generateMana(ServerPlayer collector, float amount) {
        if (LOADED) {
            LoadedOnly.generateMana(collector, amount);
        }
    }

    public static void recoverSpellCooldowns(LivingEntity mage, int enchantmentLevel) {
        if (LOADED) {
            LoadedOnly.recoverSpellCooldowns(mage, enchantmentLevel);
        }
    }
    public static void addSoulHunterSpellPower(ItemAttributeModifiers.Builder attributes, EquipmentSlotGroup group) {
        if (LOADED) {
            LoadedOnly.addSoulHunterSpellPower(attributes, group);
        }
    }
    public static void addSpellPowerToCurio(MalumCurioItem item, Multimap<Holder<Attribute>, AttributeModifier> map, ResourceLocation id, float amount) {
        if (LOADED) {
            LoadedOnly.addSpellPowerToCurio(item, map, id, amount);
        }
    }

    public static void addEchoingArcanaSpellCooldown(EchoingArcanaEffect effect) {
        if (LOADED) {
            LoadedOnly.addEchoingArcanaSpellCooldown(effect);
        }
    }

    public static void addSilencedNegativeAttributeModifiers(SilencedEffect effect) {
        if (LOADED) {
            LoadedOnly.addSilencedNegativeAttributeModifiers(effect);
        }
    }

    public static class LoadedOnly {

        public static void spellDamage(SpellDamageEvent event) {
            boolean canShatter = event.getEntity() instanceof Player ?
                    CommonConfig.IRONS_SPELLBOOKS_SPIRIT_DAMAGE.getConfigValue() :
                    CommonConfig.IRONS_SPELLBOOKS_NON_PLAYER_SPIRIT_DAMAGE.getConfigValue();
            if (canShatter) {
                event.getEntity().getData(MalumAttachmentTypes.LIVING_SOUL_INFO).setExposed();
            }
        }

        public static void triggerReplenishing(LivingDamageEvent.Post event) {
            DamageSource source = event.getSource();
            Entity directEntity = source.getDirectEntity();
            if (directEntity instanceof ServerPlayer serverPlayer) {
                if (serverPlayer.getAttackStrengthScale(0) > 0.8f) {
                    ItemStack stack = serverPlayer.getMainHandItem();
                    int level = getEnchantmentLevel(serverPlayer.level(), EnchantmentKeys.REPLENISHING, stack);
                    recoverSpellCooldowns(serverPlayer, 0.025f * level);
                }
            }
        }

        public static void generateMana(ServerPlayer mage, float amount) {
            var magicData = MagicData.getPlayerMagicData(mage);
            magicData.addMana(amount);
            //TODO: this fucker
//            UpdateClient.SendManaUpdate(collector, magicData);
        }

        public static void recoverSpellCooldowns(LivingEntity mage, float amount) {
            var cooldowns = MagicData.getPlayerMagicData(mage).getPlayerCooldowns();
            cooldowns.getSpellCooldowns().forEach((key, value) -> cooldowns.decrementCooldown(value, (int) (value.getSpellCooldown() * amount)));
            if (mage instanceof ServerPlayer serverPlayer) {
                cooldowns.syncToPlayer(serverPlayer);
            }
        }

        public static void addSoulHunterSpellPower(ItemAttributeModifiers.Builder attributes, EquipmentSlotGroup group) {
            attributes.add(AttributeRegistry.SPELL_POWER,
                    new AttributeModifier(MalumMod.malumPath("soul_hunter_armor"), 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                    group);
        }

        public static void addSpellPowerToCurio(MalumCurioItem item, Multimap<Holder<Attribute>, AttributeModifier> map, ResourceLocation id, float amount) {
            item.addAttributeModifier(map, AttributeRegistry.SPELL_POWER,
                    new AttributeModifier(id, amount, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }

        public static void addEchoingArcanaSpellCooldown(EchoingArcanaEffect effect) {
            effect.addAttributeModifier(AttributeRegistry.COOLDOWN_REDUCTION, MalumMod.malumPath("echoing_arcana"), 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        }

        public static void addSilencedNegativeAttributeModifiers(SilencedEffect effect) {
            var id = MalumMod.malumPath("silenced");
            effect.addAttributeModifier(AttributeRegistry.MANA_REGEN, id, -0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            effect.addAttributeModifier(AttributeRegistry.SPELL_POWER, id, -0.05f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        }
    }
}