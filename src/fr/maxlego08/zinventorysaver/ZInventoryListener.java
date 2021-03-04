package fr.maxlego08.zinventorysaver;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.api.storage.StorageManager;
import fr.maxlego08.zinventorysaver.listener.ListenerAdapter;

public class ZInventoryListener extends ListenerAdapter {

	private final InventoryManager inventoryManager;
	private final StorageManager storageManager;

	/**
	 * @param inventoryManager
	 * @param storageManager
	 */
	public ZInventoryListener(InventoryManager inventoryManager, StorageManager storageManager) {
		super();
		this.inventoryManager = inventoryManager;
		this.storageManager = storageManager;
	}

	@Override
	protected void onQuit(PlayerQuitEvent event, Player player) {
		if (storageManager.isReady())
			inventoryManager.saveInventory(player);
	}

	@Override
	public void onPlayerDeath(PlayerDeathEvent event, Player player) {
		if (storageManager.isReady())
			inventoryManager.saveInventory(player);
	}

}
