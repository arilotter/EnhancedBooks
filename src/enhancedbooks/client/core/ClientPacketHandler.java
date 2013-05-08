package enhancedbooks.client.core;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 02/01/13
 * Time: 3:34 AM
 */

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbooks.common.core.Utils;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

@SideOnly(Side.CLIENT)
public class ClientPacketHandler implements IPacketHandler {

    int packetType = -1;

    /*
     * Packet format: byte 0: Packet Type Currently available packet types
     * Client: 0 = Button pressed in GuiExpBook byte 1: button pressed (1 is
     * add, 2 is subtract) Server: 0 = Button pressed in GuiExpBook byte 1:
     * button pressed (1 is add, 2 is subtract)
     */
    @Override
    public void onPacketData(INetworkManager network, Packet250CustomPayload packet, Player player) {
        Utils.log("ClientPacketHandler: Side is " + FMLCommonHandler.instance().getSide());
        Utils.log("ClientPacketHandler: Packet Recieved");
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(packet.data));
        // Read the first int to determine packet type
        try {
            this.packetType = stream.readInt();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*
         * each packet type needs to implement an if and then whatever other
		 * read functions it needs complete with try/catch blocks
		 */
        if (this.packetType == 0) { // GuiExpBook button pressed
            // Nothing happens here I think ahh
        }
        Utils.log("ClientPacketHandler: Press 1 complete!");
        if (this.packetType == 1) {
            // Unimplemented, yay! :D
        }
    }
}