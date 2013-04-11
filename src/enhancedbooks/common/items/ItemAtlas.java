package enhancedbooks.common.items;

import enhancedbooks.common.core.BooksCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 1/16/13
 * Time: 2:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class ItemAtlas extends ItemBook {

    public ItemAtlas(int id) {
        super(id);
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote)
            player.openGui(BooksCore.instance, 2, world, 0, 0, 0);
        return itemStack;
    }

    /**
     * If this function returns true (or the item is damageable), the ItemStack's NBT tag will be sent to the client.
     */
    public boolean getShareTag() {
        return true;
    }

    public static boolean validBookTagPages(NBTTagCompound par0NBTTagCompound) {
        if (par0NBTTagCompound == null) {
            return false;
        } else if (!par0NBTTagCompound.hasKey("pages")) {
            return false;
        } else {
            NBTTagList var1 = (NBTTagList) par0NBTTagCompound.getTag("pages");

            for (int var2 = 0; var2 < var1.tagCount(); ++var2) {
                NBTTagString var3 = (NBTTagString) var1.tagAt(var2);

                if (var3.data == null) {
                    return false;
                }

                if (var3.data.length() > 256) {
                    return false;
                }
            }

            return true;
        }
    }


}
