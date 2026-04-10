package com.sammy.malum.compat.jei.categories;

import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.common.recipe.spirit_repair.SpiritRepairRecipe;
import com.sammy.malum.compat.jei.*;
import com.sammy.malum.registry.common.MalumContent;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
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
import java.util.Arrays;

import static com.sammy.malum.MalumMod.malumPath;

public class SpiritRepairRecipeCategory implements IRecipeCategory<SpiritRepairRecipe> {

    public static final ResourceLocation UID = malumPath("spirit_repair");
    private final IDrawable overlay;
    private final IDrawable icon;

    public SpiritRepairRecipeCategory(IGuiHelper guiHelper) {
        overlay = guiHelper.createDrawable(malumPath("textures/gui/spirit_repair_jei.png"), 0, 0, 142, 185);
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MalumContent.Artifice.REPAIR_PYLON.get()));
    }

    @Override
    public void draw(SpiritRepairRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        overlay.draw(guiGraphics);
        if (!recipe.spirits.isEmpty()) {
            CodexItemHelper.renderItemFrames(guiGraphics, recipe.spirits.size(), 61, 12, false);
        }
    }

    @Override
    public RecipeType<SpiritRepairRecipe> getRecipeType() {
        return JEIHandler.SPIRIT_REPAIR;
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
    public void setRecipe(IRecipeLayoutBuilder builder, SpiritRepairRecipe recipe, IFocusGroup focuses) {
        JEIHelper.addCustomIngredientToJei(builder, RecipeIngredientRole.INPUT, 61, 12, false, recipe.spirits);

        builder.addSlot(RecipeIngredientRole.INPUT, 44, 57)
                .addItemStacks(Arrays.asList(recipe.repairMaterial.getItems()));

        IRecipeSlotBuilder input = builder.addSlot(RecipeIngredientRole.INPUT, 82, 57)
                .addItemStacks(recipe.getDamagedItems());

        IRecipeSlotBuilder output = builder.addSlot(RecipeIngredientRole.OUTPUT, 63, 124)
                .addItemStacks(recipe.getRepairedItems());

        builder.createFocusLink(input, output);
    }
}
