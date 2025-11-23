package com.sammy.malum.compat.kubejs.wrapper;

import com.sammy.malum.compat.kubejs.component.*;
import com.sammy.malum.core.systems.recipe.*;
import dev.latvian.mods.kubejs.typings.*;
import dev.latvian.mods.kubejs.util.*;
import dev.latvian.mods.rhino.util.*;

@Info("Spirit Ingredient Builder Methods")
public interface SpiritJSComponentWrapper {

    @HideFromJS
    static SpiritIngredient wrap(RegistryAccessContainer registries, Object from) {
        return SpiritJSComponent.fromObject(registries, from);
    }
}