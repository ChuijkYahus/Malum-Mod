package com.sammy.malum.common.item.codex;

import com.sammy.malum.client.screen.codex.screens.progression.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;

public class EncyclopediaArcanaItem extends Item {

    public EncyclopediaArcanaItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.swing(hand);
        if (level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            ArcanaProgressionScreen.SCREEN.openCodexViaItem(false);
            return InteractionResultHolder.success(stack);
        }
        return super.use(level, player, hand);
    }
}