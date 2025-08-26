package com.sammy.malum.client.screen.codex.objects.progression;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import net.minecraft.client.gui.*;
import net.minecraft.resources.*;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;

public class IconObject extends ProgressionEntryObject {
    public final ResourceLocation textureLocation;
    public final int textureWidth;
    public final int textureHeight;

    public IconObject(BookEntry entry, int posX, int posY, ResourceLocation textureLocation) {
        this(entry, posX, posY, textureLocation, 16, 16);
    }
    public IconObject(BookEntry entry, int posX, int posY, ResourceLocation textureLocation, int textureWidth, int textureHeight) {
        super(entry, posX, posY);
        this.textureLocation = textureLocation;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    @Override
    public void render(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(screen, guiGraphics, mouseX, mouseY, partialTicks);
        var designType = design.getDesignType();
        int width = designType.getTextureWidth();
        int height = designType.getTextureHeight();
        int x = getOffsetXPosition() + 8 - (width - 32) / 4;
        int y = getOffsetYPosition() + 8 - (height - 32) / 4;
        renderWavyIcon(textureLocation, guiGraphics.pose(), x, y, 0, textureWidth, textureHeight);
    }

}