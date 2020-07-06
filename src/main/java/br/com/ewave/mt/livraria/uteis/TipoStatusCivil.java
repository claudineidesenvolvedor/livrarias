package br.com.ewave.mt.livraria.uteis;

public enum TipoStatusCivil {

	SOLTEIRO("Solteiro (a)"), UNIAOCIVILESTAVEL("Uni�o Civil Est�vel"), CASADDO("Casado (a)"),
	SEPARADOJUDICIALMENTE("Separado Judicialmente"), DIVPRCIADO(" Divorciado (a)"), VIUVO("Vi�vo (a)");
	
	private String descricao;

	TipoStatusCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
