package aaarsalmon.commands;

import aaarsalmon.Chat2BouyomiTCP;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class TurnOff {
	public static LiteralArgumentBuilder<CommandSource> register() {
		return Commands.literal("off")
			.requires(cs -> cs.hasPermissionLevel(0))
			.executes(ctx -> {
				Chat2BouyomiTCP.setStopped(true);
				ctx.getSource().sendFeedback(new TranslationTextComponent("feedback.chat2bouyomitcp.turned_off"), true);
				return Command.SINGLE_SUCCESS;
			});
	}
}
