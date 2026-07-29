package com.sammy.malum.client.screen.codex.objects.button;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedGizmo;

public class ButtonObject extends AbstractButtonObject{

    public ButtonObject(DisplayedGizmo gizmo, int buttonIndex, int posX, int posY) {
        super(gizmo, MalumMod.malumPath("textures/gui/book/buttons/button"), buttonIndex, posX, posY, 28, 30);
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public int getGizmoOffset() {
        return 6;
    }
}
