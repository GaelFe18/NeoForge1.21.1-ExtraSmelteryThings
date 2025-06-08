package net.gaelfe18.extrasmelterythings.block.entity.custom;

import net.gaelfe18.extrasmelterythings.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BasicTankBlockEntity extends BlockEntity {

    public BasicTankBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BASIC_TANK_BLOCK_BE.get(), pPos, pBlockState);
    }
}
