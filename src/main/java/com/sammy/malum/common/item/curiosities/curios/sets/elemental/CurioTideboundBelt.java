 package com.sammy.malum.common.item.curiosities.curios.sets.elemental;

 import com.sammy.malum.common.item.*;
 import com.sammy.malum.common.item.curiosities.curios.*;
 import com.sammy.malum.core.helpers.*;
 import net.minecraft.network.chat.*;

 import java.util.function.*;

 public class CurioTideboundBelt extends MalumCurioItem implements IMalumEventResponder {

     public CurioTideboundBelt(Properties builder) {
         super(builder, MalumTrinketFamily.ELEMENTAL);
     }

     @Override
     public void addExtraTooltipLines(Consumer<Component> consumer) {
         consumer.accept(ComponentHelper.positiveCurioEffect("tidebound_resilience"));
         consumer.accept(ComponentHelper.positiveCurioEffect("tidebound_agility"));
         consumer.accept(ComponentHelper.positiveCurioEffect("tidebound_conduit"));
     }
 }
