package fr.maxlego08.zinventorysaver.zcore.enums;

public enum EnumInventory {

	INVENTORY_DEFAULT(1), 
	INVENTORY_PLAYERS(2), 
	INVENTORY_PLAYER(3),
	
	;
	
	private final int id;

	private EnumInventory(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
