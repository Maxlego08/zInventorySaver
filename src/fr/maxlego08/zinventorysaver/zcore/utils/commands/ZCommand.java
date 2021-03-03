package fr.maxlego08.zinventorysaver.zcore.utils.commands;

import java.util.function.BiConsumer;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.command.VCommand;

public class ZCommand extends VCommand {

	private BiConsumer<VCommand, ZInventorySaverPlugin> command;

	@Override
	public CommandType perform(ZInventorySaverPlugin main) {
		
		if (command != null){
			command.accept(this, main);
		}

		return CommandType.SUCCESS;
	}

	public VCommand setCommand(BiConsumer<VCommand, ZInventorySaverPlugin> command) {
		this.command = command;
		return this;
	}

	public VCommand sendHelp(String command) {
		this.command = (cmd, main) -> main.getCommandManager().sendHelp(command, cmd.getSender());
		return this;
	}

}
