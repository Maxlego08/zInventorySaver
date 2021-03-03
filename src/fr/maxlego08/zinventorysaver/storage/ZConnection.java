package fr.maxlego08.zinventorysaver.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.maxlego08.zinventorysaver.api.storage.IConnection;
import fr.maxlego08.zinventorysaver.api.storage.Storage;

public class ZConnection implements IConnection {

	private Connection connection;
	private final Storage storage;
	private final String user;
	private final String password;
	private final String host;
	private final String dataBase;
	private final int port;

	/**
	 * @param connection
	 * @param storage
	 * @param user
	 * @param password
	 * @param host
	 * @param dataBase
	 */
	public ZConnection(Storage storage, String user, String password, String host, String dataBase, int port) {
		super();
		this.storage = storage;
		this.user = user;
		this.password = password;
		this.host = host;
		this.dataBase = dataBase;
		this.port = port;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void asyncConnect() {
		Thread thread = new Thread("sql-connect") {

			@Override
			public void run() {
				try {
					connect();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		};
		thread.start();

	}

	@Override
	public void connect() throws SQLException {

		if (this.connection != null)
			return;

		String url = storage.getUrlBase() + host + ":" + port + "/" + dataBase;
		this.connection = DriverManager.getConnection(url, user, password);
	}

	@Override
	public void disconnect() {
		if (this.connection != null)
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
