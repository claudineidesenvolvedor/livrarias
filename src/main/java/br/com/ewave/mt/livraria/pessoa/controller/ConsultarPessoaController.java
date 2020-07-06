package br.com.ewave.mt.livraria.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
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
@Named(value = "consultarPessoaController")
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = 8512199941951451052L;

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

	String confirmacao;

	@Produces
	private List<PessoaModel> pessoas;

	/***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		// RETORNAR AS PESSOAS CADASTRADAS
		this.pessoas = pessoaRepository.lista();
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM PESSOA PARA SER EDITADA
	 * 
	 * @param pessoaModel
	 */
	public void Editar(PessoaModel pessoaModel) {

		this.pessoaModel = pessoaModel;
		this.enderecoModel = pessoaModel.getEnderecoModel();
		this.telefoneModel = pessoaModel.getTelefoneModel();
		this.usuarioModel = pessoaModel.getUsuarioModel();
		this.instituicaoModel = pessoaModel.getInstituicaoModel();

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		try {
			this.pessoaModel.setStatus(Status.ALTERACAO);
			this.pessoaModel.setInstituicaoModel(this.instituicaoModel);
			this.pessoaModel.setUsuarioModel(this.usuarioModel);
			this.pessoaModel.setEnderecoModel(this.enderecoModel);
			this.pessoaModel.setTelefoneModel(this.telefoneModel);

			pessoaRepository.AlterarRegistro(this.pessoaModel);
			Uteis.MensagemInfo("Registro cadastrado com sucesso");

			this.pessoaModel = new PessoaModel();
			this.enderecoModel = new EnderecoModel();
			this.telefoneModel = new TelefoneModel();
			this.usuarioModel = new UsuarioModel();
		} catch (Exception e) {
			Uteis.MensagemError(e.getMessage());
		}

		this.init();

	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param pessoaModel
	 */
	public void Excluir(PessoaModel pessoaModel) {

		this.pessoaModel = pessoaModel;
		this.pessoaModel.setStatus(Status.INATIVACAO);
		try {
			this.pessoaRepository.AlterarRegistro(this.pessoaModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	public List<PessoaModel> completaPesoa(String nome) {
		return this.pessoaRepository.listaPessoa(nome);
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

	public InstituicaoModel getInstituicaoModel() {
		return instituicaoModel;
	}

	public void setInstituicaoModel(InstituicaoModel instituicaoModel) {
		this.instituicaoModel = instituicaoModel;
	}

	public String getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(String confirmacao) {
		this.confirmacao = confirmacao;
	}

	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}

}
