package com.sammy.malum.common.geas.pact.infernal;

import com.mojang.datafixers.util.*;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.tags.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.event.level.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;
import java.util.function.*;

public class ProspectorGeas extends GeasEffect {

    public ProspectorGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("ore_prospecting"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("prospectors_greed"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("greed_combustion"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void finalizedIncomingDamageEvent(LivingDamageEvent.Post event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (!event.getSource().is(DamageTypeTags.IS_FIRE)) {
            var effect = target.getEffect(MalumMobEffects.PROSPECTORS_GREED);
            if (effect != null) {
                target.igniteForSeconds((effect.amplifier + 1) / 2f);
                if (target.level() instanceof ServerLevel level) {
                    MalumParticleEffectTypes.PROSPECTORS_GREED_BURN.createEffect(target)
                            .color(new MalumNetworkedParticleEffectColorData(MalumSpiritTypes.INFERNAL_SPIRIT))
                            .spawn(level);
                }
            }
        }
    }

    public static void pickupItem(ItemEntityPickupEvent.Post event) {
        if (GeasEffectHandler.hasGeasEffect(event.getPlayer(), MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR)) {
            ItemStack stack = event.getItemEntity().getItem();
            ProspectorMarkData data = stack.get(MalumDataComponents.PROSPECTOR_MARK);
            if (data != null) {
                if (data.hasProspectorMark()) {
                    var target = event.getPlayer();
                    var effect = MalumMobEffects.PROSPECTORS_GREED;
                    var instance = target.getEffect(effect);
                    if (instance == null) {
                        target.addEffect(new MobEffectInstance(effect, 200, 0, true, true, true));
                    } else {
                        EntityHelper.amplifyEffect(instance, target, 1, 5);
                        EntityHelper.extendEffect(instance, target, 100, 1200);
                    }
                }
            }
        }
    }

    public static void modifyBlockDrops(BlockDropsEvent event) {
        if (hasProspector(event.getBreaker())) {
            List<ItemStack> drops = event.getDrops().stream().map(ItemEntity::getItem).toList();
            modifyBlockDrops(drops);
        }
    }

    public static void modifyExplosionDrops(Entity entity, List<Pair<ItemStack, BlockPos>> dropList, Function<BlockPos, BlockState> blockStateGetter) {
        if (ProspectorGeas.hasProspector(entity)) {
            HashMap<BlockPos, List<ItemStack>> sortedDrops = new HashMap<>();
            for (Pair<ItemStack, BlockPos> drop : dropList) {
                sortedDrops.computeIfAbsent(drop.getSecond(), k -> new ArrayList<>()).add(drop.getFirst());
            }
            for (Map.Entry<BlockPos, List<ItemStack>> loot : sortedDrops.entrySet()) {
                BlockState blockState = blockStateGetter.apply(loot.getKey());
                if (blockState.is(Tags.Blocks.ORES)) {
                    List<ItemStack> drops = loot.getValue();
                    ProspectorGeas.modifyBlockDrops(drops);
                }
            }
        }
    }

    public static boolean hasProspector(Entity entity) {
        if (entity instanceof LivingEntity breaker) {
            return GeasEffectHandler.hasGeasEffect(breaker, MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR);
        }
        return false;
    }

     public static void modifyBlockDrops(List<ItemStack> drops) {
         for (ItemStack drop : drops) {
             if (drop.is(MalumTags.ItemTags.PROSPECTORS_TREASURE) && !drop.is(Tags.Items.ORES)) {
                 drop.set(MalumDataComponents.PROSPECTOR_MARK, new ProspectorMarkData(true));
             }
         }
     }
}
