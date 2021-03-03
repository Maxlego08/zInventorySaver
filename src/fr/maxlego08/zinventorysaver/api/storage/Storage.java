package fr.maxlego08.zinventorysaver.api.storage;

public enum Storage {

	MYSQL("jdbc:mysql://"),

	MARIADB("jdbc:mariadb://"), 
	PGSQL("jdbc:postgresql://"), 

	;

	private final String urlBase;

	/**
	 * @param urlBase
	 */
	private Storage(String urlBase) {
		this.urlBase = urlBase;
	}

	private Storage() {
		this(null);
	}

	public String getUrlBase() {
		return urlBase;
	}

}
