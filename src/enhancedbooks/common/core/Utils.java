package enhancedbooks.common.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import enhancedbooks.common.blocks.BlockStorageShelf;
import enhancedbooks.common.entities.EntityFlyingBook;
import enhancedbooks.common.items.ItemHollowBook;
import enhancedbooks.common.items.ItemXPBook;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Ari
 * Date: 1/3/13
 * Time: 9:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static BlockStorageShelf blockStorageShelf;
    public static ItemXPBook itemXPBook;
    public static ItemHollowBook itemHollowBook;
    public static boolean bookshelvesEnabled;
    public static boolean xpBooksEnabled;
    public static boolean oldRecipe;
    private static ArrayList<String> bookshelfAllowedItems;


    public static boolean isDebug() {
        return true;
    }

    public static String getResourcesDir() {
        return "/mods/enhancedbooks/textures/";
    }

    public static boolean isBook(Item item) {
        return bookshelfAllowedItems.contains(item.getUnlocalizedName().substring(5, item.getUnlocalizedName().length())) || bookshelfAllowedItems.contains(String.valueOf(item.itemID)) || (item instanceof ItemBook);
    }

    public static void loadConfig(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        bookshelvesEnabled = config.get("general", "BookshelvesEnabled", true).getBoolean(true);
        blockStorageShelf = new BlockStorageShelf(config.getBlock("BookshelfID", 500).getInt());
        itemXPBook = new ItemXPBook(config.getItem("XPBookID", 5000).getInt() - 256);
        itemHollowBook = new ItemHollowBook(config.getItem("HollowBookID", 5001).getInt() - 256);
        xpBooksEnabled = config.get("general", "Enabled", true).getBoolean(true);
        oldRecipe = config.get("general", "OldRecipe", false).getBoolean(false);

        bookshelfAllowedItems = new ArrayList<String>(Arrays.asList(config.get("general", "AllowedItems", "paper," +
                "map," +
                "emptyMap," +
                "book," +
                "writingBook," +
                "writtenBook," +
                "enchantedBook," +
                "xpBook," +
                "myst.agebook," +
                "myst.linkbook," +
                "myst.notebook," +
                "myst.page," +
                "ItemThaumonomicon," +
                "ItemResearchNotes," +
                "planBlank," +
                "planFull," +
                "blueprintItem," +
                "ccprintout," +
                "spellparchment," +
                "spellbook," +
                "spellrecipescroll," +
                "bookairaffinity," +
                "bookarcaneaffinity," +
                "bookclearaffinity," +
                "bookearthaffinity," +
                "bookenderaffinity," +
                "bookfireaffinity," +
                "bookiceaffinity," +
                "booklifeaffinity," +
                "booklightningaffinity," +
                "bookmagmaaffinity," +
                "bookplantaffinity," +
                "bookwateraffinity," +
                "spellgrowth," +
                "spellfirebolt," +
                "spellgust," +
                "spelljump," +
                "spellearthshift," +
                "spelldig," +
                "spellaqua," +
                "spellfurnacetouch," +
                "spellarcanebolt," +
                "spellfrozenpath," +
                "spelllightningbolt," +
                "spellwaterbreathing," +
                "spellflight," +
                "spellfeatherfall," +
                "spellhaste," +
                "spellhealself," +
                "spelltruesight," +
                "spellfirerune," +
                "spellshockwave," +
                "spelltelekinesis," +
                "spellmark," +
                "spellrecall," +
                "spelldivineintervention," +
                "spellenderintervention," +
                "spellblink," +
                "spellregeneration," +
                "spellhealother," +
                "spellregenerateother," +
                "spelllifetap," +
                "spellfireburst," +
                "spellsummonearthgolem," +
                "spellsummonskeleton," +
                "spellsummonfireelemental," +
                "spellsummonlich," +
                "spellmagelight," +
                "spellcallrain," +
                "spellfireball," +
                "spellguardianarcanebolt," +
                "spellsummonbattlechicken," +
                "spellfireshield," +
                "spellwatershield," +
                "spellsummondryad," +
                "spellmagicshield," +
                "spelllightningstorm," +
                "spellmeteorjump," +
                "spellchainlightning," +
                "spelldispel," +
                "spelldebug," +
                "spellcharm," +
                "spellarcticwind," +
                "spellfrostbolt," +
                "spellicerune," +
                "spellearthshiftdown," +
                "spellsenseenergy," +
                "spelllifedrain," +
                "spellmanadrain," +
                "spellchronoanchor," +
                "spelltransplace," +
                "spelldigii," +
                "spellinvisibility," +
                "spellnightvision," +
                "spellinsectswarm," +
                "spelldrainingpoison," +
                "spelldrown," +
                "spellenderbolt," +
                "spellvortex," +
                "spellchannelessence," +
                "spellclarivoyance," +
                "spelldesertcurse," +
                "spellentangle," +
                "spellparasiticseed," +
                "spellwaterygrave," +
                "spellwindtalons," +
                "spellskybreath," +
                "spelldeathkiss," +
                "spellscarletflames," +
                "spellwizardsautumn," +
                "spellhandspasms," +
                "spellsevenleaguestride," +
                "spellsturdyvine," +
                "spellprotectingwinds," +
                "spellsummonshadow," +
                "spellhealingring," +
                "spellswiftswim," +
                "spellreflect," +
                "spellagility," +
                "spellaccelerate," +
                "spellwallofthorns," +
                "spellparchingwind," +
                "emptyMagicMap," +
                "emptyMazeMap," +
                "emptyOreMap," +
                "magicMap," +
                "mazeMap," +
                "oreMap," +
                "wirelessmap",
                "Insert the incode-name of whatever you want to be usable in bookshelves").getString().split(",")));

        config.save();
        registerEntityEgg(EntityFlyingBook.class, 0xffffff, 0x000000);
    }

    public static void sendGuiExpBookPacket(int data) {
        if (FMLCommonHandler.instance().getSide().equals(Side.CLIENT)) {
            log("Side is Client, creating packet.");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream dataStream = new DataOutputStream(bytes);
            try {
                dataStream.writeInt(0);
                dataStream.writeInt(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "EnhancedBooks"; // CHANNEL MAX 16 CHARS
            packet.data = bytes.toByteArray();
            packet.length = packet.data.length;

            if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
                log("Sending Packet 250 (Channel:" + packet.channel + " Data:" + packet.data + " Length:" + packet.length + ")");
                PacketDispatcher.sendPacketToServer(packet);
            }
        }
    }

    public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
        int id = 300;
        EntityList.IDtoClassMapping.put(id, entity);
        EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
    }

    public static void log(String toLog) {
        if (isDebug())
            System.out.println("[INFO] [EnhancedBooks]" + toLog);
    }
}
