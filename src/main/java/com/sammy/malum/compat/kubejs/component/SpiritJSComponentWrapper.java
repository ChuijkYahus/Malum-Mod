package com.sammy.malum.compat.kubejs.component;

import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import dev.latvian.mods.kubejs.typings.*;
import net.minecraft.core.*;

@Info("Spirit Ingredient Builder Methods")
public interface SpiritJSComponentWrapper {

    @Info("Returns a SpiritIngredient of the input")
    static SpiritIngredient of(SpiritIngredient in) {
        return in;
    }

    @Info("Returns a SpiritIngredient from the given spirit and quantity")
    static SpiritIngredient of(Holder<MalumSpiritType> spirit, int count) {
        return new SpiritIngredient(spirit, count);
    }

    @Info("Returns a SpiritIngredient from the given spirit and quantity")
    static SpiritIngredient of(Holder<MalumSpiritType> spirit) {
        return new SpiritIngredient(spirit, 1);
    }

    @Info("Returns a SpiritIngredient from the given spirit and quantity")
    static SpiritIngredient of(SpiritHolder<MalumSpiritType> spirit, int count) {
        return new SpiritIngredient(spirit, count);
    }

    @Info("Returns a SpiritIngredient from the given spirit and quantity")
    static SpiritIngredient of(SpiritHolder<MalumSpiritType> spirit) {
        return new SpiritIngredient(spirit, 1);
    }
}