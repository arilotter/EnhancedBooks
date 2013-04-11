package enhancedbooks.client.renderers;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import enhancedbooks.client.models.ModelStorageShelf;
import enhancedbooks.common.core.Utils;
import enhancedbooks.common.tileentities.TileEntityStorageShelf;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 3/8/13
 * Time: 10:48 AM
 */
public class StorageShelfRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
    ModelStorageShelf model;
    public static int storageShelfModelID = RenderingRegistry.getNextAvailableRenderId();

    public StorageShelfRenderer() {
        model = new ModelStorageShelf();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        this.renderStorageShelf(tileEntity.getBlockMetadata(), ((TileEntityStorageShelf) tileEntity).getFilledSlots());
        GL11.glPopMatrix();
    }

    private void renderStorageShelf(int direction) {
        this.renderStorageShelf(direction, new boolean[8]);
    }

    private void renderStorageShelf(int direction, boolean[] filledSlots) {

        GL11.glPushMatrix();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        GL11.glRotatef((float) (360 - (direction * 90)) + 180, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, FMLClientHandler.instance().getClient().renderEngine.getTexture(Utils.getResourcesDir() + "models/StorageShelf.png"));

        model.render(0.0625F, filledSlots);

        GL11.glPopMatrix();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        this.renderStorageShelf(1);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false; //TESR Overridden
    }

    @Override
    public boolean shouldRender3DInInventory() {
        return true;
    }

    @Override
    public int getRenderId() {
        return storageShelfModelID;
    }
}
