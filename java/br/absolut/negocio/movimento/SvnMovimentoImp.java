package br.absolut.negocio.movimento;

import java.util.List;

import br.absolut.persistencia.movimento.DaoMovimento;
import br.absolut.persistencia.movimento.Movimento;

public class SvnMovimentoImp implements SvnMovimento {
	private DaoMovimento daoMovimento;
	
	public SvnMovimentoImp() {}

	public Movimento altera(Movimento movimento) {
		return this.daoMovimento.update(movimento);
	}

	public Movimento inclui(Movimento movimento) {
		return this.daoMovimento.save(movimento);
	}

	public Movimento recuperaPorId(Long codigo) {
		return this.daoMovimento.findById(codigo);
	}

	public List<Movimento> recuperaTodos() {
		return this.daoMovimento.listAll();
	}

	public void setDaoMovimento(DaoMovimento daoMovimento) {
		this.daoMovimento = daoMovimento;
	}

}
