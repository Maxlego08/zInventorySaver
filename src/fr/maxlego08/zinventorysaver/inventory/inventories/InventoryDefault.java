package fr.maxlego08.zinventorysaver.inventory.inventories;

import org.bukkit.entity.Player;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.exceptions.InventoryOpenException;
import fr.maxlego08.zinventorysaver.inventory.VInventory;
import fr.maxlego08.zinventorysaver.zcore.utils.builder.ItemBuilder;
import fr.maxlego08.zinventorysaver.zcore.utils.inventory.InventoryResult;

public class InventoryDefault extends VInventory {

	public InventoryDefault() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public InventoryResult openInventory(ZInventorySaverPlugin main, Player player, int page, Object... args)
			throws InventoryOpenException {
		
		InventoryManager inventoryManager = main.getManager();
		createInventory("§fz§7Inventory", 54);
		
		addItem(10, new ItemBuilder(getMaterial(351), 1, 9, "§fPlayers", 
				"§8§m-+------------------------------+-",
				"",
				"§f§l» §7Displays the list of players.",
				"",
				"§8§m-+------------------------------+-"
				)).setClick(event -> {
					inventoryManager.openPlayers(player);
				});
		
		return InventoryResult.SUCCESS;
	}

}
