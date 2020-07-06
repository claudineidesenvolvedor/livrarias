package br.com.ewave.mt.livraria.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.ewave.mt.livraria.model.EmprestimoModel;
import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.model.PessoaModel;
import br.com.ewave.mt.livraria.repository.entity.EmprestimoEntity;
import br.com.ewave.mt.livraria.repository.entity.LivroEntity;
import br.com.ewave.mt.livraria.repository.entity.PessoaEntity;
import br.com.ewave.mt.livraria.uteis.Status;
import br.com.ewave.mt.livraria.uteis.Uteis;

public class EmprestimoRepositoy implements Serializable {

	private static final long serialVersionUID = 590692557605257911L;

	EntityManager entityManager;

	/***
	 * CARREGAR A LISTA DE EMPRESTIMO
	 * 
	 *
	 * @return listaEmprestimoModel
	 */
	public List<EmprestimoModel> lista() {
		List<EmprestimoModel> lista = new ArrayList<EmprestimoModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (AutorEntity.findAll)
		Query query = entityManager.createNamedQuery("EmprestimoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<EmprestimoEntity> listaEntity = (Collection<EmprestimoEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new EmprestimoModel(entity));
		});

		return lista;
	}

	/***
	 * CARREGAR A LISTA DE EMPRESTIMO PELA PESSOA
	 * 
	 * @param nome
	 *
	 * @return lista
	 */
	public List<EmprestimoModel> listaPessoa(PessoaModel pessoaModel) {
		List<EmprestimoModel> lista = new ArrayList<EmprestimoModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (EmprestimoEntity.GroupByPessoa)
		Query query = entityManager.createNamedQuery("EmprestimoEntity.GroupByPessoa");

		// PARÂMETROS DA QUERY
		query.setParameter("pessoa", pessoaModel);

		// LISTANDO DOS EMPRESTIMO
		@SuppressWarnings("unchecked")
		Collection<EmprestimoEntity> listaEntity = (Collection<EmprestimoEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new EmprestimoModel(entity));
		});

		return lista;
	}

	/***
	 * CARREGAR A LISTA DE EMPRESTIMO PELA LIVRO
	 * 
	 * @param nome
	 *
	 * @return lista
	 */
	public List<EmprestimoModel> listaLivro(LivroModel livroModel) {
		List<EmprestimoModel> lista = new ArrayList<EmprestimoModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (EmprestimoEntity.GroupByLivro)
		Query query = entityManager.createNamedQuery("EmprestimoEntity.GroupByLivro");

		// PARÂMETROS DA QUERY
		query.setParameter("livro", livroModel);

		// LISTANDO DOS EMPRESTIMO
		@SuppressWarnings("unchecked")
		Collection<EmprestimoEntity> listaEntity = (Collection<EmprestimoEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new EmprestimoModel(entity));
		});

		return lista;
	}

	/***
	 * CONSULTA UMA EMPRESTIMO CADASTRADA PELO ID
	 * 
	 * @param cod
	 * @return EmprestimoModel
	 */
	public EmprestimoModel buscaPorCod(Integer cod) {

		try {

			// QUERY QUE VAI SER EXECUTADA (EmprestimoModel.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("EmprestimoModel.GroupByCod");

			// PARÂMETROS DA QUERY
			query.setParameter("codigo", cod);

			// RETORNA O INSTITUIÇÃO SE FOR LOCALIZADO
			return new EmprestimoModel((EmprestimoEntity) query.getSingleResult());

		} catch (Exception e) {
			return null;
		}

	}

	/***
	 * CONSULTA UMA EMPRESTIMO CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return EmprestimoEntity
	 */
	public EmprestimoEntity GetEmprestimo(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(EmprestimoEntity.class, codigo);
	}

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO EMPRESTIMO
	 * 
	 * @param EmprestimoModel
	 * @throws Exception
	 */
	public void SalvarNovoRegistro(EmprestimoModel emprestimoModel) throws Exception {
		entityManager = Uteis.JpaEntityManager();
		try {
			LivroModel livroModel = emprestimoModel.getLivroModel();
			livroModel.setBloquear(true);
			livroModel.setStatus(Status.INCLUSAO);
			emprestimoModel.setLivroModel(new LivroModel(entityManager.merge(new LivroEntity(livroModel))));

			PessoaModel pessoaModel = emprestimoModel.getPessoaModel();
			pessoaModel.setEmprestismo(pessoaModel.getEmprestismo() + 1);

			emprestimoModel.setPessoaModel(new PessoaModel(entityManager.merge(new PessoaEntity(pessoaModel))));
			emprestimoModel.setDataEmprestimo(Calendar.getInstance());
			entityManager.merge(new EmprestimoEntity(emprestimoModel));

		} catch (PersistenceException e) {
			throw new Exception("Emprestimo não pode ser salvar.");

		}

	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param emprestimoModel
	 * @throws Exception
	 */
	public void AlterarRegistro(EmprestimoModel emprestimoModel) throws Exception {
		entityManager = Uteis.JpaEntityManager();
		try {

			LivroModel livroModel = emprestimoModel.getLivroModel();
			livroModel.setBloquear(false);
			livroModel.setStatus(Status.INCLUSAO);
			emprestimoModel.setLivroModel(new LivroModel(entityManager.merge(new LivroEntity(livroModel))));

			PessoaModel pessoaModel = emprestimoModel.getPessoaModel();
			pessoaModel.setEmprestismo(pessoaModel.getEmprestismo() - 1);

			emprestimoModel.setPessoaModel(new PessoaModel(entityManager.merge(new PessoaEntity(pessoaModel))));
			emprestimoModel.setDevolvido(true);
			emprestimoModel.setDataDevolucao(Calendar.getInstance());
			entityManager.merge(new EmprestimoEntity(emprestimoModel));

		} catch (PersistenceException e) {
			throw new Exception("Emprestimo não pode ser alterado.");
		}

	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 * @throws Exception
	 */
	public void ExcluirRegistro(int codigo) throws Exception {
		entityManager = Uteis.JpaEntityManager();
		try {

			entityManager.remove(this.GetEmprestimo(codigo));
		} catch (PersistenceException e) {
			throw new Exception("Emprestimo não pode ser excluído.");

		}
	}
}