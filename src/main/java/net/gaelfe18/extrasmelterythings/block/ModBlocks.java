package net.gaelfe18.extrasmelterythings.block;

import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.block.custom.*;
import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ExtraSmelteryThings.MOD_ID);

    public static final DeferredBlock<Block> MOLTEN_GLASS_BLOCK = registerBlock("molten_glass_block",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.GLASS)));

    public static final DeferredBlock<Block> CARBON_STEEL_BLOCK = registerBlock("carbon_steel_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DIRTY_RAW_IRON_BLOCK = registerBlock("dirty_raw_iron_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MOLDING_FORGE_BLOCK = registerBlock("molding_forge_block",
            () -> new MoldingForgeBlock(BlockBehaviour.Properties.of().noOcclusion()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));

    public static final DeferredBlock<Block> BASIC_FOUNDRY_BLOCK = registerBlock("basic_foundry_block",
            () -> new BasicFoundryBlock(BlockBehaviour.Properties.of().noOcclusion()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BASIC_ALLOYER_BLOCK = registerBlock("basic_alloyer_block",
            () -> new BasicAlloyerBlock(BlockBehaviour.Properties.of().noOcclusion()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ADVANCED_ALLOYER_BLOCK = registerBlock("advanced_alloyer_block",
            () -> new AdvancedAlloyerBlock(BlockBehaviour.Properties.of().noOcclusion()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));

    public static final DeferredBlock<Block> BASIC_TANK_BLOCK = registerBlock("basic_tank_block",
            () -> new BasicTankBlock(BlockBehaviour.Properties.of().noOcclusion()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItems(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block) {
            ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));

    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);

    }

}
