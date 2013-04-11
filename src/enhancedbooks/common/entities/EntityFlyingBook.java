package enhancedbooks.common.entities;

import enhancedbooks.common.core.Utils;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 1/6/13
 * Time: 11:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityFlyingBook extends EntityMob {

    private ChunkCoordinates currentFlightTarget;


    public EntityFlyingBook(World worldObj) {
        super(worldObj);
        this.texture = Utils.getResourcesDir() + "models/flyingbook.png";
        this.moveSpeed = 0.25F;

    }

    public int getMaxHealth() {
        return 1;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.currentFlightTarget != null && (!this.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ) || this.currentFlightTarget.posY < 1)) {
            this.currentFlightTarget = null;
        }

        if (this.currentFlightTarget == null || this.rand.nextInt(30) == 0 || this.currentFlightTarget.getDistanceSquared((int) this.posX, (int) this.posY, (int) this.posZ) < 4.0F) {
            this.currentFlightTarget = new ChunkCoordinates((int) this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) this.posY + this.rand.nextInt(6) - 2, (int) this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
        }

        double var1 = (double) this.currentFlightTarget.posX + 0.5D - this.posX;
        double var3 = (double) this.currentFlightTarget.posY + 0.1D - this.posY;
        double var5 = (double) this.currentFlightTarget.posZ + 0.5D - this.posZ;
        this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.10000000149011612D;
        this.motionY += (Math.signum(var3) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
        this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.10000000149011612D;
        float var7 = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
        float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
        this.moveForward = 0.2F;
        this.rotationYaw += var8;
    }

}
