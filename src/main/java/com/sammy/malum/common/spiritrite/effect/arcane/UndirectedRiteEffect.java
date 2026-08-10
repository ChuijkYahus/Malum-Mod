package com.sammy.malum.common.spiritrite.effect.arcane;

import com.sammy.malum.common.worldevent.UnchainedTotemConversionWorldEvent;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteEffect;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteEffectTag;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import net.minecraft.core.*;
import net.minecraft.server.level.ServerLevel;
import team.lodestar.lodestone.modules.toolkit.worldevent.WorldEventHandler;

import java.util.List;

public class UndirectedRiteEffect extends SpiritRiteEffect {

    public UndirectedRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE, SpiritRiteEffectTag.STRANGE_EFFECT);
    }

    @Override
    public boolean triggerRiteEffect(ServerLevel level, BlockPos pos, SpiritArcanaType definingSpirit, RiteParameters parameters) {
        WorldEventHandler.addWorldEvent(level,
                new UnchainedTotemConversionWorldEvent()
                        .setPosition(pos)
                        .setData(List.of(1, 3, 5, 6, 7, 8), 4, 0));
        return true;
    }
}
