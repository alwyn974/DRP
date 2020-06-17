/**
 * Copyright Alwyn974 2019-2020
 * 
 * @author Developed By <a href="https://github.com/alwyn974"> Alwyn974</a>
 */

package re.alwyn974.discordrichpresence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("discordrichpresence")
public class Main {
	private static final Logger LOGGER = LogManager.getLogger("Discord Rich Presence");

	public Main() {
		LOGGER.info("------------------------------");
		LOGGER.info("Mod created by Alwyn974");
		LOGGER.info("Thanks for using it !");
		LOGGER.info("------------------------------");
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPECS);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		LOGGER.info("Starting Discord RichPresence");
		new Discord().start();
	}

	public static Logger getLogger() {
		return LOGGER;
	}

}
