package fr.maxlego08.zinventorysaver.api.storage;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.zcore.utils.storage.Persist;

public interface IStorage {

	void load(Persist persist, ZInventorySaverPlugin plugin);

	void save(Persist persist, ZInventorySaverPlugin plugin);

	

}
