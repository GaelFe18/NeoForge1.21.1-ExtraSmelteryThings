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

public record BasicAlloyerBlockRecipe(Ingredient inputItem, Ingredient inputItem2, ItemStack output) implements Recipe<BasicAlloyerBlockRecipeInput> {
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        list.add(inputItem2);
        return list;
    }

    @Override
    public boolean matches(BasicAlloyerBlockRecipeInput pInput, Level pLevel) {
       if (pLevel.isClientSide()) {
        return false;
        }
       return inputItem.test(pInput.getItem(0)) && inputItem2.test(pInput.getItem(1));
    }

    @Override
    public ItemStack assemble(BasicAlloyerBlockRecipeInput pInput, HolderLookup.Provider pRegistries) {
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
        return ModRecipes.BASIC_ALLOYER_BLOCK_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.BASIC_ALLOYER_BLOCK_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<BasicAlloyerBlockRecipe> {
        public static final MapCodec<BasicAlloyerBlockRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(BasicAlloyerBlockRecipe::inputItem),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient2").forGetter(BasicAlloyerBlockRecipe::inputItem2),
                ItemStack.CODEC.fieldOf("result").forGetter(BasicAlloyerBlockRecipe::output)).apply(inst, BasicAlloyerBlockRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, BasicAlloyerBlockRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, BasicAlloyerBlockRecipe::inputItem,
                        Ingredient.CONTENTS_STREAM_CODEC, BasicAlloyerBlockRecipe::inputItem2,
                        ItemStack.STREAM_CODEC, BasicAlloyerBlockRecipe::output,
                        BasicAlloyerBlockRecipe::new);

        @Override
        public MapCodec<BasicAlloyerBlockRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, BasicAlloyerBlockRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
