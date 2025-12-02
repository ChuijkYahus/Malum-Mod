package com.sammy.malum.common.item.curiosities.curios.sets.prospector;

import com.sammy.malum.common.item.curiosities.curios.MalumCurioItem;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import team.lodestar.lodestone.helpers.CurioHelper;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Consumer;

public class CurioProspectorBelt extends MalumCurioItem {

    public CurioProspectorBelt(Properties builder) {
        super(builder, MalumTrinketType.METALLIC);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer, TooltipContext context) {
        var fortune = Enchantment.getFullname(context.registries().holderOrThrow(Enchantments.FORTUNE), 3);
        consumer.accept(ComponentHelper.positiveCurioEffect("enchanted_explosions", fortune.copy().withStyle(ChatFormatting.BLUE)));
        consumer.accept(ComponentHelper.positiveCurioEffect("ore_prospecting"));
    }

    public static LootParams.Builder applyFortune(Entity source, LootParams.Builder builder) {
        if (source instanceof LivingEntity living) {
            var level = living.level();
            var registriesProvider = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            if (hasProspectorBelt(living)) {
                int fortuneBonus = 3 + CuriosApi.getCuriosInventory(living).map(h -> h.getFortuneLevel(null)).orElse(0);
                var diamondPickaxe = new ItemStack(Items.DIAMOND_PICKAXE);
                diamondPickaxe.enchant(registriesProvider.getOrThrow(Enchantments.FORTUNE), fortuneBonus);
                return builder.withParameter(LootContextParams.TOOL, diamondPickaxe);
            }
        }
        return builder;
    }

    public static boolean hasProspectorBelt(LivingEntity entity) {
        return CurioHelper.hasCurioEquipped(entity, MalumItems.BELT_OF_THE_PROSPECTOR.get());
    }
}
