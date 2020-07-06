package br.com.ewave.mt.livraria.uteis;

public enum Status {
	
	INCLUSAO("inclusão"), ALTERACAO("alteração"), INATIVACAO("inativação");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
