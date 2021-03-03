package fr.maxlego08.zinventorysaver.api.storage;

import fr.maxlego08.zinventorysaver.zcore.utils.storage.Persist;

public interface Saveable {
	
	/**
	 * 
	 * @param persist
	 */
	void save(Persist persist);
	
	/**
	 * 
	 * @param persist
	 */
	void load(Persist persist);
}
