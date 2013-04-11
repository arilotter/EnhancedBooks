package enhancedbooks.client.core;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import enhancedbooks.client.models.ModelFlyingBook;
import enhancedbooks.client.renderers.RenderFlyingBook;
import enhancedbooks.client.renderers.StorageShelfRenderer;
import enhancedbooks.common.core.CommonProxy;
import enhancedbooks.common.entities.EntityFlyingBook;
import enhancedbooks.common.tileentities.TileEntityStorageShelf;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 02/01/13
 * Time: 3:31 AM
 */


public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderInformation() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStorageShelf.class, new StorageShelfRenderer());
        RenderingRegistry.registerBlockHandler(StorageShelfRenderer.storageShelfModelID, new StorageShelfRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityFlyingBook.class, new RenderFlyingBook(new ModelFlyingBook(), 0.5F));
    }

}