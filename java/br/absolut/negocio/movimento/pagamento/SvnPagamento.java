package br.absolut.negocio.movimento.pagamento;

import java.util.List;

import br.absolut.persistencia.movimento.pagamento.Pagamento;

public interface SvnPagamento {
	public Pagamento inclui(Pagamento pagamento);
	public Pagamento altera(Pagamento pagamento);
	public List<Pagamento> recuperaTodos();
	public Pagamento recuperaPorId(Long codigo);	
	public void exclui(Long codigo);
}
