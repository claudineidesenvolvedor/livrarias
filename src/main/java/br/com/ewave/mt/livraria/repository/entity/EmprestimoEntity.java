package br.com.ewave.mt.livraria.repository.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ewave.mt.livraria.model.EmprestimoModel;
import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.model.PessoaModel;
import lombok.Data;

@Data
@Entity
@Table(name = "emprestimo")
@SequenceGenerator(name = " Emprestimo_Seq", sequenceName = "seq_emprestimo", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "EmprestimoEntity.findAll", query = "SELECT p FROM EmprestimoEntity p"),
		@NamedQuery(name = "EmprestimoEntity.GroupByCod", query = "SELECT  p FROM EmprestimoEntity p WHERE p.codigo = :codigo "),
		@NamedQuery(name = "EmprestimoEntity.GroupByPessoa", query = "SELECT  p FROM EmprestimoEntity p WHERE p.pessoaEntity = :pessoa "),
		@NamedQuery(name = "EmprestimoEntity.GroupByLivro", query = "SELECT  p FROM EmprestimoEntity p WHERE p.livroEntity = :livro ") })
public class EmprestimoEntity implements Serializable {

	private static final long serialVersionUID = 7416958296301367882L;

	@Id
	@PrimaryKeyJoinColumn(name = "emprestimoPK")
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Emprestimo_Seq")
	private Integer codigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataEmprestimo")
	private Calendar dataEmprestimo;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataDevolucao")
	private Calendar dataDevolucao;

	@OneToOne
	@JoinColumn(name = "id_livro_emprestimo", foreignKey = @ForeignKey(name = "livroEmprestimoFK"), unique = true)
	private LivroEntity livroEntity;

	@OneToOne
	@JoinColumn(name = "id_pessoa_emprestimo", foreignKey = @ForeignKey(name = "pessoaEmprestimoFK"))
	private PessoaEntity pessoaEntity;

	@Column(name = "devolvido")
	private boolean devolvido = true;

	public EmprestimoEntity() {
		super();
	}

	public EmprestimoEntity(EmprestimoModel emprestimoModel) {
		this.codigo = emprestimoModel.getCodigo();
		this.dataEmprestimo = emprestimoModel.getDataEmprestimo();
		this.livroEntity = new LivroEntity(emprestimoModel.getLivroModel());
		this.pessoaEntity = new PessoaEntity(emprestimoModel.getPessoaModel());
		this.devolvido = emprestimoModel.isDevolvido();
		this.dataDevolucao = emprestimoModel.getDataDevolucao();
	}

	public EmprestimoEntity(Integer codigo, Calendar dataEmprestimo, LivroModel livroModel, PessoaModel pessoaModel,
			boolean devolvido, Calendar dataDevolucao) {
		this.codigo = codigo;
		this.dataEmprestimo = dataEmprestimo;
		this.livroEntity = new LivroEntity(livroModel);
		this.pessoaEntity = new PessoaEntity(pessoaModel);
		this.devolvido = devolvido;
		this.dataDevolucao = dataDevolucao;
	}

}
