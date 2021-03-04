package fr.maxlego08.zinventorysaver.inventory.inventories;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.zinventorysaver.api.Inventory;
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
		String string = object.getUniqueId().toString().substring(0, 8);
		return new ItemBuilder(Material.PAPER, 1, 0, "§fInventory §7" + string, "§7UUID§8: §f" + object.getUniqueId())
				.build();
	}

	@Override
	public void onClick(Inventory object, ItemButton button) {

	}

	@Override
	public List<Inventory> preOpenInventory() {
		PlayerInventory playerInventory = (PlayerInventory) args[0];
		return playerInventory.getInventory();
	}

	@Override
	public void postOpenInventory() {
		// TODO Auto-generated method stub

	}

}
