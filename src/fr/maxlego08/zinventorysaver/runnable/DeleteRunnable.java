package fr.maxlego08.zinventorysaver.runnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.storage.IConnection;
import fr.maxlego08.zinventorysaver.zcore.logger.Logger;

public class DeleteRunnable implements Runnable {

	private final IConnection connection;
	private final Inventory inventory;

	/**
	 * @param connection
	 * @param inventory
	 */
	public DeleteRunnable(IConnection connection, Inventory inventory) {
		super();
		this.connection = connection;
		this.inventory = inventory;
	}

	@Override
	public void run() {

		try {

			Connection connection = this.connection.getConnection();
			String request = "DELETE FROM players WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(request);
			
			statement.setString(1, inventory.getUniqueId().toString());
			
			statement.executeUpdate();
			connection.commit();
			statement.close();
			
			Logger.info("Deletion of the inventory: " + inventory.getUniqueId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
