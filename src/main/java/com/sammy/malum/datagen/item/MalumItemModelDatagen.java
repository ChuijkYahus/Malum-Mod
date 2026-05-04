package com.sammy.malum.datagen.item;

import com.sammy.malum.*;
import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.datagen.MalumMetallicsDatagen;
import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.data.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.modules.datagen.providers.item.LodestoneItemModelSystem;
import team.lodestar.lodestone.modules.datagen.smith.itemmodel.ItemModelSystemData;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static com.sammy.malum.registry.common.MalumContent.Vanity.*;

public class MalumItemModelDatagen extends LodestoneItemModelSystem {

    public MalumItemModelDatagen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<Supplier<? extends Item>> items = new HashSet<>(MalumContent.ITEMS.getEntries());

        items.removeIf(i -> i.get() instanceof BlockItem);

        ItemModelSystemData data = new ItemModelSystemData(this, items::remove);;

        MalumItemModelSmithTypes.SOUL_OF_AN_ITEM.act(data, MalumContent.SOUL_OF_A_SCYTHE, MalumContent.SOUL_OF_THE_ANCHOR);
        setTexturePath("cosmetic/weaves/pride/");
        MalumItemModelSmithTypes.GENERATED_ITEM.act(data,
                ACE_PRIDEWEAVE, AGENDER_PRIDEWEAVE, ARO_PRIDEWEAVE, AROACE_PRIDEWEAVE, BI_PRIDEWEAVE,
                DEMIBOY_PRIDEWEAVE, DEMIGIRL_PRIDEWEAVE, ENBY_PRIDEWEAVE, GAY_PRIDEWEAVE, GENDERFLUID_PRIDEWEAVE,
                GENDERQUEER_PRIDEWEAVE, INTERSEX_PRIDEWEAVE, LESBIAN_PRIDEWEAVE, PAN_PRIDEWEAVE, PLURAL_PRIDEWEAVE,
                POLY_PRIDEWEAVE, PRIDE_PRIDEWEAVE, TRANS_PRIDEWEAVE
        );

        setTexturePath("runes/");
        MalumItemModelSmithTypes.GENERATED_ITEM.act(data, items.stream().filter(i -> i.get() instanceof AbstractRuneCurioItem).collect(Collectors.toList()));

        setTexturePath("alchemy/");
        MalumItemModelSmithTypes.IMPETUS_ITEM.act(data, MalumContent.AlchemyAndMetallics.ALCHEMICAL_IMPETUS, MalumContent.AlchemyAndMetallics.FRACTURED_ALCHEMICAL_IMPETUS);
        MalumItemModelSmithTypes.IMPETUS_ITEM.act(data, MalumContent.AlchemyAndMetallics.ZEPHYR_IMPETUS, MalumContent.AlchemyAndMetallics.FRACTURED_ZEPHYR_IMPETUS);
        MalumItemModelSmithTypes.IMPETUS_ITEM.act(data, MalumContent.AlchemyAndMetallics.IFRIT_IMPETUS, MalumContent.AlchemyAndMetallics.FRACTURED_IFRIT_IMPETUS);

        setTexturePath("alchemy/metallics/");
        MalumMetallicsDatagen.MALUM.addItemModels(data);

        setTexturePath("");

        MalumItemModelSmithTypes.BUILTIN_ENTITY_ITEM.act(data, MalumContent.GEAS, MalumContent.WAND);
        MalumItemModelSmithTypes.UMBRAL_SPIRIT_ITEM.act(data, MalumContent.Spirits.UMBRAL_SPIRIT);
        MalumItemModelSmithTypes.HANDHELD_OVERLAY_ITEM.act(data, MalumContent.Gear.UNWINDING_CHAOS, MalumContent.Gear.SUNDERING_ANCHOR).forEach(result -> result.addModelLayerData().emissive(15, 15, 1));
        MalumItemModelSmithTypes.LARGE_HANDHELD_ITEM.act(data, MalumContent.Gear.CRUDE_SCYTHE, MalumContent.Gear.SOUL_STAINED_STEEL_SCYTHE, MalumContent.Gear.RAVENOUS_SCYTHE, MalumContent.Gear.EDGE_OF_DELIVERANCE, MalumContent.Gear.WEIGHT_OF_WORLDS, MalumContent.DungeonGear.SHAPED_SLAB);
        MalumItemModelSmithTypes.HANDHELD_ITEM.act(data, MalumContent.Gear.SOUL_STAINED_STEEL_KNIFE, MalumContent.Gear.GLUTTONOUS_BLUDGEON, MalumContent.Focusing.TUNING_FORK, MalumContent.Gear.LAMPLIGHTERS_TONGS, MalumContent.Artifice.ARTIFICERS_CLAW, MalumContent.Totemancy.TOTEMIC_STAFF);
        MalumItemModelSmithTypes.HANDHELD_ITEM.act(data, MalumContent.Gear.MNEMONIC_HEX_STAFF, MalumContent.Gear.EROSION_SCEPTER);
        MalumItemModelSmithTypes.SPELLWEAVING_TOOL.act(data, MalumContent.Gear.SPELLWEAVING_PICKAXE, MalumContent.Gear.SPELLWEAVING_AXE);
        MalumItemModelSmithTypes.CATALYST_LOBBER.act(data, MalumContent.Gear.CATALYST_LOBBER);
        MalumItemModelSmithTypes.POUCH.act(data, MalumContent.Gear.SOULWOVEN_POUCH, MalumContent.Gear.RAVENOUS_POUCH);

        MalumItemModelSmithTypes.SPIRIT_ITEM.act(data, items.stream().filter(i -> i.get() instanceof SpiritShardItem).collect(Collectors.toList()));
        MalumItemModelSmithTypes.HANDHELD_ITEM.act(data, items.stream().filter(i -> i.get() instanceof DiggerItem).collect(Collectors.toList()));
        MalumItemModelSmithTypes.HANDHELD_ITEM.act(data, items.stream().filter(i -> i.get() instanceof SwordItem).collect(Collectors.toList()));

        MalumItemModelSmithTypes.SKIN_APPLICABLE_ARMOR_ITEM.act(data,
                MalumContent.Gear.SOUL_HUNTER_CLOAK, MalumContent.Gear.SOUL_HUNTER_ROBE, MalumContent.Gear.SOUL_HUNTER_LEGGINGS, MalumContent.Gear.SOUL_HUNTER_BOOTS,
                MalumContent.Gear.SOUL_STAINED_STEEL_HELMET, MalumContent.Gear.SOUL_STAINED_STEEL_CHESTPLATE, MalumContent.Gear.SOUL_STAINED_STEEL_LEGGINGS, MalumContent.Gear.SOUL_STAINED_STEEL_BOOTS,
                MalumContent.Gear.MALIGNANT_STRONGHOLD_HELMET, MalumContent.Gear.MALIGNANT_STRONGHOLD_CHESTPLATE, MalumContent.Gear.MALIGNANT_STRONGHOLD_LEGGINGS, MalumContent.Gear.MALIGNANT_STRONGHOLD_BOOTS);

        MalumItemModelSmithTypes.LARGE_GENERATED_ITEM.act(data, MalumContent.DungeonGear.IRON_CROWN);

        MalumItemModelSmithTypes.GENERATED_ITEM.act(data, items);
    }

    @Override
    public String getName() {
        return "Malum Item Models";
    }
}
