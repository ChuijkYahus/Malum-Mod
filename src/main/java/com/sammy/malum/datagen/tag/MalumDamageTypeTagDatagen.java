package com.sammy.malum.datagen.tag;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.tag.*;
import net.minecraft.core.HolderLookup.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.tags.*;
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
                .add(DamageTypeRegistry.SCYTHE_MELEE, DamageTypeRegistry.SCYTHE_SWEEP);

        tag(LodestoneDamageTypeTags.IS_MAGIC)
                .add(DamageTypeRegistry.VOODOO_PLAYERLESS, DamageTypeRegistry.VOODOO, DamageTypeRegistry.VOID, DamageTypeRegistry.KARMIC,
                        DamageTypeRegistry.TYRVING,
                        DamageTypeRegistry.WARLOCK_SPIRIT_IMPACT, DamageTypeRegistry.BERSERKER_SPIRIT_IMPACT)
                .addTag(DamageTypeTagRegistry.IS_INVERTED_HEART);

        tag(DamageTypeTagRegistry.SOUL_SHATTER_DAMAGE)
                .addTags(DamageTypeTagRegistry.IS_SCYTHE, DamageTypeTagRegistry.IS_NITRATE, DamageTypeTagRegistry.IS_SUNDERING_ANCHOR_COMBO, DamageTypeTagRegistry.IS_INVERTED_HEART)
                .add(DamageTypeRegistry.VOODOO_PLAYERLESS, DamageTypeRegistry.VOODOO, DamageTypeRegistry.TYRVING, DamageTypeRegistry.WARLOCK_SPIRIT_IMPACT, DamageTypeRegistry.BERSERKER_SPIRIT_IMPACT);

        tag(DamageTypeTagRegistry.IS_SCYTHE)
                .addTags(DamageTypeTagRegistry.IS_SCYTHE_MELEE, DamageTypeTagRegistry.IS_HIDDEN_BLADE)
                .add(DamageTypeRegistry.SCYTHE_REBOUND, DamageTypeRegistry.SCYTHE_COMBO);
        tag(DamageTypeTagRegistry.IS_SCYTHE_MELEE)
                .add(DamageTypeRegistry.SCYTHE_MELEE, DamageTypeRegistry.SCYTHE_SWEEP, DamageTypeRegistry.SCYTHE_ASCENSION);

        tag(DamageTypeTagRegistry.IS_NITRATE)
                .add(DamageTypeRegistry.NITRATE, DamageTypeRegistry.NITRATE_PLAYERLESS);

        tag(DamageTypeTagRegistry.IS_HIDDEN_BLADE)
                .add(DamageTypeRegistry.HIDDEN_BLADE_PHYSICAL_COUNTER, DamageTypeRegistry.HIDDEN_BLADE_MAGIC_COUNTER);

        tag(DamageTypeTagRegistry.IS_SUNDERING_ANCHOR_COMBO)
                .add(DamageTypeRegistry.SUNDERING_ANCHOR_PHYSICAL_COMBO, DamageTypeRegistry.SUNDERING_ANCHOR_MAGIC_COMBO);

        tag(DamageTypeTagRegistry.IS_INVERTED_HEART)
                .add(DamageTypeRegistry.INVERTED_HEART_PROPAGATION, DamageTypeRegistry.INVERTED_HEART_RETALIATION);
        tag(DamageTypeTagRegistry.INVERTED_HEART_RETALIATION_BLACKLIST)
                .addTag(DamageTypeTagRegistry.IS_INVERTED_HEART).add(DamageTypes.GENERIC_KILL).addTag(Tags.DamageTypes.IS_TECHNICAL);
        tag(DamageTypeTagRegistry.INVERTED_HEART_PROPAGATION_BLACKLIST)
                .addTag(DamageTypeTagRegistry.IS_INVERTED_HEART).add(DamageTypeRegistry.SCYTHE_SWEEP).addTag(Tags.DamageTypes.IS_TECHNICAL);

        tag(DamageTypeTagRegistry.GLEEFUL_TARGET_BLACKLIST).addTag(DamageTypeTags.BYPASSES_ARMOR);

        tag(DamageTypeTags.BYPASSES_COOLDOWN)
                .addTags(DamageTypeTagRegistry.IS_HIDDEN_BLADE)
                .add(DamageTypeRegistry.VOODOO, DamageTypeRegistry.VOODOO_PLAYERLESS, DamageTypeRegistry.VOID, DamageTypeRegistry.KARMIC)
                .add(DamageTypeRegistry.SCYTHE_MAELSTROM);
        tag(DamageTypeTags.NO_KNOCKBACK)
                .addTags(DamageTypeTagRegistry.IS_HIDDEN_BLADE, DamageTypeTagRegistry.IS_SUNDERING_ANCHOR_COMBO, DamageTypeTagRegistry.IS_INVERTED_HEART)
                .add(DamageTypeRegistry.VOODOO, DamageTypeRegistry.VOODOO_PLAYERLESS, DamageTypeRegistry.VOID, DamageTypeRegistry.KARMIC)
                .add(DamageTypeRegistry.SCYTHE_MAELSTROM);
        tag(DamageTypeTags.IS_PLAYER_ATTACK)
                .addTag(DamageTypeTagRegistry.IS_SCYTHE_MELEE);

    }
}