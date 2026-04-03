package com.sammy.malum.client.screen.codex.objects;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.helper.CodexOutlineRenderer;
import com.sammy.malum.client.screen.codex.pages.PageSelectionPage;
import com.sammy.malum.client.screen.codex.screens.CodexEntryScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import team.lodestar.lodestone.helpers.DataHelper;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.renderTexture;

public class PageSelectionObject extends BookObject<CodexEntryScreen> {

    public static final ResourceLocation SELECTION = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection.png");
    public static final ResourceLocation SELECTION_HOVER = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection_hover.png");
    public static final ResourceLocation SELECTION_ACTIVE = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection_active.png");

    public static final ResourceLocation SELECTION_GLOW = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection_glow.png");
    public static final ResourceLocation SELECTION_OUTLINE = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection_outline.png");

    protected final PageSelectionPage page;
    protected final int index;
    protected final ItemStack iconStack;

    protected float oldOutlineVisibility;
    protected float outlineVisibility;

    public PageSelectionObject(PageSelectionPage page, int index, ItemStack iconStack, int posX, int posY) {
        super(posX, posY, 28, 30);
        this.page = page;
        this.index = index;
        this.iconStack = iconStack;
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int x = getOffsetXPosition();
        int y = getOffsetYPosition();
        var texture = isSelected() ? SELECTION_HOVER : SELECTION;
        var poseStack = guiGraphics.pose();
        CodexOutlineRenderer.create(SELECTION_GLOW, SELECTION_OUTLINE, x-16, y-16)
                .setEffectStrength(oldOutlineVisibility, outlineVisibility, 1f)
                .renderOutline(poseStack);
        renderTexture(texture, poseStack, x, y, 0, 0, width, height);
        if (iconStack != null) {
            guiGraphics.renderItem(iconStack, x + 6, y + 6);
        }
    }

    @Override
    public void tick(CodexEntryScreen screen, double mouseX, double mouseY) {
        super.tick(screen, mouseX, mouseY);

        oldOutlineVisibility = outlineVisibility;
        float target = 0f;
        if (isSelected()) {
            target = 1f;
        }
        else if (isHoveredOver) {
            target = 0.5f;
        }
        outlineVisibility = DataHelper.approach(outlineVisibility, target, 0.05f);
    }

    @Override
    public boolean click(CodexEntryScreen screen, double mouseX, double mouseY) {
        page.setIndex(index);
        return super.click(screen, mouseX, mouseY);
    }

    @Override
    public int getOffsetYPosition() {
        int pos = super.getOffsetYPosition();
        if (isSelected()) {
            pos += 2;
        }
        return pos;
    }

    public <T> T choose(T onDefault, T onHover, T onSelected) {
        if (isSelected()) {
            return onSelected;
        }
        if (isHoveredOver) {
            return onHover;
        }
        return onDefault;
    }

    public boolean isSelected() {
        return page.getIndex() == index;
    }
}