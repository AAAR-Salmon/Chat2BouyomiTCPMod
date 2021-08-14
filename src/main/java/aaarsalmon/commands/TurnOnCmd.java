package aaarsalmon.commands;

import aaarsalmon.Chat2BouyomiTCP;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class TurnOnCmd {
	public static ArgumentBuilder<CommandSourceStack, ?> register() {
		return Commands.literal("on").requires(requirement -> {
			return requirement.hasPermission(1);
		}).executes(command -> {
			Chat2BouyomiTCP.setStopped(false);
			command.getSource().sendSuccess(new TranslatableComponent("feedback.chat2bouyomitcp.turned_on"), false);
			return Command.SINGLE_SUCCESS;
		});
	}
}
