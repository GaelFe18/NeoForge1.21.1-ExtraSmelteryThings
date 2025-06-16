package net.gaelfe18.extrasmelterythings;

import net.gaelfe18.extrasmelterythings.block.ModBlocks;
import net.gaelfe18.extrasmelterythings.block.entity.ModBlockEntities;
import net.gaelfe18.extrasmelterythings.block.entity.renderer.MoldingForgeBlockEntityRenderer;
import net.gaelfe18.extrasmelterythings.item.ModCreativeTabs;
import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.gaelfe18.extrasmelterythings.loot.ModLootModifiers;
import net.gaelfe18.extrasmelterythings.recipe.ModRecipes;
import net.gaelfe18.extrasmelterythings.screen.ModMenuTypes;
import net.gaelfe18.extrasmelterythings.screen.custom.BasicAlloyerBlockScreen;
import net.gaelfe18.extrasmelterythings.screen.custom.BasicFoundryBlockScreen;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ExtraSmelteryThings.MOD_ID)
public class ExtraSmelteryThings {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "extrasmelterythings";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ExtraSmelteryThings(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.BASIC_FOUNDRY_BLOCK_MENU.get(), BasicFoundryBlockScreen::new);
            event.register(ModMenuTypes.BASIC_ALLOYER_BLOCK_MENU.get(), BasicAlloyerBlockScreen::new);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(ModBlockEntities.MOLDING_FORGE_BLOCK_BE.get(), MoldingForgeBlockEntityRenderer::new);
        }
    }
}
