package com.sammy.malum.datagen.tag;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.HolderLookup.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.damagesource.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.registry.common.tag.*;

import java.util.*;
import java.util.concurrent.*;

import static com.sammy.malum.registry.common.MalumTags.DamageTypeTags.*;
import static net.minecraft.tags.DamageTypeTags.*;
import static net.neoforged.neoforge.common.Tags.DamageTypes.IS_PHYSICAL;
import static team.lodestar.lodestone.registry.common.tag.LodestoneDamageTypeTags.*;

public class MalumDamageTypeTagDatagen extends DamageTypeTagsProvider {

    public MalumDamageTypeTagDatagen(PackOutput pOutput, CompletableFuture<Provider> pProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MalumMod.MALUM, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(Provider pProvider) {
        var genericMagic = List.of(IS_MAGIC, SOUL_SHATTER_DAMAGE, BYPASSES_COOLDOWN, NO_KNOCKBACK, AVOIDS_GUARDIAN_THORNS, PANIC_CAUSES);
        var genericPhysical = List.of(IS_PHYSICAL, PANIC_CAUSES, CAN_BREAK_ARMOR_STAND);
        var genericScythe = List.of(IS_PHYSICAL, IS_SCYTHE, SOUL_SHATTER_DAMAGE, PANIC_CAUSES);
        var trueDamage = List.of(BYPASSES_ARMOR, BYPASSES_SHIELD, BYPASSES_INVULNERABILITY, BYPASSES_COOLDOWN, BYPASSES_EFFECTS, BYPASSES_RESISTANCE, BYPASSES_ENCHANTMENTS);

        addTags(MalumDamageTypes.VOODOO, genericMagic);
        addTags(MalumDamageTypes.VOODOO_PLAYERLESS, genericMagic);

        addTags(MalumDamageTypes.NITRATE, genericMagic, IS_EXPLOSION);
        addTags(MalumDamageTypes.NITRATE_PLAYERLESS, genericMagic, IS_EXPLOSION);

        addManyTags(MalumDamageTypes.VOID, genericMagic, trueDamage);
        addTags(MalumDamageTypes.KARMIC, genericMagic);
        addTags(MalumDamageTypes.ROT, genericMagic);

        addTags(MalumDamageTypes.SCYTHE_MELEE, genericScythe, CAN_TRIGGER_MAGIC, IS_SCYTHE_MELEE, IS_PLAYER_ATTACK);
        addTags(MalumDamageTypes.SCYTHE_SWEEP, genericScythe, CAN_TRIGGER_MAGIC, IS_SCYTHE_MELEE);
        addTags(MalumDamageTypes.SCYTHE_REBOUND, genericScythe, CAN_TRIGGER_MAGIC, IS_PROJECTILE);
        addTags(MalumDamageTypes.SCYTHE_ASCENSION, genericScythe, CAN_TRIGGER_MAGIC);
        addTags(MalumDamageTypes.SCYTHE_COMBO, genericScythe);
        addTags(MalumDamageTypes.SCYTHE_MAELSTROM, genericScythe);

        addTags(MalumDamageTypes.HIDDEN_BLADE_PHYSICAL_COUNTER, genericScythe);
        addManyTags(MalumDamageTypes.HIDDEN_BLADE_MAGIC_COUNTER, genericScythe, genericMagic);

        addTags(MalumDamageTypes.TYRVING, genericMagic);

        addTags(MalumDamageTypes.SUNDERING_ANCHOR_PHYSICAL_COMBO, genericPhysical, IS_SUNDERING_ANCHOR_COMBO, NO_KNOCKBACK);
        addTags(MalumDamageTypes.SUNDERING_ANCHOR_MAGIC_COMBO, genericMagic, IS_SUNDERING_ANCHOR_COMBO, NO_KNOCKBACK);

        addTags(MalumDamageTypes.UNMAKERS_DISDAIN_COMBO, genericPhysical);

        addTags(MalumDamageTypes.WARLOCK_SPIRIT_IMPACT, genericMagic);
        addTags(MalumDamageTypes.BERSERKER_SPIRIT_IMPACT, genericMagic);

        addTags(MalumDamageTypes.INVERTED_HEART_RETALIATION, genericMagic, IS_INVERTED_HEART);
        addTags(MalumDamageTypes.INVERTED_HEART_PROPAGATION, genericMagic, IS_INVERTED_HEART);


        tag(LodestoneDamageTypeTags.CAN_TRIGGER_MAGIC)
                .add(MalumDamageTypes.SCYTHE_MELEE, MalumDamageTypes.SCYTHE_SWEEP);

        tag(IS_MAGIC)
                .add(MalumDamageTypes.VOODOO_PLAYERLESS, MalumDamageTypes.VOODOO, MalumDamageTypes.VOID, MalumDamageTypes.KARMIC,
                        MalumDamageTypes.TYRVING, MalumDamageTypes.SUNDERING_ANCHOR_MAGIC_COMBO,
                        MalumDamageTypes.WARLOCK_SPIRIT_IMPACT, MalumDamageTypes.BERSERKER_SPIRIT_IMPACT)
                .addTag(MalumTags.DamageTypeTags.IS_INVERTED_HEART);

        tag(SOUL_SHATTER_DAMAGE)
                .addTags(MalumTags.DamageTypeTags.IS_SCYTHE, MalumTags.DamageTypeTags.IS_NITRATE, MalumTags.DamageTypeTags.IS_SUNDERING_ANCHOR_COMBO, MalumTags.DamageTypeTags.IS_INVERTED_HEART)
                .add(MalumDamageTypes.VOODOO_PLAYERLESS, MalumDamageTypes.VOODOO, MalumDamageTypes.TYRVING, MalumDamageTypes.WARLOCK_SPIRIT_IMPACT, MalumDamageTypes.BERSERKER_SPIRIT_IMPACT);

        tag(MalumTags.DamageTypeTags.IS_SCYTHE)
                .addTags(IS_SCYTHE_MELEE, MalumTags.DamageTypeTags.IS_HIDDEN_BLADE)
                .add(MalumDamageTypes.SCYTHE_REBOUND, MalumDamageTypes.SCYTHE_COMBO);
        tag(IS_SCYTHE_MELEE)
                .add(MalumDamageTypes.SCYTHE_MELEE, MalumDamageTypes.SCYTHE_SWEEP, MalumDamageTypes.SCYTHE_ASCENSION);

        tag(MalumTags.DamageTypeTags.IS_NITRATE)
                .add(MalumDamageTypes.NITRATE, MalumDamageTypes.NITRATE_PLAYERLESS);

        tag(MalumTags.DamageTypeTags.IS_HIDDEN_BLADE)
                .add(MalumDamageTypes.HIDDEN_BLADE_PHYSICAL_COUNTER, MalumDamageTypes.HIDDEN_BLADE_MAGIC_COUNTER);

        tag(MalumTags.DamageTypeTags.IS_SUNDERING_ANCHOR_COMBO)
                .add(MalumDamageTypes.SUNDERING_ANCHOR_PHYSICAL_COMBO, MalumDamageTypes.SUNDERING_ANCHOR_MAGIC_COMBO);

        tag(MalumTags.DamageTypeTags.IS_INVERTED_HEART)
                .add(MalumDamageTypes.INVERTED_HEART_PROPAGATION, MalumDamageTypes.INVERTED_HEART_RETALIATION);
        tag(MalumTags.DamageTypeTags.INVERTED_HEART_RETALIATION_BLACKLIST)
                .addTag(MalumTags.DamageTypeTags.IS_INVERTED_HEART).add(DamageTypes.GENERIC_KILL).addTag(Tags.DamageTypes.IS_TECHNICAL);
        tag(MalumTags.DamageTypeTags.INVERTED_HEART_PROPAGATION_BLACKLIST)
                .addTag(MalumTags.DamageTypeTags.IS_INVERTED_HEART).add(MalumDamageTypes.SCYTHE_SWEEP).addTag(Tags.DamageTypes.IS_TECHNICAL);

        tag(MalumTags.DamageTypeTags.GLEEFUL_TARGET_BLACKLIST).addTag(net.minecraft.tags.DamageTypeTags.BYPASSES_ARMOR);

        tag(BYPASSES_COOLDOWN)
                .addTags(MalumTags.DamageTypeTags.IS_HIDDEN_BLADE)
                .add(MalumDamageTypes.VOODOO, MalumDamageTypes.VOODOO_PLAYERLESS, MalumDamageTypes.VOID, MalumDamageTypes.KARMIC)
                .add(MalumDamageTypes.SCYTHE_MAELSTROM);
        tag(net.minecraft.tags.DamageTypeTags.NO_KNOCKBACK)
                .addTags(MalumTags.DamageTypeTags.IS_HIDDEN_BLADE, MalumTags.DamageTypeTags.IS_SUNDERING_ANCHOR_COMBO, MalumTags.DamageTypeTags.IS_INVERTED_HEART)
                .add(MalumDamageTypes.VOODOO, MalumDamageTypes.VOODOO_PLAYERLESS, MalumDamageTypes.VOID, MalumDamageTypes.KARMIC)
                .add(MalumDamageTypes.SCYTHE_MAELSTROM);
        tag(net.minecraft.tags.DamageTypeTags.IS_PLAYER_ATTACK)
                .addTag(IS_SCYTHE_MELEE);
        tag(DamageTypeTags.BYPASSES_SHIELD)
                .remove(MalumTags.DamageTypeTags.IS_SUNDERING_ANCHOR_COMBO);
    }

    @SafeVarargs
    public final void addTags(ResourceKey<DamageType> damageType, TagKey<DamageType>... tags) {
        addTags(damageType, Arrays.asList(tags));
    }
    @SafeVarargs
    public final void addManyTags(ResourceKey<DamageType> damageType, List<TagKey<DamageType>>... extraTags) {
        for (List<TagKey<DamageType>> extraTagList : extraTags) {
            for (TagKey<DamageType> extraTag : extraTagList) {
                this.tag(extraTag).add(damageType);
            }
        }
    }

    @SafeVarargs
    public final void addTags(ResourceKey<DamageType> damageType, List<TagKey<DamageType>> tags, TagKey<DamageType>... extraTags) {
        for (TagKey<DamageType> tag : tags) {
            this.tag(tag).add(damageType);
        }
        for (TagKey<DamageType> extraTag : extraTags) {
            this.tag(extraTag).add(damageType);
        }
    }

    @SafeVarargs
    public final void removeTags(ResourceKey<DamageType> damageType, TagKey<DamageType>... tags) {
        removeTags(damageType, Arrays.asList(tags));
    }
    @SafeVarargs
    public final void removeManyTags(ResourceKey<DamageType> damageType, List<TagKey<DamageType>>... extraTags) {
        for (List<TagKey<DamageType>> extraTagList : extraTags) {
            for (TagKey<DamageType> extraTag : extraTagList) {
                this.tag(extraTag).remove(damageType);
            }
        }
    }

    @SafeVarargs
    public final void removeTags(ResourceKey<DamageType> damageType, List<TagKey<DamageType>> tags, TagKey<DamageType>... extraTags) {
        for (TagKey<DamageType> tag : tags) {
            this.tag(tag).remove(damageType);
        }
        for (TagKey<DamageType> extraTag : extraTags) {
            this.tag(extraTag).remove(damageType);
        }
    }
}