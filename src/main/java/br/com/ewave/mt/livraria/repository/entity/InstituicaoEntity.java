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

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.ewave.mt.livraria.model.EnderecoModel;
import br.com.ewave.mt.livraria.model.InstituicaoModel;
import br.com.ewave.mt.livraria.model.TelefoneModel;
import br.com.ewave.mt.livraria.uteis.Status;
import lombok.Data;

@Data
@Entity
@Table(name = "instituicao")
@SequenceGenerator(name = "Instituicao_Seq", sequenceName = "seq_instituicao", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "InstituicaoEntity.findAll", query = "SELECT p FROM InstituicaoEntity p"),
		@NamedQuery(name = "InstituicaoEntity.findNome", query = "SELECT p FROM InstituicaoEntity p WHERE p.nome = :nome"),
		@NamedQuery(name = "InstituicaoEntity.GroupByCod", query = "SELECT  p FROM InstituicaoEntity p WHERE p.codigo = :codigo ") })
public class InstituicaoEntity implements Serializable {

	private static final long serialVersionUID = 598064740979862580L;

	@Id
	@PrimaryKeyJoinColumn(name = "instituicaoPK")
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Instituicao_Seq")
	private Integer codigo;

	@NotNull(message = "nome não pode-se null")
	@Column(name = "nm_pessoa")
	private String nome;

	@NotNull(message = "CNPJ não pode-se null")
	@CNPJ(message = "CNPJ inválido")
	@Column(name = "cnpj", nullable = false)
	private String CNPJ;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	@OneToOne
	@JoinColumn(name = "id_endereco_pessoa", foreignKey = @ForeignKey(name = "enderecoInstituicaoFK"))
	private EnderecoEntity endereco;

	@NotNull(message = "telefon não pode-se null")
	@OneToOne
	@JoinColumn(name = "id_telefone_instituicao", foreignKey = @ForeignKey(name = "telefoneInstituicaoFK"), nullable = false)
	private TelefoneEntity telefoneEntity;

	public InstituicaoEntity() {
		super();
	}

	public InstituicaoEntity(InstituicaoModel instituicaoModel) {
		this.codigo = instituicaoModel.getCodigo();
		this.nome = instituicaoModel.getNome().toUpperCase();
		this.status = instituicaoModel.getStatus();
		this.endereco = new EnderecoEntity(instituicaoModel.getEnderecoModel());
		this.CNPJ = instituicaoModel.getCNPJ();
		this.telefoneEntity = new TelefoneEntity(instituicaoModel.getTelefoneModel());
	}

	public InstituicaoEntity(Integer codigo, String nome, String CNPJ, Status status, EnderecoModel enderecoModel,
			TelefoneModel telefoneModel) {
		this.codigo = codigo;
		this.nome = nome;
		this.status = status;
		this.endereco = new EnderecoEntity(enderecoModel);
		this.telefoneEntity = new TelefoneEntity(telefoneModel);
		this.CNPJ = CNPJ;
	}
}
