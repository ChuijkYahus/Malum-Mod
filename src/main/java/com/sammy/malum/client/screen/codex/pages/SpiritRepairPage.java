package com.sammy.malum.client.screen.codex.pages;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.common.recipe.*;
import net.minecraft.client.*;
import net.minecraft.world.item.*;

import java.util.function.*;
import java.util.stream.*;

import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.*;

@SuppressWarnings("all")
public class SpiritRepairPage extends BookPage {
    private final SpiritRepairRecipe recipe;

    public SpiritRepairPage(Predicate<SpiritRepairRecipe> predicate) {
        super(MalumMod.malumPath("textures/gui/book/pages/spirit_repair_page.png"));
        if (Minecraft.getInstance() == null) //this is null during datagen
        {
            this.recipe = null;
            return;
        }
        this.recipe = SpiritRepairRecipe.getRecipe(Minecraft.getInstance().level, predicate);
    }

    public SpiritRepairPage(SpiritRepairRecipe recipe) {
        super(MalumMod.malumPath("textures/gui/book/pages/spirit_repair_page.png"));
        this.recipe = recipe;
    }

    @Override
    public boolean isValid() {
        return recipe != null;
    }

    public static SpiritRepairPage fromInput(Item inputItem) {
        return new SpiritRepairPage(s -> s.doesInputMatch(inputItem.getDefaultInstance()));
    }

    @Override
    public void renderLeft(Minecraft minecraft, PoseStack poseStack, EntryScreen screen, float yOffset, int mouseX, int mouseY, float partialTicks, float xOffset) {
        int guiLeft = guiLeft();
        int guiTop = guiTop();
        renderComponent(screen, poseStack, recipe.repairMaterial, guiLeft + 48, guiTop + 59, mouseX, mouseY);
        renderItem(screen, poseStack, recipe.inputs.stream().map(s -> s.getDefaultInstance()).peek(s -> s.setDamageValue((int) (s.getMaxDamage() * recipe.durabilityPercentage))).collect(Collectors.toList()), guiLeft + 86, guiTop + 59, mouseX, mouseY);
        renderItem(screen, poseStack, recipe.inputs.stream().map(s -> SpiritRepairRecipe.getRepairRecipeOutput(s.getDefaultInstance())).collect(Collectors.toList()), guiLeft + 67, guiTop + 126, mouseX, mouseY);
        if (!recipe.spirits.isEmpty()) {
            renderComponents(screen, poseStack, recipe.spirits, guiLeft + 65, guiTop + 16, mouseX, mouseY, false);
        }
    }

    @Override
    public void renderRight(Minecraft minecraft, PoseStack poseStack, EntryScreen screen, float yOffset, int mouseX, int mouseY, float partialTicks, float xOffset) {
        int guiLeft = guiLeft();
        int guiTop = guiTop();
        renderComponent(screen, poseStack, recipe.repairMaterial, guiLeft + 190, guiTop + 59, mouseX, mouseY);
        renderItem(screen, poseStack, recipe.inputs.stream().map(s -> s.getDefaultInstance()).peek(s -> s.setDamageValue((int) (s.getMaxDamage() * recipe.durabilityPercentage))).collect(Collectors.toList()), guiLeft + 228, guiTop + 59, mouseX, mouseY);
        renderItem(screen, poseStack, recipe.inputs.stream().map(s -> SpiritRepairRecipe.getRepairRecipeOutput(s.getDefaultInstance())).collect(Collectors.toList()), guiLeft + 209, guiTop + 126, mouseX, mouseY);
        if (!recipe.spirits.isEmpty()) {
            renderComponents(screen, poseStack, recipe.spirits, guiLeft + 207, guiTop + 16, mouseX, mouseY, false);
        }
    }
}