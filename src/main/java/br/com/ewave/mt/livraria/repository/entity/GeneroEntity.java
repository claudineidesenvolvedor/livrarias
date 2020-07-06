package br.com.ewave.mt.livraria.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.ewave.mt.livraria.model.GeneroModel;
import lombok.Data;

@Data
@Entity
@Table(name = "genero")
@SequenceGenerator(name = "Genero_Seq", sequenceName = "seq_genero", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "GeneroEntity.findAll", query = "SELECT p FROM GeneroEntity p"),
		@NamedQuery(name = "GeneroEntity.GroupByOrigemNome", query = "SELECT p FROM GeneroEntity p WHERE p.nome like :nome "),
		@NamedQuery(name = "GeneroEntity.findNome", query = "SELECT p FROM GeneroEntity		p where p.nome = :nome"),
		@NamedQuery(name = "GeneroEntity.GroupByCod", query = "SELECT  p FROM GeneroEntity p WHERE p.codigo = :codigo ") })
public class GeneroEntity implements Serializable {

	private static final long serialVersionUID = 6175210684024817089L;

	@Id
	@PrimaryKeyJoinColumn(name = "generoPK")
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Genero_Seq")
	private Integer codigo;

	@NotNull(message = "nome não pode-se null")
	@Column(name = "nome", nullable = false)
	private String nome;

	public GeneroEntity() {
		super();
	}

	public GeneroEntity(GeneroModel autorModel) {
		this.codigo = autorModel.getCodigo();
		this.nome = autorModel.getNome().toUpperCase();
		;
	}

	public GeneroEntity(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

}
