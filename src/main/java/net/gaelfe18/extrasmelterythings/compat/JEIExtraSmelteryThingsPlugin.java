package net.gaelfe18.extrasmelterythings.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.block.ModBlocks;
import net.gaelfe18.extrasmelterythings.recipe.BasicAlloyerBlockRecipe;
import net.gaelfe18.extrasmelterythings.recipe.BasicFoundryBlockRecipe;
import net.gaelfe18.extrasmelterythings.recipe.ModRecipes;
import net.gaelfe18.extrasmelterythings.screen.custom.BasicAlloyerBlockScreen;
import net.gaelfe18.extrasmelterythings.screen.custom.BasicFoundryBlockScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class JEIExtraSmelteryThingsPlugin implements IModPlugin {

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(ExtraSmelteryThings.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new BasicFoundryBlockRecipeCategory
                (registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(new BasicAlloyerBlockRecipeCategory
                (registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<BasicFoundryBlockRecipe> basicFoundryBlockRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.BASIC_FOUNDRY_BLOCK_TYPE.get()).stream()
                .map(RecipeHolder::value).toList();

        registration.addRecipes(BasicFoundryBlockRecipeCategory.BASIC_FOUNDRY_BLOCK_RECIPE_RECIPE_TYPE, basicFoundryBlockRecipes);

        List<BasicAlloyerBlockRecipe> basicAlloyerBlockRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.BASIC_ALLOYER_BLOCK_TYPE.get()).stream()
                .map(RecipeHolder::value).toList();

        registration.addRecipes(BasicAlloyerBlockRecipeCategory.BASIC_ALLOYER_BLOCK_RECIPE_RECIPE_TYPE, basicAlloyerBlockRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(BasicFoundryBlockScreen.class, 72, 29, 30 , 19,
                BasicFoundryBlockRecipeCategory.BASIC_FOUNDRY_BLOCK_RECIPE_RECIPE_TYPE);

        registration.addRecipeClickArea(BasicAlloyerBlockScreen.class, 99, 29, 30 , 19,
                BasicAlloyerBlockRecipeCategory.BASIC_ALLOYER_BLOCK_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BASIC_FOUNDRY_BLOCK.get().asItem()),
                BasicFoundryBlockRecipeCategory.BASIC_FOUNDRY_BLOCK_RECIPE_RECIPE_TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BASIC_ALLOYER_BLOCK.get().asItem()),
                BasicAlloyerBlockRecipeCategory.BASIC_ALLOYER_BLOCK_RECIPE_RECIPE_TYPE);
    }
}
