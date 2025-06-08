package net.gaelfe18.extrasmelterythings.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record BasicFoundryBlockRecipe(Ingredient inputItem, ItemStack output) implements Recipe<BasicFoundryBlockRecipeInput> {
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(BasicFoundryBlockRecipeInput pInput, Level pLevel) {
       if (pLevel.isClientSide()) {
        return false;
        }

       return inputItem.test(pInput.getItem(0));
    }

    @Override
    public ItemStack assemble(BasicFoundryBlockRecipeInput pInput, HolderLookup.Provider pRegistries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.BASIC_FOUNDRY_BLOCK_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.BASIC_FOUNDRY_BLOCK_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<BasicFoundryBlockRecipe> {
        public static final MapCodec<BasicFoundryBlockRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(BasicFoundryBlockRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(BasicFoundryBlockRecipe::output)
        ).apply(inst, BasicFoundryBlockRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, BasicFoundryBlockRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, BasicFoundryBlockRecipe::inputItem,
                        ItemStack.STREAM_CODEC, BasicFoundryBlockRecipe::output,
                        BasicFoundryBlockRecipe::new);

        @Override
        public MapCodec<BasicFoundryBlockRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, BasicFoundryBlockRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
