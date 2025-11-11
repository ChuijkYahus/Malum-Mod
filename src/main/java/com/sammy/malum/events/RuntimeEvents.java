package com.sammy.malum.events;

import com.sammy.malum.common.block.storage.jar.*;
import com.sammy.malum.common.effect.*;
import com.sammy.malum.common.effect.rite.aura.*;
import com.sammy.malum.common.effect.rite.aura.soulwood.*;
import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.common.entity.nitrate.*;
import com.sammy.malum.common.geas.pact.aerial.*;
import com.sammy.malum.common.geas.pact.infernal.*;
import com.sammy.malum.common.geas.pact.earthen.ProfaneAsceticGeas;
import com.sammy.malum.common.geas.pact.sacred.*;
import com.sammy.malum.common.geas.pact.wicked.*;
import com.sammy.malum.common.item.cosmetic.curios.*;
import com.sammy.malum.common.item.curiosities.curios.runes.madness.*;
import com.sammy.malum.common.item.curiosities.curios.runes.miracle.*;
import com.sammy.malum.common.item.curiosities.curios.sets.misc.*;
import com.sammy.malum.common.item.curiosities.curios.sets.prospector.*;
import com.sammy.malum.common.item.curiosities.curios.sets.rotten.*;
import com.sammy.malum.common.item.curiosities.curios.sets.weeping.*;
import com.sammy.malum.common.item.curiosities.pouch.*;
import com.sammy.malum.common.item.curiosities.tools.spellweaver.*;
import com.sammy.malum.compat.tetra.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.listeners.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.event.*;
import net.neoforged.neoforge.event.entity.*;
import net.neoforged.neoforge.event.entity.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.event.level.*;
import net.neoforged.neoforge.event.tick.*;

@EventBusSubscriber()
public class RuntimeEvents {

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        CurioTokenOfGratitude.giveItem(event);
        SoulDataHandler.syncData(event);
        GeasEffectHandler.syncGeas(event);
        TetraCompat.entityJoin(event);
    }

    @SubscribeEvent
    public static void playerLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        //TODO: move this to the jar class
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        if (block instanceof SpiritJarBlock jarBlock) {
            Player player = event.getEntity();
            BlockHitResult target = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
            if (target.getType() == HitResult.Type.BLOCK && target.getBlockPos().equals(pos) && target.getDirection().getAxis() == Direction.Axis.X) {
                if (player.isCreative()) {
                    event.setCanceled(jarBlock.handleAttack(level, pos, player));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(MobSpawnEvent.PositionCheck event) {
        SoulDataHandler.markAsSpawnerSpawned(event);
    }


    @SubscribeEvent
    public static void onEntityFall(LivingFallEvent event) {
        AscensionEffect.onEntityFall(event);
        SkyTetherEffect.onEntityFall(event);
    }

    @SubscribeEvent
    public static void onLivingTarget(LivingChangeTargetEvent event) {
        SoulDataHandler.preventTargeting(event);
    }

    @SubscribeEvent
    public static void onLivingVisibility(LivingEvent.LivingVisibilityEvent event) {
        CurioHarmonyNecklace.preventDetection(event);
        WarlockGeas.increaseDetection(event);
    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Pre event) {
        GeasEffectHandler.entityTick(event);
        SoulDataHandler.entityTick(event);
        SoulWardHandler.entityTick(event);
        MalignantConversionHandler.entityTick(event);
        WeepingWellRejectionHandler.entityTick(event);
        TouchOfDarknessHandler.entityTick(event);
        CurioWatcherNecklace.entityTick(event);
        CurioHiddenBladeNecklace.entityTick(event);
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        StaffAbilityHandler.recoverStaffCharges(event);
    }

    @SubscribeEvent
    public static void modifyBlockDrops(BlockDropsEvent event) {
        ProspectorGeas.modifyBlockDrops(event);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void modifyBlockDropsLate(BlockDropsEvent event) {
        SpellweaverToolEffectActivatorEntity.redirectDrops(event);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void breakBlock(BlockEvent.BreakEvent event) {
        SpellweavingPickaxeItem.triggerSpellweavingEffect(event);
    }

    @SubscribeEvent
    public static void registerListeners(AddReloadListenerEvent event) {
        SpellweavingEqualityReloadListener.register(event);
        SpiritDataReloadListener.register(event);
        ReapingDataReloadListener.register(event);
        MalignantConversionReloadListener.register(event);
    }

    @SubscribeEvent
    public static void isPotionApplicable(MobEffectEvent.Applicable event) {
        GluttonyEffect.canApplyPotion(event);
    }

    @SubscribeEvent
    public static void onPotionApplied(MobEffectEvent.Added event) {
        GluttonyEffect.removeExistingHunger(event);
        RuneTwinnedDurationItem.scaleDuration(event);
        RuneAilmentCleansingItem.scaleDuration(event);
    }

    @SubscribeEvent
    public static void onPotionExpired(MobEffectEvent.Expired event) {
    }

    @SubscribeEvent
    public static void onStartUsingItem(LivingEntityUseItemEvent.Start event) {
        ProfaneAsceticGeas.modifyEating(event);
        CurioVoraciousRing.modifyEating(event);
    }

    @SubscribeEvent
    public static void onPickupItem(ItemEntityPickupEvent.Pre event) {
        SoulwovenPouchItem.trySwallowItem(event);
        RavenousPouchItem.trySwallowItem(event);
    }

    @SubscribeEvent
    public static void onPickupItem(ItemEntityPickupEvent.Post event) {
        ProspectorGeas.pickupItem(event);
    }

    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Post event) {
        SoulDataHandler.exposeSoul(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        MalignantConversionHandler.shieldPlayer(event);
    }

    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Pre event) {
        SoulWardHandler.shieldPlayer(event);
        OakenMightEffect.increaseDamage(event);
        StoneWardEffect.reduceDamage(event);
        MalumAttributeEventHandler.processAttributes(event);
    }

    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event) {
        WindsweptGeas.scaleKnockback(event);
        SkyBreakerGeas.scaleKnockback(event);
    }

    @SubscribeEvent
    public static void onHeal(LivingHealEvent event) {
        MalumAttributeEventHandler.heal(event);
        LifeweaverGeas.onHeal(event);
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        SoulHarvestHandler.onDeath(event);
    }

    @SubscribeEvent
    public static void onDrops(LivingDropsEvent event) {
        EnsouledItemHarvestHandler.onDrops(event);
    }

    @SubscribeEvent
    public static void onItemExpire(ItemExpireEvent event) {
        EnsouledItemHarvestHandler.onItemExpire(event);
    }

    @SubscribeEvent
    public static void onItemUseStart(LivingEntityUseItemEvent.Start event) {
        ContinuingShotGeas.onItemUseStart(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        CurioProspectorBelt.processExplosion(event);
        NitrateExplosion.processExplosion(event);
        PyromaniacGeas.processExplosion(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onExplosionKnockback(ExplosionKnockbackEvent event) {
        CloudSkipperGeas.onExplosionKnockback(event);
    }
}