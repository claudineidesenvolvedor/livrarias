package br.com.ewave.mt.livraria.uteis;

public enum TipoPessoa {

	FISICA("F�sica"), JURIDICA("Jur�dica");

	private String descricao;

	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
