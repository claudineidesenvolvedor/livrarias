package br.com.ewave.mt.livraria.model;

import java.util.Calendar;

import br.com.ewave.mt.livraria.repository.entity.ReservaEntity;
import br.com.ewave.mt.livraria.repository.entity.LivroEntity;
import br.com.ewave.mt.livraria.repository.entity.PessoaEntity;
import lombok.Data;

@Data
public class ReservaModel {

	private Integer codigo;

	private Calendar dataReserva;

	private LivroModel livroModel;

	private PessoaModel pessoaModel;

	public ReservaModel() {
		super();
	}

	public ReservaModel(ReservaEntity devolucaoEntity) {
		this.codigo = devolucaoEntity.getCodigo();
		this.dataReserva = devolucaoEntity.getDataReserva();
		this.livroModel = new LivroModel(devolucaoEntity.getLivroEntity());
		this.pessoaModel = new PessoaModel(devolucaoEntity.getPessoaEntity());
	}

	public ReservaModel(Integer codigo, Calendar dataReserva, LivroEntity livroEntity, PessoaEntity pessoaEntity) {
		this.codigo = codigo;
		this.dataReserva = dataReserva;
		this.livroModel = new LivroModel(livroEntity);
		this.pessoaModel = new PessoaModel(pessoaEntity);
	}
}
