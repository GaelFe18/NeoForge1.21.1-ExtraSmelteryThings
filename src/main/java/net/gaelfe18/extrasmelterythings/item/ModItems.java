package net.gaelfe18.extrasmelterythings.item;

import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.component.ModDataComponents;
import net.gaelfe18.extrasmelterythings.item.custom.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExtraSmelteryThings.MOD_ID);

    //Tools:

    public static final DeferredItem<Item> BASIC_WATER_FILTER = ITEMS.register("basic_water_filter",
            () -> new WaterFilterItem(new Item.Properties().durability(20)));

    public static final DeferredItem<Item> BASIC_GLOVES = ITEMS.register("basic_gloves",
            () -> new GlovesItem(new Item.Properties().durability(340)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.basic.tier"));

                    super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> ADVANCED_GLOVES = ITEMS.register("advanced_gloves",
            () -> new GlovesItem(new Item.Properties().durability(1000)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.advanced.tier"));

                    super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> BASIC_MOLDING_HAMMER = ITEMS.register("basic_molding_hammer",
            () -> new MoldingHammerItem(new Item.Properties().durability(6)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.basic.tier"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final DeferredItem<Item> ADVANCED_MOLDING_HAMMER = ITEMS.register("advanced_molding_hammer",
            () -> new MoldingHammerItem(new Item.Properties().durability(30)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.advanced.tier"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final DeferredItem<Item> ORES_POLISHER = ITEMS.register("ores_polisher",
            () -> new PolisherItem(new Item.Properties().durability(10)));

    //No category:


    public static final DeferredItem<Item> PLATINUM = ITEMS.register("platinum",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));


    //Iron stuff:

    public static final DeferredItem<Item> DIRTY_RAW_IRON = ITEMS.register("dirty_raw_iron",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SEMI_MOLTEN_IRON_ARMOR_PARTS = ITEMS.register("semi_molten_iron_armor_parts",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_ARMOR_PARTS = ITEMS.register("molten_iron_armor_parts",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> IRON_ARMOR_PARTS = ITEMS.register("iron_armor_parts",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MOLTEN_IRON_BOOTS = ITEMS.register("molten_iron_boots",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_LEGGINGS = ITEMS.register("molten_iron_leggings",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_CHESTPLATE = ITEMS.register("molten_iron_chestplate",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_HELMET = ITEMS.register("molten_iron_helmet",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_HOE_HEAD = ITEMS.register("molten_iron_hoe_head",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_HOE = ITEMS.register("molten_iron_hoe",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_SHOVEL_HEAD = ITEMS.register("molten_iron_shovel_head",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_SHOVEL = ITEMS.register("molten_iron_shovel",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_AXE_EDGE = ITEMS.register("molten_iron_axe_edge",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_AXE = ITEMS.register("molten_iron_axe",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_PICKAXE_HEAD = ITEMS.register("molten_iron_pickaxe_head",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_PICKAXE = ITEMS.register("molten_iron_pickaxe",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_SWORD_EDGE = ITEMS.register("molten_iron_sword_edge",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON_SWORD = ITEMS.register("molten_iron_sword",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> SEMI_MOLTEN_IRON_HOE_HEAD = ITEMS.register("semi_molten_iron_hoe_head",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> SEMI_MOLTEN_IRON_SHOVEL_HEAD = ITEMS.register("semi_molten_iron_shovel_head",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> SEMI_MOLTEN_IRON_AXE_EDGE = ITEMS.register("semi_molten_iron_axe_edge",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> SEMI_MOLTEN_IRON_PICKAXE_HEAD = ITEMS.register("semi_molten_iron_pickaxe_head",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> SEMI_MOLTEN_IRON_SWORD_EDGE = ITEMS.register("semi_molten_iron_sword_edge",
            () -> new CoolableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> IRON_HOE_HEAD = ITEMS.register("iron_hoe_head",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> IRON_SHOVEL_HEAD = ITEMS.register("iron_shovel_head",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> IRON_AXE_EDGE = ITEMS.register("iron_axe_edge",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> IRON_PICKAXE_HEAD = ITEMS.register("iron_pickaxe_head",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> IRON_SWORD_EDGE = ITEMS.register("iron_sword_edge",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> IRON_SWORD_HANDLE = ITEMS.register("iron_sword_handle",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MOLTEN_IRON = ITEMS.register("molten_iron",
            () -> new CoolableItem(new Item.Properties()));

    public static final DeferredItem<Item> IRON_MASS = ITEMS.register("iron_mass",
            () -> new Item(new Item.Properties()));

    //Carbon Steel stuff:

    public static final DeferredItem<Item> MOLTEN_CARBON_STEEL = ITEMS.register("molten_carbon_steel",
            () -> new CoolableItem(new Item.Properties()));

    public static final DeferredItem<Item> CARBON_STEEL = ITEMS.register("carbon_steel",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COKE = ITEMS.register("coke",
            () -> new FuelItem(new Item.Properties(), 2000){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    if(Screen.hasShiftDown()){
                        pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.coke.shift_down"));
                    }
                    else pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.press_shift"));

                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    //Fuels:

    public static final DeferredItem<Item> BURNING_COAL = ITEMS.register("burning_coal",
            () -> new FuelItem(new Item.Properties(), 2400){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    if(Screen.hasShiftDown()){
                        pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.burning_coal.shift_down"));
                    }
                    else pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.press_shift"));

                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
