package fr.maxlego08.zinventorysaver.save;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import fr.maxlego08.zinventorysaver.api.storage.Saveable;
import fr.maxlego08.zinventorysaver.api.storage.Storage;
import fr.maxlego08.zinventorysaver.zcore.utils.builder.ItemBuilder;
import fr.maxlego08.zinventorysaver.zcore.utils.storage.Persist;

public class Config implements Saveable {

	public static Storage storage = Storage.MYSQL;
	public static String user = "homestead";
	public static String password = "secret";
	public static String host = "192.168.10.10";
	public static String database = "zinventory";
	public static int port = 3306;

	public static boolean saveOnDisconnect = true;
	public static boolean saveOnDeath = true;
	public static long delayBetweenSaveInSecond = 60 * 5; // 5 minutes
	public static long deletedInventoryAfterInSecond = 60 * 60 * 24 * 7 * 4; // 1 months
	
	public static long autoSaveInSecond = 60 * 60 * 1;
	
	public static List<ItemBuilder> exploitDetectorItems = new ArrayList<ItemBuilder>();

	static {
		
		exploitDetectorItems.add(new ItemBuilder(Material.BEDROCK));
		exploitDetectorItems.add(new ItemBuilder(Material.BEACON));
		
	}
	
	/**
	 * static Singleton instance.
	 */
	private static volatile Config instance;

	/**
	 * Private constructor for singleton.
	 */
	private Config() {
	}

	/**
	 * Return a singleton instance of Config.
	 */
	public static Config getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (Config.class) {
				if (instance == null) {
					instance = new Config();
				}
			}
		}
		return instance;
	}

	public void save(Persist persist) {
		persist.save(getInstance());
	}

	public void load(Persist persist) {
		persist.loadOrSaveDefault(getInstance(), Config.class);
	}

}
