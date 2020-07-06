package br.com.ewave.mt.livraria.model;

import java.io.Serializable;

import br.com.ewave.mt.livraria.repository.entity.GeneroEntity;
import lombok.Data;

@Data
public class GeneroModel implements Serializable {

	private static final long serialVersionUID = -7270359358373579578L;

	private Integer codigo;

	private String nome;

	public GeneroModel() {
		super();
	}

	public GeneroModel(GeneroEntity generoEntity) {
		this.codigo = generoEntity.getCodigo();
		this.nome = generoEntity.getNome();
	}

	public GeneroModel(String nome) {
		this.nome = nome;
	}

	public GeneroModel(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
}
