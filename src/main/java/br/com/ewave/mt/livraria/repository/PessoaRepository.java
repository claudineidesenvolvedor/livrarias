package br.com.ewave.mt.livraria.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.ewave.mt.livraria.model.EnderecoModel;
import br.com.ewave.mt.livraria.model.PessoaModel;
import br.com.ewave.mt.livraria.model.TelefoneModel;
import br.com.ewave.mt.livraria.model.UsuarioModel;
import br.com.ewave.mt.livraria.repository.entity.EnderecoEntity;
import br.com.ewave.mt.livraria.repository.entity.PessoaEntity;
import br.com.ewave.mt.livraria.repository.entity.TelefoneEntity;
import br.com.ewave.mt.livraria.repository.entity.UsuarioEntity;
import br.com.ewave.mt.livraria.uteis.Uteis;

public class PessoaRepository implements Serializable {

	private static final long serialVersionUID = 5517451264504357807L;

	EntityManager entityManager;

	/***
	 * CARREGAR A LISTA DE PESSOA
	 * 
	 *
	 * @return lista
	 */
	public List<PessoaModel> lista() {
		List<PessoaModel> lista = new ArrayList<PessoaModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (PessoaEntity.findAll)
		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> listaEntity = (Collection<PessoaEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new PessoaModel(entity));
		});

		return lista;
	}

	/***
	 * CARREGAR A LISTA DE PESSOA PARA AUTO COMPLETE
	 * 
	 * @param nome
	 *
	 * @return lista
	 */
	public List<PessoaModel> listaPessoa(String nome) {
		List<PessoaModel> lista = new ArrayList<PessoaModel>();

		entityManager = Uteis.JpaEntityManager();

		// QUERY QUE VAI SER EXECUTADA (InstituicaoEntity.findNomel)
		Query query = entityManager.createNamedQuery("PessoaEntity.findNome");

		// PARÂMETROS DA QUERY
		query.setParameter("nome", "%" + nome + "%");

		// LISTANDO DOS PESSOA
		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> listaEntity = (Collection<PessoaEntity>) query.getResultList();

		listaEntity.forEach(entity -> {
			lista.add(new PessoaModel(entity));
		});

		return lista;
	}

	/***
	 * CONSULTA UMA PESSOA CADASTRADA PELO ID
	 * 
	 * @param cod
	 * @return PessoaModel
	 */
	public PessoaModel buscaPorCod(Integer cod) {

		try {

			// QUERY QUE VAI SER EXECUTADA (PessoaEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery("PessoaEntity.GroupByCod");

			// PARÂMETROS DA QUERY
			query.setParameter("codigo", cod);

			// RETORNA O PESSOA SE FOR LOCALIZADO
			return new PessoaModel((PessoaEntity) query.getSingleResult());

		} catch (Exception e) {
			return null;
		}

	}

	/***
	 * CONSULTA UM PESSOA CADASTRADA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return PessoaEntity
	 */
	public PessoaEntity GetPessoa(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(PessoaEntity.class, codigo);
	}

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
	 * 
	 * @param pessoaModel
	 * @throws Exception
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel) throws Exception {
		entityManager = Uteis.JpaEntityManager();
		try {
			if (pessoaModel.getEnderecoModel().getCodigo() == null) {
				pessoaModel.setEnderecoModel(
						new EnderecoModel(entityManager.merge(new EnderecoEntity(pessoaModel.getEnderecoModel()))));

			}

			if (pessoaModel.getTelefoneModel().getId() == null) {
				pessoaModel.setTelefoneModel(
						new TelefoneModel(entityManager.merge(new TelefoneEntity(pessoaModel.getTelefoneModel()))));
			}

			if (pessoaModel.getUsuarioModel().getCodigo() == null) {
				pessoaModel.setUsuarioModel(
						new UsuarioModel(entityManager.merge(new UsuarioEntity(pessoaModel.getUsuarioModel()))));
			}

			entityManager.merge(new PessoaEntity(pessoaModel));

		} catch (PersistenceException e) {
			throw new Exception("Pessoa não pode ser salvar.");

		}

	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param pessoaModel
	 * @throws Exception
	 */
	public void AlterarRegistro(PessoaModel pessoaModel) throws Exception {
		entityManager = Uteis.JpaEntityManager();
		try {

			pessoaModel.setTelefoneModel(
					new TelefoneModel(entityManager.merge(new TelefoneEntity(pessoaModel.getTelefoneModel()))));

			pessoaModel.setEnderecoModel(
					new EnderecoModel(entityManager.merge(new EnderecoEntity(pessoaModel.getEnderecoModel()))));

			pessoaModel.setUsuarioModel(
					new UsuarioModel(entityManager.merge(new UsuarioEntity(pessoaModel.getUsuarioModel()))));
			entityManager.merge(new PessoaEntity(pessoaModel));

		} catch (PersistenceException e) {
			throw new Exception("Instituição não pode ser alterado.");

		}
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(codigo);

		entityManager.remove(pessoaEntity);
	}

}
