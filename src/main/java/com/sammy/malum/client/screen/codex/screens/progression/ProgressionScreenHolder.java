package com.sammy.malum.client.screen.codex.screens.progression;

import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.*;
import net.minecraft.core.*;
import net.minecraft.sounds.*;

import java.util.function.*;

public class ProgressionScreenHolder<T extends AbstractProgressionCodexScreen> extends MalumScreenHolder<T> {

    public static ProgressionScreenHolder<?> mostRecentScreen;

    protected final Holder<SoundEvent> transitionSound;

    public ProgressionScreenHolder(Supplier<T> screenSupplier, Holder<SoundEvent> transitionSound) {
        super(screenSupplier);
        this.transitionSound = transitionSound;
    }

    public static void openAppropriateCodexScreen() {
        var screen = mostRecentScreen != null ? mostRecentScreen : ArcanaProgressionScreen.SCREEN;
        screen.openCodexViaItem(true);
    }

    public void openCodexViaItem(boolean isVoidTouched) {
        var minecraft = Minecraft.getInstance();
        var openScreen = minecraft.screen;
        var screen = getScreen();
        minecraft.setScreen(screen);
        screen.ignoreNextMouseInput = true;
        screen.isVoidTouched = isVoidTouched;
        if (openScreen == null) {
            screen.playSweetenedSound(MalumSoundEvents.ARCANA_CODEX_OPEN, 1.25f);
        }
    }

    public void openCodexViaTransition() {
        var screen = getScreen();
        Minecraft.getInstance().setScreen(screen);
        screen.ignoreNextMouseInput = false;
        screen.voidFadeoutCounter++;
        screen.voidFadeoutTimer = screen.getVoidFadeoutDuration();
        screen.faceObject(screen.progressionObjects.getFirst());
        screen.playSound(transitionSound, 1.25f, 1f);
        mostRecentScreen = this;
    }
}
