package com.sammy.malum.common.item.curiosities.curios.sets.weeping;

import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

import static com.sammy.malum.registry.common.MalumTags.ItemTags.*;

public class CurioGruesomeConcentrationRing extends MalumCurioItem implements IVoidItem {
    public CurioGruesomeConcentrationRing(Properties builder) {
        super(builder, MalumTrinketType.VOID);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("rotten_gluttony"));
    }

}