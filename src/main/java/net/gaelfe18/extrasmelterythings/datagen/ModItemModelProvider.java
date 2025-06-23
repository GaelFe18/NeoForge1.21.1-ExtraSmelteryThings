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
        //Tools:

        basicItem(ModItems.ORES_POLISHER.get());

        //Iron stuff:

        basicItem(ModItems.DIRTY_RAW_IRON.get());
        basicItem(ModItems.MOLTEN_IRON.get());
        basicItem(ModItems.IRON_MASS.get());

        basicItem(ModItems.SEMI_MOLTEN_IRON_HOE_HEAD.get());
        basicItem(ModItems.MOLTEN_IRON_HOE_HEAD.get());
        basicItem(ModItems.IRON_HOE_HEAD.get());

        basicItem(ModItems.SEMI_MOLTEN_IRON_SHOVEL_HEAD.get());
        basicItem(ModItems.MOLTEN_IRON_SHOVEL_HEAD.get());
        basicItem(ModItems.IRON_SHOVEL_HEAD.get());

        basicItem(ModItems.SEMI_MOLTEN_IRON_AXE_EDGE.get());
        basicItem(ModItems.MOLTEN_IRON_AXE_EDGE.get());
        basicItem(ModItems.IRON_AXE_EDGE.get());

        basicItem(ModItems.SEMI_MOLTEN_IRON_SWORD_EDGE.get());
        basicItem(ModItems.MOLTEN_IRON_SWORD_EDGE.get());
        basicItem(ModItems.IRON_SWORD_EDGE.get());
        basicItem(ModItems.IRON_SWORD_HANDLE.get());

        basicItem(ModItems.SEMI_MOLTEN_IRON_PICKAXE_HEAD.get());
        basicItem(ModItems.MOLTEN_IRON_PICKAXE_HEAD.get());
        basicItem(ModItems.IRON_PICKAXE_HEAD.get());

        //Carbon steel stuff:

        basicItem(ModItems.COKE.get());
        basicItem(ModItems.CARBON_STEEL.get());
        basicItem(ModItems.MOLTEN_CARBON_STEEL.get());

        //Fuels:
        basicItem(ModItems.BURNING_COAL.get());




    }
}
