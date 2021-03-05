package fr.maxlego08.zinventorysaver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.api.storage.IStorage;
import fr.maxlego08.zinventorysaver.save.Config;
import fr.maxlego08.zinventorysaver.zcore.enums.EnumInventory;
import fr.maxlego08.zinventorysaver.zcore.enums.Message;
import fr.maxlego08.zinventorysaver.zcore.enums.Permission;
import fr.maxlego08.zinventorysaver.zcore.utils.ZUtils;

public class ZInventoryManager extends ZUtils implements InventoryManager {

	private final Map<UUID, PlayerInventory> playerInventories = new HashMap<UUID, PlayerInventory>();
	private final List<Player> searchingPlayers = new ArrayList<>();
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
	public void openInventory(Player player, Inventory object, PlayerInventory playerInventory) {
		if (!plugin.getStorageManager().isReady()) {
			message(player, Message.PLUGIN_NOT_READY);
			return;
		}
		super.createInventory(player, EnumInventory.INVENTORY_SHOW_INVENTORY, 1, object, playerInventory);
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

	@Override
	public void updateInventory(Player player, PlayerInventory playerInventory, Inventory inventory,
			Map<Integer, ItemStack> items) {

		if (items.size() == 0) {
			message(player, Message.INVENTORY_UPDATE_ERROR);
			return;
		}

		message(player, Message.INVENTORY_UPDATE_SUCCESS);
		inventory.setItems(items);
		IStorage iStorage = this.getIStorage();
		iStorage.updateInventory(inventory);
		this.openPlayer(player, playerInventory);
	}

	@Override
	public void searchPlayer(Player player) {

		if (searchingPlayers.contains(player)) {
			message(player, Message.SEARCH_ALREADY);
			return;
		}

		searchingPlayers.add(player);
		player.closeInventory();
		message(player, Message.SEARCH_START);
	}

	@Override
	public boolean searchPlayer(Player player, String message) {

		if (!searchingPlayers.contains(player))
			return false;

		Optional<OfflinePlayer> optional = Arrays.asList(Bukkit.getOfflinePlayers()).stream().filter(offlinePlayer -> {
			if (offlinePlayer == null)
				return false;
			String name = offlinePlayer.getName();
			return name != null
					&& (name.equalsIgnoreCase(message) || message.toLowerCase().contains(name.toLowerCase()));
		}).findFirst();

		searchingPlayers.remove(player);
		if (!optional.isPresent()) {
			message(player, Message.SEARCH_ERROR, message);
			return true;
		}

		OfflinePlayer offlinePlayer = optional.get();
		Optional<PlayerInventory> optional2 = this.getInventory(offlinePlayer);

		if (!optional2.isPresent()) {
			message(player, Message.SEARCH_ERROR_INVENTORY);
			return true;
		}

		this.openPlayer(player, optional2.get());
		return true;

	}

	@Override
	public void removeSearch(Player player) {
		searchingPlayers.remove(player);
	}

	@Override
	public void clearExpireInventories() {
		IStorage iStorage = this.getIStorage();
		this.playerInventories.values().forEach(players -> players.clearExpireInventories(iStorage));
	}

	@Override
	public void reload(Player player) {
		player.closeInventory();
		Config.getInstance().load(plugin.getPersist());
		message(player, Message.CONFIG_RELOAD);
	}

	@Override
	public void alertItem(ItemStack itemStack, Player target) {
		boolean needAlert = Config.exploitDetectorItems.stream()
				.anyMatch(builder -> builder.build().isSimilar(itemStack));
		if (needAlert) {
			Bukkit.getOnlinePlayers().forEach(player -> {
				if (player.hasPermission(Permission.ZINVENTORY_ALERT.getPermission())) {

					String item = "x" + itemStack.getAmount() + " " + getItemName(itemStack);
					message(player, Message.INVENTORY_ALERT, target.getName(), item);

				}
			});
		}
	}

	@Override
	public void savePlayers() {
		Bukkit.getOnlinePlayers().forEach(player -> saveInventory(player));
	}

}
