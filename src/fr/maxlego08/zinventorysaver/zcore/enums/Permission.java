package fr.maxlego08.zinventorysaver.zcore.enums;

public enum Permission {
	ZINVENTORY_USE, ZINVENTORY_ALERT

	;

	private String permission;

	private Permission() {
		this.permission = this.name().toLowerCase().replace("_", ".");
	}

	public String getPermission() {
		return permission;
	}

}
