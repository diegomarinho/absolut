package br.absolut.persistencia.movimento.venda;

import java.util.Date;
import java.util.List;

import br.absolut.persistencia.generico.DaoGenerico;

public interface DaoVenda extends DaoGenerico<Venda, Long> {
	public List<Venda> recuperaVendasPorParametro(List<Object[]> listaParam);
	public List<DtoHistoricoVenda> recuperaHistoricoVenda(Date dtInicial, Date dtFinal);
	public List<Venda> recuperaVendasPorPeriodo(Date dtInicial, Date dtFinal);
}
