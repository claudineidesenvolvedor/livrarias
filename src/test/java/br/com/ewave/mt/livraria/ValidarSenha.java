package br.com.ewave.mt.livraria;

import org.junit.Assert;
import org.junit.Test;

import br.com.ewave.mt.livraria.model.UsuarioModel;
import br.com.ewave.mt.livraria.uteis.Role;

public class ValidarSenha {

	@Test
	public void validarSenha() {
		UsuarioModel model = new UsuarioModel(1, "admin", "admin", Role.ADMIN);
		Assert.assertSame(model.isValidadoSenha("admin"), true);
	}

}
