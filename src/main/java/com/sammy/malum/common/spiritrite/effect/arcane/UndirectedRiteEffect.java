package com.sammy.malum.common.spiritrite.effect.arcane;

import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.common.worldevent.UnchainedTotemConversionWorldEvent;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteEffect;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteEffectTag;
import net.minecraft.server.level.ServerLevel;
import team.lodestar.lodestone.handlers.WorldEventHandler;

import java.util.List;

public class UndirectedRiteEffect extends SpiritRiteEffect {

    public UndirectedRiteEffect() {
        super(SpiritRiteEffectTag.STRANGE_EFFECT);
    }

    @Override
    public void triggerRiteEffect(ServerLevel level, TotemBaseBlockEntity totemBase) {
        WorldEventHandler.addWorldEvent(level,
                new UnchainedTotemConversionWorldEvent()
                        .setPosition(totemBase.getBlockPos())
                        .setData(List.of(1, 3, 5, 6, 7, 8), 4, 0));
    }
}
