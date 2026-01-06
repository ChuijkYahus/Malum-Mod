package com.sammy.malum.common.item.curiosities.weapons.scythe;

import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.damagesource.*;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.enchanting.*;
import team.lodestar.lodestone.systems.item.*;

public class RavenousScytheItem extends MagicScytheItem {

    public RavenousScytheItem(Tier tier, float attackDamage, float attackSpeed, float magicDamage, LodestoneItemProperties properties) {
        super(tier, attackDamage, attackSpeed, magicDamage, properties);
    }

    @Override
    public void modifyAttributeTooltipEvent(AddAttributeTooltipsEvent event) {
        event.addTooltipLines(ComponentHelper.positiveEffect("ravenous_scythe_gluttony"));
    }

    @Override
    public SpiritLike getDefiningSpiritType() {
        return MalumSpiritTypes.EARTHEN_SPIRIT;
    }

    @Override
    public void outgoingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        super.outgoingDamageEvent(event, attacker, target, stack);
        if (attacker.level() instanceof ServerLevel level) {
            var source = event.getSource();
            if (source.is(MalumTags.DamageTypeTags.IS_SCYTHE) || source.is(MalumDamageTypes.INVERTED_HEART_PROPAGATION)) {
                if (source.is(MalumTags.DamageTypeTags.IS_SCYTHE_MELEE)) {
                    if (!LodestoneEnchantmentEffectCommonsHelper.isChargedAttack(attacker)) {
                        return;
                    }
                    SoundHelper.playSound(attacker, MalumSoundEvents.RAVENOUS_SCYTHE_EATS.get(), 1, RandomHelper.randomBetween(attacker.getRandom(), 1f, 1.5f));
                }
                if (source.is(MalumDamageTypes.SCYTHE_MAELSTROM)) {
                    if (level.getRandom().nextFloat() >= 0.2f) {
                        return;
                    }
                }
                GluttonyEffect.applyGluttony(attacker, b -> b
                        .setAmplifierGain(1)
                        .setAmplifierLimit(5));
            }
        }
    }
}