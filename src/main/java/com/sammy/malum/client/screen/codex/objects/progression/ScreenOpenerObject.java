package com.sammy.malum.client.screen.codex.objects.progression;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import net.minecraft.resources.*;

public class ScreenOpenerObject extends IconObject {
    private final ProgressionScreenHolder<?> destination;
    public ScreenOpenerObject(BookEntry entry, int posX, int posY, ProgressionScreenHolder<?> destination, ResourceLocation textureLocation) {
        super(entry, posX, posY, textureLocation);
        this.destination = destination;
    }

    public ScreenOpenerObject(BookEntry entry, int posX, int posY, ProgressionScreenHolder<?> destination, ResourceLocation textureLocation, int textureWidth, int textureHeight) {
        super(entry, posX, posY, textureLocation, textureWidth, textureHeight);
        this.destination = destination;
    }

    @Override
    public void click(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (!entry.isFragment) {
            destination.openCodexViaTransition();
        }
    }
}
