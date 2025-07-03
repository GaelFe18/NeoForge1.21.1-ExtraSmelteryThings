package net.gaelfe18.extrasmelterythings.recipe;

import com.mojang.serialization.MapCodec;
import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.block.custom.AdvancedAlloyerBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, ExtraSmelteryThings.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, ExtraSmelteryThings.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BasicFoundryBlockRecipe>> BASIC_FOUNDRY_BLOCK_SERIALIZER =
            SERIALIZERS.register("basic_foundry_block", BasicFoundryBlockRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<BasicFoundryBlockRecipe>> BASIC_FOUNDRY_BLOCK_TYPE =
            TYPES.register("basic_foundry_block", () -> new RecipeType<BasicFoundryBlockRecipe>() {
                @Override
                public String toString() {
                    return "basic_foundry_block";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BasicAlloyerBlockRecipe>> BASIC_ALLOYER_BLOCK_SERIALIZER =
            SERIALIZERS.register("basic_alloyer_block", BasicAlloyerBlockRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<BasicAlloyerBlockRecipe>> BASIC_ALLOYER_BLOCK_TYPE =
            TYPES.register("basic_alloyer_block", () -> new RecipeType<BasicAlloyerBlockRecipe>() {
                @Override
                public String toString() {
                    return "basic_alloyer_block";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AdvancedAlloyerBlockRecipe>> ADVANCED_ALLOYER_BLOCK_SERIALIZER =
            SERIALIZERS.register("advanced_alloyer_block", AdvancedAlloyerBlockRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<AdvancedAlloyerBlockRecipe>> ADVANCED_ALLOYER_BLOCK_TYPE =
            TYPES.register("advanced_alloyer_block", () -> new RecipeType<AdvancedAlloyerBlockRecipe>() {
                @Override
                public String toString() {
                    return "advanced_alloyer_block";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
