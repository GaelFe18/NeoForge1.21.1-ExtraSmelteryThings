package net.gaelfe18.extrasmelterythings.item;

import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.item.custom.CoolableItem;
import net.gaelfe18.extrasmelterythings.item.custom.FuelItem;
import net.gaelfe18.extrasmelterythings.item.custom.PolisherItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExtraSmelteryThings.MOD_ID);

    public static final DeferredItem<Item> ORES_POLISHER = ITEMS.register("ores_polisher",
        () -> new PolisherItem(new Item.Properties().durability(10).rarity(Rarity.COMMON)));

    public static final DeferredItem<Item> DIRTY_RAW_IRON = ITEMS.register("dirty_raw_iron",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MOLTEN_IRON = ITEMS.register("molten_iron",
        () -> new CoolableItem(new Item.Properties()));

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
                    else pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.coke"));

                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final DeferredItem<Item> BURNING_COAL = ITEMS.register("burning_coal",
            () -> new FuelItem(new Item.Properties(), 2400){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    if(Screen.hasShiftDown()){
                        pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.burning_coal.shift_down"));
                    }
                    else pTooltipComponents.add(Component.translatable("tooltip.extrasmelterythings.burning_coal"));

                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
    });


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
