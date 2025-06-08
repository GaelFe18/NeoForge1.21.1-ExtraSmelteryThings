package net.gaelfe18.extrasmelterythings.screen;

import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.screen.custom.BasicAlloyerBlockMenu;
import net.gaelfe18.extrasmelterythings.screen.custom.BasicFoundryBlockMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, ExtraSmelteryThings.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<BasicFoundryBlockMenu>> BASIC_FOUNDRY_BLOCK_MENU =
            registerMenuType("basic_foundry_block_menu", BasicFoundryBlockMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<BasicAlloyerBlockMenu>> BASIC_ALLOYER_BLOCK_MENU =
            registerMenuType("basic_alloyer_block_menu", BasicAlloyerBlockMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}