package br.com.ewave.mt.livraria.livro.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.ewave.mt.livraria.model.GeneroModel;
import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.repository.LivroReository;
import br.com.ewave.mt.livraria.repository.entity.AutorModel;
import br.com.ewave.mt.livraria.uteis.Status;
import br.com.ewave.mt.livraria.uteis.Uteis;

@RequestScoped
@Named(value = "cadastrarLivroController")
public class cadastrarLivroController implements Serializable {

	private static final long serialVersionUID = -2305926006044762419L;

	@Inject
	LivroModel livroModel;

	@Inject
	GeneroModel generoModel;

	@Inject
	AutorModel autorModel;

	@Inject
	LivroReository livroReository;

	private String imagemTemporaria;

	/**
	 * SALVA UM NOVO REGISTRO VIA INPUT
	 */
	public void SalvarNovoLivro() {

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			String caminho = (String) facesContext.getExternalContext().getSessionMap().get("caminho");
			if (StringUtils.isNotBlank(caminho)) {
				this.livroModel.setCapa(caminho);
				this.livroModel.setAutorModel(new AutorModel(this.autorModel.getNome()));
				this.livroModel.setGeneroModel(new GeneroModel(this.generoModel.getNome()));
				this.livroModel.setStatus(Status.INCLUSAO);
				livroReository.SalvarNovoRegistro(this.livroModel);

				this.livroModel = new LivroModel();
				this.autorModel = new AutorModel();
				this.generoModel = new GeneroModel();
				Uteis.MensagemInfo("Registro cadastrado com sucesso");

				FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("caminho");

			}
		} catch (Exception e) {
			Uteis.MensagemError(e.getMessage());

		}

	}

	/**
	 * PESQUISAR PELO AUTOCOMPLETE
	 * 
	 * @param nome
	 * @return lista de Genero
	 * 
	 */
	public List<GeneroModel> buscaGeneroModel(String nome) {
		List<GeneroModel> lista = new ArrayList<GeneroModel>();
		lista = (List<GeneroModel>) this.livroReository.listaGeneroModel(nome.toUpperCase());
		return lista;
	}

	/**
	 * PESQUISAR PELO AUTOCOMPLETE
	 * 
	 * @param nome
	 * @return lista de Autor model
	 * 
	 */
	public List<AutorModel> buscaAutorModel(String nome) {
		List<AutorModel> lista = new ArrayList<AutorModel>();
		lista = (List<AutorModel>) this.livroReository.listaAutorModel(nome.toUpperCase());
		return lista;
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

	public Status[] getTipoStatus() {
		return Status.values();
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

	public String getImagemTemporaria() {
		return imagemTemporaria;
	}

	public void setImagemTemporaria(String imagemTemporaria) {
		this.imagemTemporaria = imagemTemporaria;
	}

}
