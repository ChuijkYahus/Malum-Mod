package com.sammy.malum.registry.common.recipe;

import com.sammy.malum.*;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.common.recipe.spirit_repair.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.systems.recipe.*;

public class MalumRecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, MalumMod.MALUM);

    public static final DeferredHolder<RecipeType<?>, LodestoneRecipeType<SpiritInfusionRecipe>> SPIRIT_INFUSION = registerRecipeType(SpiritInfusionRecipe.NAME);
    public static final DeferredHolder<RecipeType<?>, LodestoneRecipeType<RuneworkingRecipe>> RUNEWORKING = registerRecipeType(RuneworkingRecipe.NAME);
    public static final DeferredHolder<RecipeType<?>, LodestoneRecipeType<SoulBindingRecipe>> SOUL_BINDING = registerRecipeType(SoulBindingRecipe.NAME);
    public static final DeferredHolder<RecipeType<?>, LodestoneRecipeType<SpiritFocusingRecipe>> SPIRIT_FOCUSING = registerRecipeType(SpiritFocusingRecipe.NAME);
    public static final DeferredHolder<RecipeType<?>, LodestoneRecipeType<UnchainedTransmutationRecipe>> SPIRIT_TRANSMUTATION = registerRecipeType(UnchainedTransmutationRecipe.NAME);
    public static final DeferredHolder<RecipeType<?>, LodestoneRecipeType<SpiritRepairRecipe>> SPIRIT_REPAIR = registerRecipeType(SpiritRepairRecipe.NAME);
    public static final DeferredHolder<RecipeType<?>, LodestoneRecipeType<VoidFavorRecipe>> VOID_FAVOR = registerRecipeType(VoidFavorRecipe.NAME);

    public static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, LodestoneRecipeType<T>> registerRecipeType(String identifier) {
        return RECIPE_TYPES.register(identifier, () -> new LodestoneRecipeType<>(MalumMod.malumPath(identifier)));
    }
}