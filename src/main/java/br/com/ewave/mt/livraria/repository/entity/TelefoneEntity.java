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

import br.com.ewave.mt.livraria.model.TelefoneModel;
import br.com.ewave.mt.livraria.uteis.TipoTelefone;
import lombok.Data;

@Entity
@Table(name = "telefone")
@Data
@SequenceGenerator(name = "Telefone_Seq", sequenceName = "seq_telefone", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "TelefoneEntity.findAll", query = "SELECT t FROM TelefoneEntity t"),
		@NamedQuery(name = "TelefoneEntity.GroupByNumero", query = "SELECT  t FROM TelefoneEntity t WHERE t.numero = :numero GROUP By t.numero") })
public class TelefoneEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@PrimaryKeyJoinColumn(name = "telefonePK")
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Telefone_Seq")
	private Integer id;

	@Column(name = "numero")
	private String numero;

	@Column(name = "tipotelefone")
	private TipoTelefone tipoTelefone;

	public TelefoneEntity() {
		super();
	}

	public TelefoneEntity(TelefoneModel telefoneModel) {
		this.id = telefoneModel.getId();
		this.numero = telefoneModel.getNumero();
		this.tipoTelefone = telefoneModel.getTipoTelefone();
	}

	public TelefoneEntity(Integer id, String numero, TipoTelefone tipoTelefone) {
		this.id = id;
		this.numero = numero;
		this.tipoTelefone = tipoTelefone;
	}
}
