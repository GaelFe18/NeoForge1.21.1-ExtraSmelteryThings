package net.gaelfe18.extrasmelterythings.item.custom;

import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.gaelfe18.extrasmelterythings.util.ModTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.List;
import java.util.Map;


public class CoolableItem extends Item{
    public CoolableItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof LivingEntity livingEntity) {
            ICuriosItemHandler curiosInventory = CuriosApi.getCuriosInventory(livingEntity).get();
            Map<String, ICurioStacksHandler> curios = curiosInventory.getCurios();
            ICurioStacksHandler slotInventory = curios.get("hands");
            // null check to ensure that the slot inventory exists
            if (slotInventory != null) {
                if (slotInventory.getStacks().getStackInSlot(0).getItem() == ModItems.BASIC_GLOVES.get()) {
                    damageItemStack(slotInventory);
                } else if (slotInventory.getStacks().getStackInSlot(0).getItem() == ModItems.ADVANCED_GLOVES.get()) {
                    damageItemStack(slotInventory);
                } else entity.lavaHurt();
            }
        }
    }

    void damageItemStack(ICurioStacksHandler slotInventory) {
        slotInventory.getStacks().getStackInSlot(0).setDamageValue(slotInventory.getStacks().getStackInSlot(0).getDamageValue() + 1);
        if (slotInventory.getStacks().getStackInSlot(0).getDamageValue() == slotInventory.getStacks().getStackInSlot(0).getMaxDamage()) {
            slotInventory.getStacks().getStackInSlot(0).setCount(slotInventory.getStacks().getStackInSlot(0).getCount() - 1);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(Blocks.WATER_CAULDRON == clickedBlock) {
            if(!level.isClientSide){
                ItemStack item = itemForEachCoolable(pContext);
                pContext.getPlayer().setItemInHand(InteractionHand.MAIN_HAND, item);
                ((ServerLevel) level).sendParticles(ParticleTypes.BUBBLE_POP, pContext.getClickedPos().getX() + 0.5F,
                        pContext.getClickedPos().getY() + 1F, pContext.getClickedPos().getZ() + 0.5F, 20, 0, 0,0,0.2);
            }
            pContext.getPlayer().playSound(SoundEvents.FIRE_EXTINGUISH,1F, 1F);
        }
        return InteractionResult.SUCCESS;
    }

    private ItemStack itemForEachCoolable(UseOnContext pContext) {
        int itemCount = pContext.getItemInHand().getCount();
        //Iron stuff:

        if(pContext.getItemInHand().is(ModTags.Items.MOLTENIRON)) {
            return new ItemStack(Items.IRON_INGOT, itemCount); }

        else if(pContext.getItemInHand().is(ModItems.SEMI_MOLTEN_IRON_HOE_HEAD.get())) {
            return new ItemStack(ModItems.IRON_MASS.get(), itemCount); }

        else if(pContext.getItemInHand().is(ModItems.MOLTEN_IRON_HOE_HEAD.get())) {
            return new ItemStack(ModItems.IRON_HOE_HEAD.get(), itemCount); }

        else if(pContext.getItemInHand().is(ModItems.SEMI_MOLTEN_IRON_SHOVEL_HEAD.get())) {
            return new ItemStack(ModItems.IRON_MASS.get(), itemCount); }

        else if(pContext.getItemInHand().is(ModItems.MOLTEN_IRON_SHOVEL_HEAD.get())) {
            return new ItemStack(ModItems.IRON_SHOVEL_HEAD.get(), itemCount); }

        else if(pContext.getItemInHand().is(ModItems.SEMI_MOLTEN_IRON_AXE_EDGE.get())) {
            return new ItemStack(ModItems.IRON_MASS.get(), itemCount); }

        else if(pContext.getItemInHand().is(ModItems.MOLTEN_IRON_AXE_EDGE.get())) {
            return new ItemStack(ModItems.IRON_AXE_EDGE.get(), itemCount); }

        else if(pContext.getItemInHand().is(ModItems.SEMI_MOLTEN_IRON_SWORD_EDGE.get())) {
            return new ItemStack(ModItems.IRON_MASS.get(), itemCount); }

        else if(pContext.getItemInHand().is(ModItems.MOLTEN_IRON_SWORD_EDGE.get())) {
            return new ItemStack(ModItems.IRON_SWORD_EDGE.get(), itemCount); }

        else if(pContext.getItemInHand().is(ModItems.SEMI_MOLTEN_IRON_PICKAXE_HEAD.get())) {
            return new ItemStack(ModItems.IRON_MASS.get(), itemCount); }

        else if(pContext.getItemInHand().is(ModItems.MOLTEN_IRON_PICKAXE_HEAD.get())) {
            return new ItemStack(ModItems.IRON_PICKAXE_HEAD.get(), itemCount); }

        //Carbon steel stuff:

        if(pContext.getItemInHand().is(ModTags.Items.MOLTENCARBONSTEEL)){
            return new ItemStack(ModItems.CARBON_STEEL.get(), itemCount);
        }
         return null;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.coolable_item.shift_down"));
        }
        else pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.press_shift"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

}
