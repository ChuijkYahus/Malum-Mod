package com.sammy.malum.client.screen.codex.screens;

import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.common.item.codex.*;
import com.sammy.malum.registry.common.*;

import java.util.function.*;

public class MalumScreenHolder<T extends AbstractMalumCodexScreen> {

    private T screen;

    protected final Supplier<T> screenSupplier;

    public MalumScreenHolder(Supplier<T> screenSupplier) {
        this.screenSupplier = screenSupplier;
    }

    public T getScreen() {
        if (screen == null) {
            screen = screenSupplier.get();
        }
        return screen;
    }
}
