package fr.maxlego08.zinventorysaver.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import fr.maxlego08.zinventorysaver.ZInventory;

public interface Inventory {

	public UUID getUniqueId();

	/**
	 * 
	 * @return
	 */
	public Map<Integer, ItemStack> getItemStack();

	/**
	 * 
	 * @return long
	 */
	public long getCreatedAt();

	/**
	 * 
	 * @return string
	 */
	public String serialize();

	public static Inventory create(Player player) {

		Map<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		PlayerInventory inventory = player.getInventory();
		for (int a = 0; a != 36; a++) {
			ItemStack itemStack = inventory.getItem(a);
			if (itemStack != null)
				items.put(a, itemStack);
		}

		return new ZInventory(UUID.randomUUID(), items);
	}

}
