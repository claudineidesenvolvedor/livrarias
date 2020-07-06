package br.com.ewave.mt.livraria.uteis;

public enum Status {
	
	INCLUSAO("inclus�o"), ALTERACAO("altera��o"), INATIVACAO("inativa��o");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
