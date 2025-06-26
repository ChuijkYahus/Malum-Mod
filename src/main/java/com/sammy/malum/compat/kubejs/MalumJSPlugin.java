package com.sammy.malum.compat.kubejs;

import com.sammy.malum.common.recipe.spirit_repair.*;
import com.sammy.malum.compat.kubejs.component.*;
import com.sammy.malum.core.systems.recipe.*;
import dev.latvian.mods.kubejs.plugin.*;
import dev.latvian.mods.kubejs.recipe.schema.*;
import dev.latvian.mods.kubejs.script.*;

public class MalumJSPlugin implements KubeJSPlugin {

    @Override
    public void registerRecipeComponents(RecipeComponentFactoryRegistry registry) {
        registry.register(SpiritJSComponent.SPIRIT_INGREDIENT);
        registry.register(SpiritRepairRegexDataJSComponent.REGEX_DATA);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("SpiritComponent", SpiritJSComponentWrapper.class);
        bindings.add("RepairRegex", SpiritRepairRegexDataJSComponentWrapper.class);
    }

    @Override
    public void registerTypeWrappers(TypeWrapperRegistry registry) {
        registry.register(SpiritIngredient.class, SpiritJSComponentWrapper::wrap);
        registry.register(SpiritRepairRegexData.class, SpiritRepairRegexDataJSComponentWrapper::wrap);
    }
}
