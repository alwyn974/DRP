/**
 * Copyright Alwyn974 2019-2020
 * 
 * @author Developed By <a href="https://github.com/alwyn974"> Alwyn974</a>
 */

package re.alwyn974.discordrichpresence;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

	public static final ForgeConfigSpec CLIENT_SPECS;
	public static final Client CLIENT;

	static {
		Pair<Client, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPECS = clientPair.getRight();
		CLIENT = clientPair.getLeft();
	}

	public static class Client {
		public final ForgeConfigSpec.ConfigValue<String> discordID;
		public final ForgeConfigSpec.ConfigValue<String> details;
		public final ForgeConfigSpec.ConfigValue<String> large_image_name;
		public final ForgeConfigSpec.ConfigValue<String> large_image_text;
		public final ForgeConfigSpec.ConfigValue<String> small_image_name;
		public final ForgeConfigSpec.ConfigValue<String> state_solo;
		public final ForgeConfigSpec.ConfigValue<String> state_multi;
		public final ForgeConfigSpec.ConfigValue<Integer> max_players_number;
		public final ForgeConfigSpec.ConfigValue<String> state_other;
		
		public Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Discord Rich Presence Configuration File").push("Configuration for Discord Application");
			discordID = builder.comment("Discord Rich Presence Application ID").define("discordID", "573197802985881611");
			details = builder.comment("Discord Rich Presence Details").define("details", "Minecraft Server");
			large_image_name = builder.comment("Discord Rich Presence Large Image Name").define("large_image_name", "large_image");
			large_image_text = builder.comment("Discord Rich Presence Large Image Text").define("large_image_text", "Private Minecraft Server");
			small_image_name = builder.comment("Discord Rich Presence Small Image Name").define("small_image_name", "small_image");
			state_solo = builder.comment("Text displayed in SinglePlayer").define("state_solo", "In Singleplayer");
			state_multi = builder.comment("Text displayed in SinglePlayer").define("state_multi", "In Multiplayer");
			max_players_number = builder.comment("Text displayed in Multiplayer").define("max_players_number", 100);
			state_other = builder.comment("Text displayed for other state").define("state_other", "In the Menu");
		}
	}

}
