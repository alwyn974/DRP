package re.alwyn974.discordrichpresence;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;

public class Discord {
	public final DiscordRPC client = DiscordRPC.INSTANCE;
	public final DiscordRichPresence richpresence = new DiscordRichPresence();
	Minecraft mc = Minecraft.getMinecraft();

	public void start() {
		DiscordEventHandlers event = new DiscordEventHandlers();
		event.ready = (user) -> System.out.println("Ready!");
		this.client.Discord_Initialize(Main.DISCORD_ID, event, true, "0");
		this.richpresence.startTimestamp = System.currentTimeMillis() / 1000;
		this.richpresence.details = Main.DRP_DETAILS;
		this.richpresence.largeImageKey = Main.DRP_IMAGE_LARGE;
		this.richpresence.largeImageText = Main.DRP_IMAGE_LARGE_TEXT;
		this.richpresence.smallImageKey = Main.DRP_IMAGE_SMALL;
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
						 if (mc.isSingleplayer()) {
							richpresence.state = Main.DRP_STATE_SOLO;
							richpresence.partySize = 0;
							richpresence.partyMax = 0;
							client.Discord_UpdatePresence(richpresence);
						}
						else if (mc.theWorld !=null && mc.getConnection() != null) {
							richpresence.state = Main.DRP_STATE_MULTIPLAYER;
							richpresence.partySize = mc.getConnection().getPlayerInfoMap().size();
							richpresence.partyMax = mc.getConnection().currentServerMaxPlayers;
							client.Discord_UpdatePresence(richpresence);
						} else {
							richpresence.state = Main.DRP_STATE_OTHER;
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
