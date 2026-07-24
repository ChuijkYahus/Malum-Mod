package com.sammy.malum.common.block.curiosities.artifice.crystallarium;

import com.sammy.malum.common.recipe.derealization.ConjunctureCrystallariumRecipe;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import com.sammy.malum.visual_effects.block.ConjunctureCrystallariumParticleEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;

import static com.sammy.malum.common.block.curiosities.artifice.crystallarium.ConjunctureCrystallariumContainer.*;

public class ConjunctureCrystallariumBlockEntity extends MalumAbstractFurnaceBlockEntity<SingleRecipeInput, ConjunctureCrystallariumRecipe> {

    private final MalumFurnaceBlockItemStackHandler inv = MalumFurnaceBlockItemStackHandler.create(this, 5).setOutputSlots(2, 3, 4).build();

    public ConjunctureCrystallariumBlockEntity(LodestoneBlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, MalumRecipeTypes.CONJUNCTURE_CRYSTALLARIUM.get());
    }

    public ConjunctureCrystallariumBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.CONJUNCTURE_CRYSTALLARIUM.get(), pos, state);
    }

    @Override
    public ItemInteractionResult onUse(Player player, InteractionHand pHand) {
        if (player instanceof ServerPlayer serverPlayer) {
            var container = new SimpleMenuProvider((w, p, pl) -> new ConjunctureCrystallariumContainer(w, p, ContainerLevelAccess.create(pl.level(), getBlockPos()), this.dataAccess), CONJUNCTURE_CRYSTALLARIUM);
            serverPlayer.openMenu(container, buf -> buf.writeBlockPos(this.getBlockPos()));
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public void clientTick(Level level) {
        ConjunctureCrystallariumParticleEffects.passiveCrystallariumParticles(this);
    }

    @Override
    protected MalumFurnaceBlockItemStackHandler inventory() {
        return inv;
    }

    @Override
    protected SingleRecipeInput getRecipeInput() {
        var inputStack = this.inventory().getStackInSlot(1);
        return new SingleRecipeInput(inputStack);
    }

    @Override
    protected int getBurnDuration(ItemStack fuel) {
        return 800; //TODO datamap later
    }
}