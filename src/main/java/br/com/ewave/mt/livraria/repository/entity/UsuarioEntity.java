package br.com.ewave.mt.livraria.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ewave.mt.livraria.model.UsuarioModel;
import br.com.ewave.mt.livraria.uteis.Role;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "Usuario_Seq", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "UsuarioEntity.findAll", query = "SELECT p FROM UsuarioEntity p"),
		@NamedQuery(name = "UsuarioEntity.findUser", query = "SELECT p FROM UsuarioEntity p WHERE p.usuario = :usuario AND p.senha = :senha"),
		@NamedQuery(name = "UsuarioEntity.GroupByCod", query = "SELECT  p FROM UsuarioEntity p WHERE p.codigo = :codigo ") })
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 4643365694977232649L;

	@Id
	@PrimaryKeyJoinColumn(name = "usuarioPK")
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuario_Seq")
	private Integer codigo;

	@Column(name = "ds_login")
	private String usuario;

	@Column(name = "ds_senha")
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private Role tipo; 

	public UsuarioEntity() {
		super();
	}

	public UsuarioEntity(UsuarioModel usuarioModel) {
		this.codigo = usuarioModel.getCodigo();
		this.usuario = usuarioModel.getUsuario();
		this.senha = usuarioModel.getSenha();
		this.tipo = usuarioModel.getTipo();
	}

	public UsuarioEntity(Integer codigo, String usuario, String senha,Role tipo) {
		this.codigo = codigo;
		this.usuario = usuario;
		this.senha = senha;
		this.tipo = tipo;
	}

}
