package com.sammy.malum.common.item.banner;

import com.sammy.malum.client.creative_tab.MalumCreativeTabTweaks;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.*;

import java.util.*;

import static net.minecraft.world.item.CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

public class SoulwovenBannerBlockItem extends BlockItem {
    public SoulwovenBannerBlockItem(Properties properties) {
        super(MalumBlocks.SOULWOVEN_BANNER.get(), properties.component(MalumDataComponents.SOULWOVEN_BANNER_PATTERN, SoulwovenBannerPatternDataComponent.DEFAULT));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        var pattern = stack.get(MalumDataComponents.SOULWOVEN_BANNER_PATTERN);
        if (pattern != null && !pattern.equals(SoulwovenBannerPatternDataComponent.DEFAULT)) {
            tooltipComponents.add(Component.translatable(pattern.translationKey()).withStyle(ChatFormatting.GRAY));
        }
    }

    public static float getBannerPattern(ItemStack stack) {
        var pattern = stack.getOrDefault(MalumDataComponents.SOULWOVEN_BANNER_PATTERN, SoulwovenBannerPatternDataComponent.DEFAULT);
        return SoulwovenBannerPatternDataComponent.REGISTERED_PATTERNS.contains(pattern) ? SoulwovenBannerPatternDataComponent.REGISTERED_PATTERNS.indexOf(pattern) : 0;
    }

    public static void addBannerVariantsToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        var source = MalumItems.SOULWOVEN_BANNER.get().getDefaultInstance();
        if (event.getParentEntries().contains(source)) {
            var patterns = new ArrayList<>(SoulwovenBannerPatternDataComponent.REGISTERED_PATTERNS);
            patterns.remove(SoulwovenBannerPatternDataComponent.DEFAULT);
            patterns.remove(SoulwovenBannerPatternDataComponent.COLORFUL_WORLD);
            Collections.reverse(patterns);

            tryAddBannerVariant(event, SoulwovenBannerPatternDataComponent.COLORFUL_WORLD, true);
            for (SoulwovenBannerPatternDataComponent pattern : patterns) {
                tryAddBannerVariant(event, pattern, false);
            }
        }
    }

    public static void addBannerPatterns(MalumCreativeTabTweaks.CategoryBuilder builder) {
        var patterns = new ArrayList<>(SoulwovenBannerPatternDataComponent.REGISTERED_PATTERNS);
        Collections.reverse(patterns);
        for (SoulwovenBannerPatternDataComponent pattern : patterns) {
            builder.addItem(pattern.getDefaultStack());
        }
    }

    public static void tryAddBannerVariant(BuildCreativeModeTabContentsEvent event, SoulwovenBannerPatternDataComponent pattern, boolean before) {
        var source = MalumItems.SOULWOVEN_BANNER.get().getDefaultInstance();
        ItemStack stack = pattern.getDefaultStack();
        if (!event.getParentEntries().contains(stack)) {
            if (before) {
                event.insertBefore(source, stack, PARENT_AND_SEARCH_TABS);
            }
            else {
                event.insertAfter(source, stack, PARENT_AND_SEARCH_TABS);
            }
        }
    }
}