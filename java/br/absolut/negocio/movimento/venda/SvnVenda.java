package br.absolut.negocio.movimento.venda;

import java.util.Date;
import java.util.List;

import br.absolut.persistencia.movimento.venda.DtoHistoricoVenda;
import br.absolut.persistencia.movimento.venda.Venda;

public interface SvnVenda {
	public Venda inclui(Venda venda);
	public Venda altera(Venda venda);
	public List<Venda> recuperaTodos();
	public List<Venda> consultaPorParametros(DtoParametroConsulta parametros);
	public List<DtoHistoricoVenda> recuperaHistoricoVenda(Date dtInicial, Date dtFinal);
	public List<Venda> recuperaVendasPorPeriodo(Date dtInicial,	Date dtFinal);
	public Venda recuperaPorId(Long codigo);
}
