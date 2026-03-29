package com.sammy.malum.datagen.item;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.common.item.ether.EtherItem;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import team.lodestar.lodestone.helpers.DataHelper;
import team.lodestar.lodestone.modules.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.modules.datagen.smith.itemmodel.ItemModelSmith;
import team.lodestar.lodestone.modules.datagen.smith.itemmodel.ItemModelSmithResult;
import team.lodestar.lodestone.modules.toolkit.item.LodestoneArmorItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MalumItemModelSmithTypes extends ItemModelSmithTypes {

    protected static final ResourceLocation LARGE_HANDHELD = MalumMod.malumPath("item/handheld_large");
    protected static final ResourceLocation LARGE_GENERATED = MalumMod.malumPath("item/generated_large");
    protected static final Function<ResourceLocation, Consumer<ItemModelSmithResult>> LARGE_ITEM = modelType -> result -> {
        var provider = result.provider();
        var existingFileHelper = provider.existingFileHelper;
        var separateTransforms = result.addSeparateTransformData();
        var firstPersonModel = ItemModelSmith.parentedItem(modelType, false)
                .addModelPathAffix("_huge").addTextureNameAffix("_huge")
                .act(provider, result::item);
        var guiModel = ItemModelSmithTypes.GENERATED_ITEM
                .addModelPathAffix("_gui")
                .act(provider, result::item);
        var reparent = guiModel.parentedToThis(existingFileHelper);
        separateTransforms.perspective(ItemDisplayContext.GROUND, reparent);
        separateTransforms.perspective(ItemDisplayContext.GUI, reparent);
        separateTransforms.perspective(ItemDisplayContext.FIXED, reparent);
        separateTransforms.base(firstPersonModel.parentedToThis(existingFileHelper));
    };

    public static ItemModelSmith LARGE_HANDHELD_ITEM = HANDHELD_ITEM.modifyResult(LARGE_ITEM.apply(LARGE_HANDHELD));
    public static ItemModelSmith LARGE_GENERATED_ITEM = HANDHELD_ITEM.modifyResult(LARGE_ITEM.apply(LARGE_GENERATED));

    public static ItemModelSmith SOUL_OF_AN_ITEM = new ItemModelSmith((item, provider) -> provider.getBuilder(provider.getItemName(item)))
                    .modifyResult(result -> {
                        var provider = result.provider();
                        var existingFileHelper = provider.existingFileHelper;
                        var separateTransforms = result.addSeparateTransformData();
                        var guiModel = ItemModelSmithTypes.GENERATED_ITEM.addModelPathAffix("_gui").act(provider, result::item);
                        var reparent = guiModel.parentedToThis(existingFileHelper);
                        separateTransforms.perspective(ItemDisplayContext.GUI, reparent);
                        separateTransforms.perspective(ItemDisplayContext.FIXED, reparent);
                        separateTransforms.base(provider.getBuilder("item/air"));
                    });
    public static ItemModelSmith IMPETUS_ITEM = new ItemModelSmith((item, provider) -> {
        String name = provider.getItemName(item);
        List<String> split = DataHelper.reverseOrder(new ArrayList<>(), Arrays.asList(name.split("_")));
        split.removeFirst();
        String alteredName = String.join("_", split);
        return provider.createGenericModel(item, GENERATED, provider.getItemTexture(alteredName));
    });

    public static ItemModelSmith POUCH = new ItemModelSmith((item, provider) -> {
        String base = provider.getItemName(item);
        final ResourceLocation texture = provider.getItemTexture(base);
        provider.createGenericModel(item, GENERATED, texture);
        return provider.getBuilder(BuiltInRegistries.ITEM.getKey(item).getPath()).override()
                .predicate(MalumMod.malumPath("filled"), 1)
                .model(provider.withExistingParent(base + "_filled", HANDHELD).texture("layer0", texture.withSuffix("_filled")))
                .end();
    });

    public static ItemModelSmith SOULWOVEN_BANNER = new ItemModelSmith((item, provider) -> {
        String base = provider.getItemName(item);
        var model = provider.createGenericModel(item, GENERATED, provider.getItemTexture(base + "_default"));
        for (SoulwovenBannerPatternDataComponent pattern : SoulwovenBannerPatternDataComponent.REGISTERED_PATTERNS) {
            final int i = SoulwovenBannerPatternDataComponent.REGISTERED_PATTERNS.indexOf(pattern);
            if (pattern.equals(SoulwovenBannerPatternDataComponent.DEFAULT)) {
                continue;
            }
            final String path = base + "_" + pattern.type().getPath();
            ResourceLocation itemTexturePath = provider.getItemTexture(path);
            provider.getBuilder(BuiltInRegistries.ITEM.getKey(item).getPath()).override()
                    .predicate(MalumMod.malumPath("pattern"), i)
                    .model(provider.withExistingParent(path, GENERATED).texture("layer0", itemTexturePath))
                    .end();
        }
        return model;
    });

    public static ItemModelSmith CATALYST_LOBBER = new ItemModelSmith((item, provider) -> {
        String base = provider.getItemName(item);
        var model = provider.createGenericModel(item, HANDHELD, provider.getItemTexture(base));
        for (int i = 1; i <= 2; i++) {
            String affix = i == 1 ? "open" : "loaded";
            ResourceLocation itemTexturePath = provider.getItemTexture(base + "_" + affix);
            provider.getBuilder(BuiltInRegistries.ITEM.getKey(item).getPath()).override()
                    .predicate(MalumMod.malumPath("state"), i)
                    .model(provider.withExistingParent(base + "_" + affix, HANDHELD).texture("layer0", itemTexturePath))
                    .end();
        }
        return model;
    });

    public static ItemModelSmith SPELLWEAVING_TOOL = new ItemModelSmith((item, provider) -> {
        String base = provider.getItemName(item);
        var model = provider.createGenericModel(item, HANDHELD, provider.getItemTexture(base));
        var primed = base + "_primed";
        provider.getBuilder(BuiltInRegistries.ITEM.getKey(item).getPath()).override()
                .predicate(MalumMod.malumPath("primed"), 1)
                .model(provider.withExistingParent(primed, HANDHELD).texture("layer0", provider.getItemTexture(primed)))
                .end();
        return model;
    });

    public static ItemModelSmith UMBRAL_SPIRIT_ITEM = new ItemModelSmith((item, provider) -> provider.createGenericModel(item, GENERATED, provider.getItemTexture("umbral_spirit_shard")));

    public static ItemModelSmith SPIRIT_ITEM = new ItemModelSmith((item, provider) -> provider.createGenericModel(item, GENERATED, provider.getItemTexture("spirit_shard")));

    public static ItemModelSmith GENERATED_OVERLAY_ITEM = new ItemModelSmith((item, provider) -> {
        String name = provider.getItemName(item);
        return provider.withExistingParent(name, GENERATED).texture("layer0", provider.getItemTexture(name)).texture("layer1", provider.getItemTexture(name + "_overlay"));
    });

    public static ItemModelSmith HANDHELD_OVERLAY_ITEM = new ItemModelSmith((item, provider) -> {
        String name = provider.getItemName(item);
        return provider.withExistingParent(name, HANDHELD).texture("layer0", provider.getItemTexture(name)).texture("layer1", provider.getItemTexture(name + "_overlay"));
    });

    public static Function<String, ItemModelSmith> ETHER_CONTAINING_ITEM = Util.memoize(n -> new ItemModelSmith((item, provider) -> {
        boolean isIridescent = ((EtherItem) item).isIridescent;
        String name = provider.getItemName(item);
        int ether = name.indexOf(isIridescent ? "iridescent_ether" : "ether");
        String base = name.substring(0, ether);
        String containerName = base + n;
        String overlayName = n + "_overlay";
        if (isIridescent) {
            return provider.withExistingParent(name, GENERATED)
                    .texture("layer0", provider.getItemTexture(containerName))
                    .texture("layer1", provider.getItemTexture("iridescent_" + n))
                    .texture("layer2", provider.getItemTexture("iridescent_" + overlayName));
        }
        return provider.withExistingParent(name, GENERATED)
                .texture("layer0", provider.getItemTexture(containerName))
                .texture("layer1", provider.getItemTexture(overlayName));
    }));

    public static ItemModelSmith SKIN_APPLICABLE_ARMOR_ITEM = new ItemModelSmith((item, provider) -> {
        String name = provider.getItemName(item);
        var model = provider.createGenericModel(item, GENERATED, provider.getItemTexture(name));
        for (ItemSkinComponent registeredSkin : ItemSkinComponent.REGISTERED_SKINS) {
            final int index = registeredSkin.id();
            var armorItem = (LodestoneArmorItem) item;
            var itemTexture = index < 18 ? getDefaultPrideTexturePath(registeredSkin, armorItem) : getDefaultTexturePath(registeredSkin, armorItem);
            var split = itemTexture.getPath().split("/");
            var modelName = split[split.length - 1];
            provider.getBuilder(BuiltInRegistries.ITEM.getKey(item).getPath()).override()
                    .predicate(MalumMod.malumPath("item_skin"), index)
                    .model(provider.withExistingParent(modelName, GENERATED).texture("layer0", itemTexture))
                    .end();
        }
        return model;
    });


    public static ItemModelSmith WEEPING_WELL_BLOCK_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        return provider.getBuilder(name).parent(new ModelFile.UncheckedModelFile(provider.modLoc("block/weeping_well/" + name)));
    }));
    public static ItemModelSmith LAYERED_WEEPING_WELL_BLOCK_ITEM = new ItemModelSmith(((item, provider) -> {
        String name = provider.getItemName(item);
        return provider.getBuilder(name).parent(new ModelFile.UncheckedModelFile(provider.modLoc("block/weeping_well/" + name + "_0")));
    }));


    public static ResourceLocation getDefaultPrideTexturePath(ItemSkinComponent skin, LodestoneArmorItem item) {
        ResourceLocation path = MalumMod.malumPath("item/cosmetic/armor_icons/pride/" + skin.name().getPath());
        switch (item.getEquipmentSlot()) {
            case HEAD -> {
                return path.withSuffix("_beanie");
            }
            case CHEST -> {
                return path.withSuffix("_hoodie");
            }
            case LEGS -> {
                return path.withSuffix("_shorts");
            }
            case FEET -> {
                return path.withSuffix("_socks");
            }
        }
        return null;
    }

    public static ResourceLocation getDefaultTexturePath(ItemSkinComponent skin, LodestoneArmorItem item) {
        ResourceLocation path = MalumMod.malumPath("item/cosmetic/armor_icons/" + skin.name().getPath());
        switch (item.getEquipmentSlot()) {
            case HEAD -> {
                return path.withSuffix("_head");
            }
            case CHEST -> {
                return path.withSuffix("_body");
            }
            case LEGS -> {
                return path.withSuffix("_legs");
            }
            case FEET -> {
                return path.withSuffix("_feet");
            }
        }
        return null;
    }
}