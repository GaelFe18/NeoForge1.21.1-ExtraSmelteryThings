package net.gaelfe18.extrasmelterythings.datagen;


import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.block.ModBlocks;
import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.gaelfe18.extrasmelterythings.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ExtraSmelteryThings.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("dirty_raw_iron_from_iron_ore",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.IRON_ORE).build(),
                        LootItemRandomChanceCondition.randomChance(1f).build() }, ModItems.DIRTY_RAW_IRON.get()));
        this.add("dirty_raw_iron_from_deepslate_iron_ore",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DEEPSLATE_IRON_ORE).build(),
                        LootItemRandomChanceCondition.randomChance(1f).build()}, ModItems.DIRTY_RAW_IRON.get()));
        this.add("dirty_raw_iron_from_raw_iron_block",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.RAW_IRON_BLOCK).build(),
                        LootItemRandomChanceCondition.randomChance(1f).build()}, ModBlocks.DIRTY_RAW_IRON_BLOCK.get().asItem()));
    }
}