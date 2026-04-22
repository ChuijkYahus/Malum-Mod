 package com.sammy.malum.common.item.curiosities.curios.sets.elemental;

 import com.sammy.malum.common.item.*;
 import com.sammy.malum.common.item.curiosities.curios.*;
 import com.sammy.malum.core.helpers.*;
 import net.minecraft.network.chat.*;

 import java.util.function.*;

 public class CurioInoculationBelt extends MalumCurioItem implements IMalumEventResponder {

     public CurioInoculationBelt(Properties builder) {
         super(builder, MalumTrinketFamily.ELEMENTAL);
     }

     @Override
     public void addExtraTooltipLines(Consumer<Component> consumer) {
         consumer.accept(ComponentHelper.positiveCurioEffect("inoculation_effect_duration"));
         consumer.accept(ComponentHelper.positiveCurioEffect("inoculation_effect_buff"));
     }
 }
