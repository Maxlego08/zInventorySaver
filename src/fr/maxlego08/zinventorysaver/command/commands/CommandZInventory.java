package fr.maxlego08.zinventorysaver.command.commands;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.command.VCommand;
import fr.maxlego08.zinventorysaver.zcore.enums.Permission;
import fr.maxlego08.zinventorysaver.zcore.utils.commands.CommandType;

public class CommandZInventory extends VCommand {

	public CommandZInventory() {
		this.setConsoleCanUse(false);
		this.setPermission(Permission.ZINVENTORY_USE);
		this.setDescription("Open inventory manager");
		this.addSubCommand(new CommandZInventoryVersion());
	}

	@Override
	protected CommandType perform(ZInventorySaverPlugin plugin) {

		plugin.getManager().openManager(player);

		return CommandType.SUCCESS;
	}

}
