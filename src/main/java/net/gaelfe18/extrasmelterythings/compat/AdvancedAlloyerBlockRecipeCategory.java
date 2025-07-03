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
import net.gaelfe18.extrasmelterythings.recipe.AdvancedAlloyerBlockRecipe;
import net.gaelfe18.extrasmelterythings.recipe.BasicAlloyerBlockRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class AdvancedAlloyerBlockRecipeCategory implements IRecipeCategory<AdvancedAlloyerBlockRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(ExtraSmelteryThings.MOD_ID, "advanced_alloyer_block");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ExtraSmelteryThings.MOD_ID,
            "textures/gui/basic_alloyer_block/basic_alloyer_block_jei_recipe.png");

    public static final RecipeType<AdvancedAlloyerBlockRecipe> ADVANCED_ALLOYER_BLOCK_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, AdvancedAlloyerBlockRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public AdvancedAlloyerBlockRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 138, 56);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ADVANCED_ALLOYER_BLOCK));
    }

    @Override
    public RecipeType<AdvancedAlloyerBlockRecipe> getRecipeType() {
        return ADVANCED_ALLOYER_BLOCK_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.extrasmelterythings.advanced_alloyer_block");
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
    public void setRecipe(IRecipeLayoutBuilder builder, AdvancedAlloyerBlockRecipe recipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 7, 7).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 7).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 114, 7).addItemStack(recipe.getResultItem(null));
    }
}
