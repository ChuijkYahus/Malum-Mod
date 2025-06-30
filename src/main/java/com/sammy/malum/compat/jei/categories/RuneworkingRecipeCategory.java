package com.sammy.malum.compat.jei.categories;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.recipe.RuneworkingRecipe;
import com.sammy.malum.compat.jei.JEIHandler;
import com.sammy.malum.registry.common.item.MalumItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

import java.util.*;

import static com.sammy.malum.MalumMod.malumPath;

public class RuneworkingRecipeCategory implements IRecipeCategory<RuneworkingRecipe> {

    public static final ResourceLocation UID = malumPath("runeworking");
    private final IDrawable overlay;
    private final IDrawable icon;

    public RuneworkingRecipeCategory(IGuiHelper guiHelper) {
        overlay = guiHelper.createDrawable(MalumMod.malumPath("textures/gui/runeworking_jei.png"), 0, 0, 142, 185);
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MalumItems.RUNIC_WORKBENCH.get()));
    }

    @Override
    public void draw(RuneworkingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        overlay.draw(guiGraphics);
    }

    @Override
    public RecipeType<RuneworkingRecipe> getRecipeType() {
        return JEIHandler.RUNEWORKING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("malum.jei." + UID.getPath());
    }

    @Override
    public int getHeight() {
        return overlay.getHeight();
    }

    @Override
    public int getWidth() {
        return overlay.getWidth();
    }

    @Nonnull
    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RuneworkingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 63, 14)
                .addItemStacks(List.of(recipe.secondaryInput.getItems()));
        builder.addSlot(RecipeIngredientRole.INPUT, 63, 57)
                .addItemStacks(List.of(recipe.input.getItems()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 63, 124)
                .addItemStack(recipe.output);
    }
}
