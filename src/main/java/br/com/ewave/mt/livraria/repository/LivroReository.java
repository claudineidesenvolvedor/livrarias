package br.com.ewave.mt.livraria.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import br.com.ewave.mt.livraria.model.GeneroModel;
import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.repository.entity.AutorEntity;
import br.com.ewave.mt.livraria.repository.entity.AutorModel;
import br.com.ewave.mt.livraria.repository.entity.GeneroEntity;
import br.com.ewave.mt.livraria.repository.entity.LivroEntity;
import br.com.ewave.mt.livraria.uteis.Uteis;

public class LivroReository implements Serializable {

	private static final long serialVersionUID = 5517451264504357807L;

	EntityManager entityManager;

	/***
	 * CARREGAR A LISTA DE LIVROS
	 * 
	 *
	 * @return lista
	 */
	public List<LivroModel> lista() {
		List<LivroModel> lista = new ArrayList<LivroModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (LivroEntity.findAll)
		Query query = entityManager.createNamedQuery("LivroEntity.findAll");

		// LISTANDO DOS OS LIVROS
		@SuppressWarnings("unchecked")
		Collection<LivroEntity> listaLivroEntityy = (Collection<LivroEntity>) query.getResultList();

		listaLivroEntityy.forEach(entity -> {
			lista.add(new LivroModel(entity));
		});
		return lista;
	}

	/***
	 * CARREGAR A LISTA DE AUTOR
	 * 
	 *
	 * @return listaAutorModel
	 */
	public List<AutorModel> listaAutorModel() {
		List<AutorModel> lista = new ArrayList<AutorModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (AutorEntity.findAll)
		Query query = entityManager.createNamedQuery("AutorEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<AutorEntity> listaEntity = (Collection<AutorEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new AutorModel(entity));
		});

