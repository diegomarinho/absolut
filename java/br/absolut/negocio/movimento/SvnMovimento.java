package br.absolut.negocio.movimento;

import java.util.List;

import br.absolut.persistencia.movimento.Movimento;

public interface SvnMovimento {
	public Movimento inclui(Movimento movimento);
	public Movimento altera(Movimento movimento);
	public List<Movimento> recuperaTodos();
	public Movimento recuperaPorId(Long codigo);	
}
