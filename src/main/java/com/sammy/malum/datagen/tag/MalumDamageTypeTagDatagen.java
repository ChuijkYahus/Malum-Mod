package com.sammy.malum.datagen.tag;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.HolderLookup.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.world.damagesource.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.registry.common.tag.*;

import java.util.concurrent.*;

public class MalumDamageTypeTagDatagen extends DamageTypeTagsProvider {

    public MalumDamageTypeTagDatagen(PackOutput pOutput, CompletableFuture<Provider> pProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MalumMod.MALUM, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(Provider pProvider) {
        tag(LodestoneDamageTypeTags.CAN_TRIGGER_MAGIC)
                .add(MalumDataTypes.SCYTHE_MELEE, MalumDataTypes.SCYTHE_SWEEP);

        tag(LodestoneDamageTypeTags.IS_MAGIC)
                .add(MalumDataTypes.VOODOO_PLAYERLESS, MalumDataTypes.VOODOO, MalumDataTypes.VOID, MalumDataTypes.KARMIC,
                        MalumDataTypes.TYRVING,
                        MalumDataTypes.WARLOCK_SPIRIT_IMPACT, MalumDataTypes.BERSERKER_SPIRIT_IMPACT)
                .addTag(MalumTags.DamageTypeTags.IS_INVERTED_HEART);

        tag(MalumTags.DamageTypeTags.SOUL_SHATTER_DAMAGE)
                .addTags(MalumTags.DamageTypeTags.IS_SCYTHE, MalumTags.DamageTypeTags.IS_NITRATE, MalumTags.DamageTypeTags.IS_SUNDERING_ANCHOR_COMBO, MalumTags.DamageTypeTags.IS_INVERTED_HEART)
                .add(MalumDataTypes.VOODOO_PLAYERLESS, MalumDataTypes.VOODOO, MalumDataTypes.TYRVING, MalumDataTypes.WARLOCK_SPIRIT_IMPACT, MalumDataTypes.BERSERKER_SPIRIT_IMPACT);

        tag(MalumTags.DamageTypeTags.IS_SCYTHE)
                .addTags(MalumTags.DamageTypeTags.IS_SCYTHE_MELEE, MalumTags.DamageTypeTags.IS_HIDDEN_BLADE)
                .add(MalumDataTypes.SCYTHE_REBOUND, MalumDataTypes.SCYTHE_COMBO);
        tag(MalumTags.DamageTypeTags.IS_SCYTHE_MELEE)
                .add(MalumDataTypes.SCYTHE_MELEE, MalumDataTypes.SCYTHE_SWEEP, MalumDataTypes.SCYTHE_ASCENSION);

        tag(MalumTags.DamageTypeTags.IS_NITRATE)
                .add(MalumDataTypes.NITRATE, MalumDataTypes.NITRATE_PLAYERLESS);

        tag(MalumTags.DamageTypeTags.IS_HIDDEN_BLADE)
                .add(MalumDataTypes.HIDDEN_BLADE_PHYSICAL_COUNTER, MalumDataTypes.HIDDEN_BLADE_MAGIC_COUNTER);

        tag(MalumTags.DamageTypeTags.IS_SUNDERING_ANCHOR_COMBO)
                .add(MalumDataTypes.SUNDERING_ANCHOR_PHYSICAL_COMBO, MalumDataTypes.SUNDERING_ANCHOR_MAGIC_COMBO);

        tag(MalumTags.DamageTypeTags.IS_INVERTED_HEART)
                .add(MalumDataTypes.INVERTED_HEART_PROPAGATION, MalumDataTypes.INVERTED_HEART_RETALIATION);
        tag(MalumTags.DamageTypeTags.INVERTED_HEART_RETALIATION_BLACKLIST)
                .addTag(MalumTags.DamageTypeTags.IS_INVERTED_HEART).add(DamageTypes.GENERIC_KILL).addTag(Tags.DamageTypes.IS_TECHNICAL);
        tag(MalumTags.DamageTypeTags.INVERTED_HEART_PROPAGATION_BLACKLIST)
                .addTag(MalumTags.DamageTypeTags.IS_INVERTED_HEART).add(MalumDataTypes.SCYTHE_SWEEP).addTag(Tags.DamageTypes.IS_TECHNICAL);

        tag(MalumTags.DamageTypeTags.GLEEFUL_TARGET_BLACKLIST).addTag(net.minecraft.tags.DamageTypeTags.BYPASSES_ARMOR);

        tag(net.minecraft.tags.DamageTypeTags.BYPASSES_COOLDOWN)
                .addTags(MalumTags.DamageTypeTags.IS_HIDDEN_BLADE)
                .add(MalumDataTypes.VOODOO, MalumDataTypes.VOODOO_PLAYERLESS, MalumDataTypes.VOID, MalumDataTypes.KARMIC)
                .add(MalumDataTypes.SCYTHE_MAELSTROM);
        tag(net.minecraft.tags.DamageTypeTags.NO_KNOCKBACK)
                .addTags(MalumTags.DamageTypeTags.IS_HIDDEN_BLADE, MalumTags.DamageTypeTags.IS_SUNDERING_ANCHOR_COMBO, MalumTags.DamageTypeTags.IS_INVERTED_HEART)
                .add(MalumDataTypes.VOODOO, MalumDataTypes.VOODOO_PLAYERLESS, MalumDataTypes.VOID, MalumDataTypes.KARMIC)
                .add(MalumDataTypes.SCYTHE_MAELSTROM);
        tag(net.minecraft.tags.DamageTypeTags.IS_PLAYER_ATTACK)
                .addTag(MalumTags.DamageTypeTags.IS_SCYTHE_MELEE);

    }
}