package br.com.ewave.mt.livraria.model;

import java.io.Serializable;

import br.com.ewave.mt.livraria.repository.entity.UsuarioEntity;
import br.com.ewave.mt.livraria.uteis.Role;
import lombok.Data;

@Data
public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = -5157708894131511301L;

	private Integer codigo;

	private String usuario;

	private String senha;

	private Role tipo;

	public UsuarioModel() {
		super();
	}

	public UsuarioModel(Integer codigo, String usuario, String senha, Role tipo) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.senha = senha;
		this.tipo = tipo;
	}

	public UsuarioModel(UsuarioEntity usuarioEntity) {
		super();
		this.codigo = usuarioEntity.getCodigo();
		this.usuario = usuarioEntity.getUsuario();
		this.senha = usuarioEntity.getSenha();
		this.tipo = usuarioEntity.getTipo();
	}

	public boolean isValidadoSenha(String confirmacaoo) {
		if (this.senha.equals(confirmacaoo))
			return true;
		else
			return false;
	}
}
