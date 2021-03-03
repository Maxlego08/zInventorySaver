package fr.maxlego08.zinventorysaver;

import java.util.Map;

import org.bukkit.inventory.ItemStack;

import com.google.gson.Gson;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.zcore.ZPlugin;

public class ZInventory implements Inventory {

	private final Map<Integer, ItemStack> items;
	private final long createdAt;

	/**
	 * @param items
	 */
	public ZInventory(Map<Integer, ItemStack> items) {
		super();
		this.items = items;
		this.createdAt = System.currentTimeMillis();
	}

	@Override
	public Map<Integer, ItemStack> getItemStack() {
		return this.items;
	}

	@Override
	public long getCreatedAt() {
		return this.createdAt;
	}

	@Override
	public String serialize() {
		Gson gson = ZPlugin.z().getGson();
		return gson.toJson(this.items);
	}

}
