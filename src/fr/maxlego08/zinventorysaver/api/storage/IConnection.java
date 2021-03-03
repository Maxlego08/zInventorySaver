package fr.maxlego08.zinventorysaver.api.storage;

import java.sql.Connection;
import java.sql.SQLException;

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

}
