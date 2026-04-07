package com.sammy.malum.client.screen.codex.pages.recipe.vanilla;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.fml.*;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.util.*;

import static net.minecraft.world.item.Items.AIR;

public class CraftingPage extends BookPage {

    public static final ResourceLocation CRAFTING_SEGMENTS = MalumMod.malumPath("textures/gui/book/entry_elements/crafting_segments.png");

    private final ItemStack outputStack;
    private final List<ItemStack> inputStacks;

    public CraftingPage(ItemStack outputStack, List<ItemStack> inputStacks) {
        this.outputStack = outputStack;
        this.inputStacks = inputStacks;
    }

    public CraftingPage(ItemStack outputStack, ItemStack... inputStacks) {
        this(outputStack, List.of(inputStacks));
    }

    public CraftingPage(Item outputItem, Item... inputItems) {
        this(outputItem.getDefaultInstance(), inputItems);
    }

    public CraftingPage(ItemStack outputStack, Item... inputItems) {
        this(outputStack, Arrays.stream(inputItems).map(Item::getDefaultInstance).toList());
    }

    @Override
    public ResourceLocation getBackground() {
        return MalumMod.malumPath("textures/gui/book/pages/crafting_page.png");
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {

        var segments = VFXBuilders.createScreen().setTexture(CRAFTING_SEGMENTS)
                        .setShader(GameRenderer::getPositionTexColorShader);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int index = x * 3 + y;
                if (inputStacks.size() <= index) {
                    continue;
                }
                var stack = inputStacks.get(index);
                if (stack.isEmpty()) {
                    continue;
                }
                int itemPosX = left + 43 + y * 20;
                int itemPosY = top + 50 + x * 20;

                segments.setPositionWithWidth(itemPosX, itemPosY, 16, 16)
                        .setUVWithWidth(x*20, y*20, 18, 18, 58, 58)
                        .blit(guiGraphics.pose());

                CodexItemHelper.renderItem(screen, guiGraphics, stack, itemPosX, itemPosY, mouseX, mouseY);
            }
        }

        CodexItemHelper.renderItem(screen, guiGraphics, outputStack, left + 63, top + 162, mouseX, mouseY);
    }

    public static CraftingPage topAndMiddle(Item output, Item top, Item middle) {
        return new CraftingPage(output.getDefaultInstance(), AIR, top, AIR, AIR, middle);
    }

    public static CraftingPage shapeless(Item output, Item... inputs) {
        return new CraftingPage(output.getDefaultInstance(), inputs);
    }

    public static CraftingPage fullPage(Item output, Item input) {
        return fullPage(output.getDefaultInstance(), input.getDefaultInstance());
    }

    public static CraftingPage fullPage(ItemStack output, ItemStack input) {
        return new CraftingPage(output, input, input, input, input, input, input, input, input, input);
    }

    public static CraftingPage bannerPage(Item input, SoulwovenBannerPatternDataComponent pattern) {
        return new CraftingPage(pattern.getDefaultStack(), MalumItems.SOULWOVEN_BANNER.get(), input);
    }

    public static CraftingPage scythePage(Item scythe, Item metal, Item reagent) {
        return scythePage(scythe.getDefaultInstance(), metal.getDefaultInstance(), reagent.getDefaultInstance());
    }

    public static CraftingPage scythePage(ItemStack scythe, ItemStack metal, ItemStack reagent) {
        ItemStack stick = Items.STICK.getDefaultInstance();
        var empty = AIR.getDefaultInstance();
        return new CraftingPage(scythe, metal, metal, reagent, empty, stick, metal, stick, empty, empty);
    }

    public static CraftingPage broochPage(Item brooch, Item ingot, Item block) {
        return broochPage(brooch.getDefaultInstance(), Items.LEATHER.getDefaultInstance(), ingot.getDefaultInstance(), block.getDefaultInstance());
    }

    public static CraftingPage broochPage(ItemStack brooch, ItemStack material, ItemStack ingot, ItemStack block) {
        var empty = AIR.getDefaultInstance();
        return new CraftingPage(brooch, empty, material, empty, material, ingot, material, empty, block, empty);
    }

    public static CraftingPage beltPage(Item belt, Item material) {
        var empty = AIR;
        var leather = Items.LEATHER;
        return new CraftingPage(belt, empty, leather, empty, leather, empty, leather, empty, material, empty);
    }

    public static CraftingPage necklacePage(Item necklace, Item material) {
        var empty = AIR;
        var weave = MalumItems.EERIE_WEAVE.get();
        return new CraftingPage(necklace, empty, weave, empty, weave, empty, weave, empty, material, empty);
    }

    public static CraftingPage ringPage(Item ring, Item material) {
        var empty = AIR;
        var leather = Items.LEATHER;
        return new CraftingPage(ring, material, leather, empty, leather, empty, leather, empty, leather, empty);
    }

    public static CraftingPage itemPedestalPage(Item pedestal, Item fullBlock, Item slab) {
        return itemPedestalPage(pedestal.getDefaultInstance(), fullBlock.getDefaultInstance(), slab.getDefaultInstance());
    }

    public static CraftingPage itemPedestalPage(ItemStack pedestal, ItemStack fullBlock, ItemStack slab) {
        var empty = AIR.getDefaultInstance();
        return new CraftingPage(pedestal, slab, slab, slab, empty, fullBlock, empty, slab, slab, slab);
    }

    public static CraftingPage itemStandPage(Item stand, Item fullBlock, Item slab) {
        return itemStandPage(stand.getDefaultInstance(), fullBlock.getDefaultInstance(), slab.getDefaultInstance());
    }

    public static CraftingPage itemStandPage(ItemStack stand, ItemStack fullBlock, ItemStack slab) {
        var empty = AIR.getDefaultInstance();
        return new CraftingPage(stand.copyWithCount(2), empty, empty, empty, slab, slab, slab, fullBlock, fullBlock, fullBlock);
    }

    public static CraftingPage toolPage(Item tool, Item metal) {
        return toolPage(tool.getDefaultInstance(), metal.getDefaultInstance());
    }

    public static CraftingPage toolPage(ItemStack tool, ItemStack metal) {
        ItemStack stick = Items.STICK.getDefaultInstance();
        var empty = AIR.getDefaultInstance();
        return switch (tool.getItem()) {
            case SwordItem swordItem ->
                    new CraftingPage(tool, empty, metal, empty, empty, metal, empty, empty, stick, empty);
            case AxeItem axeItem ->
                    new CraftingPage(tool, metal, metal, empty, metal, stick, empty, empty, stick, empty);
            case HoeItem hoeItem ->
                    new CraftingPage(tool, metal, metal, empty, empty, stick, empty, empty, stick, empty);
            case ShovelItem shovelItem ->
                    new CraftingPage(tool, empty, metal, empty, empty, stick, empty, empty, stick, empty);
            case PickaxeItem pickaxeItem ->
                    new CraftingPage(tool, metal, metal, metal, empty, stick, empty, empty, stick, empty);
            default -> null;
        };
    }

    public static CraftingPage knifePage(Item tool, Item metal) {
        return knifePage(tool.getDefaultInstance(), metal.getDefaultInstance());
    }

    public static CraftingPage knifePage(ItemStack tool, ItemStack metal) {
        ItemStack stick = Items.STICK.getDefaultInstance();
        var empty = AIR.getDefaultInstance();
        return new CraftingPage(tool, empty, empty, empty, empty, metal, empty, stick, empty) {
            @Override
            public boolean isValid() {
                return ModList.get().isLoaded("farmersdelight");
            }
        };
    }
}