package com.sammy.malum.common.creativetab;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.creativetab.button.ItemChoiceSlotStorage;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabHeader;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabVisualInfo;
import team.lodestar.lodestone.modules.toolkit.creative_tab.slot.SlotStorage;

import java.util.Optional;

public class MalumVisualInfo extends CreativeTabVisualInfo {

    public static final MalumVisualInfo MALUM = new MalumVisualInfo();
    private static final ResourceLocation SLOT_WRAPPER = MalumMod.malumPath("slot_wrapper");
    private static final ResourceLocation SLOT_WRAPPER_LEFT = MalumMod.malumPath("slot_wrapper_left");
    private static final ResourceLocation SLOT_WRAPPER_RIGHT = MalumMod.malumPath("slot_wrapper_right");
    private static final ResourceLocation EMPTY_SLOT = MalumMod.malumPath("empty_slot");
    private static final ResourceLocation BUTTON_SLOT = MalumMod.malumPath("button_slot");

    @Override
    public Optional<ResourceLocation> getHeaderTexture(CreativeTabHeader header, int row, int column) {
        if (column == 0) {
            return Optional.of(SLOT_WRAPPER_LEFT);
        } else if (column == 8) {
            return Optional.of(SLOT_WRAPPER_RIGHT);
        }
        return Optional.of(SLOT_WRAPPER);
    }

    @Override
    public Optional<ResourceLocation> getEmptySlotTexture(SlotStorage slot) {
        if (slot instanceof ItemChoiceSlotStorage itemChoiceSlotStorage) {
            return Optional.of(BUTTON_SLOT);
        }
        return Optional.of(EMPTY_SLOT);
    }
}
