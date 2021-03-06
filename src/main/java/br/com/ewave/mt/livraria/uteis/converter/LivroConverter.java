package br.com.ewave.mt.livraria.uteis.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ewave.mt.livraria.model.LivroModel;
import br.com.ewave.mt.livraria.repository.LivroReository;

//@FacesConverter(forClass = LivroModel.class)
@FacesConverter("livroConverter")
public class LivroConverter implements Converter {

	private LivroReository livroReository = new LivroReository();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return livroReository.buscaPorCod(Integer.valueOf(value));
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !"".equals(value)) {
			LivroModel livroModel = (LivroModel) value;
			return livroModel.getCodigo().toString();
		}

		return null;
	}

}
