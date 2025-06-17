package net.gaelfe18.extrasmelterythings.datagen;

import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.gaelfe18.extrasmelterythings.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ExtraSmelteryThings.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.MOLTENCARBONSTEEL)
                .add(ModItems.MOLTEN_CARBON_STEEL.get());
        tag(ModTags.Items.MOLTENIRON)
                .add(ModItems.MOLTEN_IRON.get())
                .add(ModItems.SEMI_MOLTEN_IRON_SWORD_EDGE.get())
                .add(ModItems.MOLTEN_IRON_SWORD_EDGE.get());
        tag(ModTags.Items.MOLTENORES)
                .add(ModItems.MOLTEN_IRON.get())
                .add(ModItems.MOLTEN_CARBON_STEEL.get());
        tag(ModTags.Items.HIGHFUELITEMS)
                .add(ModItems.BURNING_COAL.get())
                .add(Items.LAVA_BUCKET);
        tag(ModTags.Items.MIDFUELITEMS)
                .add(ModItems.COKE.get());
        tag(ModTags.Items.LOWFUELITEMS)
                .add(Items.COAL)
                .add(Items.CHARCOAL);
    }

}
