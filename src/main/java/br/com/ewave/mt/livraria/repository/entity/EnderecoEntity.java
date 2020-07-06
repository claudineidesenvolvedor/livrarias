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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.ewave.mt.livraria.model.EnderecoModel;
import lombok.Data;

@Data
@Entity
@Table(name = "endereco")
@SequenceGenerator(name = "Endereco_Seq", sequenceName = "seq_endereco", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "EnderecoEntity.findAll", query = "SELECT p FROM EnderecoEntity p"),
		@NamedQuery(name = "EnderecoEntity.findCEP", query = "SELECT p FROM EnderecoEntity p where p.cep = :cep"),
		@NamedQuery(name = "EnderecoEntity.GroupByCod", query = "SELECT  p FROM EnderecoEntity p WHERE p.codigo = :codigo ") })
public class EnderecoEntity implements Serializable {

	private static final long serialVersionUID = 6172353236768570803L;

	@Id
	@PrimaryKeyJoinColumn(name = "enderecoPK")
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Endereco_Seq")
	private Integer codigo;

	@NotBlank
	@Size(max = 150)
	@Column(name = "logradouro", nullable = false)
	private String logradouro;

	@NotBlank
	@Size(max = 20)
	@Column(name = "numero", nullable = false, length = 20)
	private String numero;

	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "CEP", nullable = false )
	private String cep;

	@Column(name = "bairro", nullable = false)
	private String bairro;
	
	@Column(name = "cidade", nullable = false)
	private String cidade;

	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "pais", nullable = false)
	private String pais;

	public EnderecoEntity() {
		super();
	}

	public EnderecoEntity(Integer codigo, String logradouro, String numero, String complemento, String cep,
			String bairro,String cidade,String estado,String pais) {
		this.codigo = codigo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais  = pais;
	}

	public EnderecoEntity(EnderecoModel entity) {
		this.codigo = entity.getCodigo();
		this.logradouro = entity.getLogradouro().toUpperCase();
		this.numero = entity.getNumero();
		this.complemento = entity.getComplemento().toUpperCase();
		this.cep = entity.getCep();
		this.bairro = entity.getBairro().toUpperCase();
		this.cidade = entity.getCidade().toUpperCase();
		this.estado = entity.getEstado().toUpperCase();;
		this.pais  = entity.getPais().toUpperCase();;
	}

}
