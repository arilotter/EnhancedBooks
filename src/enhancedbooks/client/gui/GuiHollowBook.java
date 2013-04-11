package enhancedbooks.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbooks.common.core.Utils;
import enhancedbooks.common.inventory.ContainerHollowBook;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created with IntelliJ IDEA.
 * Date: 4/6/13
 * Time: 3:15 AM
 */
@SideOnly(Side.CLIENT)
public class GuiHollowBook extends GuiContainer {
    private IInventory upperInventory;
    private IInventory lowerInventory;

    public GuiHollowBook(IInventory inventoryPlayer, IInventory inventoryHollowBook) {
        super(new ContainerHollowBook(inventoryPlayer, inventoryHollowBook, null));
        upperInventory = inventoryHollowBook;
        lowerInventory = inventoryPlayer;
        this.ySize = 176;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(StatCollector.translateToLocal(upperInventory.getInvName()), 8, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal(lowerInventory.getInvName()), 8, ySize - 104, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(Utils.getResourcesDir() + "gui/GuiHollowBook.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}

