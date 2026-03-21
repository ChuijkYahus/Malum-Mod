package com.sammy.malum.compat.jei;

import com.google.common.collect.Maps;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.common.recipe.spirit_repair.*;
import com.sammy.malum.compat.farmersdelight.FarmersDelightCompat;
import com.sammy.malum.compat.jei.categories.*;
import com.sammy.malum.compat.jei.recipes.SpiritTransmutationWrapper;
import com.sammy.malum.config.*;
import com.sammy.malum.core.handlers.hiding.HiddenTagHandler;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.MalumItems;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import org.apache.commons.compress.utils.Lists;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneInWorldRecipe;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;


@JeiPlugin
public class JEIHandler implements IModPlugin {
    private static final ResourceLocation ID = MalumMod.malumPath("main");

    public static final RecipeType<SpiritInfusionRecipe> SPIRIT_INFUSION = new RecipeType<>(SpiritInfusionRecipeCategory.UID, SpiritInfusionRecipe.class);
    public static final RecipeType<SpiritTransmutationWrapper> TRANSMUTATION = new RecipeType<>(SpiritTransmutationRecipeCategory.UID, SpiritTransmutationWrapper.class);
    public static final RecipeType<SpiritFocusingRecipe> FOCUSING = new RecipeType<>(SpiritFocusingRecipeCategory.UID, SpiritFocusingRecipe.class);
    public static final RecipeType<SpiritRepairRecipe> SPIRIT_REPAIR = new RecipeType<>(SpiritRepairRecipeCategory.UID, SpiritRepairRecipe.class);
    public static final RecipeType<VoidFavorRecipe> WEEPING_WELL = new RecipeType<>(WeepingWellRecipeCategory.UID, VoidFavorRecipe.class);
    public static final RecipeType<RuneworkingRecipe> RUNEWORKING = new RecipeType<>(RuneworkingRecipeCategory.UID, RuneworkingRecipe.class);

    public JEIHandler() {
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();

        registry.addRecipeCategories(new SpiritInfusionRecipeCategory(guiHelper),
            new SpiritTransmutationRecipeCategory(guiHelper),
            new SpiritFocusingRecipeCategory(guiHelper),
            new SpiritRepairRecipeCategory(guiHelper),
            new RuneworkingRecipeCategory(guiHelper),
            new WeepingWellRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registry) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            registry.addRecipes(SPIRIT_INFUSION, LodestoneRecipeType.getRecipes(level, MalumRecipeTypes.SPIRIT_INFUSION.get()));

            List<UnchainedTransmutationRecipe> transmutation = LodestoneRecipeType.getRecipes(level, MalumRecipeTypes.UNCHAINED_TRANSMUTATION.get());
            List<UnchainedTransmutationRecipe> leftovers = Lists.newArrayList();
            Map<String, List<UnchainedTransmutationRecipe>> groups = Maps.newLinkedHashMap();
            for (UnchainedTransmutationRecipe recipe : transmutation) {
                if (!recipe.getGroup().isEmpty()) {
                    var group = groups.computeIfAbsent(recipe.getGroup(), k -> Lists.newArrayList());
                    group.add(recipe);
                } else
                    leftovers.add(recipe);
            }

            registry.addRecipes(TRANSMUTATION, groups.values().stream()
                    .map(list -> list.stream().filter(it -> !it.output.isEmpty() && !it.ingredient.isEmpty()).collect(Collectors.toList()))
                    .map(SpiritTransmutationWrapper::new)
                    .collect(Collectors.toList()));
            registry.addRecipes(TRANSMUTATION, leftovers.stream()
                    .filter(it -> !it.output.isEmpty() && !it.ingredient.isEmpty())
                    .map(r -> new SpiritTransmutationWrapper(List.of(r)))
                    .collect(Collectors.toList()));

            //TODO: this is a mess :(
            registry.addRecipes(FOCUSING, LodestoneRecipeType.getRecipes(level, MalumRecipeTypes.SPIRIT_FOCUSING.get()).stream()
                .filter(it -> !it.output.isEmpty()).collect(Collectors.toList()));
            registry.addRecipes(SPIRIT_REPAIR, LodestoneRecipeType.getRecipes(level, MalumRecipeTypes.SPIRIT_REPAIR.get()).stream()
                .filter(it -> !it.validItems.isEmpty()).collect(Collectors.toList()));
            registry.addRecipes(WEEPING_WELL, LodestoneRecipeType.getRecipes(level, MalumRecipeTypes.VOID_FAVOR.get()).stream()
                .filter(it -> !it.result.isEmpty()).collect(Collectors.toList()));
            registry.addRecipes(RUNEWORKING, LodestoneRecipeType.getRecipes(level, MalumRecipeTypes.RUNEWORKING.get()).stream()
                .filter(it -> !it.output.isEmpty()).collect(Collectors.toList()));
            if (FarmersDelightCompat.LOADED) {
                FarmersDelightCompat.AndJeiLoadedOnly.addInfo(registry);
            }
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(MalumItems.SPIRIT_ALTAR.get()), SPIRIT_INFUSION);
        registry.addRecipeCatalyst(new ItemStack(MalumItems.SPIRIT_CRUCIBLE.get()), FOCUSING);
        registry.addRecipeCatalyst(new ItemStack(MalumItems.REPAIR_PYLON.get()), SPIRIT_REPAIR);
        registry.addRecipeCatalyst(new ItemStack(MalumItems.SOULWOOD_TOTEM_BASE.get()), TRANSMUTATION);
        registry.addRecipeCatalyst(new ItemStack(MalumItems.RUNIC_WORKBENCH.get()), RUNEWORKING);
        registry.addRecipeCatalyst(new ItemStack(MalumItems.VOID_DEPOT.get()), WEEPING_WELL);
    }


    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        HiddenRecipeHandler.onRuntimeAvailable(jeiRuntime);
    }

    @Override
    public void onRuntimeUnavailable() {
        HiddenRecipeHandler.onRuntimeUnavailable();
    }
}
