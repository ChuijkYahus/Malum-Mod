package com.sammy.malum.registry.common.item;

import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("NullableProblems")
public enum MalumItemTiers implements Tier{
    ARCHAIC_SLATE(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 500, 4.5f, 3.5f, 4, MalumContent.Materials.CTHONIC_GOLD_FRAGMENT),
    SOUL_STAINED_STEEL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1250, 7.5f, 2.5f, 16, MalumContent.Materials.SOUL_STAINED_STEEL_INGOT),

    RAVENOUS(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1500, 7.5f, 2.5f, 24, MalumContent.Materials.GRIM_TALC),
    SPELLWEAVING_TOOLS(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1500, 5f, 3f, 24, MalumContent.Materials.CTHONIC_GOLD),
    MALIGNANT_ALLOY(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2500, 8f, 4f, 24, MalumContent.Materials.MALIGNANT_PEWTER_INGOT),
    HARNESSED_CHAOS(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2500, 8f, 2.5f, 24, MalumContent.Materials.FUSED_CONSCIOUSNESS),

    TYRVING(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1500, 8f, 1f, 16, MalumContent.BuildingBlocks.TWISTED_ROCK_SET.rock.block),
    HEX_STAFF(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1250, 8f, 2.5f, 16, MalumContent.Materials.MNEMONIC_FRAGMENT);
    
    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;
    
    MalumItemTiers(TagKey<Block> incorrectBlockForDrops, int uses, float speed, float damage, int enchantmentValue, ItemLike repairItem) {
        this.incorrectBlocksForDrops = incorrectBlockForDrops;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Ingredient.of(repairItem.asItem());
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
}
