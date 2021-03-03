package fr.maxlego08.zinventorysaver.api;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.maxlego08.zinventorysaver.api.storage.IStorage;

public interface PlayerInventory {

	public UUID getUniqueId();
	
	public List<Inventory> getInventory();

	public void saveInventory(Player player, boolean force, IStorage iStorage);
	
}
