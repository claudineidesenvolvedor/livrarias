package br.com.ewave.mt.livraria.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.ewave.mt.livraria.model.EnderecoModel;
import br.com.ewave.mt.livraria.model.InstituicaoModel;
import br.com.ewave.mt.livraria.model.TelefoneModel;
import br.com.ewave.mt.livraria.repository.entity.EnderecoEntity;
import br.com.ewave.mt.livraria.repository.entity.InstituicaoEntity;
import br.com.ewave.mt.livraria.repository.entity.TelefoneEntity;
import br.com.ewave.mt.livraria.uteis.Uteis;

public class InstituicaoRepository implements Serializable {

	private static final long serialVersionUID = 5517451264504357807L;

	EntityManager entityManager;

	/***
	 * CARREGAR A LISTA DE INSTITUIÇÃO
	 * 
	 *
	 * @return listaInstituicaoModel
	 */
	public List<InstituicaoModel> lista() {
		List<InstituicaoModel> lista = new ArrayList<InstituicaoModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (AutorEntity.findAll)
		Query query = entityManager.createNamedQuery("InstituicaoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<InstituicaoEntity> listaEntity = (Collection<InstituicaoEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new InstituicaoModel(entity));
		});

		return lista;
	}

	/***
	 * CARREGAR A LISTA DE INSTITUIÇÃO PARA AUTO COMPLETE
	 * 
	 * @param nome
	 *
	 * @return lista
	 */
	public List<InstituicaoModel> listaNome(String nome) {
		List<InstituicaoModel> lista = new ArrayList<InstituicaoModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (InstituicaoEntity.findNomel)
		Query query = entityManager.createNamedQuery("InstituicaoEntity.findNome");

		// PARÂMETROS DA QUERY
		query.setParameter("nome", "%" + nome + "%");

		// LISTANDO DOS INSTITUIÇÃO
		@SuppressWarnings("unchecked")
		Collection<InstituicaoEntity> listaEntity = (Collection<InstituicaoEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new InstituicaoModel(entity));
		});

		return lista;
	}

	/***
	 * CONSULTA UMA INSTITUIÇÃO CADASTRADA PELO ID
	 * 
	 * @param cod
	 * @return InstituicaoModel
	 */
	public InstituicaoModel buscaPorCod(Integer cod) {

		try {

			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("InstituicaoEntity.GroupByCod");

			// PARÂMETROS DA QUERY
			query.setParameter("codigo", cod);

			// RETORNA O INSTITUIÇÃO SE FOR LOCALIZADO
			return new InstituicaoModel((InstituicaoEntity) query.getSingleResult());

		} catch (Exception e) {
			return null;
		}

	}

	/***
	 * CONSULTA UMA INSTITUIÇÃO CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return instituicaoEntity
	 */
	public InstituicaoEntity GetInstituicao(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(InstituicaoEntity.class, codigo);
	}

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA INSTITUIÇÃO
	 * 
	 * @param instituicaoModel
	 * @throws Exception
	 */
	public void SalvarNovoRegistro(InstituicaoModel instituicaoModel) throws Exception {
		entityManager = Uteis.JpaEntityManager();
		try {

			if (instituicaoModel.getTelefoneModel().getId() == null) {
				instituicaoModel.setTelefoneModel(new TelefoneModel(
						entityManager.merge(new TelefoneEntity(instituicaoModel.getTelefoneModel()))));
			}

			if (instituicaoModel.getEnderecoModel().getCodigo() == null) {
				instituicaoModel.setEnderecoModel(new EnderecoModel(
						entityManager.merge(new EnderecoEntity(instituicaoModel.getEnderecoModel()))));

			}

			entityManager.merge(new InstituicaoEntity(instituicaoModel));

		} catch (PersistenceException e) {
			throw new Exception("Instituição não pode ser salvar.");

		}

	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param instituicaoModel
	 * @throws Exception
	 */
	public void AlterarRegistro(InstituicaoModel instituicaoModel) throws Exception {
		entityManager = Uteis.JpaEntityManager();
		try {

			instituicaoModel.setTelefoneModel(
					new TelefoneModel(entityManager.merge(new TelefoneEntity(instituicaoModel.getTelefoneModel()))));

			instituicaoModel.setEnderecoModel(
					new EnderecoModel(entityManager.merge(new EnderecoEntity(instituicaoModel.getEnderecoModel()))));

			entityManager.merge(new InstituicaoEntity(instituicaoModel));

		} catch (PersistenceException e) {
			throw new Exception("Instituição não pode ser alterado.");

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

			entityManager.remove(this.GetInstituicao(codigo));
		} catch (PersistenceException e) {
			throw new Exception("Instituição não pode ser excluído.");

		}
	}

}
