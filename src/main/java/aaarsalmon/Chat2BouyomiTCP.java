package aaarsalmon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

@Mod(Chat2BouyomiTCP.MODID)
public class Chat2BouyomiTCP {
	public static final String MODID = "chat2bouyomitcp";
	public static final String MODNAME = "Chat 2 Bouyomi TCP";
	public static final Logger LOGGER = LogManager.getLogger(MODNAME);
	private static boolean stopped = false;

	public Chat2BouyomiTCP() {
		MinecraftForge.EVENT_BUS.register(this);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
	}

	@SubscribeEvent
	public void onServerChat(ServerChatEvent event) {
		if (Config.ENABLE.get() && !getStopped()) {
			sendBouyomi(event.getMessage());
		}
	}

	public void sendBouyomi(String message) {
		Socket socket = null;
		OutputStream out = null;
		String host = Config.HOST.get();
		int port = Config.PORT.get();
		try {
			socket = new Socket(host, port);
			out = socket.getOutputStream();

			byte[] messageData = message.getBytes(StandardCharsets.UTF_8);
			byte[] data = new byte[15 + messageData.length];
			ByteBuffer bb = ByteBuffer.wrap(data);
			bb.order(ByteOrder.LITTLE_ENDIAN);
			bb.putShort(0, (short) 0x0001);
			bb.putShort(2, (short) 0xFFFF); // 速度
			bb.putShort(4, (short) 0xFFFF); // 音程
			bb.putShort(6, (short) 0xFFFF); // 音量
			bb.putShort(8, (short) 0x0000); // 声質
			bb.putChar(10, (char) 0x00); // 文字コード UTF-8
			bb.putInt(11, messageData.length); // 文字列の長さ
			System.arraycopy(messageData, 0, data, 15, messageData.length);
			out.write(data);

			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (out != null) {
					out.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			LOGGER.error("Connection to Bouyomi-chan failed.");
			setStopped(true);
			LOGGER.info("Sending to Bouyomi-chan was turned off.");
			LOGGER.info("Please execute `/bouyomi on` if you want to restart.");
		}
	}

	public static void setStopped(boolean bool) {
		stopped = bool;
	}

	public static boolean getStopped() {
		return stopped;
	}
}
