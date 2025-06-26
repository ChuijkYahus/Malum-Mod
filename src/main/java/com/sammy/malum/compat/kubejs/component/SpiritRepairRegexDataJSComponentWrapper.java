package com.sammy.malum.compat.kubejs.component;

import com.sammy.malum.common.recipe.spirit_repair.*;
import dev.latvian.mods.kubejs.typings.*;
import dev.latvian.mods.kubejs.util.*;
import dev.latvian.mods.rhino.util.*;

@Info("Spirit Ingredient Builder Methods")
public interface SpiritRepairRegexDataJSComponentWrapper {

    @HideFromJS
    static SpiritRepairRegexData wrap(RegistryAccessContainer registries, Object from) {
        return SpiritRepairRegexDataJSComponent.fromObject(registries, from);
    }
}