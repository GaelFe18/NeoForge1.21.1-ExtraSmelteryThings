package net.gaelfe18.extrasmelterythings.item.custom;

import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.data.ISlotData;

import java.util.List;

public class CoolableItem extends Item {

    public CoolableItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(entity.fireImmune()){
        }
        else entity.lavaHurt();
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(Blocks.WATER_CAULDRON == clickedBlock) {
            if(!level.isClientSide){
                ItemStack item = itemForEachCoolable(pContext);
                pContext.getPlayer().setItemInHand(InteractionHand.MAIN_HAND, item);
                
            }
        }
        return InteractionResult.SUCCESS;
    }

    private ItemStack itemForEachCoolable(UseOnContext pContext) {
        int itemCount = pContext.getItemInHand().getCount();
        if(pContext.getItemInHand().is(ModItems.MOLTEN_IRON.get())) {
            return new ItemStack(Items.IRON_INGOT, itemCount);
        }

        if(pContext.getItemInHand().is(ModItems.MOLTEN_CARBON_STEEL.get())){
            return new ItemStack(ModItems.CARBON_STEEL.get(), itemCount);
        }
         return null;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.coolable_item.shift_down"));
        }
        else pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.coolable_item"));

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
