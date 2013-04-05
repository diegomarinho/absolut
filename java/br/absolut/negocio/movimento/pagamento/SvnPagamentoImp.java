package br.absolut.negocio.movimento.pagamento;

import java.util.List;

import br.absolut.persistencia.movimento.pagamento.DaoPagamento;
import br.absolut.persistencia.movimento.pagamento.Pagamento;

public class SvnPagamentoImp implements SvnPagamento {
	private DaoPagamento daoPagamento;
	
	public SvnPagamentoImp() {}

	public Pagamento altera(Pagamento pagamento) {
		return this.daoPagamento.update(pagamento);
	}

	public void exclui(Long codigo) {
		Pagamento pagamento = this.daoPagamento.findById(codigo);
		
		if(pagamento != null)
			this.daoPagamento.delete(pagamento);		
	}

	public Pagamento inclui(Pagamento pagamento) {
		return this.daoPagamento.save(pagamento);
	}

	public Pagamento recuperaPorId(Long codigo) {
		return this.daoPagamento.findById(codigo);
	}

	public List<Pagamento> recuperaTodos() {
		return this.daoPagamento.listAll();
	}

	public void setDaoPagamento(DaoPagamento daoPagamento) {
		this.daoPagamento = daoPagamento;
	}

}
