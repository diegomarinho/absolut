package br.absolut.negocio.acesso;

import java.util.List;

import br.absolut.persistencia.acesso.Acesso;


public interface SvnAcesso {
	public Acesso inclui(Acesso acesso);
	public Acesso altera(Acesso acesso);
	public List<Acesso> recuperaTodos();
	public Acesso recuperaPorId(Long codigo);	
	public void exclui(Long codigo);
}
