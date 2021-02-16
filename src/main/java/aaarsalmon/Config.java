package aaarsalmon;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.BooleanValue ENABLE;
	public static final ForgeConfigSpec.ConfigValue<String> HOST;
	public static final ForgeConfigSpec.IntValue PORT;

	static {
		final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

		BUILDER.push("System");
		ENABLE = BUILDER
			.translation("configGui.chat2bouyomi.enable")
			.define("Enable", true);
		BUILDER.pop();

		BUILDER.push("Connection");
		HOST = BUILDER
			.comment("Host IP address.")
			.translation("configGui.chat2bouyomitcp.port")
			.define("HostIP", "127.0.0.1");
		PORT = BUILDER
			.comment("TCP port number.")
			.translation("configGui.chat2bouyomitcp.port")
			.defineInRange("Port", 50001, 0, 65535);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
