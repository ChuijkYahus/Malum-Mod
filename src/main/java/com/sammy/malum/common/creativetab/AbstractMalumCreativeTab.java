package com.sammy.malum.common.creativetab;

import com.sammy.malum.MalumMod;
import com.sammy.malum.core.handlers.hiding.HiddenTagHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import team.lodestar.lodestone.modules.toolkit.creative_tab.*;

import java.util.Optional;

public abstract class AbstractMalumCreativeTab extends CategorizedCreativeTab {

        protected AbstractMalumCreativeTab(CategorizedBuilder categorizedBuilder) {
                super(MalumMod.MALUM, categorizedBuilder);
        }

        @Override
        public boolean isItemVisible(ItemStack stack) {
                return !HiddenTagHandler.isHiddenItem(stack);
        }
}