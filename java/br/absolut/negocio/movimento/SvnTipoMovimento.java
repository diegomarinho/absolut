package br.absolut.negocio.movimento;

import java.util.List;

import br.absolut.persistencia.movimento.TipoMovimento;

public interface SvnTipoMovimento {
	public TipoMovimento inclui(TipoMovimento tipoMovimento);
	public TipoMovimento altera(TipoMovimento tipoMovimento);
	public List<TipoMovimento> recuperaTodos();
	public TipoMovimento recuperaPorId(Long codigo);	
	public void exclui(Long codigo);
}
