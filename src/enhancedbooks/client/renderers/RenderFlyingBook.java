package enhancedbooks.client.renderers;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 1/7/13
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */

import enhancedbooks.client.models.ModelFlyingBook;
import enhancedbooks.common.entities.EntityFlyingBook;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

public class RenderFlyingBook extends RenderLiving {
    protected ModelFlyingBook model;

    public RenderFlyingBook(ModelFlyingBook modelFlyingBook, float f) {

        super(modelFlyingBook, f);
        model = ((ModelFlyingBook) mainModel);
    }

    public void preRenderCallback(EntityLiving par1EntityLiving, float par2) {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }

    public void renderFlyingBook(EntityFlyingBook entity, double par2, double par4, double par6, float par8, float par9) {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        renderFlyingBook((EntityFlyingBook) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        renderFlyingBook((EntityFlyingBook) par1Entity, par2, par4, par6, par8, par9);
    }
}