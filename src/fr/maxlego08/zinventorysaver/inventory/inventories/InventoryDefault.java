package fr.maxlego08.zinventorysaver.inventory.inventories;

import org.bukkit.entity.Player;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.exceptions.InventoryOpenException;
import fr.maxlego08.zinventorysaver.inventory.VInventory;
import fr.maxlego08.zinventorysaver.zcore.utils.inventory.InventoryResult;

public class InventoryDefault extends VInventory {

	public InventoryDefault() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public InventoryResult openInventory(ZInventorySaverPlugin main, Player player, int page, Object... args)
			throws InventoryOpenException {
		
		createInventory("§fz§7Inventory", 54);
		
		return InventoryResult.SUCCESS;
	}

}
