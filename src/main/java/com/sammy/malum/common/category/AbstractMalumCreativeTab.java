package com.sammy.malum.common.category;

import com.sammy.malum.MalumMod;
import com.sammy.malum.core.handlers.hiding.HiddenTagHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedCreativeTab;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategory;

import java.util.Optional;

public abstract class AbstractMalumCreativeTab extends CategorizedCreativeTab {

        private static final ResourceLocation SLOT_WRAPPER = MalumMod.malumPath("slot_wrapper");
        private static final ResourceLocation SLOT_WRAPPER_LEFT = MalumMod.malumPath("slot_wrapper_left");
        private static final ResourceLocation SLOT_WRAPPER_RIGHT = MalumMod.malumPath("slot_wrapper_right");
        private static final ResourceLocation EMPTY_SLOT = MalumMod.malumPath("empty_slot");

        public AbstractMalumCreativeTab(Builder builder) {
                super(MalumMod.MALUM, builder);
        }

        @Override
        public Optional<ResourceLocation> getHeaderTexture(CreativeTabCategory.CategoryHeader header, int row, int column) {
                if (column == 0) {
                        return Optional.of(SLOT_WRAPPER_LEFT);
                } else if (column == 8) {
                        return Optional.of(SLOT_WRAPPER_RIGHT);
                }
                return Optional.of(SLOT_WRAPPER);
        }

        @Override
        public Optional<ResourceLocation> getEmptySlotTexture(int row, int column) {
                return Optional.of(EMPTY_SLOT);
        }

        @Override
        public boolean isItemVisible(ItemStack stack) {
                return !HiddenTagHandler.isHiddenItem(stack);
        }
}