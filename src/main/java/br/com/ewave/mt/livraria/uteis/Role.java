package br.com.ewave.mt.livraria.uteis;

public enum Role {

	ADMIN("ADMIN"), COMUM("COMUM");

	private String value;

	private Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}