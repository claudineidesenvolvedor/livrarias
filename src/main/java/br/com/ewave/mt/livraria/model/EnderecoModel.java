package br.com.ewave.mt.livraria.model;

import java.io.Serializable;

import br.com.ewave.mt.livraria.repository.entity.EnderecoEntity;
import lombok.Data;

@Data
public class EnderecoModel implements Serializable {

	private static final long serialVersionUID = -6769954344797533265L;

	private Integer codigo;

	private String logradouro;

	private String numero;

	private String complemento;

	private String cep;

	private String bairro;

	private String estado;

	private String cidade;

	private String pais;

	public EnderecoModel() {
		super();
	}

	public EnderecoModel(Integer codigo, String logradouro, String numero, String complemento, String cep,
			String bairro,String cidade, String estado, String pais) {
		this.codigo = codigo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}

	public EnderecoModel(EnderecoEntity entity) {
		this.codigo = entity.getCodigo();
		this.logradouro = entity.getLogradouro();
		this.numero = entity.getNumero();
		this.complemento = entity.getComplemento();
		this.cep = entity.getCep();
		this.bairro = entity.getBairro();
		this.cidade = entity.getCidade();
		this.estado = entity.getEstado();
		this.pais = entity.getPais();
	}
}
