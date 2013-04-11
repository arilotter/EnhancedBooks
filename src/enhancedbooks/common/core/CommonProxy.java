package enhancedbooks.common.core;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 02/01/13
 * Time: 2:12 AM
 */

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import enhancedbooks.common.entities.EntityFlyingBook;
import enhancedbooks.common.tileentities.TileEntityStorageShelf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CommonProxy implements IGuiHandler { //THIS IS IMPORTANT, CANNOT BE A PROXY/GUI HANDLER WITHOUT THIS!!


    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { //For GUI's
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { //For GUI's
        return null;
    }

    public void registerRenderInformation() {
        // Do nothing, this is for client
    }

    public void registerTileEntities() { //For registering TileEntities
        GameRegistry.registerTileEntity(TileEntityStorageShelf.class, "TileStorageShelf");
    }

    public void registerBlocks() { //For registering Blocks
        if (Utils.bookshelvesEnabled) {
            GameRegistry.registerBlock(Utils.blockStorageShelf, "StorageShelf");//Register the block
        }
    }

    public void registerItems() {
        if (Utils.xpBooksEnabled) {
            GameRegistry.registerItem(Utils.itemXPBook, "expBook");
        }
        GameRegistry.registerItem(Utils.itemHollowBook, "hollowBook");
    }

    public void registerEntities() {
        EntityRegistry.registerModEntity(EntityFlyingBook.class, "FlyingBook", 1, BooksCore.instance, 80, 3, true);
    }

    public void addNames() { //For adding ingame names
        if (Utils.bookshelvesEnabled) {
            LanguageRegistry.addName(Utils.blockStorageShelf, "Empty Bookshelf");
        }
        if (Utils.xpBooksEnabled) {
            LanguageRegistry.addName(Utils.itemXPBook, "XP-Infused Book");
        }
        LanguageRegistry.addName(Utils.itemHollowBook, "Hollow Book");
        LanguageRegistry.instance().addStringLocalization("entity.EnhancedBooks.FlyingBook.name", "Flying Book");
    }

    public void addRecipes() { //For adding your Items' recipes
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.blockStorageShelf, true, new Object[] {
                "ooo",
                "---",
                "ooo",
                Character.valueOf('o'), "plankWood", Character.valueOf('-'), "slabWood"
        }));

        GameRegistry.addShapelessRecipe(new ItemStack(Utils.itemHollowBook), new ItemStack(Item.book), new ItemStack(Item.stick));

        if (Utils.oldRecipe) {
            GameRegistry.addShapelessRecipe(
                    new ItemStack(Utils.itemXPBook, 1, Utils.itemXPBook.getMaxDamage()),
                    new ItemStack(Item.book), new ItemStack(Item.expBottle));
        } else {
            GameRegistry.addShapelessRecipe(
                    new ItemStack(Utils.itemXPBook, 1, Utils.itemXPBook.getMaxDamage()),
                    new ItemStack(Item.eyeOfEnder), new ItemStack(Item.book),
                    new ItemStack(Item.blazeRod));
        }
    }
}