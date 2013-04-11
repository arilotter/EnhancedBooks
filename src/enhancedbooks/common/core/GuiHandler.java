package enhancedbooks.common.core;

import cpw.mods.fml.common.network.IGuiHandler;
import enhancedbooks.client.gui.GuiHollowBook;
import enhancedbooks.client.gui.GuiStorageShelf;
import enhancedbooks.client.gui.GuiXPBook;
import enhancedbooks.common.inventory.ContainerHollowBook;
import enhancedbooks.common.inventory.ContainerStorageShelf;
import enhancedbooks.common.items.ItemHollowBook;
import enhancedbooks.common.tileentities.TileEntityStorageShelf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 02/01/13
 * Time: 4:53 AM
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == 0) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity instanceof TileEntityStorageShelf) {
                return new ContainerStorageShelf(player.inventory, (TileEntityStorageShelf) tileEntity);
            }
        }
        if (id == 1) {
            // Do nothing :D
        }
        if (id == 2) {
            return new ContainerHollowBook(player.inventory, ItemHollowBook.getHollowBookInv(player), player.inventory.getCurrentItem());
        }
        return null;
    }

    // returns an instance of the gui thingamabob
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == 0) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity instanceof TileEntityStorageShelf) {
                return new GuiStorageShelf(player.inventory,
                        (TileEntityStorageShelf) tileEntity);
            }
        }
        if (id == 1) {
            return new GuiXPBook(player.inventory.getCurrentItem());
        }
        if (id == 2) {
            return new GuiHollowBook(player.inventory, ItemHollowBook.getHollowBookInv(player));
        }
        return null;
    }

}
