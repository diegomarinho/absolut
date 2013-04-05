package br.absolut.negocio.movimento.compra;

import java.util.Date;
import java.util.List;

import br.absolut.persistencia.movimento.compra.Compra;

public interface SvnCompra {
	public Compra inclui(Compra compra);
	public Compra altera(Compra compra);
	public List<Compra> recuperaTodos();
	public List<Compra> consultaPorParametros(DtoParametroConsulta parametros);
	public Compra recuperaPorId(Long codigo);
	public List<Compra> recuperaComprasPorPeriodo(Date dtInicial, Date dtFinal);
	
	/**
	 * Método responsável por recuperar uma lista de Compras parceladas.
	 * 
	 * @return List{@literal<Compra>}
	 */
	public List<Compra> recuperaComprasParceladas();
}
