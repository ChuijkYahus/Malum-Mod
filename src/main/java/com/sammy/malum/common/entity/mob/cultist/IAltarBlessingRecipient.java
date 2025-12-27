package com.sammy.malum.common.entity.mob.cultist;

public interface IAltarBlessingRecipient {

    default boolean canReceiveAltarBuff() {
        return true;
    }

    default void receiveAltarBuff() {

    }
}
