package com.sammy.malum.client.screen.container.tinkerer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;

public class ItemDepositWidget extends AbstractWidget {

    protected final Runnable clickBehavior;
    public ItemDepositWidget(int x, int y, Runnable clickBehavior) {
        super(x, y, 64, 64, Component.empty());
        this.clickBehavior = clickBehavior;
    }

    @Override
    public void onClick(double mouseX, double mouseY, int button) {
        clickBehavior.run();
        super.onClick(mouseX, mouseY, button);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (isHovered) {
            guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), 1073741825);
        }

    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }

    @Override
    public void playDownSound(SoundManager handler) {

    }
}
