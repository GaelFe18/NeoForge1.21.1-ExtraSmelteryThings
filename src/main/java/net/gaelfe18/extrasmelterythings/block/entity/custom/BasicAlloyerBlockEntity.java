package net.gaelfe18.extrasmelterythings.block.entity.custom;


import net.gaelfe18.extrasmelterythings.block.entity.ModBlockEntities;
import net.gaelfe18.extrasmelterythings.recipe.BasicAlloyerBlockRecipe;
import net.gaelfe18.extrasmelterythings.recipe.BasicAlloyerBlockRecipeInput;
import net.gaelfe18.extrasmelterythings.recipe.ModRecipes;
import net.gaelfe18.extrasmelterythings.screen.custom.BasicAlloyerBlockMenu;
import net.gaelfe18.extrasmelterythings.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BasicAlloyerBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(4){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 4);
            }
        }
    };
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int INPUT_SLOT2 = 2;
    private static final int FUEL_SLOT = 3;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 160;
    private int currentFuel = 0;
    private int maxFuel = 1280;

    public BasicAlloyerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BASIC_ALLOYER_BLOCK_BE.get(), pPos, pBlockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> BasicAlloyerBlockEntity.this.progress;
                    case 1 -> BasicAlloyerBlockEntity.this.maxProgress;
                    case 2 -> BasicAlloyerBlockEntity.this.currentFuel;
                    case 3 -> BasicAlloyerBlockEntity.this.maxFuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: BasicAlloyerBlockEntity.this.progress = value;
                    case 1: BasicAlloyerBlockEntity.this.maxProgress = value;
                    case 2: BasicAlloyerBlockEntity.this.currentFuel = value;
                    case 3: BasicAlloyerBlockEntity.this.maxFuel = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("basic_alloyer.progress", progress);
        pTag.putInt("basic_alloyer.max_progress", maxProgress);
        pTag.putInt("basic_alloyer.current_fuel", currentFuel);
        pTag.putInt("basic_alloyer.max_fuel", maxFuel);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("basic_alloyer.progress");
        maxProgress = pTag.getInt("basic_alloyer.max_progress");
        currentFuel = pTag.getInt("basic_alloyer.current_fuel");
        maxFuel = pTag.getInt("basic_alloyer.max_fuel");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.extrasmelterythings.basic_alloyer_block");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new BasicAlloyerBlockMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState){
        ItemStack fuelSlotItemStack = itemHandler.getStackInSlot(FUEL_SLOT);
        if(usableFuelItemIsInFuelSlot(fuelSlotItemStack)) {
            cookTimeForEachFuel(fuelSlotItemStack);
        }

        if (hasRecipe() && hasFuel()){
            decreaseFuel();
            increaseCraftingProgress();
            setChanged(level, blockPos, blockState);

            if(hasCraftingFinished()){
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void cookTimeForEachFuel(ItemStack fuelSlotItemStack) {
        if(fuelSlotItemStack.is(ModTags.Items.LOWFUELITEMS) && currentFuel <= maxFuel -160) {
            itemHandler.extractItem(FUEL_SLOT, 1, false);
            currentFuel += 160;
        }
        if(fuelSlotItemStack.is(ModTags.Items.MIDFUELITEMS) && currentFuel <= maxFuel -320) {
            itemHandler.extractItem(FUEL_SLOT, 1, false);
            currentFuel += 320;
        }
        if(fuelSlotItemStack.is(ModTags.Items.HIGHFUELITEMS) && currentFuel <= maxFuel -640) {
            itemHandler.extractItem(FUEL_SLOT, 1, false);
            currentFuel += 640;
        }
    }

    private boolean usableFuelItemIsInFuelSlot(ItemStack fuelSlotItemStack) {
        return fuelSlotItemStack.is(ModTags.Items.LOWFUELITEMS) || fuelSlotItemStack.is(ModTags.Items.MIDFUELITEMS) || fuelSlotItemStack.is(ModTags.Items.HIGHFUELITEMS);
    }


    private void decreaseFuel() {
        currentFuel -= 1;
    }

    private boolean hasFuel() {
        return this.currentFuel > 0;
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 160;
    }

    private void craftItem() {
        Optional<RecipeHolder<BasicAlloyerBlockRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        itemHandler.extractItem(INPUT_SLOT, 1, false);
        itemHandler.extractItem(INPUT_SLOT2, 1, false);
        itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;

    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<BasicAlloyerBlockRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }

        ItemStack output = recipe.get().value().output();

        return canInsertItemIntoOutputSlot(output) && canInsertAmountIntoOutputSlot(output.getCount());
    }

    private Optional<RecipeHolder<BasicAlloyerBlockRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.BASIC_ALLOYER_BLOCK_TYPE.get(), new BasicAlloyerBlockRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT), itemHandler.getStackInSlot(INPUT_SLOT2)), level);
    }


    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

}