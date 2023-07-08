package com.sammy.malum.common.spiritrite;

import com.sammy.malum.common.block.blight.BlightedSoilBlock;
import com.sammy.malum.common.block.curiosities.spirit_altar.IAltarProvider;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.common.packets.particle.curiosities.rite.generic.BlockSparkleParticlePacket;
import com.sammy.malum.common.packets.particle.curiosities.blight.BlightMistParticlePacket;
import com.sammy.malum.common.packets.particle.curiosities.blight.BlightTransformItemParticlePacket;
import com.sammy.malum.common.recipe.SpiritTransmutationRecipe;
import com.sammy.malum.common.worldevent.TotemCreatedBlightEvent;
import com.sammy.malum.core.systems.rites.MalumRiteEffect;
import com.sammy.malum.core.systems.rites.MalumRiteType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import team.lodestar.lodestone.handlers.WorldEventHandler;
import team.lodestar.lodestone.helpers.block.BlockStateHelper;
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntityInventory;

import java.util.List;
import java.util.Set;

import static com.sammy.malum.registry.common.SpiritTypeRegistry.ARCANE_SPIRIT;
import static com.sammy.malum.registry.common.PacketRegistry.MALUM_CHANNEL;

public class ArcaneRiteType extends MalumRiteType {
    public ArcaneRiteType() {
        super("arcane_rite", "Undirected Rite", "Unchained Rite", ARCANE_SPIRIT, ARCANE_SPIRIT, ARCANE_SPIRIT, ARCANE_SPIRIT, ARCANE_SPIRIT);
    }

    @Override
    public MalumRiteEffect getNaturalRiteEffect() {
        return new MalumRiteEffect() {
            @Override
            public boolean isOneAndDone() {
                return true;
            }

            @SuppressWarnings("ConstantConditions")
            @Override
            public void riteEffect(TotemBaseBlockEntity totemBase) {
                WorldEventHandler.addWorldEvent(totemBase.getLevel(), new TotemCreatedBlightEvent().setPosition(totemBase.getBlockPos()).setBlightData(1, 4, 4));
            }
        };
    }

    @Override
    public MalumRiteEffect getCorruptedEffect() {
        return new MalumRiteEffect() {

            @Override
            public int getRiteEffectRadius() {
                return (BASE_RADIUS * 2)+1;
            }

            @Override
            public int getRiteEffectTickRate() {
                return BASE_TICK_RATE * 5;
            }

            @Override
            public boolean canAffectBlock(TotemBaseBlockEntity totemBase, Set<Block> filters, BlockState state, BlockPos pos) {
                //The rite looks for blighted soil, then tries to transmute anything above it.
                //We wanna check for filters on the block above the blight, not the actual blight.
                BlockPos actualPos = pos.above();
                return super.canAffectBlock(totemBase, filters, totemBase.getLevel().getBlockState(actualPos), actualPos);
            }


            @SuppressWarnings("ConstantConditions")
            @Override
            public void riteEffect(TotemBaseBlockEntity totemBase) {
                Level level = totemBase.getLevel();
                BlockPos pos = totemBase.getBlockPos();
                List<BlockPos> nearbyBlocks = getNearbyBlocks(totemBase, BlightedSoilBlock.class).toList();
                for (BlockPos p : nearbyBlocks) {
                    BlockPos posToTransmute = p.above();
                    BlockState stateToTransmute = level.getBlockState(posToTransmute);
                    if (level.getBlockEntity(posToTransmute) instanceof IAltarProvider iAltarProvider) {
                        LodestoneBlockEntityInventory inventoryForAltar = iAltarProvider.getInventoryForAltar();
                        ItemStack stack = inventoryForAltar.getStackInSlot(0);
                        var recipe = SpiritTransmutationRecipe.getRecipe(level, stack);
                        if (recipe != null) {
                            Vec3 itemPos = iAltarProvider.getItemPosForAltar();
                            level.addFreshEntity(new ItemEntity(level, itemPos.x, itemPos.y, itemPos.z, recipe.output.copy()));
                            MALUM_CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(p)), new BlightTransformItemParticlePacket(List.of(ARCANE_SPIRIT.identifier), itemPos));
                            inventoryForAltar.getStackInSlot(0).shrink(1);
                            BlockStateHelper.updateAndNotifyState(level, p);
                        }
                    }
                    ItemStack stack = stateToTransmute.getBlock().asItem().getDefaultInstance();
                    var recipe = SpiritTransmutationRecipe.getRecipe(level, stack);
                    if (recipe != null) {
                        ItemStack output = recipe.output.copy();
                        if (output.getItem() instanceof BlockItem blockItem) {
                            Block block = blockItem.getBlock();
                            BlockEntity entity = level.getBlockEntity(posToTransmute);
                            BlockState newState = BlockStateHelper.setBlockStateWithExistingProperties(level, posToTransmute, block.defaultBlockState(), 3);
                            level.levelEvent(2001, posToTransmute, Block.getId(newState));
                            MALUM_CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(posToTransmute)), new BlockSparkleParticlePacket(ARCANE_SPIRIT.getPrimaryColor(), posToTransmute));
                            MALUM_CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(posToTransmute)), new BlightMistParticlePacket(posToTransmute)); //TODO: convert these 2 into a single packet, rlly don't feel like doing it rn
                            if (block instanceof EntityBlock entityBlock) {
                                if (entity != null) {
                                    BlockEntity newEntity = entityBlock.newBlockEntity(pos, newState);
                                    if (newEntity != null) {
                                        if (newEntity.getClass().equals(entity.getClass())) {
                                            level.setBlockEntity(entity);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };
    }
}
