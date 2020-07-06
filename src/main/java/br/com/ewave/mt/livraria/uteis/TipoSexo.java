package br.com.ewave.mt.livraria.uteis;

public enum TipoSexo {

	MASCULINO("Masculino"), FEMININO("Feminino"),OUTRO("Outro");

	private String descricao;

	TipoSexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
