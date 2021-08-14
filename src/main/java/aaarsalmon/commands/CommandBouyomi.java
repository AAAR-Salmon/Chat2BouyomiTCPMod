package aaarsalmon.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;

public class CommandBouyomi {
	public CommandBouyomi(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(
			LiteralArgumentBuilder.<CommandSourceStack>literal("bouyomi")
			.then(TurnOnCmd.register())
			.then(TurnOffCmd.register())
		);
	}
}
