package fr.maxlego08.zinventorysaver;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.listener.ListenerAdapter;

public class ZInventoryListener extends ListenerAdapter {

	private final InventoryManager inventoryManager;

	/**
	 * @param inventoryManager
	 */
	public ZInventoryListener(InventoryManager inventoryManager) {
		super();
		this.inventoryManager = inventoryManager;
	}

	@Override
	protected void onQuit(PlayerQuitEvent event, Player player) {
		inventoryManager.saveInventory(player);
	}
	
}
