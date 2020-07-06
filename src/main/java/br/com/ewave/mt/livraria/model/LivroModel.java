package br.com.ewave.mt.livraria.model;

import java.io.File;
import java.io.Serializable;

import br.com.ewave.mt.livraria.repository.entity.AutorEntity;
import br.com.ewave.mt.livraria.repository.entity.AutorModel;
import br.com.ewave.mt.livraria.repository.entity.GeneroEntity;
import br.com.ewave.mt.livraria.repository.entity.LivroEntity;
import br.com.ewave.mt.livraria.uteis.Status;
import lombok.Data;

@Data
public class LivroModel implements Serializable {

	private static final long serialVersionUID = 4794051923184737588L;

	private Integer codigo;

	private String titulo;

	private GeneroModel generoModel;

	private AutorModel autorModel;

	private String sinopse;

	private String capa;

	private Status status;

	private boolean bloquear;

	public LivroModel() {
		super();
	}

	public LivroModel(LivroEntity livroEntity) {
		this.codigo = livroEntity.getCodigo();
		this.titulo = livroEntity.getTitulo();
		this.generoModel = new GeneroModel(livroEntity.getGeneroEntity());
		this.autorModel = new AutorModel(livroEntity.getAutorEntity());
		this.sinopse = livroEntity.getSinopse();
		this.capa = livroEntity.getCapa();
		this.bloquear = livroEntity.isBloquear();
	}

	public LivroModel(Integer codigo, String titulo, GeneroEntity genero, AutorEntity autor, String sinopse,
			String capa, boolean bloquear) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.generoModel = new GeneroModel(genero);
		this.autorModel = new AutorModel(autor);
		this.sinopse = sinopse;
		this.capa = capa;
		this.bloquear = bloquear;
	}

	public String getImagen() {
		String img = null;
		File file = new File(this.capa);
		if (!file.exists())
			file.mkdirs();
		if (file.isFile()) {
			String ext = file.getName().substring(file.getName().lastIndexOf(".")).toLowerCase();
			if (ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".bmp") || ext.equals(".gif")
					|| ext.equals(".png")) {
				img = "/resources/capa/" + file.getName();
			}
		}
		return img;
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
