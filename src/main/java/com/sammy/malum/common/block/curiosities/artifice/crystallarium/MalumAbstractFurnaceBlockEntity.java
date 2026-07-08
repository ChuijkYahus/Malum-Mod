package com.sammy.malum.common.block.curiosities.artifice.crystallarium;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import team.lodestar.lodestone.modules.toolkit.blockentity.IInventoryCapabilityProvider;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackBlockHandler;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.lang.reflect.Array;
import java.util.Optional;

public abstract class MalumAbstractFurnaceBlockEntity<I extends RecipeInput, R extends Recipe<I>> extends LodestoneBlockEntity implements MenuProvider, IInventoryCapabilityProvider {
    public int litTime;
    public int litDuration;
    public int cookingProgress;
    public int cookingTotalTime;
    private final LodestoneItemStackBlockHandler inventory; //TODO custom furnace stack handler that has slot array for input/output or custom input/output hanlder below
    private final IItemHandler inputHandler;
    private final IItemHandler outputHandler;
    protected final ContainerData dataAccess;
    private final RecipeManager.CachedCheck<I, R> quickCheck;

    public MalumAbstractFurnaceBlockEntity(LodestoneBlockEntityType<?> type, BlockPos pos, BlockState state, LodestoneItemStackBlockHandler inventory, IItemHandler inputHandler, IItemHandler outputHandler, RecipeManager.CachedCheck<I, R> cachedRecipeCheck) {
        super(type, pos, state);
        this.inventory = inventory;
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.dataAccess = createDataAccess();
        this.quickCheck = cachedRecipeCheck;
    }

    @Override
    public void serverTick(ServerLevel level) {
        if (this.isLit()) {
            this.litTime--;
        }

        ItemStack fuel = this.inventory.getStackInSlot(1);
        ItemStack input = this.inventory.getStackInSlot(0);
        if (hasFuel() && hasInput()) {
            var optionalRecipeHolder = getValidRecipe(level);
            if (optionalRecipeHolder.isPresent() && canProcess(optionalRecipeHolder.get().value(), level)) {

            }
        }



    }

    protected boolean canProcess(R recipe, Level level) {
        /*todo finish
        if (hasInput() && isLit()) {
            ItemStack result = recipe.assemble(getRecipeInput(), level.registryAccess());
            if (result.isEmpty()) return false;
        }
         */
        return false;
    }

    public Optional<RecipeHolder<R>> getValidRecipe(Level level) {
        return hasInput() ? quickCheck.getRecipeFor(getRecipeInput(), level) : Optional.empty();
    }

    @Override
    public IItemHandler getInventory(Direction direction) {
        return inventory;
    }

    public boolean isLit() {
        return this.litTime > 0;
    }

    public boolean hasFuel() {
        return !this.inventory.getStackInSlot(1).isEmpty();
    }

    public boolean hasInput() {
        return !this.inventory.getStackInSlot(0).isEmpty();
    }

    public LodestoneItemStackBlockHandler getInventory() {
        return this.inventory;
    }

    protected abstract I getRecipeInput();

    protected abstract int getBurnDuration(ItemStack fuel);

    protected abstract int getTotalCookTime(Level level, MalumAbstractFurnaceBlockEntity<I, R> blockEntity);

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        inventory.save(registries, tag);
        tag.putInt("BurnTime", this.litTime);
        tag.putInt("CookTime", this.cookingProgress);
        tag.putInt("CookTimeTotal", this.cookingTotalTime);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider pRegistries) {
        inventory.load(pRegistries, compound);
        super.loadAdditional(compound, pRegistries);
        this.litTime = compound.getInt("BurnTime");
        this.cookingProgress = compound.getInt("CookTime");
        this.cookingTotalTime = compound.getInt("CookTimeTotal");
        this.litDuration = this.getBurnDuration(this.inventory.getStackInSlot(1));
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        var tag = new CompoundTag();
        inventory.save(registries, tag);
        return tag;
    }

    private ContainerData createDataAccess() {
        return new ContainerData()
        {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MalumAbstractFurnaceBlockEntity.this.litTime;
                    case 1 -> MalumAbstractFurnaceBlockEntity.this.litDuration;
                    case 2 -> MalumAbstractFurnaceBlockEntity.this.cookingProgress;
                    case 3 -> MalumAbstractFurnaceBlockEntity.this.cookingTotalTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> MalumAbstractFurnaceBlockEntity.this.litTime = value;
                    case 1 -> MalumAbstractFurnaceBlockEntity.this.litDuration = value;
                    case 2 -> MalumAbstractFurnaceBlockEntity.this.cookingProgress = value;
                    case 3 -> MalumAbstractFurnaceBlockEntity.this.cookingTotalTime = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }
}
