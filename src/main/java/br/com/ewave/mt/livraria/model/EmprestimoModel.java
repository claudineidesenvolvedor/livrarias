package br.com.ewave.mt.livraria.model;

import java.util.Calendar;

import br.com.ewave.mt.livraria.repository.entity.EmprestimoEntity;
import br.com.ewave.mt.livraria.repository.entity.LivroEntity;
import br.com.ewave.mt.livraria.repository.entity.PessoaEntity;
import lombok.Data;

@Data
public class EmprestimoModel {

	private Integer codigo;

	private Calendar dataEmprestimo;

	private LivroModel livroModel;

	private PessoaModel pessoaModel;

	private boolean devolvido;

	private Calendar dataDevolucao;

	public EmprestimoModel() {
		super();
	}

	public EmprestimoModel(EmprestimoEntity emprestimoEntity) {
		this.codigo = emprestimoEntity.getCodigo();
		this.dataEmprestimo = emprestimoEntity.getDataEmprestimo();
		this.livroModel = new LivroModel(emprestimoEntity.getLivroEntity());
		this.pessoaModel = new PessoaModel(emprestimoEntity.getPessoaEntity());
		this.devolvido = emprestimoEntity.isDevolvido();
		this.dataDevolucao = emprestimoEntity.getDataDevolucao();
	}

	public EmprestimoModel(Integer codigo, Calendar dataEmprestimo, LivroEntity livroEntity, PessoaEntity pessoaEntity,
			boolean devolvido, Calendar dataDevolucao) {
		this.codigo = codigo;
		this.dataEmprestimo = dataEmprestimo;
		this.livroModel = new LivroModel(livroEntity);
		this.pessoaModel = new PessoaModel(pessoaEntity);
		this.devolvido = devolvido;
		this.dataDevolucao = dataDevolucao;
	}

	public boolean isValidarEmprestimo() {
		if (this.pessoaModel.isBloquear() == true || this.pessoaModel.getEmprestismo() < 2
				|| this.livroModel.isBloquear() == true)
			return false;
		else
			return true;
	}

	@SuppressWarnings("static-access")
	public String getCorLinha() {
		String cor = "";
		int mes = Calendar.getInstance().MONTH + 1;
		if (this.dataEmprestimo.MONTH > mes)
			cor = "INATIVACAO";
		return cor;
	}

}
