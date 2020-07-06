package br.com.ewave.mt.livraria.uteis.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ewave.mt.livraria.model.PessoaModel;
import br.com.ewave.mt.livraria.repository.PessoaRepository;

//@FacesConverter(forClass = PessoaModel.class)
@FacesConverter("pessoaConverter")
public class PessoaConverter implements Converter {

	private PessoaRepository pessoaRepository = new PessoaRepository();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return pessoaRepository.buscaPorCod(Integer.valueOf(value));
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !"".equals(value)) {
			PessoaModel pessoaModel = (PessoaModel) value;
			return pessoaModel.getCodigo().toString();
		}

		return null;
	}

}
