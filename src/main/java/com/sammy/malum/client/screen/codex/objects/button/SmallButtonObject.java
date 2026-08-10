package com.sammy.malum.client.screen.codex.objects.button;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedGizmo;

public class SmallButtonObject extends AbstractButtonObject{

    public SmallButtonObject(DisplayedGizmo gizmo, int buttonIndex, int posX, int posY) {
        super(gizmo, MalumMod.malumPath("textures/gui/book/buttons/small_button"), buttonIndex, posX, posY, 20, 22);
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public int getGizmoOffset() {
        return 2;
    }
}
