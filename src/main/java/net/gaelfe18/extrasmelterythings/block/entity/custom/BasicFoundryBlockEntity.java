package net.gaelfe18.extrasmelterythings.block.entity.custom;


import net.gaelfe18.extrasmelterythings.block.entity.ModBlockEntities;
import net.gaelfe18.extrasmelterythings.recipe.BasicFoundryBlockRecipe;
import net.gaelfe18.extrasmelterythings.recipe.BasicFoundryBlockRecipeInput;
import net.gaelfe18.extrasmelterythings.recipe.ModRecipes;
import net.gaelfe18.extrasmelterythings.screen.custom.BasicFoundryBlockMenu;
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

public class BasicFoundryBlockEntity extends BlockEntity implements MenuProvider {
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
    private static final int FUEL_SLOT = 2;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 80;
    private int currentFuel = 0;
    private int maxFuel = 720;

    public BasicFoundryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BASIC_FOUNDRY_BLOCK_BE.get(), pPos, pBlockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> BasicFoundryBlockEntity.this.progress;
                    case 1 -> BasicFoundryBlockEntity.this.maxProgress;
                    case 2 -> BasicFoundryBlockEntity.this.currentFuel;
                    case 3 -> BasicFoundryBlockEntity.this.maxFuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: BasicFoundryBlockEntity.this.progress = value;
                    case 1: BasicFoundryBlockEntity.this.maxProgress = value;
                    case 2: BasicFoundryBlockEntity.this.progress = value;
                    case 3: BasicFoundryBlockEntity.this.maxProgress = value;
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
        pTag.putInt("basic_foundry.progress", progress);
        pTag.putInt("basic_foundry.max_progress", maxProgress);
        pTag.putInt("basic_foundry.current_fuel", currentFuel);
        pTag.putInt("basic_foundry.max_fuel", maxFuel);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("basic_foundry.progress");
        maxProgress = pTag.getInt("basic_foundry.max_progress");
        currentFuel = pTag.getInt("basic_foundry.current_fuel");
        maxFuel = pTag.getInt("basic_foundry.max_fuel");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.extrasmelterythings.basic_foundry_block");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new BasicFoundryBlockMenu(pContainerId, pPlayerInventory, this, this.data);
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
        if(fuelSlotItemStack.is(ModTags.Items.LOWFUELITEMS) && currentFuel <= maxFuel -80) {
            itemHandler.extractItem(FUEL_SLOT, 1, false);
            currentFuel += 80;
        }
        if(fuelSlotItemStack.is(ModTags.Items.MIDFUELITEMS) && currentFuel <= maxFuel -160) {
            itemHandler.extractItem(FUEL_SLOT, 1, false);
            currentFuel += 160;
        }
        if(fuelSlotItemStack.is(ModTags.Items.HIGHFUELITEMS) && currentFuel <= maxFuel -240) {
            itemHandler.extractItem(FUEL_SLOT, 1, false);
            currentFuel += 240;
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
        this.maxProgress = 80;
    }

    private void craftItem() {
        Optional<RecipeHolder<BasicFoundryBlockRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        itemHandler.extractItem(INPUT_SLOT, 1, false);
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
        Optional<RecipeHolder<BasicFoundryBlockRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }

        ItemStack output = recipe.get().value().output();

        return canInsertItemIntoOutputSlot(output) && canInsertAmountIntoOutputSlot(output.getCount());
    }

    private Optional<RecipeHolder<BasicFoundryBlockRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.BASIC_FOUNDRY_BLOCK_TYPE.get(), new BasicFoundryBlockRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT)), level);
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