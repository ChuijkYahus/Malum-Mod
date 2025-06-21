package com.sammy.malum.common.item.augment.core;

import com.sammy.malum.core.systems.artifice.ArtificeModifier;
import com.sammy.malum.common.item.augment.*;
import com.sammy.malum.core.systems.spirit.type.*;

import java.util.List;

public class CoreAugmentItem extends AugmentItem {

    public CoreAugmentItem(Properties pProperties, List<SpiritLike> spiritTypes, boolean isCoreAugment, ArtificeModifier... modifiers) {
        super(pProperties, spiritTypes, isCoreAugment, modifiers);
    }

    @Override
    public String getAugmentTypeTranslator() {
        return "core_augment";
    }
}
