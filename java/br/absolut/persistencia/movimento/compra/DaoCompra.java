package br.absolut.persistencia.movimento.compra;

import java.util.Date;
import java.util.List;

import br.absolut.persistencia.generico.DaoGenerico;

public interface DaoCompra extends DaoGenerico<Compra, Long>{
	public List<Compra> recuperaComprasPorParametro(List<Object[]> listaParam);
	public List<Compra> recuperaComprasPorPeriodo(Date dtInicial, Date dtFinal);
	
	/**
	 * Método responsável por recuperar uma lista de compras que foram parceladas.
	 * 
	 * @return List{@literal<Compra>}
	 */
	public List<Compra> recuperaComprasParceladas();
}
