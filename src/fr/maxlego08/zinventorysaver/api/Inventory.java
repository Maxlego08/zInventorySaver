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
	 * @return
	 */
	public long getUpdatedAt();

	/**
	 * 
	 * @return string
	 */
	public String serialize();

	/**
	 * 
	 * @param items
	 */
	public void setItems(Map<Integer, ItemStack> items);

	/**
	 * 
	 * @return
	 */
	public boolean hasExpired();

	/**
	 * 
	 * @param player
	 * @return
	 */
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

	/**
	 * 
	 * @param player
	 * @return true if inventory is empty
	 */
	public static boolean isInventoryEmpty(Player player) {
		int numberOfEmptySlot = 0;
		PlayerInventory inventory = player.getInventory();
		for (int a = 0; a != 36; a++) {
			ItemStack itemStack = inventory.getItem(a);
			if (itemStack == null)
				numberOfEmptySlot++;
		}
		return numberOfEmptySlot == 36;
	}

}
