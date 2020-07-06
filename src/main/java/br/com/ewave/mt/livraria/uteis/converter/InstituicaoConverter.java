package br.com.ewave.mt.livraria.uteis.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ewave.mt.livraria.model.InstituicaoModel;
import br.com.ewave.mt.livraria.repository.InstituicaoRepository;

@FacesConverter("instituicaoConverter")
public class InstituicaoConverter implements Converter {

	private InstituicaoRepository instituicaoRepository = new InstituicaoRepository();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return instituicaoRepository.buscaPorCod(Integer.valueOf(value));
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !"".equals(value)) {
			InstituicaoModel instituicaoModel = (InstituicaoModel) value;
			return instituicaoModel.getCodigo().toString();
		}

		return null;
	}

}
