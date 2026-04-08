package com.sammy.malum.client.screen.codex.pages.recipe.vanilla;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.display.DisplayedGizmo;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.util.*;
import java.util.function.Consumer;

import static com.sammy.malum.client.screen.codex.display.DisplayedGizmo.item;
import static com.sammy.malum.registry.common.item.MalumItems.ARCANE_CHARCOAL;
import static com.sammy.malum.registry.common.item.MalumItems.BLOCK_OF_ARCANE_CHARCOAL;

public class CraftingPage extends BookPage {

    public static final ResourceLocation CRAFTING_SEGMENTS = MalumMod.malumPath("textures/gui/book/entry_elements/crafting_segments.png");

    public record CraftingGridContents(DisplayedGizmo[] inputs) {

        public CraftingGridContents() {
            this(new DisplayedGizmo[9]);
        }

        public CraftingGridContents fill(DisplayedGizmo display) {
            Arrays.fill(inputs, display);
            return this;
        }
        public CraftingGridContents topLeft(DisplayedGizmo display) {
            return set(0, display);
        }

        public CraftingGridContents top(DisplayedGizmo display) {
            return set(1, display);
        }

        public CraftingGridContents topRight(DisplayedGizmo display) {
            return set(2, display);
        }

        public CraftingGridContents middleLeft(DisplayedGizmo display) {
            return set(3, display);
        }

        public CraftingGridContents middle(DisplayedGizmo display) {
            return set(4, display);
        }

        public CraftingGridContents middleRight(DisplayedGizmo display) {
            return set(5, display);
        }

        public CraftingGridContents bottomLeft(DisplayedGizmo display) {
            return set(6, display);
        }

        public CraftingGridContents bottom(DisplayedGizmo display) {
            return set(7, display);
        }

        public CraftingGridContents bottomRight(DisplayedGizmo display) {
            return set(8, display);
        }
        
        public CraftingGridContents set(int index, DisplayedGizmo display) {
            inputs[index] = display;
            return this;
        }
        
    }
    private final DisplayedGizmo output;
    private final CraftingGridContents gridContents;

    public static CraftingPage fullBlock(DisplayedGizmo block, DisplayedGizmo input) {
        return new CraftingPage(block, c -> c.fill(input));
    }

    public CraftingPage(DisplayedGizmo output, Consumer<CraftingGridContents> builder) {
        this.output = output;
        this.gridContents = new CraftingGridContents();
        builder.accept(gridContents);
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
                var display = gridContents.inputs[index];
                if (display == null) {
                    continue;
                }
                int itemPosX = left + 43 + y * 20;
                int itemPosY = top + 50 + x * 20;

                segments.setPositionWithWidth(itemPosX, itemPosY, 16, 16)
                        .setUVWithWidth(x*20, y*20, 18, 18, 58, 58)
                        .blit(guiGraphics.pose());

                display.render(screen, guiGraphics, itemPosX, itemPosY, mouseX, mouseY);
            }
        }

        output.render(screen, guiGraphics, left + 63, top + 162, mouseX, mouseY);
    }
}