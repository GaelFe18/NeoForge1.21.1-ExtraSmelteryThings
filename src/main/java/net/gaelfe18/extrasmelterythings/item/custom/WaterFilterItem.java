package net.gaelfe18.extrasmelterythings.item.custom;

import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class WaterFilterItem extends Item{
    public WaterFilterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(Blocks.WATER == clickedBlock) {
            assert pContext.getPlayer() != null;
            if (pContext.getPlayer().getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
                if (!level.isClientSide) {
                    ItemStack item = itemGenerator();
                    assert pContext.getPlayer() != null;
                    pContext.getPlayer().setItemInHand(InteractionHand.OFF_HAND, item);
                    pContext.getPlayer().getCooldowns().addCooldown(pContext.getItemInHand().getItem(), 100);
                    ((ServerLevel) level).sendParticles(ParticleTypes.DUST_PLUME, pContext.getClickedPos().getX() + 0.5F,
                            pContext.getClickedPos().getY() + 1F, pContext.getClickedPos().getZ() + 0.5F, 20, 0, 0, 0, 0.2);
                }
                assert pContext.getPlayer() != null;
                pContext.getPlayer().playSound(SoundEvents.FIRE_EXTINGUISH, 1F, 1F);
            }
        }
        return InteractionResult.SUCCESS;
    }

    private ItemStack itemGenerator(){
        int randomInt = (int)(Math.random() * 101);
        if(randomInt <= 35){
            return new ItemStack(Items.IRON_NUGGET, 1);
        }
        else if(randomInt <= 50){
            return new ItemStack(Items.IRON_NUGGET, 3);
        }
        else if(randomInt <= 65){
            return new ItemStack(ModItems.PLATINUM_NUGGET.get(), 1);
        }
        else if(randomInt <= 80){
            return new ItemStack(ModItems.PLATINUM_NUGGET.get(), 2);
        }
        else if(randomInt <= 90){
            return new ItemStack(ModItems.PLATINUM.get(), 1);
        }
        else if(randomInt <= 100){
            return new ItemStack(ModItems.PLATINUM.get(), 1);
        }
        return ItemStack.EMPTY;
    }
}
