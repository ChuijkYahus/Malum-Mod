package com.sammy.malum.core.systems.events;


import com.sammy.malum.client.screen.codex.screens.progression.*;
import net.neoforged.bus.api.*;

/**
 * Called when the book is opened for the first time per minecraft client instance, prior to setting up book objects.
 * All the relevant book parameters are static, so you may simply edit those.
 */
public class SetupMalumCodexEntriesEvent extends Event {
    protected final AbstractProgressionCodexScreen screen;

    public SetupMalumCodexEntriesEvent(AbstractProgressionCodexScreen screen) {
        this.screen = screen;
    }

    public AbstractProgressionCodexScreen getScreen() {
        return screen;
    }
}
