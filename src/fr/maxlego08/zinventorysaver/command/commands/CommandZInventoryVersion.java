package fr.maxlego08.zinventorysaver.command.commands;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.command.VCommand;
import fr.maxlego08.zinventorysaver.zcore.utils.commands.CommandType;

public class CommandZInventoryVersion extends VCommand {

	public CommandZInventoryVersion() {
		this.setDescription("Show plugin version");
		this.addSubCommand("v", "version", "?");
	}

	@Override
	protected CommandType perform(ZInventorySaverPlugin plugin) {

		message(sender, "§aVersion du plugin§7: §2" + plugin.getDescription().getVersion());
		message(sender, "§aAuteur§7: §2Maxlego08");
		message(sender, "§aDiscord§7: §2http://discord.groupez.xyz/");

		return CommandType.SUCCESS;
	}

}
