package aaarsalmon.commands;

import aaarsalmon.Chat2BouyomiTCP;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class TurnOffCmd {
	public static ArgumentBuilder<CommandSourceStack, ?> register() {
		return Commands.literal("off").requires(requirement -> {
			return requirement.hasPermission(1);
		}).executes(command -> {
			Chat2BouyomiTCP.setStopped(true);
			command.getSource().sendSuccess(new TranslatableComponent("feedback.chat2bouyomitcp.turned_off"), false);
			return Command.SINGLE_SUCCESS;
		});
	}
}
