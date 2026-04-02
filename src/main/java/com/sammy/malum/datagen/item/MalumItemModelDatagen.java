package com.sammy.malum.datagen.item;

import com.sammy.malum.*;
import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.common.item.impetus.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.data.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.modules.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.modules.datagen.providers.item.LodestoneItemModelSystem;
import team.lodestar.lodestone.modules.datagen.smith.itemmodel.ItemModelSmith;
import team.lodestar.lodestone.modules.datagen.smith.itemmodel.ItemModelSystemData;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static com.sammy.malum.registry.common.item.MalumItems.*;

public class MalumItemModelDatagen extends LodestoneItemModelSystem {

    public MalumItemModelDatagen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<Supplier<? extends Item>> items = new HashSet<>(ITEMS.getEntries());

        items.removeIf(i -> i.get() instanceof BlockItem);

        ItemModelSystemData data = new ItemModelSystemData(this, items::remove);;

        MalumItemModelSmithTypes.SOUL_OF_AN_ITEM.act(data, SOUL_OF_A_SCYTHE, SOUL_OF_THE_ANCHOR);
        setTexturePath("cosmetic/weaves/pride/");
        MalumItemModelSmithTypes.GENERATED_ITEM.act(data,
                ACE_PRIDEWEAVE, AGENDER_PRIDEWEAVE, ARO_PRIDEWEAVE, AROACE_PRIDEWEAVE, BI_PRIDEWEAVE,
                DEMIBOY_PRIDEWEAVE, DEMIGIRL_PRIDEWEAVE, ENBY_PRIDEWEAVE, GAY_PRIDEWEAVE, GENDERFLUID_PRIDEWEAVE,
                GENDERQUEER_PRIDEWEAVE, INTERSEX_PRIDEWEAVE, LESBIAN_PRIDEWEAVE, PAN_PRIDEWEAVE, PLURAL_PRIDEWEAVE,
                POLY_PRIDEWEAVE, PRIDE_PRIDEWEAVE, TRANS_PRIDEWEAVE
        );
        setTexturePath("cosmetic/weaves/");
        MalumItemModelSmithTypes.GENERATED_ITEM.act(data,
                ANCIENT_WEAVE, CORNERED_WEAVE, MECHANICAL_WEAVE_V1, MECHANICAL_WEAVE_V2
        );

        setTexturePath("runes/");
        MalumItemModelSmithTypes.GENERATED_ITEM.act(data, items.stream().filter(i -> i.get() instanceof AbstractRuneCurioItem).collect(Collectors.toList()));

        setTexturePath("impetus/");
        MalumItemModelSmithTypes.IMPETUS_ITEM.act(data, items.stream().filter(i -> i.get() instanceof ImpetusItem ||
                i.get() instanceof FracturedImpetusItem).toList()); //TODO: make this cleaner :3
        MalumItemModelSmithTypes.GENERATED_ITEM.act(data, items.stream().filter(i -> i.get() instanceof NodeItem).collect(Collectors.toList()));

        setTexturePath("");

        MalumItemModelSmithTypes.BUILTIN_ENTITY_ITEM.act(data, GEAS);
        MalumItemModelSmithTypes.UMBRAL_SPIRIT_ITEM.act(data, UMBRAL_SPIRIT);
        MalumItemModelSmithTypes.HANDHELD_OVERLAY_ITEM.act(data, UNWINDING_CHAOS, SUNDERING_ANCHOR).forEach(result -> result.addModelLayerData().emissive(15, 15, 1));
        MalumItemModelSmithTypes.LARGE_HANDHELD_ITEM.act(data, CRUDE_SCYTHE, SOUL_STAINED_STEEL_SCYTHE, RAVENOUS_SCYTHE, EDGE_OF_DELIVERANCE, WEIGHT_OF_WORLDS, SHAPED_SLAB);
        MalumItemModelSmithTypes.HANDHELD_ITEM.act(data, SOUL_STAINED_STEEL_KNIFE, GLUTTONOUS_BLUDGEON, TUNING_FORK, LAMPLIGHTERS_TONGS, ARTIFICERS_CLAW, TOTEMIC_STAFF);
        MalumItemModelSmithTypes.HANDHELD_ITEM.act(data, MNEMONIC_HEX_STAFF, EROSION_SCEPTER);
        MalumItemModelSmithTypes.SPELLWEAVING_TOOL.act(data, SPELLWEAVING_PICKAXE, SPELLWEAVING_AXE);
        MalumItemModelSmithTypes.CATALYST_LOBBER.act(data, CATALYST_LOBBER);
        MalumItemModelSmithTypes.POUCH.act(data, SOULWOVEN_POUCH, RAVENOUS_POUCH);

        MalumItemModelSmithTypes.SPIRIT_ITEM.act(data, items.stream().filter(i -> i.get() instanceof SpiritShardItem).collect(Collectors.toList()));
        MalumItemModelSmithTypes.HANDHELD_ITEM.act(data, items.stream().filter(i -> i.get() instanceof DiggerItem).collect(Collectors.toList()));
        MalumItemModelSmithTypes.HANDHELD_ITEM.act(data, items.stream().filter(i -> i.get() instanceof SwordItem).collect(Collectors.toList()));

        MalumItemModelSmithTypes.SKIN_APPLICABLE_ARMOR_ITEM.act(data,
                SOUL_HUNTER_CLOAK, SOUL_HUNTER_ROBE, SOUL_HUNTER_LEGGINGS, SOUL_HUNTER_BOOTS,
                SOUL_STAINED_STEEL_HELMET, SOUL_STAINED_STEEL_CHESTPLATE, SOUL_STAINED_STEEL_LEGGINGS, SOUL_STAINED_STEEL_BOOTS,
                MALIGNANT_STRONGHOLD_HELMET, MALIGNANT_STRONGHOLD_CHESTPLATE, MALIGNANT_STRONGHOLD_LEGGINGS, MALIGNANT_STRONGHOLD_BOOTS);

        MalumItemModelSmithTypes.LARGE_GENERATED_ITEM.act(data, IRON_CROWN);

        MalumItemModelSmithTypes.GENERATED_ITEM.act(data, items);
    }

    @Override
    public String getName() {
        return "Malum Item Models";
    }
}
