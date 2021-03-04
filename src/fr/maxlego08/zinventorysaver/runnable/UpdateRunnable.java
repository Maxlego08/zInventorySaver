package fr.maxlego08.zinventorysaver.runnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.storage.IConnection;

public class UpdateRunnable implements Runnable {

	private final IConnection connection;
	private final Inventory inventory;

	/**
	 * @param connection
	 * @param inventory
	 */
	public UpdateRunnable(IConnection connection, Inventory inventory) {
		super();
		this.connection = connection;
		this.inventory = inventory;
	}

	@Override
	public void run() {

		try {
			Connection connection = this.connection.getConnection();

			String request = "UPDATE players SET items = ?, updated_at = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(request);

			statement.setString(1, inventory.serialize());
			statement.setLong(2, inventory.getUpdatedAt());
			statement.setString(3, inventory.getUniqueId().toString());

			statement.executeUpdate();
			connection.commit();

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
