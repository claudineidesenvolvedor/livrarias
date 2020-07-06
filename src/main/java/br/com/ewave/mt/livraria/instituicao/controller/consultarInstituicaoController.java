package br.com.ewave.mt.livraria.instituicao.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ewave.mt.livraria.model.EnderecoModel;
import br.com.ewave.mt.livraria.model.InstituicaoModel;
import br.com.ewave.mt.livraria.model.TelefoneModel;
import br.com.ewave.mt.livraria.repository.InstituicaoRepository;
import br.com.ewave.mt.livraria.uteis.Status;
import br.com.ewave.mt.livraria.uteis.TipoTelefone;
import br.com.ewave.mt.livraria.uteis.Uteis;

@RequestScoped
@Named(value = "consultarInstituicaoController")
public class consultarInstituicaoController implements Serializable {

	private static final long serialVersionUID = -8196567250968866708L;
	@Inject
	InstituicaoModel instituicaoModel;

	@Inject
	EnderecoModel enderecoModel;

	@Inject
	TelefoneModel telefoneModel;

	@Inject
	InstituicaoRepository instituiçãoRepository;

	@Produces
	private List<InstituicaoModel> insitituicoes;

	/***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		// RETORNAR AS INSITUIÇÕES CADASTRADAS
		this.insitituicoes = instituiçãoRepository.lista();
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM LIVRO PARA SER EDITADA
	 * 
	 * @param instituicaoModel
	 */
	public void Editar(InstituicaoModel instituicaoModel) {

		this.instituicaoModel = instituicaoModel;
		this.enderecoModel = instituicaoModel.getEnderecoModel();
		this.telefoneModel = instituicaoModel.getTelefoneModel();

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		try {
			this.instituicaoModel.setStatus(Status.ALTERACAO);
			this.instituicaoModel.setEnderecoModel(this.enderecoModel);
			this.instituicaoModel.setTelefoneModel(this.telefoneModel);
			instituiçãoRepository.AlterarRegistro(this.instituicaoModel);

			this.instituicaoModel = new InstituicaoModel();
			this.telefoneModel = new TelefoneModel();
			this.enderecoModel = new EnderecoModel();
			Uteis.MensagemInfo("Registro cadastrado com sucesso");

		} catch (Exception e) {
			Uteis.MensagemError(e.getMessage());
		}

		this.init();

	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param livroModel
	 */
	public void Excluir(InstituicaoModel instituicaoModel) {

		this.instituicaoModel = instituicaoModel;
		this.instituicaoModel.setStatus(Status.INATIVACAO);
		try {
			this.instituiçãoRepository.AlterarRegistro(this.instituicaoModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	public TipoTelefone[] getTipoTelefone() {
		return TipoTelefone.values();
	}

	public InstituicaoModel getInstituicaoModel() {
		return instituicaoModel;
	}

	public void setInstituicaoModel(InstituicaoModel instituicaoModel) {
		this.instituicaoModel = instituicaoModel;
	}

	public EnderecoModel getEnderecoModel() {
		return enderecoModel;
	}

	public void setEnderecoModel(EnderecoModel enderecoModel) {
		this.enderecoModel = enderecoModel;
	}

	public TelefoneModel getTelefoneModel() {
		return telefoneModel;
	}

	public void setTelefoneModel(TelefoneModel telefoneModel) {
		this.telefoneModel = telefoneModel;
	}

	public List<InstituicaoModel> getInsitituicoes() {
		return insitituicoes;
	}

	public void setInsitituicoes(List<InstituicaoModel> insitituicoes) {
		this.insitituicoes = insitituicoes;
	}

}
