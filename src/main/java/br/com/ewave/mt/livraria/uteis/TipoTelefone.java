package br.com.ewave.mt.livraria.uteis;

public enum TipoTelefone {
	RESIDENCIAL("Residencial"), CELULAR("Celular"), FAX("FAX"), CONTATO("Contato");

	private String value;

	private TipoTelefone(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
