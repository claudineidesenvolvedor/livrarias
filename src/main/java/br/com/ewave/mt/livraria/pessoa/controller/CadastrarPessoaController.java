package br.com.ewave.mt.livraria.pessoa.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ewave.mt.livraria.model.EnderecoModel;
import br.com.ewave.mt.livraria.model.InstituicaoModel;
import br.com.ewave.mt.livraria.model.PessoaModel;
import br.com.ewave.mt.livraria.model.TelefoneModel;
import br.com.ewave.mt.livraria.model.UsuarioModel;
import br.com.ewave.mt.livraria.repository.PessoaRepository;
import br.com.ewave.mt.livraria.usuario.controller.UsuarioController;
import br.com.ewave.mt.livraria.uteis.Role;
import br.com.ewave.mt.livraria.uteis.Status;
import br.com.ewave.mt.livraria.uteis.TipoPessoa;
import br.com.ewave.mt.livraria.uteis.TipoSexo;
import br.com.ewave.mt.livraria.uteis.TipoTelefone;
import br.com.ewave.mt.livraria.uteis.Uteis;

@RequestScoped
@Named(value = "cadastrarPessoaController")
public class CadastrarPessoaController implements Serializable {

	private static final long serialVersionUID = -5956892046029450764L;

	@Inject
	PessoaModel pessoaModel;

	@Inject
	EnderecoModel enderecoModel;

	@Inject
	TelefoneModel telefoneModel;

	@Inject
	UsuarioModel usuarioModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	PessoaRepository pessoaRepository;

	@Inject
	InstituicaoModel instituicaoModel;

	String confirmacaoo;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovo() {
		try {
			if (this.usuarioModel.isValidadoSenha(this.confirmacaoo)) {

				this.pessoaModel.setStatus(Status.INCLUSAO);
				this.pessoaModel.setBloquear(false);
				this.pessoaModel.setEmprestismo(0);
				this.pessoaModel.setInstituicaoModel(this.instituicaoModel);
				this.pessoaModel.setUsuarioModel(this.usuarioModel);
				this.pessoaModel.setEnderecoModel(this.enderecoModel);
				this.pessoaModel.setTelefoneModel(this.telefoneModel);

				pessoaRepository.SalvarNovoRegistro(this.pessoaModel);

				Uteis.MensagemInfo("Registro cadastrado com sucesso");

				this.pessoaModel = new PessoaModel();
				this.enderecoModel = new EnderecoModel();
				this.telefoneModel = new TelefoneModel();
				this.usuarioModel = new UsuarioModel();
			} else {
				Uteis.MensagemAtencao("A senha não conferer");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Uteis.MensagemError(e.getMessage());
		}

	}

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	public EnderecoModel getEnderecoModel() {
		return enderecoModel;
	}

	public void setEnderecoModel(EnderecoModel enderecoModel) {
		this.enderecoModel = enderecoModel;
	}

	public Role[] getTipo() {
		return Role.values();
	}

	public TipoPessoa[] getTipoPessoa() {
		return TipoPessoa.values();
	}

	public TipoTelefone[] getTipoTelefone() {
		return TipoTelefone.values();
	}

	public TipoSexo[] getTipoSexo() {
		return TipoSexo.values();
	}

	public Status[] getTipoStatus() {
		return Status.values();
	}

	public TelefoneModel getTelefoneModel() {
		return telefoneModel;
	}

	public void setTelefoneModel(TelefoneModel telefoneModel) {
		this.telefoneModel = telefoneModel;
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public String getConfirmacao() {
		return confirmacaoo;
	}

	public void setConfirmacao(String confirmacaoo) {
		this.confirmacaoo = confirmacaoo;
	}

	public InstituicaoModel getInstituicaoModel() {
		return instituicaoModel;
	}

	public void setInstituicaoModel(InstituicaoModel instituicaoModel) {
		this.instituicaoModel = instituicaoModel;
	}
}
