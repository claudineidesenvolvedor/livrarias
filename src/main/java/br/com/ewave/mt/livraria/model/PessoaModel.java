package br.com.ewave.mt.livraria.model;

import java.io.Serializable;
import java.util.Calendar;

import br.com.ewave.mt.livraria.repository.entity.EnderecoEntity;
import br.com.ewave.mt.livraria.repository.entity.InstituicaoEntity;
import br.com.ewave.mt.livraria.repository.entity.PessoaEntity;
import br.com.ewave.mt.livraria.repository.entity.TelefoneEntity;
import br.com.ewave.mt.livraria.repository.entity.UsuarioEntity;
import br.com.ewave.mt.livraria.uteis.Status;
import br.com.ewave.mt.livraria.uteis.TipoSexo;
import lombok.Data;

@Data
public class PessoaModel implements Serializable {

	private static final long serialVersionUID = -6082133380563533491L;

	private Integer codigo;

	private String nome;

	private TipoSexo sexo;

	private Status status;

	private String email;

	private String CPF;

	private int emprestismo;

	private EnderecoModel enderecoModel;

	private UsuarioModel usuarioModel;

	private TelefoneModel telefoneModel;

	private InstituicaoModel instituicaoModel;

	private boolean bloquear;

	private Calendar dataBloqueio;

	public PessoaModel() {
		super();
	}

	public PessoaModel(PessoaEntity pessoaEntity) {
		this.codigo = pessoaEntity.getCodigo();
		this.nome = pessoaEntity.getNome().toUpperCase();
		this.sexo = pessoaEntity.getSexo();
		this.status = pessoaEntity.getStatus();
		this.email = pessoaEntity.getEmail();
		this.enderecoModel = new EnderecoModel(pessoaEntity.getEndereco());
		this.usuarioModel = new UsuarioModel(pessoaEntity.getUsuarioEntity());
		this.CPF = pessoaEntity.getCPF();
		this.emprestismo = pessoaEntity.getEmprestismo();
		this.bloquear = pessoaEntity.isBloquear();
		this.telefoneModel = new TelefoneModel(pessoaEntity.getTelefoneEntity());
		this.instituicaoModel = new InstituicaoModel(pessoaEntity.getInstituicaoEntity());
		this.dataBloqueio = pessoaEntity.getDataBloqueio();
	}

	public PessoaModel(Integer codigo, String nome, TipoSexo sexo, Status status, String email, EnderecoEntity endereco,
			boolean bloquear, UsuarioEntity usuarioEntity, String CPF, int emprestismo,
			InstituicaoEntity instituicaoEntity, TelefoneEntity telefoneEntity, Calendar dataBloqueio) {
		this.codigo = codigo;
		this.nome = nome;
		this.sexo = sexo;
		this.status = status;
		this.email = email;
		this.enderecoModel = new EnderecoModel(endereco);
		this.usuarioModel = new UsuarioModel(usuarioEntity);
		this.CPF = CPF;
		this.emprestismo = emprestismo;
		this.bloquear = bloquear;
		this.instituicaoModel = new InstituicaoModel(instituicaoEntity);
		this.telefoneModel = new TelefoneModel(telefoneEntity);
		this.dataBloqueio = dataBloqueio;
	}

	public String getCorLinha() {
		String cor = "";
		if (isPessoaAlterado())
			return "ALTERACAO";
		if (isPessoaInativado())
			cor = "INATIVACAO";
		return cor;

	}

	public boolean isPessoaAlterado() {
		return this.status.equals(Status.ALTERACAO);
	}

	public boolean isPessoaInativado() {
		return this.status.equals(Status.INATIVACAO);
	}

	public boolean isRequesito() {
		if (this.telefoneModel == null && this.email.isEmpty())
			return true;
		else
			return false;
	}
}
