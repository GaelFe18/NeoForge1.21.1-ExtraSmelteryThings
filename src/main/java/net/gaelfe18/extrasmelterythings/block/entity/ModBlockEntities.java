package net.gaelfe18.extrasmelterythings.block.entity;

import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.block.ModBlocks;
import net.gaelfe18.extrasmelterythings.block.entity.custom.BasicAlloyerBlockEntity;
import net.gaelfe18.extrasmelterythings.block.entity.custom.BasicFoundryBlockEntity;
import net.gaelfe18.extrasmelterythings.block.entity.custom.BasicTankBlockEntity;
import net.gaelfe18.extrasmelterythings.block.entity.custom.MoldingForgeBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ExtraSmelteryThings.MOD_ID);

    public static final Supplier<BlockEntityType<BasicTankBlockEntity>> BASIC_TANK_BLOCK_BE =
            BLOCK_ENTITIES.register("basic_tank_block_be", () -> BlockEntityType.Builder.of(
                    BasicTankBlockEntity::new, ModBlocks.BASIC_TANK_BLOCK.get()).build(null));

    public static final Supplier<BlockEntityType<MoldingForgeBlockEntity>> MOLDING_FORGE_BLOCK_BE =
            BLOCK_ENTITIES.register("molding_forge_block_be", () -> BlockEntityType.Builder.of(
                    MoldingForgeBlockEntity::new, ModBlocks.MOLDING_FORGE_BLOCK.get()).build(null));

    public static final Supplier<BlockEntityType<BasicFoundryBlockEntity>> BASIC_FOUNDRY_BLOCK_BE =
            BLOCK_ENTITIES.register("basic_foundry_block_be", () -> BlockEntityType.Builder.of(
                    BasicFoundryBlockEntity::new, ModBlocks.BASIC_FOUNDRY_BLOCK.get()).build(null));

    public static final Supplier<BlockEntityType<BasicAlloyerBlockEntity>> BASIC_ALLOYER_BLOCK_BE =
            BLOCK_ENTITIES.register("basic_alloyer_block_be", () -> BlockEntityType.Builder.of(
                    BasicAlloyerBlockEntity::new, ModBlocks.BASIC_ALLOYER_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);

    }

}
