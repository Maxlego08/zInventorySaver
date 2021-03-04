package fr.maxlego08.zinventorysaver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.api.storage.IStorage;
import fr.maxlego08.zinventorysaver.save.Config;

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
		return Collections.unmodifiableList(this.inventories);
	}

	@Override
	public void saveInventory(Player player, boolean force, IStorage iStorage) {

		if (lastInventorySave != 0 && lastInventorySave > System.currentTimeMillis() && !force)
			return;

		if (Inventory.isInventoryEmpty(player))
			return;

		this.lastInventorySave = System.currentTimeMillis() + Config.delayBetweenSaveInSecond * 1000;
		Inventory inventory = Inventory.create(player);
		this.inventories.add(inventory);
		iStorage.asyncInsert(this, inventory);
	}

	@Override
	public void addInventory(Inventory inventory) {
		this.inventories.add(inventory);
	}

	@Override
	public List<Inventory> getSortInventory() {
		List<Inventory> inventories = new ArrayList<Inventory>(this.inventories);
		Collections.sort(inventories, Comparator.comparingLong(Inventory::getCreatedAt).reversed());
		return inventories;
	}

}
