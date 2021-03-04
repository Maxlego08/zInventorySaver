package fr.maxlego08.zinventorysaver.storage;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.api.storage.IStorage;
import fr.maxlego08.zinventorysaver.api.storage.Saveable;
import fr.maxlego08.zinventorysaver.api.storage.StorageManager;
import fr.maxlego08.zinventorysaver.save.Config;
import fr.maxlego08.zinventorysaver.storage.empty.DefaultStorage;
import fr.maxlego08.zinventorysaver.storage.sql.SqlStorage;
import fr.maxlego08.zinventorysaver.zcore.utils.storage.Persist;

public class ZStorageManager implements StorageManager, Saveable {

	private final ZInventorySaverPlugin plugin;
	private final IStorage iStorage;
	private boolean isReady = false;

	/**
	 * @param storage
	 * @param plugin
	 */
	public ZStorageManager(ZInventorySaverPlugin plugin) {
		super();
		this.plugin = plugin;

		switch (Config.storage) {
		case MARIADB:
		case MYSQL:
		case PGSQL:
			this.iStorage = new SqlStorage(Config.storage);
			break;
		default:
			this.iStorage = new DefaultStorage();
			break;
		}
	}

	@Override
	public void save(Persist persist) {
		switch (Config.storage) {
		case MARIADB:
		case MYSQL:
		case PGSQL:
			this.iStorage.save(persist, plugin);
			break;
		default:
			break;
		}
	}

	@Override
	public void load(Persist persist) {
		switch (Config.storage) {
		case MARIADB:
		case MYSQL:
		case PGSQL:
			this.iStorage.load(persist, plugin);
			break;
		default:
			this.isReady = true;
			break;
		}
	}

	@Override
	public IStorage getStorage() {
		return this.iStorage;
	}

	@Override
	public void setReady(boolean value) {
		this.isReady = value;
	}

	@Override
	public boolean isReady() {
		return this.isReady;
	}

}
