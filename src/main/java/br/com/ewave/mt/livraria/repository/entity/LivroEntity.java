package br.com.ewave.mt.livraria.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.ewave.mt.livraria.model.GeneroModel;
import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.uteis.Status;
import lombok.Data;

@Data
@Entity
@Table(name = "livro")
@SequenceGenerator(name = "Livro_Seq", sequenceName = "seq_livro", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "LivroEntity.findAll", query = "SELECT p FROM LivroEntity p"),
		@NamedQuery(name = "LivroEntity.GroupByTitulo", query = "SELECT  p FROM LivroEntity p WHERE p.titulo = :titulo "),
		@NamedQuery(name = "LivroEntity.GroupByCod", query = "SELECT  p FROM LivroEntity p WHERE p.codigo = :codigo ") })
public class LivroEntity implements Serializable {

	private static final long serialVersionUID = 814137790033349491L;

	@Id
	@PrimaryKeyJoinColumn(name = "livroPK")
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Livro_Seq")
	private Integer codigo;

	@NotNull(message = "titulo não pode-se null")
	@Column(name = "titulo", nullable = false)
	private String titulo;

	@OneToOne
	@NotNull(message = "genero não pode-se null")
	@JoinColumn(name = "genero_id", foreignKey = @ForeignKey(name = "generoFK"), nullable = false)
	private GeneroEntity generoEntity;

	@OneToOne
	@NotNull(message = "autor não pode-se null")
	@JoinColumn(name = "autor_id", foreignKey = @ForeignKey(name = "autorFK"), nullable = false)
	private AutorEntity autorEntity;

	@Column(name = "sinopse")
	private String sinopse;

	@NotNull(message = "capa não pode-se null")
	@Column(name = "capa", nullable = false)
	private String capa;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	@Column(name = "bloquear")
	private boolean bloquear = false;

	public LivroEntity() {
		super();
	}

	public LivroEntity(LivroModel livroModel) {
		this.codigo = livroModel.getCodigo();
		this.titulo = livroModel.getTitulo().toUpperCase();
		this.generoEntity = new GeneroEntity(livroModel.getGeneroModel());
		this.autorEntity = new AutorEntity(livroModel.getAutorModel());
		this.sinopse = livroModel.getSinopse().toUpperCase();
		this.capa = livroModel.getCapa();
		this.status = livroModel.getStatus();
		this.bloquear = livroModel.isBloquear();
	}

	public LivroEntity(Integer codigo, String titulo, GeneroModel genero, AutorModel autor, String sinopse, String capa,
			Status status, boolean bloquear) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.generoEntity = new GeneroEntity(genero);
		this.autorEntity = new AutorEntity(autor);
		this.sinopse = sinopse;
		this.capa = capa;
		this.status = status;
		this.bloquear = bloquear;
	}

}
