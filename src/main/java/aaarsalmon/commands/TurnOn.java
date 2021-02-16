// 参考: https://github.com/sinkillerj/ProjectE/blob/mc1.16.x/src/main/java/moze_intel/projecte/network/commands/SetEmcCMD.java

package aaarsalmon.commands;

import aaarsalmon.Chat2BouyomiTCP;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class TurnOn {
	public static LiteralArgumentBuilder<CommandSource> register() {
		return Commands.literal("on")
			.requires(cs -> cs.hasPermissionLevel(0))
			.executes(ctx -> {
				Chat2BouyomiTCP.setStopped(false);
				ctx.getSource().sendFeedback(new TranslationTextComponent("feedback.chat2bouyomitcp.turned_on"), true);
				return Command.SINGLE_SUCCESS;
			});
	}
}
