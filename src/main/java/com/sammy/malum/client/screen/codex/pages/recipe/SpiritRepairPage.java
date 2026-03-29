package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.common.recipe.spirit_repair.*;
import com.sammy.malum.core.systems.recipe.SpiritBasedRecipeInput;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import team.lodestar.lodestone.modules.toolkit.recipe.LodestoneInWorldRecipe;

import java.util.*;
import java.util.function.*;

public class SpiritRepairPage extends BookRecipePage<SpiritBasedRecipeInput, SpiritRepairRecipe> {

    private List<ItemStack> damagedStacks;
    private List<ItemStack> repairedStacks;

    public SpiritRepairPage(String id) {
        super(id);
    }

    public SpiritRepairPage(ResourceLocation id) {
        super(id);
    }

    public SpiritRepairPage(Predicate<SpiritRepairRecipe> filter) {
        super(filter);
    }

    @Override
    public void gatherRecipeData() {
        this.damagedStacks = recipe.getDamagedItems();
        this.repairedStacks = recipe.getRepairedItems();
    }

    @Override
    public RecipeType<SpiritRepairRecipe> getRecipeType() {
        return MalumRecipeTypes.SPIRIT_REPAIR.get();
    }

    public static SpiritRepairPage fromOutput(Item outputItem) {
        return new SpiritRepairPage(recipe -> recipe.isValidItemForRepair(outputItem));
    }

    public static SpiritRepairPage fromId(String recipeId) {
        return new SpiritRepairPage(recipeId);
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        CodexItemHelper.renderIngredients(screen, guiGraphics, recipe.spirits, left + 63, top + 16, mouseX, mouseY, false);
        CodexItemHelper.renderIngredients(screen, guiGraphics, List.of(recipe.repairMaterial), left + 63, top + 32, mouseX, mouseY, false);
        CodexItemHelper.renderItem(screen, guiGraphics, damagedStacks, left + 63, top + 56, mouseX, mouseY);
        CodexItemHelper.renderItem(screen, guiGraphics, repairedStacks, left + 63, top + 132, mouseX, mouseY);

        renderRecipeInfo(guiGraphics, screen, "spirit_repair", left + 62, top + 74, mouseX, mouseY);
    }
}