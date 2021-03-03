package fr.maxlego08.zinventorysaver.storage.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import fr.maxlego08.zinventorysaver.ZInventorySaverPlugin;
import fr.maxlego08.zinventorysaver.api.Inventory;
import fr.maxlego08.zinventorysaver.api.PlayerInventory;
import fr.maxlego08.zinventorysaver.api.enums.Script;
import fr.maxlego08.zinventorysaver.api.storage.IConnection;
import fr.maxlego08.zinventorysaver.api.storage.IStorage;
import fr.maxlego08.zinventorysaver.api.storage.Storage;
import fr.maxlego08.zinventorysaver.save.Config;
import fr.maxlego08.zinventorysaver.storage.ZConnection;
import fr.maxlego08.zinventorysaver.zcore.logger.Logger;
import fr.maxlego08.zinventorysaver.zcore.logger.Logger.LogType;
import fr.maxlego08.zinventorysaver.zcore.utils.storage.Persist;
import fr.maxlego08.zinventorysaver.zcore.utils.storage.ScriptRunner;

public class SqlStorage implements IStorage {

	private IConnection iConnection;
	private final Storage storage;

	public SqlStorage(Storage storage) {
		this.storage = storage;
		
	}

	@Override
	public void load(Persist persist, ZInventorySaverPlugin plugin) {
		Logger.info("Load SQL...");
		
		String user = Config.user;
		String password = Config.password;
		String host = Config.host;
		String dataBase = Config.database;
		int port = Config.port;
		
		this.iConnection = new ZConnection(storage, user, password, host, dataBase, port);
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

			try {
				iConnection.connect();
				Logger.info("Database connect to " + host);
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}

			try {

				for (Script script : Script.values()) {
					File file = new File(plugin.getDataFolder(), "scripts/" + script.name().toLowerCase() + ".sql");
					ScriptRunner runner = new ScriptRunner(iConnection.getConnection());
					Reader reader = new BufferedReader(new FileReader(file));
					runner.runScript(reader);
					Logger.info("Script " + script.name() + " successfuly run", LogType.SUCCESS);
					reader.close();
				}

				Logger.info("Loading players", LogType.INFO);
				iConnection.selectItems(plugin, this);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

	@Override
	public void save(Persist persist, ZInventorySaverPlugin plugin) {
		Logger.info("Database disconnect...");
		this.iConnection.disconnect();
	}

	@Override
	public IConnection getIConnection() {
		return this.iConnection;
	}

	@Override
	public void asyncInsert(PlayerInventory playerInventory, Inventory inventory) {
		this.iConnection.asyncInsert(playerInventory, inventory);
	}

}
