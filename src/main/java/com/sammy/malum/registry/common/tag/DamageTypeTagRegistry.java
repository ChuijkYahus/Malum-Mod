package com.sammy.malum.registry.common.tag;

import com.sammy.malum.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.damagesource.*;
import team.lodestar.lodestone.registry.common.tag.*;

public class DamageTypeTagRegistry {

    public static final TagKey<DamageType> SOUL_SHATTER_DAMAGE = malumTag("can_soul_shatter");
    public static final TagKey<DamageType> IS_SCYTHE = malumTag("is_scythe");
    public static final TagKey<DamageType> IS_SCYTHE_MELEE = malumTag("is_scythe_melee");
    public static final TagKey<DamageType> IS_NITRATE = malumTag("is_nitrate");
    public static final TagKey<DamageType> IS_HIDDEN_BLADE = malumTag("is_hidden_blade");
    public static final TagKey<DamageType> IS_SUNDERING_ANCHOR_COMBO = malumTag("is_sundering_anchor_combo");
    public static final TagKey<DamageType> IS_INVERTED_HEART = malumTag("soulwashing");
    public static final TagKey<DamageType> INVERTED_HEART_RETALIATION_BLACKLIST = malumTag("inverted_heart_retaliation_blacklist");
    public static final TagKey<DamageType> INVERTED_HEART_PROPAGATION_BLACKLIST = malumTag("inverted_heart_propagation_blacklist");
    public static final TagKey<DamageType> GLEEFUL_TARGET_BLACKLIST = malumTag("lions_heart_blacklist");

    private static TagKey<DamageType> modTag(String path) {
        return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse(path));
    }

    public static TagKey<DamageType> malumTag(String path) {
        return TagKey.create(Registries.DAMAGE_TYPE, MalumMod.malumPath(path));
    }

    public static TagKey<DamageType> forgeTag(String path) {
        return LodestoneDamageTypeTags.forgeTag(path);
    }
}
