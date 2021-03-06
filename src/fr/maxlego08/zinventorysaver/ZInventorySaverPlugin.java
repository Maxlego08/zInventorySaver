package fr.maxlego08.zinventorysaver;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.plugin.ServicePriority;

import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.api.storage.IStorage;
import fr.maxlego08.zinventorysaver.api.storage.StorageManager;
import fr.maxlego08.zinventorysaver.command.CommandManager;
import fr.maxlego08.zinventorysaver.command.commands.CommandZInventory;
import fr.maxlego08.zinventorysaver.inventory.VInventoryManager;
import fr.maxlego08.zinventorysaver.inventory.inventories.InventoryDefault;
import fr.maxlego08.zinventorysaver.inventory.inventories.InventoryPlayer;
import fr.maxlego08.zinventorysaver.inventory.inventories.InventoryPlayers;
import fr.maxlego08.zinventorysaver.inventory.inventories.InventoryShowInventory;
import fr.maxlego08.zinventorysaver.listener.AdapterListener;
import fr.maxlego08.zinventorysaver.save.Config;
import fr.maxlego08.zinventorysaver.storage.ZStorageManager;
import fr.maxlego08.zinventorysaver.zcore.ZPlugin;
import fr.maxlego08.zinventorysaver.zcore.enums.EnumInventory;

/**
 * System to create your plugins very simply Projet:
 * https://github.com/Maxlego08/TemplatePlugin
 * 
 * @author Maxlego08
 *
 */
public class ZInventorySaverPlugin extends ZPlugin {

	private final InventoryManager inventoryManager = new ZInventoryManager(this);
	private StorageManager storageManager;

	@Override
	public void onEnable() {

		preEnable();

		this.getServer().getServicesManager().register(InventoryManager.class, inventoryManager, this,
				ServicePriority.High);

		commandManager = new CommandManager(this);

		super.inventoryManager = VInventoryManager.getInstance();

		/* Inventories */

		this.registerInventory(EnumInventory.INVENTORY_DEFAULT, new InventoryDefault());
		this.registerInventory(EnumInventory.INVENTORY_PLAYERS, new InventoryPlayers());
		this.registerInventory(EnumInventory.INVENTORY_PLAYER, new InventoryPlayer());
		this.registerInventory(EnumInventory.INVENTORY_SHOW_INVENTORY, new InventoryShowInventory());

		this.registerCommand("zinventory", new CommandZInventory(), "zi");

		/* Add Listener */

		addListener(new AdapterListener(this));
		addListener(super.inventoryManager);


		/* Add Saver */
		addSave(Config.getInstance());

		getSavers().forEach(saver -> saver.load(this.getPersist()));

		this.storageManager = new ZStorageManager(this);
		this.storageManager.load(this.getPersist());
		
		addListener(new ZInventoryListener(this.inventoryManager, this.storageManager));

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				inventoryManager.savePlayers();
			}
		}, Config.autoSaveInSecond * 1000, Config.autoSaveInSecond * 1000);
		
		postEnable();
	}

	@Override
	public void onDisable() {

		preDisable();

		this.storageManager.save(this.getPersist());
		getSavers().forEach(saver -> saver.save(this.getPersist()));

		postDisable();

	}

	public InventoryManager getManager() {
		return inventoryManager;
	}

	public IStorage getStorage() {
		return this.storageManager.getStorage();
	}

	public StorageManager getStorageManager() {
		return storageManager;
	}

}
