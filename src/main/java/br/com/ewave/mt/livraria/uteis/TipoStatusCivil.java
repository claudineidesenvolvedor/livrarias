package br.com.ewave.mt.livraria.uteis;

public enum TipoStatusCivil {

	SOLTEIRO("Solteiro (a)"), UNIAOCIVILESTAVEL("União Civil Estável"), CASADDO("Casado (a)"),
	SEPARADOJUDICIALMENTE("Separado Judicialmente"), DIVPRCIADO(" Divorciado (a)"), VIUVO("Viúvo (a)");
	
	private String descricao;

	TipoStatusCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
