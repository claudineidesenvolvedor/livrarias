package br.com.ewave.mt.livraria.instituicao.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
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
@Named(value = "cadastrarInstituicaoController")
public class cadastrarInstituicaoController implements Serializable {

	private static final long serialVersionUID = 9057130755137100617L;

	@Inject
	InstituicaoModel instituicaoModel;

	@Inject
	EnderecoModel enderecoModel;
	
	@Inject
	TelefoneModel telefoneModel;
	
	@Inject
	InstituicaoRepository instituiçãoRepository;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovo() {

		try {
			this.instituicaoModel.setStatus(Status.INCLUSAO);
			this.instituicaoModel.setEnderecoModel(this.enderecoModel);
			this.instituicaoModel.setTelefoneModel(this.telefoneModel);
			instituiçãoRepository.SalvarNovoRegistro(this.instituicaoModel);

			this.instituicaoModel = new InstituicaoModel();
			this.telefoneModel =  new TelefoneModel();
			this.enderecoModel  = new EnderecoModel();
			Uteis.MensagemInfo("Registro cadastrado com sucesso");

		} catch (Exception e) {
			Uteis.MensagemError(e.getMessage());

		}

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
}
