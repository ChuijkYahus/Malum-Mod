package com.sammy.malum.compat.kubejs;

import com.sammy.malum.compat.kubejs.component.*;
import com.sammy.malum.registry.common.recipe.*;
import dev.latvian.mods.kubejs.plugin.*;
import dev.latvian.mods.kubejs.recipe.schema.*;
import dev.latvian.mods.kubejs.script.*;

public class MalumJSPlugin implements KubeJSPlugin {

    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry registry) {
        registry.register(MalumRecipeTypes.SPIRIT_INFUSION.getId(), SpiritInfusionRecipeJS.SPIRIT_INFUSION);
        registry.register(MalumRecipeTypes.SPIRIT_FOCUSING.getId(), SpiritInfusionRecipeJS.SPIRIT_FOCUSING);
    }

    @Override
    public void registerRecipeComponents(RecipeComponentFactoryRegistry registry) {
        registry.register(SpiritJSComponent.SPIRIT_INGREDIENT);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("SpiritComponent", SpiritJSComponent.class);
    }
}
