package com.sammy.malum.compat.jei;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.recipe.*;
import com.sammy.malum.common.recipe.spirit_repair.*;
import com.sammy.malum.compat.farmersdelight.FarmersDelightCompat;
import com.sammy.malum.compat.jei.categories.*;
import com.sammy.malum.compat.jei.recipes.SpiritTransmutationWrapper;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneInWorldRecipe;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeSearch;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneRecipeType;

import javax.annotation.Nonnull;


@JeiPlugin
public class JEIHandler implements IModPlugin {
    private static final ResourceLocation ID = MalumMod.malumPath("main");

    public static final RecipeType<SpiritInfusionRecipe> SPIRIT_INFUSION = new RecipeType<>(SpiritInfusionRecipeCategory.UID, SpiritInfusionRecipe.class);
    public static final RecipeType<SpiritTransmutationWrapper> TRANSMUTATION = new RecipeType<>(SpiritTransmutationRecipeCategory.UID, SpiritTransmutationWrapper.class);
    public static final RecipeType<SpiritFocusingRecipe> FOCUSING = new RecipeType<>(SpiritFocusingRecipeCategory.UID, SpiritFocusingRecipe.class);
    public static final RecipeType<SpiritRepairRecipe> SPIRIT_REPAIR = new RecipeType<>(SpiritRepairRecipeCategory.UID, SpiritRepairRecipe.class);
    public static final RecipeType<VoidFavorRecipe> VOID_FAVOR = new RecipeType<>(VoidFavorRecipeCategory.UID, VoidFavorRecipe.class);
    public static final RecipeType<RuneworkingRecipe> RUNEWORKING = new RecipeType<>(RuneworkingRecipeCategory.UID, RuneworkingRecipe.class);

    public JEIHandler() {
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
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
            new VoidFavorRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registry) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            addRecipes(registry, SPIRIT_INFUSION, MalumRecipeTypes.SPIRIT_INFUSION.get());
            addRecipes(registry, FOCUSING, MalumRecipeTypes.SPIRIT_FOCUSING.get());
            addRecipes(registry, SPIRIT_REPAIR, MalumRecipeTypes.SPIRIT_REPAIR.get());
            addRecipes(registry, VOID_FAVOR, MalumRecipeTypes.VOID_FAVOR.get());
            addRecipes(registry, RUNEWORKING, MalumRecipeTypes.RUNEWORKING.get());

//            List<UnchainedTransmutationRecipe> transmutation = LodestoneRecipeType.getRecipes(level, MalumRecipeTypes.UNCHAINED_TRANSMUTATION.get());
//            List<UnchainedTransmutationRecipe> leftovers = Lists.newArrayList();
//            Map<String, List<UnchainedTransmutationRecipe>> groups = Maps.newLinkedHashMap();
//            for (UnchainedTransmutationRecipe recipe : transmutation) {
//                if (!recipe.getGroup().isEmpty()) {
//                    var group = groups.computeIfAbsent(recipe.getGroup(), k -> Lists.newArrayList());
//                    group.add(recipe);
//                } else
//                    leftovers.add(recipe);
//            }
//
//            registry.addRecipes(TRANSMUTATION, groups.values().stream()
//                    .map(list -> list.stream().filter(it -> !it.output.isEmpty() && !it.ingredient.isEmpty()).collect(Collectors.toList()))
//                    .map(SpiritTransmutationWrapper::new)
//                    .collect(Collectors.toList()));
//            registry.addRecipes(TRANSMUTATION, leftovers.stream()
//                    .filter(it -> !it.output.isEmpty() && !it.ingredient.isEmpty())
//                    .map(r -> new SpiritTransmutationWrapper(List.of(r)))
//                    .collect(Collectors.toList()));

            if (FarmersDelightCompat.LOADED) {
                FarmersDelightCompat.AndJeiLoadedOnly.addInfo(registry);
            }
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(MalumContent.Sorcery.SPIRIT_ALTAR.get()), SPIRIT_INFUSION);
        registry.addRecipeCatalyst(new ItemStack(MalumContent.Focusing.SPIRIT_CRUCIBLE.get()), FOCUSING);
        registry.addRecipeCatalyst(new ItemStack(MalumContent.Focusing.REPAIR_PYLON.get()), SPIRIT_REPAIR);
        registry.addRecipeCatalyst(new ItemStack(MalumContent.Totemancy.SOULWOOD_TOTEM_BASE.get()), TRANSMUTATION);
        registry.addRecipeCatalyst(new ItemStack(MalumContent.Sorcery.RUNIC_WORKBENCH.get()), RUNEWORKING);
        registry.addRecipeCatalyst(new ItemStack(MalumContent.WeepingWell.VOID_DEPOT.get()), VOID_FAVOR);
    }


    @Override
    public void onRuntimeAvailable(@NotNull IJeiRuntime jeiRuntime) {
        HiddenRecipeHandler.onRuntimeAvailable(jeiRuntime);
    }

    @Override
    public void onRuntimeUnavailable() {
        HiddenRecipeHandler.onRuntimeUnavailable();
    }

    public <T extends RecipeInput, K extends LodestoneInWorldRecipe<T>> void addRecipes(
            IRecipeRegistration registry, RecipeType<K> jeiType, LodestoneRecipeType<K> lodestoneType) {
        var search = LodestoneRecipeSearch.search(Minecraft.getInstance().level, lodestoneType);
        registry.addRecipes(jeiType, search.findRecipes(r -> !r.output.isEmpty()));
    }
}
