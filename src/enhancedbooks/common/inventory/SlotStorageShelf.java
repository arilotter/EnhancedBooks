package enhancedbooks.common.inventory;

import enhancedbooks.common.core.Utils;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 1/3/13
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SlotStorageShelf extends Slot {
    final ContainerStorageShelf linkedContainer;

    public SlotStorageShelf(ContainerStorageShelf bookshelf, IInventory par2IInventory, int slotNumber, int posX, int posY) {
        super(par2IInventory, slotNumber, posX, posY);
        this.linkedContainer = bookshelf;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        return isBook(par1ItemStack);
    }

    public int getSlotStackLimit() {
        return 1;
    }

    public static boolean isBook(ItemStack item) {
        Utils.log(item.getItemName());
        return item != null && (Utils.isBook(item.getItem()));

    }
}
