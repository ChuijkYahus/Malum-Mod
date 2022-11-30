package com.sammy.malum.client.screen.codex.pages;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.ProgressionBookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HeadlineTextItemPage extends BookPage
{
    private final String headlineTranslationKey;
    private final String translationKey;
    private final ItemStack spiritStack;
    public HeadlineTextItemPage(String headlineTranslationKey, String translationKey, ItemStack spiritStack)
    {
        super(MalumMod.malumPath("textures/gui/book/pages/headline_item_page.png"));
        this.headlineTranslationKey = headlineTranslationKey;
        this.translationKey = translationKey;
        this.spiritStack = spiritStack;
    }
    public HeadlineTextItemPage(String headlineTranslationKey, String translationKey, Item spirit)
    {
        this(headlineTranslationKey,translationKey,spirit.getDefaultInstance());
    }

    public String headlineTranslationKey()
    {
        return "malum.gui.book.entry.page.headline." + headlineTranslationKey;
    }
    public String translationKey()
    {
        return "malum.gui.book.entry.page.text." + translationKey;
    }
    @Override
    public void renderLeft(Minecraft minecraft, PoseStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks)
    {
        int guiLeft = guiLeft();
        int guiTop = guiTop();
        Component component = Component.translatable(headlineTranslationKey());
        ProgressionBookScreen.renderText(poseStack, component, guiLeft+75 - minecraft.font.width(component.getString())/2,guiTop+10);
        ProgressionBookScreen.renderWrappingText(poseStack, translationKey(), guiLeft+14,guiTop+76,126);
        ProgressionBookScreen.renderItem(poseStack, spiritStack, guiLeft+67, guiTop+44,mouseX,mouseY);
    }

    @Override
    public void renderRight(Minecraft minecraft, PoseStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks)
    {
        int guiLeft = guiLeft();
        int guiTop = guiTop();
        Component component = Component.translatable(headlineTranslationKey());
        ProgressionBookScreen.renderText(poseStack, component, guiLeft+218 - minecraft.font.width(component.getString())/2,guiTop+10);
        ProgressionBookScreen.renderWrappingText(poseStack, translationKey(), guiLeft+156,guiTop+76,126);
        ProgressionBookScreen.renderItem(poseStack, spiritStack, guiLeft+209, guiTop+44,mouseX,mouseY);
    }
}
