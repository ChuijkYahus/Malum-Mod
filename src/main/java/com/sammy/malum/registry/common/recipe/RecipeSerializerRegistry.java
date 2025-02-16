package com.sammy.malum.registry.common.recipe;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.common.recipe.node_cooking.MetalNodeBlastingRecipe;
import com.sammy.malum.common.recipe.node_cooking.MetalNodeSmeltingRecipe;
import com.sammy.malum.common.recipe.node_cooking.NodeCookingSerializer;
import com.sammy.malum.common.recipe.void_favor.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.systems.recipe.*;

public class RecipeSerializerRegistry {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MalumMod.MALUM);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MetalNodeBlastingRecipe>> METAL_NODE_BLASTING_SERIALIZER = RECIPE_SERIALIZERS.register(MetalNodeBlastingRecipe.NAME, () -> new NodeCookingSerializer<>(MetalNodeBlastingRecipe::new, 100));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MetalNodeSmeltingRecipe>> METAL_NODE_SMELTING_SERIALIZER = RECIPE_SERIALIZERS.register(MetalNodeSmeltingRecipe.NAME, () -> new NodeCookingSerializer<>(MetalNodeSmeltingRecipe::new, 200));

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SpiritInfusionRecipe>> INFUSION_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(SpiritInfusionRecipe.NAME, () -> new LodestoneRecipeSerializer<>(SpiritInfusionRecipe.CODEC));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<RunicWorkbenchRecipe>> RUNEWORKING_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(RunicWorkbenchRecipe.NAME, () -> new LodestoneRecipeSerializer<>(RunicWorkbenchRecipe.CODEC));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SoulBindingRecipe>> SOUL_BINDING_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(SoulBindingRecipe.NAME, () -> new LodestoneRecipeSerializer<>(SoulBindingRecipe.CODEC));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SpiritFocusingRecipe>> FOCUSING_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(SpiritFocusingRecipe.NAME, () -> new LodestoneRecipeSerializer<>(SpiritFocusingRecipe.CODEC));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SpiritRepairRecipe>> REPAIR_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(SpiritRepairRecipe.NAME, () -> new LodestoneRecipeSerializer<>(SpiritRepairRecipe.CODEC));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SpiritTransmutationRecipe>> SPIRIT_TRANSMUTATION_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(SpiritTransmutationRecipe.NAME, () -> new LodestoneRecipeSerializer<>(SpiritTransmutationRecipe.CODEC));

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<FavorOfTheVoidRecipe>> VOID_FAVOR_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(FavorOfTheVoidRecipe.NAME, () -> new LodestoneRecipeSerializer<>(FavorOfTheVoidRecipe.CODEC));
}
