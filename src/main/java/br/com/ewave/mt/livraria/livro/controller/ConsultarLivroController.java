package br.com.ewave.mt.livraria.livro.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.ewave.mt.livraria.model.GeneroModel;
import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.model.PessoaModel;
import br.com.ewave.mt.livraria.repository.LivroReository;
import br.com.ewave.mt.livraria.repository.entity.AutorModel;
import br.com.ewave.mt.livraria.uteis.Status;
import br.com.ewave.mt.livraria.uteis.Uteis;

@ViewScoped
@Named(value = "consultarLivroController")
public class ConsultarLivroController implements Serializable {

	private static final long serialVersionUID = -5848479713160172503L;

	@Inject
	transient LivroModel livroModel;

	@Inject
	transient GeneroModel generoModel;

	@Inject
	transient AutorModel autorModel;

	@Inject
	transient LivroReository livroReository;

	@Produces
	private List<LivroModel> livros;

	private String imagemTemporaria;

	/***
	 * CARREGA AS LIVROS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		// RETORNAROAS LIVROS CADASTRADAS
		this.livros = livroReository.lista();
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM LIVRO PARA SER EDITADA
	 * 
	 * @param livroModel
	 */
	public void Editar(LivroModel livroModel) {

		this.livroModel = livroModel;
		this.autorModel = livroModel.getAutorModel();
		this.generoModel = livroModel.getGeneroModel();

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		String caminho = (String) facesContext.getExternalContext().getSessionMap().get("caminho");
		if (StringUtils.isNotBlank(caminho)) {
			if (!caminho.equals(this.livroModel.getCapa()))
				this.livroModel.setCapa(caminho);
		}
		this.livroModel.setStatus(Status.ALTERACAO);
		this.livroModel.setAutorModel(new AutorModel(this.autorModel.getNome()));
		this.livroModel.setGeneroModel(new GeneroModel(this.generoModel.getNome()));

		this.livroReository.AlterarRegistro(this.livroModel);

		this.livroModel = new LivroModel();
		this.autorModel = new AutorModel();
		this.generoModel = new GeneroModel();

		/* RECARREGA OS REGISTROS */
		this.init();
		if (StringUtils.isNotBlank(caminho)) {

			FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("caminho");

		}
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param livroModel
	 */
	public void ExcluirLivro(LivroModel livroModel) {

		// EXCLUI A BAIRRO DO BANCO DE DADOS
		/// this.livroReository.ExcluirRegistro(livroModel.getCodigo());

		// REMOVENDO A LIVRO DA LISTA
		// ASSIM QUE O LIVRO É INATIVAÇÃO DA LISTA O DATATABLE É ATUALIZADO
		this.livroModel.setStatus(Status.INATIVACAO);
		this.livroReository.AlterarRegistro(this.livroModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/**
	 * SALVANDO O AQRUIVO DO UPLOAD
	 */
	public void criaArquivo(byte[] bytes, String arquivo, String nome) {
		FileOutputStream fos;
		try {
			File dir = new File(arquivo);

			if (!dir.exists()) {
				dir.mkdirs();
			}

			fos = new FileOutputStream(arquivo + nome);

			FacesContext facesContext = FacesContext.getCurrentInstance();

			facesContext.getExternalContext().getSessionMap().put("caminho", arquivo + nome);
			fos.write(bytes);
			fos.close();
		} catch (FileNotFoundException e) {
			Uteis.MensagemInfo(e.getMessage());
		} catch (IOException e) {
			Uteis.MensagemInfo(e.getMessage());
		}
	}

	/**
	 * REALIZANDO UPLOAD DE ARQUIVO
	 */
	public void enviarImagem(FileUploadEvent event) {
		byte[] img = event.getFile().getContents();
		imagemTemporaria = event.getFile().getFileName();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

		String arquivo = scontext.getRealPath("/resources/capa/");
		criaArquivo(img, arquivo, imagemTemporaria);
	}

	public List<LivroModel> completaTitulo(String nome) {
		return this.livroReository.listaTitulo(nome);
	}

	public List<LivroModel> getLivros() {
		return livros;
	}

	public void setLivros(List<LivroModel> livros) {
		this.livros = livros;
	}

	public LivroModel getLivroModel() {
		return livroModel;
	}

	public void setLivroModel(LivroModel livroModel) {
		this.livroModel = livroModel;
	}

	public GeneroModel getGeneroModel() {
		return generoModel;
	}

	public void setGeneroModel(GeneroModel generoModel) {
		this.generoModel = generoModel;
	}

	public AutorModel getAutorModel() {
		return autorModel;
	}

	public void setAutorModel(AutorModel autorModel) {
		this.autorModel = autorModel;
	}

}
