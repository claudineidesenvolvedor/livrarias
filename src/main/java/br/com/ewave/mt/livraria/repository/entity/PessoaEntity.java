package br.com.ewave.mt.livraria.repository.entity;

import java.io.Serializable;
import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.ewave.mt.livraria.model.EnderecoModel;
import br.com.ewave.mt.livraria.model.InstituicaoModel;
import br.com.ewave.mt.livraria.model.PessoaModel;
import br.com.ewave.mt.livraria.model.TelefoneModel;
import br.com.ewave.mt.livraria.model.UsuarioModel;
import br.com.ewave.mt.livraria.uteis.Status;
import br.com.ewave.mt.livraria.uteis.TipoSexo;
import lombok.Data;

@Data
@Entity
@Table(name = "pessoa")
@SequenceGenerator(name = "Pessoa_Seq", sequenceName = "seq_pessoa", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "PessoaEntity.findAll", query = "SELECT p FROM PessoaEntity p"),
		@NamedQuery(name = "PessoaEntity.findNome", query = "SELECT p FROM PessoaEntity p WHERE p.nome = :nome"),
		@NamedQuery(name = "PessoaEntity.GroupByCod", query = "SELECT  p FROM PessoaEntity p WHERE p.codigo = :codigo ") })
public class PessoaEntity implements Serializable {

	private static final long serialVersionUID = -2525919085515462813L;

	@Id
	@PrimaryKeyJoinColumn(name = "pessoaPK")
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Pessoa_Seq")
	private Integer codigo;

	@NotNull(message = "nome não pode-se null")
	@Column(name = "nm_pessoa", nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "fl_sexo")
	private TipoSexo sexo;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	@Column(name = "ds_email")
	@Email(message = "e-mail inválido")
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataBloqueio")
	private Calendar dataBloqueio;

	@NotNull(message = "CPF não pode-se null")
	@CPF(message = "CPF inválido")
	@Column(name = "CPF", nullable = false)
	private String CPF;

	@Column(name = "emprestismo")
	private int emprestismo = 0;

	@Column(name = "bloquear")
	private boolean bloquear = false;

	@NotNull(message = "endereço não pode-se null")
	@OneToOne
	@JoinColumn(name = "id_endereco_pessoa", foreignKey = @ForeignKey(name = "enderecoPessoaFK"), nullable = false)
	private EnderecoEntity endereco;

	@OneToOne
	@JoinColumn(name = "id_telefone_pessoa", foreignKey = @ForeignKey(name = "telefonePessoaFK"))
	private TelefoneEntity telefoneEntity;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro", foreignKey = @ForeignKey(name = "usuarioPessoaFK"))
	private UsuarioEntity usuarioEntity;

	@OneToOne
	@JoinColumn(name = "id_instituicao_cadastro", foreignKey = @ForeignKey(name = "instituicaoPessoaFK"), unique = true)
	private InstituicaoEntity instituicaoEntity;

	public PessoaEntity() {
		super();
	}

	public PessoaEntity(PessoaModel pessoaModel) {
		this.codigo = pessoaModel.getCodigo();
		this.nome = pessoaModel.getNome().toUpperCase();
		this.sexo = pessoaModel.getSexo();
		this.status = pessoaModel.getStatus();
		this.email = pessoaModel.getEmail();
		this.endereco = new EnderecoEntity(pessoaModel.getEnderecoModel());
		this.usuarioEntity = new UsuarioEntity(pessoaModel.getUsuarioModel());
		this.emprestismo = pessoaModel.getEmprestismo();
		this.instituicaoEntity = new InstituicaoEntity(pessoaModel.getInstituicaoModel());
		this.telefoneEntity = new TelefoneEntity(pessoaModel.getTelefoneModel());
		this.CPF = pessoaModel.getCPF();
		this.bloquear = pessoaModel.isBloquear();
		this.dataBloqueio = pessoaModel.getDataBloqueio();
	}

	public PessoaEntity(Integer codigo, String nome, TipoSexo sexo, Status status, String CPF, String email,
			EnderecoModel endereco, boolean bloquear, UsuarioModel usuarioModel, InstituicaoModel instituicaoModel,
			int emprestismo, TelefoneModel telefoneModel, Calendar dataBloqueio) {
		this.codigo = codigo;
		this.nome = nome;
		this.sexo = sexo;
		this.status = status;
		this.email = email;
		this.endereco = new EnderecoEntity(endereco);
		this.usuarioEntity = new UsuarioEntity(usuarioModel);
		this.emprestismo = emprestismo;
		this.bloquear = bloquear;
		this.instituicaoEntity = new InstituicaoEntity(instituicaoModel);
		this.telefoneEntity = new TelefoneEntity(telefoneModel);
		this.CPF = CPF;
		this.dataBloqueio = dataBloqueio;
	}

}
