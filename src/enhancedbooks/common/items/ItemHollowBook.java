package enhancedbooks.common.items;

import enhancedbooks.common.core.BooksCore;
import enhancedbooks.common.inventory.InventoryHollowBook;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * Date: 4/6/13
 * Time: 3:06 AM
 */
public class ItemHollowBook extends ItemBook {
    public ItemHollowBook(int i) {
        super(i);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("itemHollowBook");
        this.setMaxDamage(50);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("enhancedbooks:HollowBook");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        entityPlayer.openGui(BooksCore.instance, 2, world, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        return itemStack;
    }

    public static IInventory getHollowBookInv(EntityPlayer player) {
        ItemStack hollowBook;
        IInventory inventoryHollowBook = null;

        hollowBook = player.getCurrentEquippedItem();

        if (hollowBook != null && hollowBook.getItem() instanceof ItemHollowBook) {
            inventoryHollowBook = new InventoryHollowBook(player, hollowBook);
        }

        return inventoryHollowBook;
    }
}
