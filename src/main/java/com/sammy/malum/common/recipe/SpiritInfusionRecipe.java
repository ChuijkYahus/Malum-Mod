package com.sammy.malum.common.recipe;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import team.lodestar.lodestone.systems.recipe.*;

import java.util.*;

public class SpiritInfusionRecipe extends LodestoneInWorldRecipe<SpiritBasedRecipeInput> {

    public static final MapCodec<SpiritInfusionRecipe> CODEC = RecordCodecBuilder.mapCodec((obj) -> obj.group(
            SizedIngredient.FLAT_CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
            ItemStack.CODEC.fieldOf("output").forGetter(recipe -> recipe.output),
            SizedIngredient.FLAT_CODEC.listOf().optionalFieldOf("extraIngredients", List.of()).forGetter(recipe -> recipe.extraIngredients),
            SpiritIngredient.CODEC.codec().listOf().fieldOf("spirits").forGetter(recipe -> recipe.spirits),
            Codec.BOOL.optionalFieldOf("carryOverComponentData", false).forGetter(recipe -> recipe.carryOverComponentData)
    ).apply(obj, SpiritInfusionRecipe::new));

    public static final String NAME = "spirit_infusion";

    public final SizedIngredient ingredient;
    public final ItemStack output;

    public final List<SizedIngredient> extraIngredients;
    public final List<SpiritIngredient> spirits;
    public final boolean carryOverComponentData;

    public SpiritInfusionRecipe(SizedIngredient ingredient, ItemStack output, List<SizedIngredient> extraIngredients, List<SpiritIngredient> spirits, boolean carryOverComponentData) {
        super(MalumRecipeSerializers.INFUSION_RECIPE_SERIALIZER.get(), MalumRecipeTypes.SPIRIT_INFUSION.get());
        this.ingredient = ingredient;
        this.output = output;
        this.extraIngredients = extraIngredients;
        this.spirits = spirits;
        this.carryOverComponentData = carryOverComponentData;
    }

    @Override
    public boolean matches(SpiritBasedRecipeInput input, Level level) {
        return input.test(ingredient, spirits);
    }

    public ItemStack getOutput(ItemStack input) {
        ItemStack outputStack = output.copy();
        if (carryOverComponentData) {
            List<DataComponentType<?>> toCopy = new ArrayList<>();
            for (TypedDataComponent<?> component : input.getComponents()) {
                var key = BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(component.type());
                if (key == null) {
                    throw new IllegalArgumentException("Data component type " + component.type() + " is not registered, somehow.");
                }
                Holder<DataComponentType<?>> holder = BuiltInRegistries.DATA_COMPONENT_TYPE.getHolder(key).orElseThrow();
                if (holder.is(MalumTags.DataComponentTags.SPIRIT_INFUSION_BLACKLIST)) {
                    continue;
                }
                toCopy.add(component.type());
            }
            for (DataComponentType<?> dataComponentType : toCopy) {
                outputStack.copyFrom(input, dataComponentType);
            }
        }
        return outputStack;
    }
}
