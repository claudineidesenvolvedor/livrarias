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

import br.com.ewave.mt.livraria.model.ReservaModel;
import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.model.PessoaModel;
import lombok.Data;

@Data
@Entity
@Table(name = "reserva")
@SequenceGenerator(name = " Reserva_Seq", sequenceName = "seq_reserva", allocationSize = 1, initialValue = 1)
@NamedQueries({ @NamedQuery(name = "ReservaEntity.findAll", query = "SELECT p FROM ReservaEntity p"),
		@NamedQuery(name = "ReservaEntity.GroupByCod", query = "SELECT  p FROM ReservaEntity p WHERE p.codigo = :codigo "),
		@NamedQuery(name = "ReservaEntity.GroupByPessoa", query = "SELECT  p FROM ReservaEntity p WHERE p.pessoaEntity = :pessoa "),
		@NamedQuery(name = "ReservaEntity.GroupByLivro", query = "SELECT  p FROM ReservaEntity p WHERE p.livroEntity = :livro ") })
public class ReservaEntity implements Serializable {

	private static final long serialVersionUID = 7416958296301367882L;

	@Id
	@PrimaryKeyJoinColumn(name = "emprestimoPK")
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Reserva_Seq")
	private Integer codigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataReserva")
	private Calendar dataReserva;

	@OneToOne
	@JoinColumn(name = "id_livro_reserva", foreignKey = @ForeignKey(name = "livroReservaFK"), unique = true)
	private LivroEntity livroEntity;

	@OneToOne
	@JoinColumn(name = "id_pessoa_reserva", foreignKey = @ForeignKey(name = "pessoaReservaFK"), unique = true)
	private PessoaEntity pessoaEntity;

	public ReservaEntity() {
		super();
	}

	public ReservaEntity(ReservaModel devolucaoModel) {
		this.codigo = devolucaoModel.getCodigo();
		this.dataReserva = devolucaoModel.getDataReserva();
		this.livroEntity = new LivroEntity(devolucaoModel.getLivroModel());
		this.pessoaEntity = new PessoaEntity(devolucaoModel.getPessoaModel());
	}

	public ReservaEntity(Integer codigo, Calendar dataReserva, LivroModel livroModel, PessoaModel pessoaModel) {
		this.codigo = codigo;
		this.dataReserva = dataReserva;
		this.livroEntity = new LivroEntity(livroModel);
		this.pessoaEntity = new PessoaEntity(pessoaModel);
	}

}
