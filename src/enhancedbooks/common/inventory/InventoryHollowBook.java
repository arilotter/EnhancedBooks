package enhancedbooks.common.inventory;

/**
 * Created with IntelliJ IDEA.
 * Date: 4/6/13
 * Time: 3:08 AM
 */

import enhancedbooks.common.core.NBTUtil;
import enhancedbooks.common.items.ItemHollowBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryHollowBook extends InventoryBasic {
    // the title of the hollowBook
    private String inventoryTitle;

    // an instance of the player to get the inventory
    private EntityPlayer playerEntity;
    // the original ItemStack to compare with the player inventory
    private ItemStack originalIS;

    // if class is reading from NBT tag
    private boolean reading = false;

    /**
     * Takes a player and an ItemStack.
     *
     * @param player The player which has the hollowBook.
     * @param is     The ItemStack which holds the hollowBook.
     */
    public InventoryHollowBook(EntityPlayer player, ItemStack is) {
        super("", false, getInventorySize(is));

        playerEntity = player;
        originalIS = is;

        // check if inventory exists if not create one
        if (!hasInventory()) {
            createInventory();
        }

        loadInventory();
    }

    /**
     * Is called whenever something is changed in the inventory.
     */
    @Override
    public void onInventoryChanged() {
        super.onInventoryChanged();
        // if reading from NBT don't write
        if (!reading) {
            saveInventory();
        }
    }

    /**
     * This method is called when the chest opens the inventory. It loads the
     * content of the inventory and its title.
     */
    @Override
    public void openChest() {
        loadInventory();
    }

    /**
     * This method is called when the chest closes the inventory. It then throws
     * out every hollowBook which is inside the hollowBook and saves the inventory.
     */
    @Override
    public void closeChest() {
        saveInventory();
    }

    /**
     * Returns the name of the inventory.
     */
    @Override
    public String getInvName() {
        return this.inventoryTitle;
    }

    // ***** custom methods which are not in IInventory *****

    /**
     * Returns the size of the inventory based on the ItemStack.
     *
     * @param is The ItemStack to check for the size.
     * @return The number of slots the inventory has.
     */
    protected static int getInventorySize(ItemStack is) {
        return 1;
    }

    /**
     * Returns if an Inventory is saved in the NBT.
     *
     * @return True when the NBT is not null and the NBT has key "Inventory"
     *         otherwise false.
     */
    private boolean hasInventory() {
        return NBTUtil.hasTag(originalIS, "Inventory");
    }

    /**
     * Creates the Inventory Tag in the NBT with an empty inventory.
     */
    private void createInventory() {
        setInvName(originalIS.getDisplayName());
        writeToNBT();
    }

    /**
     * Sets the name of the inventory.
     *
     * @param name The new name.
     */
    public void setInvName(String name) {
        this.inventoryTitle = name;
    }

    /**
     * Searches the hollowBook in players inventory and saves NBT data in it.
     */
    private void setNBT() {
        if (playerEntity.getCurrentEquippedItem() != null) {
            if (playerEntity.getCurrentEquippedItem().getItem() instanceof ItemHollowBook) {
                playerEntity.getCurrentEquippedItem().setTagCompound(originalIS.getTagCompound());
            }
        }
    }

    /**
     * If there is no inventory create one. Then load the content and title of
     * the inventory from the NBT
     */
    public void loadInventory() {
        readFromNBT();
    }

    /**
     * Saves the actual content of the inventory to the NBT.
     */
    public void saveInventory() {
        writeToNBT();
        setNBT();
    }

    /**
     * Writes a NBT Node with inventory.
     *
     * @return The written NBT Node.
     */
    private void writeToNBT() {
        // save name in display->Name	
        NBTTagCompound name = new NBTTagCompound();
        name.setString("Name", getInvName());
        NBTUtil.setCompoundTag(originalIS, "display", name);

        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != null) {
                NBTTagCompound slotEntry = new NBTTagCompound();
                slotEntry.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(slotEntry);
                itemList.appendTag(slotEntry);
            }
        }
        // save content in Inventory->Items
        NBTTagCompound inventory = new NBTTagCompound();
        inventory.setTag("Items", itemList);
        NBTUtil.setCompoundTag(originalIS, "Inventory", inventory);
        //return outerTag;
    }

    /**
     * Reads the inventory from a NBT Node.
     */
    private void readFromNBT() {
        reading = true;
        if (NBTUtil.getCompoundTag(originalIS, "Inventory").hasKey("title")) {
            setInvName(NBTUtil.getCompoundTag(originalIS, "Inventory").getString("title"));
        } else {
            setInvName(NBTUtil.getCompoundTag(originalIS, "display").getString("Name"));
        }

        NBTTagList itemList = NBTUtil.getCompoundTag(originalIS, "Inventory").getTagList("Items");
        for (int i = 0; i < itemList.tagCount(); i++) {
            NBTTagCompound slotEntry = (NBTTagCompound) itemList.tagAt(i);
            int j = slotEntry.getByte("Slot") & 0xff;

            if (j >= 0 && j < getSizeInventory()) {
                setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotEntry));
            }
        }
        reading = false;
    }
}