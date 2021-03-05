package fr.maxlego08.zinventorysaver.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.zinventorysaver.api.storage.IStorage;

public interface InventoryManager {

	/***
	 * 
	 * @param offlinePlayer
	 * @return
	 */
	public Optional<PlayerInventory> getInventory(OfflinePlayer offlinePlayer);

	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public Optional<PlayerInventory> getInventory(UUID uuid);

	/**
	 * 
	 * @param offlinePlayer
	 * @return
	 */
	public PlayerInventory createPlayerInventory(OfflinePlayer offlinePlayer);

	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public PlayerInventory createPlayerInventory(UUID uuid);

	/**
	 * 
	 * @param uuid
	 * @param inventories
	 * @return
	 */
	public PlayerInventory createPlayerInventory(UUID uuid, List<Inventory> inventories);

	/**
	 * 
	 * @param player
	 */
	public void openManager(Player player);

	/**
	 * Save inventory
	 * 
	 * @param player
	 */
	public void saveInventory(Player player);

	/**
	 * 
	 * @return
	 */
	public IStorage getIStorage();

	/**
	 * 
	 * @param player
	 */
	public void openPlayers(Player player);

	/**
	 * 
	 * @return
	 */
	public List<PlayerInventory> getPlayers();

	/**
	 * 
	 * @param player
	 * @param playerInventory
	 */
	public void openPlayer(Player player, PlayerInventory playerInventory);

	/**
	 * 
	 * @param player
	 * @param object
	 */
	public void openInventory(Player player, Inventory object, PlayerInventory playerInventory);

	/**
	 * 
	 * @param player
	 * @param playerInventory
	 * @param inventory
	 * @param items
	 */
	public void updateInventory(Player player, PlayerInventory playerInventory, Inventory inventory,
			Map<Integer, ItemStack> items);

	/**
	 * 
	 * @param player
	 */
	public void searchPlayer(Player player);

	/**
	 * 
	 * @param player
	 * @param message
	 */
	public void searchPlayer(Player player, String message);
	
	public void removeSearch(Player player);


}
