package fr.maxlego08.zinventorysaver;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import com.google.gson.Gson;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.zcore.ZPlugin;
import fr.maxlego08.zinventorysaver.zcore.utils.ItemDecoder;

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
		Map<Integer, String> clonedMaps = new HashMap<Integer, String>();
		items.forEach((k, v) -> clonedMaps.put(k, ItemDecoder.serializeItemStack(v)));
		Gson gson = ZPlugin.z().getGson();
		return gson.toJson(clonedMaps);
	}

	@Override
	public UUID getUniqueId() {
		return this.uuid;
	}

}
