package br.com.ewave.mt.livraria.uteis;

public enum TipoPessoa {

	FISICA("Física"), JURIDICA("Jurídica");

	private String descricao;

	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
