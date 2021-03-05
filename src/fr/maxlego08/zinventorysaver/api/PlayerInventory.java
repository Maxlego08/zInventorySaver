package fr.maxlego08.zinventorysaver.api;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.maxlego08.zinventorysaver.api.storage.IStorage;

public interface PlayerInventory {

	/**
	 * 
	 * @return
	 */
	public UUID getUniqueId();
	
	/**
	 * 
	 * @return
	 */
	public List<Inventory> getInventories();

	/**
	 * 
	 * @param player
	 * @param force
	 * @param iStorage
	 */
	public void saveInventory(Player player, boolean force, IStorage iStorage);

	/**
	 * 
	 * @param inventory
	 */
	public void addInventory(Inventory inventory);

	/**
	 * 
	 * @return
	 */
	public List<Inventory> getSortInventory();
	
	/**
	 * 
	 */
	public void clearExpireInventories(IStorage iStorage);
	
}
