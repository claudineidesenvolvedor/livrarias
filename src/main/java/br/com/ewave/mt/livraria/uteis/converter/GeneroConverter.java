package br.com.ewave.mt.livraria.uteis.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ewave.mt.livraria.model.GeneroModel;
import br.com.ewave.mt.livraria.repository.LivroReository;

@FacesConverter("generoConverter")
public class GeneroConverter implements Converter {

	private LivroReository livroReository = new LivroReository();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return livroReository.buscaGeneroPorNome(value);// (Integer.valueOf(value));
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !"".equals(value)) {
			GeneroModel generoModel = (GeneroModel) value;
			return generoModel.getCodigo().toString();
		}

		return null;
	}
}
