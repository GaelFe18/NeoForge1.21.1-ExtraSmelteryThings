package net.gaelfe18.extrasmelterythings.item;

import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExtraSmelteryThings.MOD_ID);

    public static final Supplier<CreativeModeTab> EXTRASMELTERYTHINGS_ITEMS_TAB = CREATIVE_MOD_TAB.register("extrasmelterythings_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MOLTEN_IRON.get()))
                    .title(Component.translatable("creativetab.extrasmelterythings.extrasmelterythings_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //abajo el input del item
                        output.accept(ModItems.BASIC_GLOVES.get());
                        output.accept(ModItems.ADVANCED_GLOVES.get());
                        output.accept(ModItems.BASIC_MOLDING_HAMMER.get());
                        output.accept(ModItems.ORES_POLISHER.get());
                        output.accept(ModItems.CARBON_STEEL.get());
                        output.accept(ModItems.MOLTEN_CARBON_STEEL.get());
                        output.accept(ModItems.COKE.get());
                        output.accept(ModItems.MOLTEN_IRON_SWORD_EDGE.get());
                        output.accept(ModItems.SEMI_MOLTEN_IRON_SWORD_EDGE.get());
                        output.accept(ModItems.MOLTEN_IRON.get());
                        output.accept(ModItems.DIRTY_RAW_IRON.get());
                        output.accept(ModItems.BURNING_COAL.get());

                    }).build());


    public static final Supplier<CreativeModeTab> EXTRASMELTERYTHINGS_BLOCKS_TAB = CREATIVE_MOD_TAB.register("extrasmelterythings_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BASIC_FOUNDRY_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ExtraSmelteryThings.MOD_ID,"extrasmelterythings_items_tab"))
                    .title(Component.translatable("creativetab.extrasmelterythings.extrasmelterythings_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //abajo el input del bloque
                        output.accept(ModBlocks.MOLDING_FORGE_BLOCK.get());
                        output.accept(ModBlocks.BASIC_FOUNDRY_BLOCK.get());
                        output.accept(ModBlocks.BASIC_TANK_BLOCK.get());
                        output.accept(ModBlocks.BASIC_ALLOYER_BLOCK.get());
                        output.accept(ModBlocks.DIRTY_RAW_IRON_BLOCK.get());

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TAB.register(eventBus);

    }
}
