package com.sammy.malum.client.screen.codex.objects.button;

import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedGizmo;
import com.sammy.malum.client.screen.codex.pages.display.*;
import com.sammy.malum.client.screen.codex.screens.*;

public class SoulstoneGrowthStageSelectionObject extends SmallButtonObject {

    protected final SoulstoneGrowthStagePage page;

    public SoulstoneGrowthStageSelectionObject(SoulstoneGrowthStagePage page, DisplayedGizmo gizmo, int index, int posX, int posY) {
        super(gizmo, index, posX, posY);
        this.page = page;
    }

    @Override
    public boolean release(CodexEntryScreen screen, double mouseX, double mouseY) {
        boolean release = super.release(screen, mouseX, mouseY);
        page.setStage(buttonIndex);
        return release;
    }

    @Override
    public boolean isSelected() {
        return page.getSelectedStage() == buttonIndex;
    }
}