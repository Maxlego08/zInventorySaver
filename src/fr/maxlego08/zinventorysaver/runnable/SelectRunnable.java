package fr.maxlego08.zinventorysaver.runnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import fr.maxlego08.zinventorysaver.ZInventory;
import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.InventoryManager;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.api.storage.IConnection;
import fr.maxlego08.zinventorysaver.api.storage.StorageManager;
import fr.maxlego08.zinventorysaver.zcore.logger.Logger;
import fr.maxlego08.zinventorysaver.zcore.logger.Logger.LogType;

public class SelectRunnable implements Runnable {

	private final IConnection connection;
	private final InventoryManager inventoryManager;
	private final StorageManager storageManager;

	/**
	 * @param connection
	 * @param inventoryManager
	 * @param storageManager
	 */
	public SelectRunnable(IConnection connection, InventoryManager inventoryManager, StorageManager storageManager) {
		super();
		this.connection = connection;
		this.inventoryManager = inventoryManager;
		this.storageManager = storageManager;
	}

	@Override
	public void run() {

		try {
			storageManager.setReady(false);

			Connection connection = this.connection.getConnection();
			
			String request = "SELECT * FROM players";
			
			PreparedStatement statement = connection.prepareStatement(request);
			ResultSet resultSet = statement.executeQuery();
			connection.commit();

			while (resultSet.next()) {
				
				UUID uniqueId = UUID.fromString(resultSet.getString("id"));
				UUID playerUniqueId = UUID.fromString(resultSet.getString("player_id"));
				String jsonItems = resultSet.getString("items");
				long createdAt = resultSet.getLong("created_at");
				
				PlayerInventory playerInventory = inventoryManager.createPlayerInventory(playerUniqueId);
				Inventory inventory = new ZInventory(uniqueId, jsonItems, createdAt);
				
				playerInventory.addInventory(inventory);
				
			}
			
			statement.close();
			
			Logger.info("Loading of players successfully completed.", LogType.INFO);
			storageManager.setReady(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
