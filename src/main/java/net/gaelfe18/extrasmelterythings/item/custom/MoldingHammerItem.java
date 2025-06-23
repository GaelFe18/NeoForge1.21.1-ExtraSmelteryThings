package net.gaelfe18.extrasmelterythings.item.custom;

import net.gaelfe18.extrasmelterythings.component.ModDataComponents;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MoldingHammerItem extends Item {
    public MoldingHammerItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        ItemStack itemStackInHand = context.getItemInHand();
        String moldingType = itemStackInHand.get(ModDataComponents.MOLDING_TYPE);

        switch (moldingType) {
            case "Pickaxe" -> itemStackInHand.set(ModDataComponents.MOLDING_TYPE, "Sword");
            case "Sword" -> itemStackInHand.set(ModDataComponents.MOLDING_TYPE, "Axe");
            case "Axe" -> itemStackInHand.set(ModDataComponents.MOLDING_TYPE, "Shovel");
            case "Shovel" -> itemStackInHand.set(ModDataComponents.MOLDING_TYPE, "Hoe");
            case null, default -> itemStackInHand.set(ModDataComponents.MOLDING_TYPE, "Pickaxe");
        }

        assert context.getPlayer() != null;
        context.getPlayer().playSound(SoundEvents.EXPERIENCE_ORB_PICKUP,1F, 1F);
        return InteractionResult.SUCCESS;
    }

    void translate(ItemStack stack, List<Component> tooltipComponents){
        String getStackMode = stack.get(ModDataComponents.MOLDING_TYPE);
        switch (getStackMode){
            case "Sword" -> tooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.molding_hammer.mode_sword"));
            case "Axe" -> tooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.molding_hammer.mode_axe"));
            case "Shovel" -> tooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.molding_hammer.mode_shovel"));
            case "Hoe" -> tooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.molding_hammer.mode_hoe"));
            case null, default -> tooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.molding_hammer.mode_pickaxe"));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        translate(stack, tooltipComponents);
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.molding_hammer.shift_down"));
        }
        else tooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.press_shift"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
