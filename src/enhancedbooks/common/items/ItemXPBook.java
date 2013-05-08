package enhancedbooks.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbooks.common.core.BooksCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemXPBook extends ItemBook {
    public ItemXPBook(int i) {
        super(i);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("xpBook");
        this.setMaxDamage(50);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("enhancedbooks:XPBook");
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List descriptionList, boolean par4) {
        descriptionList.add("Level: " + (itemStack.getMaxDamage() - itemStack.getItemDamage()));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        entityPlayer.openGui(BooksCore.instance, 1, world, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        return itemStack;
    }

    @SideOnly(Side.CLIENT)
    // That goddamn enchanted shining effect :D
    public boolean hasEffect(ItemStack itemStack) {
        return itemStack.getItemDamage() != itemStack.getMaxDamage();
    }
}
