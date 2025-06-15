package net.gaelfe18.extrasmelterythings.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;


public class GlovesItem extends Item implements ICurioItem {
    public GlovesItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return ICurioItem.super.canEquipFromUse(slotContext, stack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.gloves.shift_down"));
        } else pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.gloves"));

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