		return lista;
	}

	/***
	 * CARREGAR A LISTA DE GENERO
	 * 
	 *
	 * @return listaGeneroModel
	 */
	public List<GeneroModel> listaGeneroModel() {
		List<GeneroModel> lista = new ArrayList<GeneroModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (GeneroEntity.findAll)
		Query query = entityManager.createNamedQuery("GeneroEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<GeneroEntity> listaEntity = (Collection<GeneroEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new GeneroModel(entity));
		});

		return lista;
	}

	/***
	 * CONSULTA UM AUTOR CADASTRADA PELO NOME
	 * 
	 * @param nome
	 * @return AutorModel
	 */
	public AutorModel buscaAutorPorNome(String nome) {

		try {
			entityManager = Uteis.JpaEntityManager();

			// QUERY QUE VAI SER EXECUTADA (AutorEntity.GroupByOrigemNome)
			Query query = Uteis.JpaEntityManager().createNamedQuery("AutorEntity.GroupByOrigemNome");

			// PARÂMETROS DA QUERY
			query.setParameter("nome", nome);

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return new AutorModel((AutorEntity) query.getSingleResult());

		} catch (Exception e) {
			return null;
		}

	}

	/***
	 * CARREGAR A LISTA DE AUTOR PARA AUTO COMPLETE
	 * 
	 * @param nome
	 *
	 * @return lista
	 */
	public List<AutorModel> listaAutorModel(String nome) {
		List<AutorModel> lista = new ArrayList<AutorModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (AutorEntity.findAll)
		Query query = entityManager.createNamedQuery("AutorEntity.GroupByOrigemNome");

		// PARÂMETROS DA QUERY
		query.setParameter("nome", "%" + nome + "%");

		// LISTANDO DOS AUTOR
		@SuppressWarnings("unchecked")
		Collection<AutorEntity> listaAutorEntity = (Collection<AutorEntity>) query.getResultList();

		listaAutorEntity.forEach(entity -> {
			lista.add(new AutorModel(entity));
		});

		return lista;
	}

	/***
	 * CONSULTA UM GENERO CADASTRADA PELO NOME
	 * 
	 * @param nome
	 * @return GeneroModel
	 */
	public GeneroModel buscaGeneroPorNome(String nome) {

		try {
			entityManager = Uteis.JpaEntityManager();

			// QUERY QUE VAI SER EXECUTADA (GeneroEntity.GroupByOrigemNome)
			Query query = Uteis.JpaEntityManager().createNamedQuery("GeneroEntity.GroupByOrigemNome");

			// PARÂMETROS DA QUERY
			query.setParameter("nome", nome);

			// RETORNA O GENERO SE FOR LOCALIZADO
			return new GeneroModel((GeneroEntity) query.getSingleResult());

		} catch (Exception e) {
			return null;
		}

	}

	/***
	 * CARREGAR A LISTA DE AUTOR PARA AUTO COMPLETE
	 * 
	 * @param nome
	 *
	 * @return lista
	 */
	public List<GeneroModel> listaGeneroModel(String nome) {
		List<GeneroModel> lista = new ArrayList<GeneroModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (GeneroEntity.findAll)
		Query query = entityManager.createNamedQuery("GeneroEntity.GroupByOrigemNome");

		// PARÂMETROS DA QUERY
		query.setParameter("nome", "%" + nome + "%");

		// LISTANDO DOS AUTOR
		@SuppressWarnings("unchecked")
		Collection<GeneroEntity> listaGeneroEntity = (Collection<GeneroEntity>) query.getResultList();

		listaGeneroEntity.forEach(entity -> {
			lista.add(new GeneroModel(entity));
		});

		return lista;
	}

	/***
	 * CARREGAR A LISTA DE AUTOR PARA AUTO COMPLETE
	 * 
	 * @param nome
	 *
	 * @return lista
	 */
	public List<LivroModel> listaTitulo(String nome) {
		List<LivroModel> lista = new ArrayList<LivroModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (LivroEntity.GroupByTitulo)
		Query query = entityManager.createNamedQuery("LivroEntity.GroupByTitulo");

		// PARÂMETROS DA QUERY
		query.setParameter("titulo", "%" + nome + "%");

		// LISTANDO DOS AUTOR
		@SuppressWarnings("unchecked")
		Collection<LivroEntity> listaGeneroEntity = (Collection<LivroEntity>) query.getResultList();

		listaGeneroEntity.forEach(entity -> {
			lista.add(new LivroModel(entity));
		});

		return lista;
	}

	/***
	 * CONSULTA UMA LIVRO CADASTRADA PELO ID
	 * 
	 * @param cod
	 * @return LivroModel
	 */
	public LivroModel buscaPorCod(Integer cod) {

		try {

			// QUERY QUE VAI SER EXECUTADA (LivroEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("LivroEntity.GroupByCod");

			// PARÂMETROS DA QUERY
			query.setParameter("codigo", cod);

			// RETORNA O LIVRO SE FOR LOCALIZADO
			return new LivroModel((LivroEntity) query.getSingleResult());

		} catch (Exception e) {
			return null;
		}

	}

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO LIVRO
	 * 
	 * @param livroModel EnderecoRepository
	 */
	public void SalvarNovoRegistro(LivroModel livroModel) throws Exception {

		try {
			entityManager = Uteis.JpaEntityManager();

			if (livroModel.getAutorModel().getCodigo() == null) {
				livroModel.setAutorModel(
						new AutorModel(entityManager.merge(new AutorEntity(livroModel.getAutorModel()))));
			}

			if (livroModel.getGeneroModel().getCodigo() == null) {
				livroModel.setGeneroModel(
						new GeneroModel(entityManager.merge(new GeneroEntity(livroModel.getGeneroModel()))));
			}

			entityManager.persist(new LivroEntity(livroModel));
		} catch (PersistenceException e) {
			throw new Exception("Endereço não pode ser salvar.");

		}
	}

	/***
	 * CONSULTA UM AUTOR CADASTRADO PELO ID
	 * 
	 * @param cod
	 * @return AutorModel
	 */
	public AutorModel buscaAutorPorCod(Integer cod) {

		try {

			// QUERY QUE VAI SER EXECUTADA (AutorEntity.GroupByCod)
			Query query = Uteis.JpaEntityManager().createNamedQuery("AutorEntity.GroupByCod");

			// PARÂMETROS DA QUERY
			query.setParameter("id", cod);

			// RETORNA O AUTOR SE FOR LOCALIZADO
			return new AutorModel((AutorEntity) query.getSingleResult());

		} catch (Exception e) {
			return null;
		}

	}

	/***
	 * CONSULTA UM GENÊRO CADASTRADO PELO ID
	 * 
	 * @param cod
	 * @return generoModel
	 */
	public GeneroModel buscaGeneroPorCod(Integer cod) {

		try {

			// QUERY QUE VAI SER EXECUTADA (GeneroEntity.GroupByCod)
			Query query = Uteis.JpaEntityManager().createNamedQuery("GeneroEntity.GroupByCod");

			// PARÂMETROS DA QUERY
			query.setParameter("id", cod);

			// RETORNA O GENÊRO SE FOR LOCALIZADO
			return new GeneroModel((GeneroEntity) query.getSingleResult());

		} catch (Exception e) {
			return null;
		}

	}

	/***
	 * CONSULTA UMA LIVRO CADASTRADO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private LivroEntity GetLivro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(LivroEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param livroModel
	 */
	public void AlterarRegistro(LivroModel livroModel) {

		entityManager = Uteis.JpaEntityManager();
		if (StringUtils.isNotBlank(livroModel.getAutorModel().getCodigo().toString())) {
			livroModel.setAutorModel(new AutorModel(entityManager.merge(new AutorEntity(livroModel.getAutorModel()))));
		}

		if (StringUtils.isNotBlank(livroModel.getGeneroModel().getCodigo().toString())) {
			livroModel.setGeneroModel(
					new GeneroModel(entityManager.merge(new GeneroEntity(livroModel.getGeneroModel()))));
		}
		entityManager.merge(new LivroEntity(livroModel));
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		entityManager.remove(this.GetLivro(codigo));
	}

}
