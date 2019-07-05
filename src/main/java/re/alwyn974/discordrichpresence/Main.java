package re.alwyn974.discordrichpresence;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION , acceptedMinecraftVersions = Main.ACCEPTVER)
public class Main {
	public static final String MODID = "DRP";
	public static final String VERSION = "1.10.2";
	public static final String NAME = "Discord Rich Presence";
	public static final String ACCEPTVER = "[1.10.2]";
	public static String DISCORD_ID, DRP_DETAILS, DRP_IMAGE_LARGE, DRP_IMAGE_LARGE_TEXT, DRP_IMAGE_SMALL, DRP_STATE_SOLO, DRP_STATE_MULTIPLAYER, DRP_STATE_OTHER;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try {
			cfg.load();
			DISCORD_ID = cfg.getString("DISCORD_ID", "discord","0", "Add your Discord App ID");
			DRP_DETAILS = cfg.getString("DRP_DETAILS", "discord","Minecraft Server", "Add your Discord Details");
			DRP_IMAGE_LARGE = cfg.getString("DRP_IMAGE_LARGE", "discord","large_image", "Add your Discord Large Image Key");
			DRP_IMAGE_LARGE_TEXT = cfg.getString("DRP_IMAGE_LARGE_TEXT", "discord","Private Minecraft Server", "Add your Discord Image Large Text");
			DRP_IMAGE_SMALL = cfg.getString("DRP_IMAGE_SMALL", "discord","small_image", "Add your Discord Small Image Key");
			DRP_STATE_SOLO = cfg.getString("DRP_STATE_SOLO", "discord","In SinglePlayer", "Add your Text for the Solo State");
			DRP_STATE_MULTIPLAYER = cfg.getString("DRP_STATE_MULTIPLAYER", "discord","In Multiplayer", "Add your Text for the MultiPlayer State");
			DRP_STATE_OTHER = cfg.getString("DRP_STATE_OTHER", "discord", "In The Menu", "Add your Text for the Other State");
		} catch (Exception ex) {
			event.getModLog().error("Failed to load configuration");
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
		new Discord().start();
	}
}
