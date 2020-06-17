/**
 * Copyright Alwyn974 2019-2020
 * 
 * @author Developed By <a href="https://github.com/alwyn974"> Alwyn974</a>
 */

package re.alwyn974.discordrichpresence;

import static re.alwyn974.discordrichpresence.Config.CLIENT;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;

public class Discord {
	
	private final DiscordRPC client = DiscordRPC.INSTANCE;
	private final DiscordRichPresence richpresence = new DiscordRichPresence();
	private Minecraft mc = Minecraft.getInstance();
	
	public void start() {
		DiscordEventHandlers event = new DiscordEventHandlers();
		event.ready = (user) -> Main.getLogger().info("Ready");;
		this.client.Discord_Initialize(CLIENT.discordID.get(), event, true, "0");
		this.richpresence.startTimestamp = System.currentTimeMillis() / 1000;
		this.richpresence.details = CLIENT.details.get();
		this.richpresence.largeImageKey = CLIENT.large_image_name.get();
		this.richpresence.largeImageText = CLIENT.large_image_text.get();
		this.richpresence.smallImageKey = CLIENT.small_image_name.get();
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
							richpresence.state = CLIENT.state_solo.get();
							richpresence.partySize = 0;
							richpresence.partyMax = 0;
							client.Discord_UpdatePresence(richpresence);
						} else if (mc.world != null && mc.getConnection() != null) {
							richpresence.state = CLIENT.state_multi.get();
							richpresence.partySize = mc.getConnection().getPlayerInfoMap().size();
						    richpresence.partyMax = CLIENT.max_players_number.get();
							client.Discord_UpdatePresence(richpresence);
						} else {
							richpresence.state = CLIENT.state_other.get();
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
