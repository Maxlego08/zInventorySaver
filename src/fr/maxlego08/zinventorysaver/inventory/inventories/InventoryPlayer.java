package fr.maxlego08.zinventorysaver.inventory.inventories;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.zcore.utils.builder.ItemBuilder;
import fr.maxlego08.zinventorysaver.zcore.utils.inventory.ItemButton;
import fr.maxlego08.zinventorysaver.zcore.utils.inventory.PaginateInventory;

public class InventoryPlayer extends PaginateInventory<Inventory> {

	public InventoryPlayer() {
		super("§fz§7Inventory §8- §fPlayer", 54);
	}

	@Override
	public ItemStack buildItem(Inventory object) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy 'at' hh:mm:ss");
		Date created = new Date(object.getCreatedAt());
		Date update = new Date(object.getUpdatedAt());
		
		return new ItemBuilder(Material.PAPER, 1, 0, "§8§m-+------------------------------+-",
				"",
				"§f§l» §7UUID§8: §f" + object.getUniqueId(),
				"§f§l» §7Created at§8: §f" + dateFormat.format(created),
				"§f§l» §fUpdated at§8: §f" + dateFormat.format(update),
				"",
				"§8§m-+------------------------------+-"
				)
				.build();
	}

	@Override
	public void onClick(Inventory object, ItemButton button) {
		InventoryManager inventoryManager = plugin.getManager();
		PlayerInventory playerInventory = (PlayerInventory) args[0];
		inventoryManager.openInventory(player, object, playerInventory);
	}

	@Override
	public List<Inventory> preOpenInventory() {
		PlayerInventory playerInventory = (PlayerInventory) args[0];
		return playerInventory.getSortInventory();
	}

	@Override
	public void postOpenInventory() {
		ItemButton button = addItem(49, new ItemBuilder(Material.BARRIER, "§cBack"));
		button.setClick(event -> {
			InventoryManager inventoryManager = plugin.getManager();
			inventoryManager.openPlayers(player);
		});
	}

}
