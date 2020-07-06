package br.com.ewave.mt.livraria.model;

import java.io.Serializable;

import br.com.ewave.mt.livraria.repository.entity.EnderecoEntity;
import br.com.ewave.mt.livraria.repository.entity.InstituicaoEntity;
import br.com.ewave.mt.livraria.repository.entity.TelefoneEntity;
import br.com.ewave.mt.livraria.uteis.Status;
import lombok.Data;

@Data
public class InstituicaoModel implements Serializable {

	private static final long serialVersionUID = -7402920406367948926L;

	private Integer codigo;

	private String nome;

	private String CNPJ;

	private Status status;

	private EnderecoModel enderecoModel;

	private TelefoneModel telefoneModel;

	public InstituicaoModel() {
		super();
	}

	public InstituicaoModel(InstituicaoEntity instituicaoEntity) {
		this.codigo = instituicaoEntity.getCodigo();
		this.nome = instituicaoEntity.getNome();
		this.status = instituicaoEntity.getStatus();
		this.enderecoModel = new EnderecoModel(instituicaoEntity.getEndereco());
		this.CNPJ = instituicaoEntity.getCNPJ();
		this.telefoneModel = new TelefoneModel(instituicaoEntity.getTelefoneEntity());
	}

	public InstituicaoModel(Integer codigo, String nome, String CNPJ, Status status, EnderecoEntity endereco,
			TelefoneEntity telefoneEntity) {
		this.codigo = codigo;
		this.nome = nome;
		this.status = status;
		this.enderecoModel = new EnderecoModel(endereco);
		this.telefoneModel = new TelefoneModel(telefoneEntity);
		this.CNPJ = CNPJ;
	}

	public String getCorLinha() {
		String cor = "";
		if (isLivroAlterado())
			return "ALTERACAO";
		if (isLivroInativado())
			cor = "INATIVACAO";
		return cor;

	}

	public boolean isLivroAlterado() {
		return this.status.equals(Status.ALTERACAO);
	}

	public boolean isLivroInativado() {
		return this.status.equals(Status.INATIVACAO);
	}
}
