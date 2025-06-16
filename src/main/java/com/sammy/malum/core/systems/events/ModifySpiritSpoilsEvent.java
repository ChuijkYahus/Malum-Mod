package com.sammy.malum.core.systems.events;

import net.minecraft.world.entity.*;
import net.neoforged.neoforge.event.entity.living.*;

public class ModifySpiritSpoilsEvent extends LivingEvent {

    private final LivingEntity attacker;
    private final int spiritBonus;
    private int newSpiritBonus;

    public ModifySpiritSpoilsEvent(LivingEntity entity, LivingEntity attacker, int spiritBonus) {
        super(entity);
        this.attacker = attacker;
        this.spiritBonus = spiritBonus;
        this.newSpiritBonus = spiritBonus;
    }

    public LivingEntity getAttacker() {
        return attacker;
    }

    public int getOriginalSpiritBonus() {
        return spiritBonus;
    }

    public int getNewSpiritBonus() {
        return newSpiritBonus;
    }

    public void setNewSpiritBonus(int newSpiritBonus) {
        this.newSpiritBonus = newSpiritBonus;
    }

    public void addBonus(int bonus) {
        setNewSpiritBonus(getNewSpiritBonus() + bonus);
    }
}