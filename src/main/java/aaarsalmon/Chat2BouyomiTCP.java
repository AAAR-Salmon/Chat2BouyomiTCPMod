package aaarsalmon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("chat2bouyomitcp")
public class Chat2BouyomiTCP {
	private static final Logger LOGGER = LogManager.getLogger();

	public Chat2BouyomiTCP() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onServerChat(ServerChatEvent event) {
		LOGGER.debug("コメントされたねぇ");
		LOGGER.info(event.getMessage());
	}
}
