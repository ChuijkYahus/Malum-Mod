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

    public static ProgressionScreenHolder<?> getAppropriateCodexScreen() {
        return mostRecentScreen != null ? mostRecentScreen : ArcanaProgressionScreen.SCREEN;
    }

    public void reopenCodexFromEntryScreen(boolean isVoidTouched, boolean ignoreNextMouseInput) {
        openCodex(isVoidTouched, ignoreNextMouseInput);
    }

    public void openCodexViaItem(boolean isVoidTouched) {
        openCodex(isVoidTouched, true);
    }

    public void openCodex(boolean isVoidTouched, boolean ignoreNextMouseInput) {
        var minecraft = Minecraft.getInstance();
        var openScreen = minecraft.screen;
        var screen = getScreen();
        minecraft.setScreen(screen);
        screen.ignoreNextMouseInput = ignoreNextMouseInput;
        screen.setVoidTouched(isVoidTouched);
        if (openScreen == null) {
            screen.playSweetenedSound(MalumSoundEvents.ARCANA_CODEX_OPEN, 1.25f);
        }
        mostRecentScreen = this;
    }

    public void openCodexViaTransition() {
        var screen = getScreen();
        Minecraft.getInstance().setScreen(screen);
        screen.ignoreNextMouseInput = false;
        screen.voidFadeoutCounter++;
        screen.voidFadeoutTimer = screen.getVoidFadeoutDuration();
        screen.faceOrigin();
        screen.playSound(transitionSound, 1.25f, 1f);
        mostRecentScreen = this;
    }
}
