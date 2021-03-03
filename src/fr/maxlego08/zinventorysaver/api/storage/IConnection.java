package fr.maxlego08.zinventorysaver.api.storage;

import java.sql.Connection;
import java.sql.SQLException;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;

public interface IConnection {

	/**
	 * 
	 * @return
	 */
	public Connection getConnection();

	/*
	 * 
	 */
	public void asyncConnect();

	/**
	 * 
	 * @throws SQLException
	 */
	public void connect() throws SQLException;

	/**
	 * 
	 */
	public void disconnect();

	public void selectItems(ZInventorySaverPlugin plugin, IStorage sqlStorage);

	public void asyncInsert(PlayerInventory playerInventory, Inventory inventory);

}
