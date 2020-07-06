package br.com.ewave.mt.livraria.livro.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ewave.mt.livraria.model.EmprestimoModel;
import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.model.PessoaModel;
import br.com.ewave.mt.livraria.repository.EmprestimoRepositoy;
import br.com.ewave.mt.livraria.uteis.Uteis;

@RequestScoped
@Named(value = "cadastrarEmprestimoController")
public class CadastrarEmprestimoController implements Serializable {

	private static final long serialVersionUID = 4312439571129179280L;

	@Inject
	LivroModel livroModel;

	@Inject
	PessoaModel pessoaModel;

	@Inject
	EmprestimoModel emprestimoModel;

	@Inject
	EmprestimoRepositoy emprestimoRepositoy;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovo() {

		try {
			this.emprestimoModel.setLivroModel(this.livroModel);
			this.emprestimoModel.setPessoaModel(this.pessoaModel);
			if (!this.emprestimoModel.isValidarEmprestimo()) {
				emprestimoRepositoy.SalvarNovoRegistro(this.emprestimoModel);

				this.livroModel = new LivroModel();
				this.pessoaModel = new PessoaModel();
				this.emprestimoModel = new EmprestimoModel();
				Uteis.MensagemInfo("Emprestimo cadastrado com sucesso");
			} else {
				Uteis.MensagemAtencao("Você não pode realiza o empréstim");
			}
		} catch (Exception e) {
			Uteis.MensagemError(e.getMessage());

		}

	}

	public LivroModel getLivroModel() {
		return livroModel;
	}

	public void setLivroModel(LivroModel livroModel) {
		this.livroModel = livroModel;
	}

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	public EmprestimoModel getEmprestimoModel() {
		return emprestimoModel;
	}

	public void setEmprestimoModel(EmprestimoModel emprestimoModel) {
		this.emprestimoModel = emprestimoModel;
	}

}
