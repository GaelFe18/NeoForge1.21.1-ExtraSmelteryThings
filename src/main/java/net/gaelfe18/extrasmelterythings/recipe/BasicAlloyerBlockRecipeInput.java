package net.gaelfe18.extrasmelterythings.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record BasicAlloyerBlockRecipeInput(ItemStack input, ItemStack input2) implements RecipeInput {


    @Override
    public ItemStack getItem(int pIndex) {
        return switch (pIndex) {
            case 0 -> this.input;
            case 1 -> this.input2;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + pIndex);
        };
    }

        @Override
        public int size () {
            return 2;
        }

        @Override
        public boolean isEmpty () {
            return this.input.isEmpty() && this.input2.isEmpty();
        }
}