package br.com.ewave.mt.livraria.livro.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ewave.mt.livraria.model.EmprestimoModel;
import br.com.ewave.mt.livraria.repository.EmprestimoRepositoy;

@ViewScoped
@Named(value = "consultarEmprestimoController")
public class ConsultarEmprestimoController implements Serializable {

	private static final long serialVersionUID = 4312439571129179280L;

	@Inject
	EmprestimoRepositoy emprestimoRepositoy;

	@Produces
	private List<EmprestimoModel> emprestimmos;

	/***
	 * CARREGA AS EMPRESTIMO NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		// RETORNAROAS EMPRESTIMO CADASTRADOS
		this.emprestimmos = emprestimoRepositoy.lista();
	}

	/***
	 * DELVOVE UM EMPRESTIMO
	 * 
	 * @param emprestimoModel
	 */
	public void Devolver(EmprestimoModel emprestimoModel) {

		try {
			this.emprestimoRepositoy.AlterarRegistro(emprestimoModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	public List<EmprestimoModel> getEmprestimmos() {
		return emprestimmos;
	}

	public void setEmprestimmos(List<EmprestimoModel> emprestimmos) {
		this.emprestimmos = emprestimmos;
	}

}
