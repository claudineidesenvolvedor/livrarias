package br.com.ewave.mt.livraria.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.ewave.mt.livraria.model.ReservaModel;
import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.model.PessoaModel;
import br.com.ewave.mt.livraria.repository.entity.ReservaEntity;
import br.com.ewave.mt.livraria.uteis.Uteis;

public class ReservaRepositoy implements Serializable {

	private static final long serialVersionUID = 590692557605257911L;

	EntityManager entityManager;

	/***
	 * CARREGAR A LISTA DE DEVOLU��O
	 * 
	 *
	 * @return lista
	 */
	public List<ReservaModel> lista() {
		List<ReservaModel> lista = new ArrayList<ReservaModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (DevolucaoEntity.findAll)
		Query query = entityManager.createNamedQuery("DevolucaoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<ReservaEntity> listaEntity = (Collection<ReservaEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new ReservaModel(entity));
		});

		return lista;
	}

	/***
	 * CARREGAR A LISTA DE DEVOLU��O PELA PESSOA
	 * 
	 * @param nome
	 *
	 * @return lista
	 */
	public List<ReservaModel> listaPessoa(PessoaModel pessoaModel) {
		List<ReservaModel> lista = new ArrayList<ReservaModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (DevolucaoEntity.GroupByPessoa)
		Query query = entityManager.createNamedQuery("DevolucaoEntity.GroupByPessoa");

		// PAR�METROS DA QUERY
		query.setParameter("pessoa", pessoaModel);

		// LISTANDO DOS DEVOLU��O
		@SuppressWarnings("unchecked")
		Collection<ReservaEntity> listaEntity = (Collection<ReservaEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new ReservaModel(entity));
		});

		return lista;
	}

	/***
	 * CARREGAR A LISTA DE DEVOLU��O PELA LIVRO
	 * 
	 * @param nome
	 *
	 * @return lista
	 */
	public List<ReservaModel> listaLivro(LivroModel livroModel) {
		List<ReservaModel> lista = new ArrayList<ReservaModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (DevolucaoEntity.GroupByLivro)
		Query query = entityManager.createNamedQuery("DevolucaoEntity.GroupByLivro");

		// PAR�METROS DA QUERY
		query.setParameter("livro", livroModel);

		// LISTANDO DOS DEVOLU��O
		@SuppressWarnings("unchecked")
		Collection<ReservaEntity> listaEntity = (Collection<ReservaEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new ReservaModel(entity));
		});

		return lista;
	}

	/***
	 * CONSULTA UMA DEVOLU��O CADASTRADA PELO ID
	 * 
	 * @param cod
	 * @return DevolucaoModel
	 */
	public ReservaModel buscaPorCod(Integer cod) {

		try {

			// QUERY QUE VAI SER EXECUTADA (DevolucaoEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("DevolucaoEntity.GroupByCod");

			// PAR�METROS DA QUERY
			query.setParameter("codigo", cod);

			// RETORNA O DEVOLU��O SE FOR LOCALIZADO
			return new ReservaModel((ReservaEntity) query.getSingleResult());

		} catch (Exception e) {
			return null;
		}

	}

	/***
	 * CONSULTA UMA DEVOLU��O CADASTRADA PELO C�DIGO
	 * 
	 * @param codigo
	 * @return DevolucaoEntity
	 */
	public ReservaEntity GetDevolucao(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(ReservaEntity.class, codigo);
	}

	/***
	 * M�TODO RESPONS�VEL POR SALVAR UM NOVO RESERVA
	 * 
	 * @param devolucaoModel
	 * @throws Exception
	 */
	public void SalvarNovoRegistro(ReservaModel devolucaoModel) throws Exception {
		entityManager = Uteis.JpaEntityManager();
		try {

			entityManager.merge(new ReservaEntity(devolucaoModel));

		} catch (PersistenceException e) {
			throw new Exception("Devolu��o n�o pode ser salvar.");

		}

	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param devolucaoModel
	 * @throws Exception
	 */
	public void AlterarRegistro(ReservaModel devolucaoModel) throws Exception {
		entityManager = Uteis.JpaEntityManager();
		try {

			entityManager.merge(new ReservaEntity(devolucaoModel));

		} catch (PersistenceException e) {
			throw new Exception("Devolu��o n�o pode ser alterado.");

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

			entityManager.remove(this.GetDevolucao(codigo));
		} catch (PersistenceException e) {
			throw new Exception("Devolu��o n�o pode ser exclu�do.");

		}
	}
}