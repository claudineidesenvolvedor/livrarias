package br.com.ewave.mt.livraria.model;

import br.com.ewave.mt.livraria.repository.entity.TelefoneEntity;
import br.com.ewave.mt.livraria.uteis.TipoTelefone;
import lombok.Data;

@Data
public class TelefoneModel {

	private Integer id;

	private String numero;

	private TipoTelefone tipoTelefone;

	public TelefoneModel() {
		super();
	}

	public TelefoneModel(TelefoneEntity telefoneEntity) {
		this.id = telefoneEntity.getId();
		this.numero = telefoneEntity.getNumero();
		this.tipoTelefone = telefoneEntity.getTipoTelefone();
	}

	public TelefoneModel(Integer id, String numero, TipoTelefone tipoTelefone) {
		this.id = id;
		this.numero = numero;
		this.tipoTelefone = tipoTelefone;
	}

}
