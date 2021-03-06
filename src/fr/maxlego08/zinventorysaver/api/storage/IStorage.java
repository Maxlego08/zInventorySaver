package fr.maxlego08.zinventorysaver.api.storage;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.zcore.utils.storage.Persist;

public interface IStorage {

	void load(Persist persist, ZInventorySaverPlugin plugin);

	void save(Persist persist, ZInventorySaverPlugin plugin);

	public IConnection getIConnection();

	void asyncInsert(PlayerInventory zPlayerInventory, Inventory inventory);

	void updateInventory(Inventory inventory);
	
	void deleteInventory(Inventory inventory);
	

}
