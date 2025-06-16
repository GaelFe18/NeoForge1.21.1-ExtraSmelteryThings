package net.gaelfe18.extrasmelterythings.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.gaelfe18.extrasmelterythings.block.entity.custom.MoldingForgeBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class MoldingForgeBlockEntityRenderer implements BlockEntityRenderer<MoldingForgeBlockEntity> {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public MoldingForgeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(MoldingForgeBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = pBlockEntity.inventory.getStackInSlot(0);

        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.7f, 0.5f);
        pPoseStack.scale(0.5f, 0.5f, 0.5f);
        pPoseStack.rotateAround(Axis.XP.rotationDegrees(90f), 0f, 0f, 0f);
        switch (pBlockEntity.getBlockState().getValue(FACING)) {
            case EAST, WEST -> pPoseStack.rotateAround(Axis.ZN.rotationDegrees(0f), 0f, 0f, 0f);
            default -> pPoseStack.rotateAround(Axis.ZN.rotationDegrees(90f), 0f, 0f, 0f);
        }
        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(),
                pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
