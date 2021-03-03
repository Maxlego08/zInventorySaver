package fr.maxlego08.zinventorysaver;

import java.util.Map;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import com.google.gson.Gson;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.zcore.ZPlugin;

public class ZInventory implements Inventory {

	private final UUID uuid;
	private final Map<Integer, ItemStack> items;
	private final long createdAt;

	/**
	 * @param items
	 */
	public ZInventory(UUID uuid, Map<Integer, ItemStack> items) {
		super();
		this.uuid = uuid;
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

	@Override
	public UUID getUniqueId() {
		return this.uuid;
	}

}
