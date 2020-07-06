package br.com.ewave.mt.livraria.repository.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class AutorModel implements Serializable {

	private static final long serialVersionUID = -4776125255774175831L;

	private Integer codigo;

	private String nome;

	public AutorModel() {
		super();
	}

	public AutorModel(AutorEntity autorEntity) {
		this.codigo = autorEntity.getCodigo();
		this.nome = autorEntity.getNome();
	}
	public AutorModel( String nome) {
		this.nome = nome;
	}
	public AutorModel(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
}
