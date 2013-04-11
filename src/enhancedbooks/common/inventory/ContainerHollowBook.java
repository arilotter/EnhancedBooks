package enhancedbooks.common.inventory;

import enhancedbooks.common.items.ItemHollowBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created with IntelliJ IDEA.
 * Date: 4/6/13
 * Time: 3:03 AM
 */
public class ContainerHollowBook extends Container {
    private ItemStack openedHollowBook;

    public ContainerHollowBook(IInventory playerInventory, IInventory hollowBookInventory, ItemStack hollowBook) {
        hollowBookInventory.openChest();

        // hollowBook
        this.addSlotToContainer(new Slot(hollowBookInventory, 0, 79, 32));

        this.bindPlayerInventory(playerInventory);

        openedHollowBook = hollowBook;
    }

    protected void bindPlayerInventory(IInventory inventoryPlayer) {

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    /**
     * True is the current equipped item is the opened item otherwise false.
     */
    @Override
    public boolean canInteractWith(EntityPlayer player) {

        if (player.getCurrentEquippedItem() != null) {
            if (player.getCurrentEquippedItem().isItemEqual(openedHollowBook)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Called when a player shift-clicks on a slot.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotPos) {
        ItemStack returnStack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotPos);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            if ((itemStack.getItem() instanceof ItemHollowBook)) {
                return returnStack;
            }
            returnStack = itemStack.copy();

            if (slotPos < 1) {
                if (!this.mergeItemStack(itemStack, 1, inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemStack, 0, 9, false)) {
                return null;
            }

            if (itemStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }

        return returnStack;
    }

}