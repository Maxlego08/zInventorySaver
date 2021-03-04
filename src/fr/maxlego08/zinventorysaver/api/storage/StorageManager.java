package fr.maxlego08.zinventorysaver.api.storage;

public interface StorageManager extends Saveable{

	public IStorage getStorage();

	public void setReady(boolean value);
	
	public boolean isReady();
	
}
