package enhancedbooks.client.gui;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 02/01/13
 * Time: 4:50 AM
 */

import enhancedbooks.common.core.Utils;
import enhancedbooks.common.inventory.ContainerStorageShelf;
import enhancedbooks.common.tileentities.TileEntityStorageShelf;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiStorageShelf extends GuiContainer {

    public GuiStorageShelf(InventoryPlayer inventoryPlayer, TileEntityStorageShelf tileEntity) {

        super(new ContainerStorageShelf(inventoryPlayer, tileEntity));
        this.ySize = 176;

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {

        fontRenderer.drawString("Bookshelf", 8, 6, 4210752);
        //draws "Inventory" or your regional equivalent
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(Utils.getResourcesDir() + "gui/GuiStorageShelf.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

}