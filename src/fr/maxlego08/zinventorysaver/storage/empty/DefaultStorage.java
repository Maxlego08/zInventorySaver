package fr.maxlego08.zinventorysaver.storage.empty;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.api.storage.IConnection;
import fr.maxlego08.zinventorysaver.api.storage.IStorage;
import fr.maxlego08.zinventorysaver.zcore.utils.storage.Persist;

public class DefaultStorage implements IStorage {

	public DefaultStorage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load(Persist persist, ZInventorySaverPlugin plugin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Persist persist, ZInventorySaverPlugin plugin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IConnection getIConnection() {
		return null;
	}

	@Override
	public void asyncInsert(PlayerInventory zPlayerInventory, Inventory inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}

}
