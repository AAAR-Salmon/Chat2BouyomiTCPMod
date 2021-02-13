package aaarsalmon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

@Mod("chat2bouyomitcp")
public class Chat2BouyomiTCP {
	private static final Logger LOGGER = LogManager.getLogger();

	public Chat2BouyomiTCP() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onServerChat(ServerChatEvent event) {
		send(event.getMessage());
	}

	public static void send(String message) {
		Socket socket = null;
		OutputStream out = null;
		try {
			socket = new Socket("127.0.0.1", 50001);
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
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
