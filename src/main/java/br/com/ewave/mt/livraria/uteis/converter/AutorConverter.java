package br.com.ewave.mt.livraria.uteis.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ewave.mt.livraria.repository.LivroReository;
import br.com.ewave.mt.livraria.repository.entity.AutorModel;

@FacesConverter("autorConverter")
public class AutorConverter implements Converter {

	private LivroReository livroReository = new LivroReository();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return livroReository.buscaAutorPorNome(value);// (Integer.valueOf(value));
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !"".equals(value)) {
			AutorModel autorModel = (AutorModel) value;
			return autorModel.getCodigo().toString();
		}

		return null;
	}

}
