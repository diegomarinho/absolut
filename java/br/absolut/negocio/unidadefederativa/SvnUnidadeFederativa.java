package br.absolut.negocio.unidadefederativa;

import java.util.List;

import br.absolut.persistencia.unidadefederativa.DaoUnidadeFederativa;
import br.absolut.persistencia.unidadefederativa.UnidadeFederativa;

public class SvnUnidadeFederativa {
	DaoUnidadeFederativa dao;
	
	public SvnUnidadeFederativa() throws Exception {
		dao = new DaoUnidadeFederativa();
	}
	
	public List<UnidadeFederativa> recuperaUfs() throws Exception {
		return dao.recuperaUFs();
	}
}
