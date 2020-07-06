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

import lombok.Data;

@Data
@Entity
@Table(name = "autor")
@SequenceGenerator(name = "Autor_Seq", sequenceName = "seq_autor", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "AutorEntity.findAll", query = "SELECT p FROM AutorEntity p"),
		@NamedQuery(name = "AutorEntity.GroupByOrigemNome", query = "SELECT p FROM AutorEntity p WHERE p.nome like :nome "),
		@NamedQuery(name = "AutorEntity.findNome", query = "SELECT p FROM AutorEntity		p where p.nome = :nome"),
		@NamedQuery(name = "AutorEntity.GroupByCod", query = "SELECT  p FROM AutorEntity p WHERE p.codigo = :codigo ") })
public class AutorEntity implements Serializable {

	private static final long serialVersionUID = 7416958296301367882L;

	@Id
	@PrimaryKeyJoinColumn(name = "autorPK")
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Autor_Seq")
	private Integer codigo;

	@NotNull(message = "nome não pode-se null")
	@Column(name = "nome", nullable = false)
	private String nome;

	public AutorEntity() {
		super();
	}

	public AutorEntity(AutorModel autorModel) {
		this.codigo = autorModel.getCodigo();
		this.nome = autorModel.getNome().toUpperCase();
		;
	}

	public AutorEntity(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

}
