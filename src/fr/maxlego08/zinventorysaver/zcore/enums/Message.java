package fr.maxlego08.zinventorysaver.zcore.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Message {

	PREFIX("�8(�6zInventory�8)"),
	
	TELEPORT_MOVE("�cVous ne devez pas bouger !"),
	TELEPORT_MESSAGE("�7T�l�portatio dans �3%s �7secondes !"),
	TELEPORT_ERROR("�cVous avez d�j� une t�l�portation en cours !"),
	TELEPORT_SUCCESS("�7T�l�portation effectu� !"),
	
	INVENTORY_NULL("�cImpossible de trouver l'inventaire avec l'id �6%s�c."),
	INVENTORY_CLONE_NULL("�cThe clone of the inventory is null !"),
	INVENTORY_OPEN_ERROR("�cAn error occurred with the opening of the inventory �6%s�c."),
	
	TIME_DAY("%02d jour(s) %02d heure(s) %02d minute(s) %02d seconde(s)"),
	TIME_HOUR("%02d heure(s) %02d minute(s) %02d seconde(s)"),
	TIME_HOUR_SIMPLE("%02d:%02d:%02d"),
	TIME_MINUTE("%02d minute(s) %02d seconde(s)"),
	TIME_SECOND("%02d seconde(s)"),
	
	COMMAND_SYNTAXE_ERROR("�cYou have to execute the command like this�7: �a%s"),
	COMMAND_NO_PERMISSION("�cYou do not have permission to execute this command.."),
	COMMAND_NO_CONSOLE("�cOnly a player can execute this command."),
	COMMAND_NO_ARG("�cImpossible to find the command with its arguments."),
	COMMAND_SYNTAXE_HELP("�a%s �b� �7%s"), 
	PLUGIN_NOT_READY("�cThe plugin is not yet activated, please wait."),
	
	INVENTORY_UPDATE_ERROR("�cNo item in the inventory, impossible to save it."),
	INVENTORY_UPDATE_SUCCESS("�aYou have just modified the inventory."), 
	
	SEARCH_ALREADY("�cYou are already looking for a player."), 
	SEARCH_START("�aWrite the nickname of the player you are looking for in the chat."), 
	SEARCH_ERROR("�cUnable to find the player �f%s�c."), 
	SEARCH_ERROR_INVENTORY("�cThe player has no saved inventory."), 
	
	CONFIG_RELOAD("�aReload done !"), 
	INVENTORY_ALERT("�aThe player �f%s �ahas �7�n%s�a in his inventory."),
	
	
	;

	private List<String> messages;
	private String message;
	private Map<String, Object> titles = new HashMap<>();
	private boolean use = true;

	/**
	 * 
	 * @param message
	 */
	private Message(String message) {
		this.message = message;
		this.use = true;
	}

	private Message(String title, String subTitle, int a, int b, int c) {
		this.use = true;
		this.titles.put("title", title);
		this.titles.put("subtitle", subTitle);
		this.titles.put("start", a);
		this.titles.put("time", b);
		this.titles.put("end", c);
		this.titles.put("isUse", true);
	}

	/**
	 * 
	 * @param message
	 */
	private Message(String... message) {
		this.messages = Arrays.asList(message);
		this.use = true;
	}

	/**
	 * 
	 * @param message
	 * @param use
	 */
	private Message(String message, boolean use) {
		this.message = message;
		this.use = use;
	}

	public String getMessage() {
		return message;
	}

	public String toMsg() {
		return message;
	}

	public String msg() {
		return message;
	}

	public boolean isUse() {
		return use;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMessages() {
		return messages == null ? Arrays.asList(message) : messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public boolean isMessage() {
		return messages != null && messages.size() > 1;
	}

	public String getTitle() {
		return (String) titles.get("title");
	}

	public Map<String, Object> getTitles() {
		return titles;
	}

	public void setTitles(Map<String, Object> titles) {
		this.titles = titles;
	}

	public String getSubTitle() {
		return (String) titles.get("subtitle");
	}

	public boolean isTitle() {
		return titles.containsKey("title");
	}

	public int getStart() {
		return ((Number) titles.get("start")).intValue();
	}

	public int getEnd() {
		return ((Number) titles.get("end")).intValue();
	}

	public int getTime() {
		return ((Number) titles.get("time")).intValue();
	}

	public boolean isUseTitle() {
		return (boolean) titles.getOrDefault("isUse", "true");
	}

	public String replace(String a, String b) {
		return message.replace(a, b);
	}

}

