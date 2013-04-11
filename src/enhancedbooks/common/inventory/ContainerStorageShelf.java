package enhancedbooks.common.inventory;

import enhancedbooks.common.tileentities.TileEntityStorageShelf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 02/01/13
 * Time: 4:47 AM
 */
public class ContainerStorageShelf extends Container {

    protected TileEntityStorageShelf tileEntity;
    int slotNum = 0;

    public ContainerStorageShelf(InventoryPlayer inventoryPlayer, TileEntityStorageShelf te) {
        tileEntity = te;

        //the Slot constructor takes the IInventory and the slot number in that it binds to
        //and the x-y coordinates it resides on-screen
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.addSlotToContainer(new SlotStorageShelf(this, this.tileEntity, slotNum, 32 + j * 32, 23 + i * 32));
                ++slotNum;
            }
        }

        //commonly used vanilla code that adds the player's inventory
        this.bindPlayerInventory(inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }


    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 94 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 152));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer,
                                         int slotNum) {
        ItemStack stack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotNum);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();

            if (slotNum >= 0 && slotNum < 8) {
                // Put into player inv
                if (!mergeItemStack(slotStack, 8, inventorySlots.size(), true)) {
                    return null;
                }

                slot.onSlotChange(slotStack, stack);
            } else {
                if (SlotStorageShelf.isBook(slotStack)) {
                    if (!this.mergeItemStack(slotStack, 0, 8, false)) {
                        return null;
                    }
                }
            }

            if (slotStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.stackSize == stack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, slotStack);
        }

        return stack;
    }

    // Modified Version - Only merges for 1 stack
    @Override
    protected boolean mergeItemStack(ItemStack par1ItemStack, int startSlot, int endSlot, boolean reverse) {
        boolean success = false;
        int thisStartSlot;

        Slot currentSlot;
        ItemStack currentStack;

        if (par1ItemStack.stackSize > 0) {
            if (reverse) {
                thisStartSlot = endSlot - 1;
            } else {
                thisStartSlot = startSlot;
            }

            while (!reverse && thisStartSlot < endSlot || reverse && thisStartSlot >= startSlot) {
                currentSlot = (Slot) this.inventorySlots.get(thisStartSlot);
                currentStack = currentSlot.getStack();

                if (currentStack == null) {
                    currentSlot.putStack(par1ItemStack.copy());
                    currentSlot.getStack().stackSize = 1;
                    currentSlot.onSlotChanged();
                    par1ItemStack.stackSize -= 1;
                    success = true;
                    break;
                }

                if (reverse) {
                    --thisStartSlot;
                } else {
                    ++thisStartSlot;
                }
            }
        }

        return success;
    }

}