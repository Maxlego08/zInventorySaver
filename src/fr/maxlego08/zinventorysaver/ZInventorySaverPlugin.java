package fr.maxlego08.zinventorysaver;

import org.bukkit.plugin.ServicePriority;

import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.api.storage.IStorage;
import fr.maxlego08.zinventorysaver.api.storage.StorageManager;
import fr.maxlego08.zinventorysaver.command.CommandManager;
import fr.maxlego08.zinventorysaver.inventory.VInventoryManager;
import fr.maxlego08.zinventorysaver.inventory.inventories.InventoryDefault;
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

		/* Add Listener */

		addListener(new AdapterListener(this));
		addListener(super.inventoryManager);

		/* Add Saver */
		addSave(Config.getInstance());

		getSavers().forEach(saver -> saver.load(this.getPersist()));

		this.storageManager = new ZStorageManager(this);
		this.storageManager.load(this.getPersist());

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

}
