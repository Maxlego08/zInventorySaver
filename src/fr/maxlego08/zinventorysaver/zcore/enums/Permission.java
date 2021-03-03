package fr.maxlego08.zinventorysaver.zcore.enums;

public enum Permission {
	ZINVENTORY_USE

	;

	private String permission;

	private Permission() {
		this.permission = this.name().toLowerCase().replace("_", ".");
	}

	public String getPermission() {
		return permission;
	}

}
