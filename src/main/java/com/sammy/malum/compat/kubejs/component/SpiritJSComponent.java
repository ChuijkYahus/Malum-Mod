//package com.sammy.malum.compat.kubejs.component;
//
//import com.sammy.malum.compat.kubejs.match.*;
//import com.sammy.malum.core.systems.recipe.*;
//import dev.latvian.mods.kubejs.recipe.*;
//import dev.latvian.mods.kubejs.recipe.component.*;
//import dev.latvian.mods.kubejs.recipe.match.*;
//import dev.latvian.mods.rhino.*;
//import dev.latvian.mods.rhino.type.*;
//
//public interface SpiritJSComponent {
//
//    TypeInfo SPIRIT = TypeInfo.of(SpiritIngredient.class);
//
//    RecipeComponent<SpiritIngredient> SPIRIT_INGREDIENT = new SimpleRecipeComponent<>("malum:spirit_ingredient", SpiritIngredient.CODEC.codec(), SPIRIT) {
//        @Override
//        public boolean hasPriority(Context cx, KubeRecipe recipe, Object from) {
//            return from instanceof SpiritIngredient;
//        }
//        @Override
//        public boolean matches(Context cx, KubeRecipe recipe, SpiritIngredient value, ReplacementMatchInfo match) {
//            return match.match() instanceof SpiritMatch spiritMatch && spiritMatch.matches(cx, value.spirit().getSpiritShard(), match.exact());
//        }
//
//        @Override
//        public boolean isEmpty(SpiritIngredient value) {
//            return value.count() <= 0;
//        }
//
//        @Override
//        public void buildUniqueId(UniqueIdBuilder builder, SpiritIngredient value) {
//            builder.append(value.spirit().getRegistryName());
//        }
//    };
//}
