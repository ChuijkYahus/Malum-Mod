package com.sammy.malum.common.item.curiosities.tools;

import com.sammy.malum.common.block.curiosities.decor.mana_mote.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.shapes.*;

public class LamplightersTongsItem extends Item {
    public LamplightersTongsItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        var context = new BlockPlaceContext(pContext);
        if (!context.canPlace()) {
            return super.useOn(context);
        }
        var player = context.getPlayer();
        if (player == null) {
            return super.useOn(context);
        }
        var level = context.getLevel();
        var hand = context.getHand();
        var spiritStack = player.getItemInHand(hand.equals(InteractionHand.MAIN_HAND) ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
        if (!(spiritStack.getItem() instanceof SpiritShardItem spiritShard)) {
            return super.useOn(context);
        }
        if (spiritShard.matches(MalumSpiritTypes.UMBRAL_SPIRIT)) {
            return super.useOn(context);
        }
        var state = MalumContent.Sorcery.SPIRIT_MOTE.get().getStateForPlacement(context);
        if (state == null || !canPlace(context, state)) {
            return super.useOn(context);
        }
        var spiritMote = ManaMoteBlock.createManaMoteState(state, spiritShard);
        var pPos = context.getClickedPos();
        SoundType soundtype = spiritMote.getSoundType(level, pPos, player);
        level.setBlock(pPos, spiritMote, 3);
        level.levelEvent(2001, pPos, Block.getId(spiritMote));
        level.playSound(player, pPos, MalumSoundEvents.SPIRIT_MOTE_CREATED.get(), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, Mth.nextFloat(level.random, 1.1f, 1.4f));
        if (!player.getAbilities().instabuild) {
            spiritStack.shrink(1);
        }
        if (level instanceof ServerLevel serverLevel) {
            MalumParticleEffectTypes.SPIRIT_MOTE_SPARKLES.createEffect(pPos)
                    .color(spiritShard)
                    .spawn(serverLevel);
        }
        return InteractionResult.SUCCESS;
    }

    protected boolean canPlace(BlockPlaceContext pContext, BlockState pState) {
        Player player = pContext.getPlayer();
        CollisionContext collisioncontext = player == null ? CollisionContext.empty() : CollisionContext.of(player);
        return pContext.getLevel().isUnobstructed(pState, pContext.getClickedPos(), collisioncontext);
    }
}