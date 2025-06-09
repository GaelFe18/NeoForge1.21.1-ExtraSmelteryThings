package net.gaelfe18.extrasmelterythings.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.gaelfe18.extrasmelterythings.block.ModBlocks;
import net.gaelfe18.extrasmelterythings.recipe.BasicFoundryBlockRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class BasicFoundryBlockRecipeCategory implements IRecipeCategory<BasicFoundryBlockRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(ExtraSmelteryThings.MOD_ID, "basic_foundry_block");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ExtraSmelteryThings.MOD_ID,
            "textures/gui/basic_foundry_block/basic_foundry_block_jei_recipe.png");

    public static final RecipeType<BasicFoundryBlockRecipe> BASIC_FOUNDRY_BLOCK_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, BasicFoundryBlockRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public BasicFoundryBlockRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 106, 56);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BASIC_FOUNDRY_BLOCK));
    }

    @Override
    public RecipeType<BasicFoundryBlockRecipe> getRecipeType() {
        return BASIC_FOUNDRY_BLOCK_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.extrasmelterythings.basic_foundry_block");
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BasicFoundryBlockRecipe recipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 7, 7).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 79, 11).addItemStack(recipe.getResultItem(null));
    }
}
