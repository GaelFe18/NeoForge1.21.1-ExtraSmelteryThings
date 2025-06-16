package net.gaelfe18.extrasmelterythings.block.custom;

import com.mojang.serialization.MapCodec;
import net.gaelfe18.extrasmelterythings.block.entity.custom.MoldingForgeBlockEntity;
import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.gaelfe18.extrasmelterythings.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MoldingForgeBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final MapCodec<MoldingForgeBlock> CODEC = simpleCodec(MoldingForgeBlock::new);
    public static final VoxelShape SHAPE = MoldingForgeBlock.box(3,0, 1, 13, 11, 15);
    public MoldingForgeBlock(Properties pProperties) {
        super(pProperties);
    }
    public static final VoxelShape SHAPE_WEST;
    public static final VoxelShape SHAPE_NORTH;
    public static final VoxelShape SHAPE_EAST;
    public static final VoxelShape SHAPE_SOUTH;

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
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(FACING)) {
            case NORTH -> {
                return SHAPE_NORTH;
            }
            case SOUTH -> {
                return SHAPE_SOUTH;
            }
            case EAST -> {
                return SHAPE_EAST;
            }
            case WEST -> {
                return SHAPE_WEST;
            }
            default -> {
                return SHAPE;
            }
        }
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MoldingForgeBlockEntity(pPos, pState);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()){
            if(level.getBlockEntity(pos) instanceof MoldingForgeBlockEntity moldingForgeBlockEntity){
                moldingForgeBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.getBlockEntity(pos) instanceof MoldingForgeBlockEntity moldingForgeBlockEntity){

            if (moldingForgeBlockEntity.inventory.getStackInSlot(0).isEmpty() && !stack.isEmpty() && stack.is(ModTags.Items.MOLTENORES)) {
                moldingForgeBlockEntity.inventory.insertItem(0, stack.copy(), false);
                stack.shrink(1);
                level.playSound(player, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.2f, 2.3f);
            }

            else if (stack.isEmpty()) {
                ItemStack stackOnMoldingForge = moldingForgeBlockEntity.inventory.extractItem(0, 1, false);
                player.setItemInHand(InteractionHand.MAIN_HAND, stackOnMoldingForge);
                moldingForgeBlockEntity.clearContents();
                level.playSound(player, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.2f, 1.5f);
            }

            else if (stack.getItem() == ModItems.BASIC_MOLDING_HAMMER.get()) {

                if (moldingForgeBlockEntity.inventory.getStackInSlot(0).is(ModTags.Items.MOLTENIRON)) {
                    moldingForgeBlockEntity.inventory.setStackInSlot(0, new ItemStack(Items.IRON_INGOT, 1));
                    anvilSound(level, player, pos);
                }

                if (moldingForgeBlockEntity.inventory.getStackInSlot(0).is(ModTags.Items.MOLTENCARBONSTEEL)) {
                    moldingForgeBlockEntity.inventory.setStackInSlot(0, new ItemStack(ModItems.CARBON_STEEL.get(), 1));
                    anvilSound(level, player, pos);
                }
            }
        }
        return ItemInteractionResult.SUCCESS;
    }

    void anvilSound(Level level, Player player, BlockPos pos) {level.playSound(player, pos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 0.5f, 1.7f);}

    static {
        SHAPE_WEST = MoldingForgeBlock.box(1,0, 3, 15, 11, 13);
        SHAPE_NORTH = MoldingForgeBlock.box(3,0, 1, 13, 11, 15);
        SHAPE_EAST = MoldingForgeBlock.box(1,0, 3, 15, 11, 13);
        SHAPE_SOUTH = MoldingForgeBlock.box(3,0, 1, 13, 11, 15);
    }
}
