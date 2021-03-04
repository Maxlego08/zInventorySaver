package fr.maxlego08.zinventorysaver.inventory.inventories;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.exceptions.InventoryOpenException;
import fr.maxlego08.zinventorysaver.inventory.VInventory;
import fr.maxlego08.zinventorysaver.zcore.utils.builder.ItemBuilder;
import fr.maxlego08.zinventorysaver.zcore.utils.inventory.InventoryResult;
import fr.maxlego08.zinventorysaver.zcore.utils.inventory.ItemButton;

public class InventoryShowInventory extends VInventory {

	public InventoryShowInventory() {
		this.disableClick = false;
	}

	@Override
	public InventoryResult openInventory(ZInventorySaverPlugin main, Player player, int page, Object... args)
			throws InventoryOpenException {

		Inventory inventory = (Inventory) args[0];
		PlayerInventory playerInventory = (PlayerInventory) args[1];

		createInventory("§fz§fInventory §8- §fShow", 45);

		inventory.getItemStack().forEach((slot, item) -> addItem(slot, item));

		for (int a = 36; a != 45; a++)
			addItem(a, new ItemBuilder(getMaterial(160), 1, 8, "§f")).setClick(e -> e.setCancelled(true));

		ItemButton button = addItem(39, new ItemBuilder(Material.BARRIER, "§cBack"));
		button.setClick(event -> {
			event.setCancelled(true);
			InventoryManager inventoryManager = plugin.getManager();
			inventoryManager.openPlayer(player, playerInventory);
		});

		ItemButton buttonSave = addItem(41, new ItemBuilder(getMaterial(160), 1, 13, "§aSave"));
		buttonSave.setClick(event -> {
			event.setCancelled(true);
			Map<Integer, ItemStack> items = new HashMap<>();
			for (int a = 0; a != 36; a++) {
				ItemStack itemStack = super.inventory.getItem(a);
				if (itemStack != null)
					items.put(a, itemStack);
			}
			InventoryManager inventoryManager = plugin.getManager();
			inventoryManager.updateInventory(player, playerInventory, inventory, items);
		});

		return InventoryResult.SUCCESS;
	}

}
