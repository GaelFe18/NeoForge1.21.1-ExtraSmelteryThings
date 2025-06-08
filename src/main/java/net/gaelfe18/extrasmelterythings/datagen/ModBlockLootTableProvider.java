package net.gaelfe18.extrasmelterythings.datagen;

import net.gaelfe18.extrasmelterythings.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags() ,registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.BASIC_ALLOYER_BLOCK.get());
        this.dropSelf(ModBlocks.BASIC_FOUNDRY_BLOCK.get());
        this.dropSelf(ModBlocks.BASIC_TANK_BLOCK.get());
        dropSelf(ModBlocks.DIRTY_RAW_IRON_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
