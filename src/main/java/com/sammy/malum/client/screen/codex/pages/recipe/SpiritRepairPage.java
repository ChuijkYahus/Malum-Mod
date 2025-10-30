package com.sammy.malum.client.screen.codex.pages.recipe;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.common.recipe.spirit_repair.*;
import com.sammy.malum.registry.common.recipe.MalumRecipeTypes;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import team.lodestar.lodestone.systems.recipe.*;

import java.util.*;
import java.util.function.*;

public class SpiritRepairPage extends BookPage {

    private final SpiritRepairRecipe recipe;

    private final List<ItemStack> damagedStacks;
    private final List<ItemStack> repairedStacks;

    public SpiritRepairPage(Predicate<SpiritRepairRecipe> predicate) {
        this(LodestoneRecipeType.findRecipe(Minecraft.getInstance().level, MalumRecipeTypes.SPIRIT_REPAIR.get(), predicate));
    }

    public SpiritRepairPage(SpiritRepairRecipe recipe) {
        super(MalumMod.malumPath("textures/gui/book/pages/spirit_repair_page.png"));
        this.recipe = recipe;
        this.damagedStacks = recipe != null ? recipe.getDamagedItems() : Collections.emptyList();
        this.repairedStacks = recipe != null ? recipe.getRepairedItems() : Collections.emptyList();
    }

    @Override
    public boolean isValid() {
        return recipe != null;
    }

    public static SpiritRepairPage fromOutput(Item outputItem) {
        return new SpiritRepairPage(recipe -> recipe.isValidItemForRepair(outputItem));
    }

    public static SpiritRepairPage fromId(String recipeId) {
        return fromId(MalumMod.malumPath(recipeId));
    }

    public static SpiritRepairPage fromId(ResourceLocation recipeId) {
        var level = Minecraft.getInstance().level;
        ResourceLocation other = recipeId.withPrefix("spirit_repair/");
        var recipe = LodestoneRecipeType.getRecipeHolders(level, MalumRecipeTypes.SPIRIT_REPAIR.get())
                .stream()
                .filter(r -> r.id().equals(other))
                .findFirst()
                .map(RecipeHolder::value)
                .orElse(null);
        return new SpiritRepairPage(recipe);
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