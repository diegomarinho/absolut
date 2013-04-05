package br.absolut.negocio.movimento;

import java.util.List;

import br.absolut.persistencia.movimento.DaoTipoMovimento;
import br.absolut.persistencia.movimento.TipoMovimento;

public class SvnTipoMovimentoImp implements SvnTipoMovimento {
	private DaoTipoMovimento daoTipoMovimento;
	
	public SvnTipoMovimentoImp() {}

	public TipoMovimento altera(TipoMovimento tipoMovimento) {
		return this.daoTipoMovimento.update(tipoMovimento);
	}

	public void exclui(Long codigo) {
		TipoMovimento tipo = this.daoTipoMovimento.findById(codigo);
		
		if(tipo != null)
			this.daoTipoMovimento.delete(tipo);
		
	}

	public TipoMovimento inclui(TipoMovimento tipoMovimento) {
		return this.daoTipoMovimento.save(tipoMovimento);
	}

	public TipoMovimento recuperaPorId(Long codigo) {
		return this.daoTipoMovimento.findById(codigo);
	}

	public List<TipoMovimento> recuperaTodos() {
		return this.daoTipoMovimento.listAll();
	}

	public void setDaoTipoMovimento(DaoTipoMovimento daoTipoMovimento) {
		this.daoTipoMovimento = daoTipoMovimento;
	}

}
