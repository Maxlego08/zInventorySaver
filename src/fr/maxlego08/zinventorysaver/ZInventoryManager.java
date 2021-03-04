package fr.maxlego08.zinventorysaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.api.storage.IStorage;
import fr.maxlego08.zinventorysaver.zcore.enums.EnumInventory;
import fr.maxlego08.zinventorysaver.zcore.enums.Message;
import fr.maxlego08.zinventorysaver.zcore.utils.ZUtils;

public class ZInventoryManager extends ZUtils implements InventoryManager {

	private final Map<UUID, PlayerInventory> playerInventories = new HashMap<UUID, PlayerInventory>();
	private final ZInventorySaverPlugin plugin;

	/**
	 * @param plugin
	 */
	public ZInventoryManager(ZInventorySaverPlugin plugin) {
		super();
		this.plugin = plugin;
	}

	@Override
	public Optional<PlayerInventory> getInventory(OfflinePlayer offlinePlayer) {
		return this.getInventory(offlinePlayer.getUniqueId());
	}

	@Override
	public Optional<PlayerInventory> getInventory(UUID uuid) {
		return Optional.ofNullable(this.playerInventories.getOrDefault(uuid, null));
	}

	@Override
	public PlayerInventory createPlayerInventory(OfflinePlayer offlinePlayer) {
		return this.createPlayerInventory(offlinePlayer.getUniqueId());
	}

	@Override
	public PlayerInventory createPlayerInventory(UUID uuid) {
		return this.createPlayerInventory(uuid, new ArrayList<>());
	}

	@Override
	public PlayerInventory createPlayerInventory(UUID uuid, List<Inventory> inventories) {
		Optional<PlayerInventory> optional = this.getInventory(uuid);
		if (optional.isPresent())
			return optional.get();

		PlayerInventory inventory = new ZPlayerInventory(uuid, inventories);
		this.playerInventories.put(uuid, inventory);
		return inventory;
	}

	@Override
	public void openManager(Player player) {
		if (!plugin.getStorageManager().isReady()) {
			message(player, Message.PLUGIN_NOT_READY);
			return;
		}
		super.createInventory(player, EnumInventory.INVENTORY_DEFAULT);
	}

	@Override
	public void openPlayers(Player player) {
		if (!plugin.getStorageManager().isReady()) {
			message(player, Message.PLUGIN_NOT_READY);
			return;
		}
		super.createInventory(player, EnumInventory.INVENTORY_PLAYERS);
	}
	
	@Override
	public void openPlayer(Player player, PlayerInventory playerInventory) {
		if (!plugin.getStorageManager().isReady()) {
			message(player, Message.PLUGIN_NOT_READY);
			return;
		}
		super.createInventory(player, EnumInventory.INVENTORY_PLAYER, 1, playerInventory);
	}

	@Override
	public void saveInventory(Player player) {

		PlayerInventory inventory = this.createPlayerInventory(player);
		inventory.saveInventory(player, false, this.getIStorage());

	}

	@Override
	public IStorage getIStorage() {
		return this.plugin.getStorage();
	}

	@Override
	public List<PlayerInventory> getPlayers() {
		return new ArrayList<>(this.playerInventories.values());
	}

}
