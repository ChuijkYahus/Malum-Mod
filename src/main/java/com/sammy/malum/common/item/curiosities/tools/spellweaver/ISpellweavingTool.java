package com.sammy.malum.common.item.curiosities.tools.spellweaver;

public interface ISpellweavingTool {

    enum Mode {
        NEAREST,
        FURTHEST;

    }

    Mode getMode();
}
