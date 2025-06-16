package net.gaelfe18.extrasmelterythings.datagen;

import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExtraSmelteryThings.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BURNING_COAL.get());
        basicItem(ModItems.COKE.get());
        basicItem(ModItems.CARBON_STEEL.get());
        basicItem(ModItems.MOLTEN_CARBON_STEEL.get());
        basicItem(ModItems.MOLTEN_IRON.get());
        basicItem(ModItems.DIRTY_RAW_IRON.get());
        basicItem(ModItems.ORES_POLISHER.get());
    }
}
