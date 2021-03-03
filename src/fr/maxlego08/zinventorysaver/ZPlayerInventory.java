package fr.maxlego08.zinventorysaver;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.api.storage.IStorage;

public class ZPlayerInventory implements PlayerInventory {

	private final UUID uniqueId;
	private final List<Inventory> inventories;
	private transient long lastInventorySave;

	/**
	 * @param uniqueId
	 * @param inventories
	 */
	public ZPlayerInventory(UUID uniqueId, List<Inventory> inventories) {
		super();
		this.uniqueId = uniqueId;
		this.inventories = inventories;
	}

	@Override
	public UUID getUniqueId() {
		return this.uniqueId;
	}

	@Override
	public List<Inventory> getInventory() {
		return this.inventories;
	}

	@Override
	public void saveInventory(Player player, boolean force, IStorage iStorage) {

		if (lastInventorySave != 0 && lastInventorySave > System.currentTimeMillis() && !force)
			return;

		Inventory inventory = Inventory.create(player);
		this.inventories.add(inventory);
	}

}
