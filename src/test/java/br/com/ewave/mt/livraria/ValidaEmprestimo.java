package br.com.ewave.mt.livraria;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import br.com.ewave.mt.livraria.model.EmprestimoModel;
import br.com.ewave.mt.livraria.model.EnderecoModel;
import br.com.ewave.mt.livraria.model.GeneroModel;
import br.com.ewave.mt.livraria.model.InstituicaoModel;
import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.model.PessoaModel;
import br.com.ewave.mt.livraria.model.TelefoneModel;
import br.com.ewave.mt.livraria.model.UsuarioModel;
import br.com.ewave.mt.livraria.repository.entity.AutorEntity;
import br.com.ewave.mt.livraria.repository.entity.AutorModel;
import br.com.ewave.mt.livraria.repository.entity.EnderecoEntity;
import br.com.ewave.mt.livraria.repository.entity.GeneroEntity;
import br.com.ewave.mt.livraria.repository.entity.InstituicaoEntity;
import br.com.ewave.mt.livraria.repository.entity.LivroEntity;
import br.com.ewave.mt.livraria.repository.entity.PessoaEntity;
import br.com.ewave.mt.livraria.repository.entity.TelefoneEntity;
import br.com.ewave.mt.livraria.repository.entity.UsuarioEntity;
import br.com.ewave.mt.livraria.uteis.Role;
import br.com.ewave.mt.livraria.uteis.Status;
import br.com.ewave.mt.livraria.uteis.TipoSexo;
import br.com.ewave.mt.livraria.uteis.TipoTelefone;

public class ValidaEmprestimo {
	@Test
	public void validarEmprestimo() {
		AutorModel autorModel = new AutorModel(1, "a");
		GeneroModel generoModel = new GeneroModel(1, "a");
		LivroModel livroModel = new LivroModel(1, "titulo", new GeneroEntity(generoModel), new AutorEntity(autorModel),
				"sinopse", "capa", false);
		EnderecoModel enderecoModel = new EnderecoModel(1, "", "", "", "", "", "", "", "");
		UsuarioModel usuarioModel = new UsuarioModel(1, "a", "a", Role.ADMIN);
		TelefoneModel telefoneModel = new TelefoneModel(1, "00", TipoTelefone.CELULAR);
		InstituicaoModel instituicaoModel = new InstituicaoModel(1, "fa", "00", Status.INATIVACAO,
				new EnderecoEntity(enderecoModel), new TelefoneEntity(telefoneModel));

		PessoaModel pessoaModel = new PessoaModel(1, "nome", TipoSexo.MASCULINO, Status.INATIVACAO, "email",
				new EnderecoEntity(enderecoModel), false, new UsuarioEntity(usuarioModel), "00.000", 0,
				new InstituicaoEntity(instituicaoModel), new TelefoneEntity(telefoneModel), Calendar.getInstance());

		EmprestimoModel model = new EmprestimoModel(1, Calendar.getInstance(), new LivroEntity(livroModel),
				new PessoaEntity(pessoaModel), true, Calendar.getInstance());

		Assert.assertSame(model.isValidarEmprestimo(), false);
	}
}
