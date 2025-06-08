package net.gaelfe18.extrasmelterythings.block.custom;


import com.mojang.serialization.MapCodec;
import net.gaelfe18.extrasmelterythings.block.entity.custom.BasicTankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BasicTankBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = BasicTankBlock.box(4,0, 4, 12, 12.5, 12);
    public static final MapCodec<BasicTankBlock> CODEC = simpleCodec(BasicTankBlock::new);

    public BasicTankBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BasicTankBlockEntity(pPos, pState);
    }

}