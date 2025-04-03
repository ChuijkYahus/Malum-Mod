package com.sammy.malum.core.systems.events;

import net.minecraft.world.entity.*;
import net.neoforged.neoforge.event.entity.living.*;

public class ModifySpiritSpoilsEvent extends LivingEvent {

    private final LivingEntity attacker;
    private final int extraSpirits;
    private int newExtraSpirits;

    public ModifySpiritSpoilsEvent(LivingEntity entity, LivingEntity attacker, int spiritsDropped) {
        super(entity);
        this.attacker = attacker;
        this.extraSpirits = spiritsDropped;
        this.newExtraSpirits = spiritsDropped;
    }

    public LivingEntity getAttacker() {
        return attacker;
    }

    public int getExtraSpirits() {
        return extraSpirits;
    }

    public int getNewExtraSpirits() {
        return newExtraSpirits;
    }

    public void setNewExtraSpirits(int newExtraSpirits) {
        this.newExtraSpirits = newExtraSpirits;
    }

    public void addSpirits(int bonus) {
        setNewExtraSpirits(getNewExtraSpirits() + bonus);
    }
}