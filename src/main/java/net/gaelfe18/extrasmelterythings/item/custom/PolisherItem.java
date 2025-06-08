package net.gaelfe18.extrasmelterythings.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class PolisherItem extends Item {
    public PolisherItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack returnVal = new ItemStack(this);
        returnVal.setDamageValue(itemStack.getDamageValue() + 1);
        if (returnVal.getDamageValue() >= returnVal.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return returnVal;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.ores_polisher.shift_down"));
        }
        else pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.ores_polisher"));

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
