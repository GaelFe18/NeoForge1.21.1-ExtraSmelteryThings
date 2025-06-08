package net.gaelfe18.extrasmelterythings.datagen;

import net.gaelfe18.extrasmelterythings.block.ModBlocks;
import net.gaelfe18.extrasmelterythings.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIRTY_RAW_IRON_BLOCK.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', ModItems.DIRTY_RAW_IRON.get())
                .unlockedBy(getHasName(ModItems.DIRTY_RAW_IRON.get()), has(ModItems.DIRTY_RAW_IRON.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BASIC_ALLOYER_BLOCK.get())
                .pattern("SDS")
                .pattern("SFS")
                .pattern("III")
                .define('S', Items.STONE_BRICKS)
                .define('I', Items.IRON_BLOCK)
                .define('D', Items.DROPPER)
                .define('F', Items.BLAST_FURNACE)
                .unlockedBy(getHasName(Items.STONE_BRICKS), has(Items.STONE_BRICKS))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.DROPPER), has(Items.DROPPER))
                .unlockedBy(getHasName(Items.BLAST_FURNACE), has(Items.BLAST_FURNACE)).save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BASIC_FOUNDRY_BLOCK.get())
                .pattern("IDI")
                .pattern("BFB")
                .pattern("BBB")
                .define('B', Items.BRICKS)
                .define('I', Items.IRON_INGOT)
                .define('D', Items.DROPPER)
                .define('F', Items.BLAST_FURNACE)
                .unlockedBy(getHasName(Items.BRICKS), has(Items.BRICKS))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.DROPPER), has(Items.DROPPER))
                .unlockedBy(getHasName(Items.BLAST_FURNACE), has(Items.BLAST_FURNACE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BASIC_TANK_BLOCK.get())
                .pattern("CDC")
                .pattern("GGG")
                .pattern("CCC")
                .define('C', Items.COBBLESTONE)
                .define('G', Items.GLASS)
                .define('D', Items.DROPPER)
                .unlockedBy(getHasName(Items.COBBLESTONE), has(Items.COBBLESTONE))
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .unlockedBy(getHasName(Items.DROPPER), has(Items.DROPPER)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RAW_IRON,1)
                .requires(ModItems.DIRTY_RAW_IRON.get())
                .requires(ModItems.ORES_POLISHER.get())
                .unlockedBy(getHasName(ModItems.ORES_POLISHER.get()), has(ModItems.ORES_POLISHER.get()))
                .unlockedBy(getHasName(ModItems.DIRTY_RAW_IRON.get()), has(ModItems.DIRTY_RAW_IRON.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.IRON_NUGGET,3)
                .requires(Items.GRAVEL, 8)
                .requires(ModItems.ORES_POLISHER.get())
                .unlockedBy(getHasName(Items.GRAVEL), has(Items.GRAVEL))
                .unlockedBy(getHasName(ModItems.ORES_POLISHER.get()), has(ModItems.ORES_POLISHER.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GOLD_NUGGET,3)
                .requires(Items.SAND, 8)
                .requires(ModItems.ORES_POLISHER.get())
                .unlockedBy(getHasName(Items.GRAVEL), has(Items.GRAVEL))
                .unlockedBy(getHasName(ModItems.ORES_POLISHER.get()), has(ModItems.ORES_POLISHER.get())).save(recipeOutput);

    }
}
