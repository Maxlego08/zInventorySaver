package fr.maxlego08.zinventorysaver.runnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.api.storage.IConnection;

public class InsertRunnable implements Runnable {

	private final PlayerInventory playerInventory;
	private final Inventory inventory;
	private final IConnection iConnection;

	/**
	 * @param playerInventory
	 * @param inventory
	 * @param iConnection
	 */
	public InsertRunnable(PlayerInventory playerInventory, Inventory inventory, IConnection iConnection) {
		super();
		this.playerInventory = playerInventory;
		this.inventory = inventory;
		this.iConnection = iConnection;
	}

	@Override
	public void run() {

		try {

			Connection connection = this.iConnection.getConnection();

			String request = "INSERT INTO players (id, items, player_id, created_at) VALUES ( ?, ?, ?, ? )";
			PreparedStatement statement = connection.prepareStatement(request);

			statement.setString(1, inventory.getUniqueId().toString());
			statement.setString(2, inventory.serialize());
			statement.setString(3, playerInventory.getUniqueId().toString());
			statement.setLong(4, inventory.getCreatedAt());

			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
