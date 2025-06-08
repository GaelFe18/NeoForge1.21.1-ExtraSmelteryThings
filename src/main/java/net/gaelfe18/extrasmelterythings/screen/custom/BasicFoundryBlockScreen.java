package net.gaelfe18.extrasmelterythings.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BasicFoundryBlockScreen extends AbstractContainerScreen<BasicFoundryBlockMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ExtraSmelteryThings.MOD_ID,"textures/gui/basic_foundry_block/basic_foundry_block_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ExtraSmelteryThings.MOD_ID,"textures/gui/arrow_progress.png");
    private static final ResourceLocation FUEL_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ExtraSmelteryThings.MOD_ID,"textures/gui/fuel_progress.png");


    public BasicFoundryBlockScreen(BasicFoundryBlockMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(pGuiGraphics, x, y);
        renderProgressFuel(pGuiGraphics,x ,y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(ARROW_TEXTURE,x + 75, y + 31, 0, 0, menu.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    private void renderProgressFuel(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(FUEL_TEXTURE,x + 77, y + 59, 0, 0, menu.getScaledFuelProgress(),8 , 20, 8);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
