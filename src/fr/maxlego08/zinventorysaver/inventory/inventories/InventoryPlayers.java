package fr.maxlego08.zinventorysaver.inventory.inventories;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.zcore.utils.builder.ItemBuilder;
import fr.maxlego08.zinventorysaver.zcore.utils.inventory.ItemButton;
import fr.maxlego08.zinventorysaver.zcore.utils.inventory.PaginateInventory;

public class InventoryPlayers extends PaginateInventory<PlayerInventory> {

	public InventoryPlayers() {
		super("§fz§7Inventory §8- §fPlayers", 54);
		this.openAsync = true;
	}

	@Override
	public ItemStack buildItem(PlayerInventory object) {

		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(object.getUniqueId());
		ItemBuilder builder = new ItemBuilder(getMaterial(397), 1, 3, "§f" + offlinePlayer.getName());
		builder.owner(offlinePlayer.getName());
		builder.setLore("§8§m-+------------------------------+-", "",
				"§f§l» §7Click to access the §n" + offlinePlayer.getName() + "§7 inventories.", "",
				"§8§m-+------------------------------+-");

		return builder.build();
	}

	@Override
	public void onClick(PlayerInventory object, ItemButton button) {
		InventoryManager inventoryManager = plugin.getManager();
		inventoryManager.openPlayer(player, object);
	}

	@Override
	public List<PlayerInventory> preOpenInventory() {
		InventoryManager inventoryManager = plugin.getManager();
		return inventoryManager.getPlayers();
	}

	@Override
	public void postOpenInventory() {

	}

}
