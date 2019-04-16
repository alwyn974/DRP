package re.alwyn974.discordrichpresence;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;

@Mod(modid = Discord.MODID, name = Discord.NAME, version = Discord.VERSION)
public class Discord {
	public static final String MODID = "DRP";
	public static final String VERSION = "1.0.0";
	public static final String NAME = "Discord Rich Presence";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
			this.Start();
	}
	
	public final DiscordRPC client = DiscordRPC.INSTANCE;
	public final DiscordRichPresence richpresence = new DiscordRichPresence();
	Minecraft mc = Minecraft.getMinecraft();

	public void Start() {
		DiscordEventHandlers event = new DiscordEventHandlers();
		event.ready = (user) -> System.out.println("Ready!");
		this.client.Discord_Initialize("YOUR APP ID", event, true, "0");
		this.richpresence.startTimestamp = System.currentTimeMillis() / 1000;
		this.richpresence.details = "YOUR DETAILS";
		this.richpresence.largeImageKey = "YOUR IMAGE";
		this.richpresence.largeImageText = "YOUR TEXT";
		this.richpresence.smallImageKey = "YOUR IMAGE";
		this.richpresence.partySize = 0; 
		this.richpresence.partyMax = 0;
		this.richpresence.smallImageText = mc.getSession().getUsername();
		this.client.Discord_UpdatePresence(richpresence);

		new Thread("RPC-Callback-Handler") {
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					client.Discord_UpdatePresence(richpresence);
					client.Discord_RunCallbacks();
					try {
						//Status in singleplayer
						 if (mc.isSingleplayer()) {
							richpresence.state = "In Singleplayer";
							richpresence.partySize = 0;
							richpresence.partyMax = 0;
							client.Discord_UpdatePresence(richpresence);
						}
						//Status in multiplayer
						else if (mc.theWorld !=null && mc.getNetHandler() != null) {
							richpresence.state = "In Multiplayer";
							richpresence.partySize = mc.getNetHandler().playerInfoList.size();
							richpresence.partyMax = mc.getNetHandler().currentServerMaxPlayers;
							client.Discord_UpdatePresence(richpresence);
						} 
						//Status for other
						else {
							richpresence.state = "In The Menu";
							richpresence.partySize = 0;
							richpresence.partyMax = 0;
							client.Discord_UpdatePresence(richpresence);
						}
						Thread.sleep(2000);
					} catch (InterruptedException ignored) {
						client.Discord_Shutdown();
					}
				}
			}
		}.start();
	}
}
