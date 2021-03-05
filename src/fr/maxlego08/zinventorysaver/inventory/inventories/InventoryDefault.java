package fr.maxlego08.zinventorysaver.inventory.inventories;

import org.bukkit.Material;
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
		createInventory("§fz§7Inventory", 27);
		
		addItem(10, new ItemBuilder(getMaterial(351), 1, 9, "§fPlayers", 
				"§8§m-+------------------------------+-",
				"",
				"§f§l» §7Displays the list of players.",
				"",
				"§8§m-+------------------------------+-"
				)).setClick(event -> {
					inventoryManager.openPlayers(player);
				});
		
		addItem(12, new ItemBuilder(getMaterial(351), 1, 8, "§fSearch", 
				"§8§m-+------------------------------+-",
				"",
				"§f§l» §7Click to search for a player",
				"",
				"§8§m-+------------------------------+-"
				)).setClick(event -> {
					inventoryManager.searchPlayer(player);
				});
		
		addItem(26, new ItemBuilder(Material.NETHER_STAR, 1, 0, "§fReload", 
				"§8§m-+------------------------------+-",
				"",
				"§f§l» §7Click to reload the configuration.",
				"",
				"§8§m-+------------------------------+-"
				)).setClick(event -> {
					inventoryManager.reload(player);
				});
		
		return InventoryResult.SUCCESS;
	}

}
