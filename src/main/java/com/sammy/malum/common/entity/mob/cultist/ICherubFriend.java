package com.sammy.malum.common.entity.mob.cultist;

import net.minecraft.world.phys.Vec3;

public interface ICherubFriend {

    enum CherubPriority {
        HIGHEST(2),
        HIGH(1),
        STANDARD(0);

        final int priority;

        CherubPriority(int priority) {
            this.priority = priority;
        }
    }

    int getCherubCapacity();

    CherubPriority getCherubPriority();

    Vec3 getCherubHoverOffset(int cherub);
}
