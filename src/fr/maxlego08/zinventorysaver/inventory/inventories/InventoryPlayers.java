package fr.maxlego08.zinventorysaver.inventory.inventories;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

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

	@SuppressWarnings("deprecation")
	@Override
	public ItemStack buildItem(PlayerInventory object) {

		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(object.getUniqueId());

		ItemStack itemStack = playerHead();
		SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
		itemMeta.setOwner(offlinePlayer.getName());
		itemMeta.setDisplayName("§f" + offlinePlayer.getName());
		itemMeta.setLore(Arrays.asList("§8§m-+------------------------------+-", "",
				"§f§l» §7Click to access the §n" + offlinePlayer.getName() + "§7 inventories.", "",
				"§8§m-+------------------------------+-"));
		itemStack.setItemMeta(itemMeta);
		return itemStack;
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

		ItemButton button = addItem(49, new ItemBuilder(Material.BARRIER, "§cBack"));
		button.setClick(event -> {
			InventoryManager inventoryManager = plugin.getManager();
			inventoryManager.openManager(player);
		});

	}

}
