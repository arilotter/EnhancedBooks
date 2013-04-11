package enhancedbooks.client.gui;

import enhancedbooks.common.core.Utils;
import enhancedbooks.common.items.ItemXPBook;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

public class GuiXPBook extends GuiScreen {

    public final int xSize = 176;
    public final int ySize = 88;
    protected int guiLeft;
    protected int guiTop;

    private GuiButton plus;
    private GuiButton minus;
    private ItemStack xpBook;

    public GuiXPBook(ItemStack xpBook) {
        this.xpBook = xpBook;
    }

    @Override
    public void updateScreen() {
        if (mc.thePlayer.isDead || xpBook == null) {
            this.mc.thePlayer.closeScreen();
            return;
        }
        if (mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemXPBook) {
            xpBook = mc.thePlayer.inventory.getCurrentItem();
        }
        plus.enabled = mc.thePlayer.inventory.getCurrentItem().getItemDamage() > 0 && mc.thePlayer.experienceLevel > 0 && !mc.thePlayer.capabilities.isCreativeMode;
        minus.enabled = mc.thePlayer.inventory.getCurrentItem().getItemDamage() < mc.thePlayer.inventory.getCurrentItem().getMaxDamage() && !mc.thePlayer.capabilities.isCreativeMode;
    }

    /**
     * Draw the Gui
     */
    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.mc.renderEngine.bindTexture(Utils.getResourcesDir() + "gui/GuiXPBook.png"); //Bind Texture
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        drawString(fontRenderer, "Level: " + (xpBook.getMaxDamage() - xpBook.getItemDamage()), guiLeft + 64, guiTop + 20, 0xEEEEEE);
        for (int j = 0; j < this.buttonList.size(); ++j) {
            GuiButton guibutton = (GuiButton) this.buttonList.get(j);
            guibutton.drawButton(this.mc, par1, par2);

        }
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.buttonList.add(plus = new GuiButton(0, guiLeft + xSize - 40, guiTop + ySize / 2, 20, 20, "+"));
        this.buttonList.add(minus = new GuiButton(1, guiLeft + 20, guiTop + ySize / 2, 20, 20, "-"));
        plus.enabled = mc.thePlayer.inventory.getCurrentItem().getItemDamage() > 0;
        minus.enabled = mc.thePlayer.inventory.getCurrentItem().getItemDamage() < mc.thePlayer.inventory.getCurrentItem().getMaxDamage();
    }

    @Override
    public void actionPerformed(GuiButton button) {
        //If shift-clicking, add ALL the player's xp, or take ALL of the stuff in book out.
        switch (button.id) {
            case 0: //Plus
                if (isShiftKeyDown()) {
                    //All the XP!
                    Utils.sendGuiExpBookPacket(2);
                } else {
                    //Just one
                    Utils.sendGuiExpBookPacket(1);
                }
                break;
            case 1: //Minus
                if (isShiftKeyDown()) {
                    //All the XP!
                    Utils.sendGuiExpBookPacket(-2);
                } else {
                    //Just One
                    Utils.sendGuiExpBookPacket(-1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        if (par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.keyCode) {
            this.mc.thePlayer.closeScreen();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
