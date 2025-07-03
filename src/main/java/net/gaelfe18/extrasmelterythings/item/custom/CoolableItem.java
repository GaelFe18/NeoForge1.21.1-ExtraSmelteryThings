package net.gaelfe18.extrasmelterythings.item.custom;

import net.gaelfe18.extrasmelterythings.component.ModDataComponents;
import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.gaelfe18.extrasmelterythings.util.ModTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.Map;

public class CoolableItem extends Item {
    public CoolableItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
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

        if (Blocks.WATER_CAULDRON == clickedBlock) {
            if (!level.isClientSide) {
                ItemStack item = itemForEachCoolable(pContext);
                assert pContext.getPlayer() != null;
                assert item != null;
                pContext.getPlayer().setItemInHand(InteractionHand.MAIN_HAND, item);
                ((ServerLevel) level).sendParticles(ParticleTypes.BUBBLE_POP, pContext.getClickedPos().getX() + 0.5F,
                        pContext.getClickedPos().getY() + 1F, pContext.getClickedPos().getZ() + 0.5F, 20, 0, 0, 0, 0.2);
            }
            assert pContext.getPlayer() != null;
            pContext.getPlayer().playSound(SoundEvents.FIRE_EXTINGUISH, 1F, 1F);
        }
        return InteractionResult.SUCCESS;
    }

    private ItemStack itemForEachCoolable(UseOnContext pContext) {
        int itemCount = pContext.getItemInHand().getCount();

        //Iron stuff:

        if (pContext.getItemInHand().is(ModTags.Items.MOLTENIRON)) {
            return new ItemStack(Items.IRON_INGOT, itemCount);
        }

        else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_ARMOR_PARTS.get())) {
            return new ItemStack(ModItems.IRON_ARMOR_PARTS.get(), itemCount);
        }

        else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_BOOTS.get())) {
            ItemStack newItemstack = new ItemStack(Items.IRON_BOOTS, itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_LEGGINGS.get())) {
            ItemStack newItemstack = new ItemStack(Items.IRON_LEGGINGS, itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_CHESTPLATE.get())) {
            ItemStack newItemstack = new ItemStack(Items.IRON_CHESTPLATE, itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_HELMET.get())) {
            ItemStack newItemstack = new ItemStack(Items.IRON_HELMET, itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        }

        else if (pContext.getItemInHand().is(ModItems.SEMI_MOLTEN_IRON_HOE_HEAD.get())) {
            return new ItemStack(ModItems.IRON_MASS.get(), itemCount);
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_HOE_HEAD.get())) {
            ItemStack newItemstack = new ItemStack(ModItems.IRON_HOE_HEAD.get(), itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_HOE.get())) {
            ItemStack newItemstack = new ItemStack(Items.IRON_HOE, itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        }

        else if (pContext.getItemInHand().is(ModItems.SEMI_MOLTEN_IRON_SHOVEL_HEAD.get())) {
            return new ItemStack(ModItems.IRON_MASS.get(), itemCount);
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_SHOVEL_HEAD.get())) {
            ItemStack newItemstack = new ItemStack(ModItems.IRON_SHOVEL_HEAD.get(), itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_SHOVEL.get())) {
            ItemStack newItemstack = new ItemStack(Items.IRON_SHOVEL, itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        }

        else if (pContext.getItemInHand().is(ModItems.SEMI_MOLTEN_IRON_AXE_EDGE.get())) {
            return new ItemStack(ModItems.IRON_MASS.get(), itemCount);
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_AXE_EDGE.get())) {
            ItemStack newItemstack = new ItemStack(ModItems.IRON_AXE_EDGE.get(), itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_AXE.get())) {
            ItemStack newItemstack = new ItemStack(Items.IRON_AXE, itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        }

        else if (pContext.getItemInHand().is(ModItems.SEMI_MOLTEN_IRON_SWORD_EDGE.get())) {
            return new ItemStack(ModItems.IRON_MASS.get(), itemCount);
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_SWORD_EDGE.get())) {
            ItemStack newItemstack = new ItemStack(ModItems.IRON_SWORD_EDGE.get(), itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_SWORD.get())) {
            ItemStack newItemstack = new ItemStack(Items.IRON_SWORD, itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        }

        else if (pContext.getItemInHand().is(ModItems.SEMI_MOLTEN_IRON_PICKAXE_HEAD.get())) {
            return new ItemStack(ModItems.IRON_MASS.get(), itemCount);
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_PICKAXE_HEAD.get())) {
            ItemStack newItemstack = new ItemStack(ModItems.IRON_PICKAXE_HEAD.get(), itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        } else if (pContext.getItemInHand().is(ModItems.MOLTEN_IRON_PICKAXE.get())) {
            ItemStack newItemstack = new ItemStack(Items.IRON_PICKAXE, itemCount);
            newItemstack.set(ModDataComponents.QUALITY, qualityGenerator());
            newItemstack.set(DataComponents.MAX_DAMAGE, maxDamageModify(newItemstack));
            return newItemstack;
        }

        //Carbon steel stuff:

        if (pContext.getItemInHand().is(ModTags.Items.MOLTENCARBONSTEEL)) {
            return new ItemStack(ModItems.CARBON_STEEL.get(), itemCount);
        }
        return null;
    }

    private int maxDamageModify(ItemStack newItemstack){
        String getStackQuality = newItemstack.get(ModDataComponents.QUALITY);
        if (getStackQuality != null){
            switch(getStackQuality){
                case "Bad Forged" -> {
                    return newItemstack.getMaxDamage() - 200;
                }
                case "Mid Forged" -> {
                    return newItemstack.getMaxDamage() - 100;
                }
                case "Basic" -> {
                    return newItemstack.getMaxDamage();
                }
                case "Well Forged" -> {
                    return newItemstack.getMaxDamage() + 100;
                }
                case "Legendary" -> {
                    return newItemstack.getMaxDamage() + 200;
                }
            }
        }
        return newItemstack.getMaxDamage();
    }

    private String qualityGenerator(){
        int randomInt = (int)(Math.random() * 5);
        switch (randomInt) {
            case 0 -> {
                return "Bad Forged";
            }
            case 1 -> {
                return "Mid Forged";
            }
            case 2 -> {
                return "Basic";
            }
            case 3 -> {
                return "Well Forged";
            }
            case 4 -> {
                return "Legendary";
            }
        }
        return "Basic";
    }
}
