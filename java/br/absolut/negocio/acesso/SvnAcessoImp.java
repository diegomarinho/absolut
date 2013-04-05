package br.absolut.negocio.acesso;

import java.util.List;

import br.absolut.persistencia.acesso.Acesso;
import br.absolut.persistencia.acesso.DaoAcesso;

public class SvnAcessoImp implements SvnAcesso {
	private DaoAcesso daoAcesso;
	
	public SvnAcessoImp() {}

	public Acesso altera(Acesso acesso) {
		return this.daoAcesso.update(acesso);
	}

	public void exclui(Long codigo) {
		Acesso acesso = this.daoAcesso.findById(codigo);
		
		if(acesso != null)
			this.daoAcesso.delete(acesso);
		
	}

	public Acesso inclui(Acesso acesso) {
		return this.daoAcesso.save(acesso);
	}

	public Acesso recuperaPorId(Long codigo) {
		return this.daoAcesso.findById(codigo);
	}

	public List<Acesso> recuperaTodos() {
		return this.daoAcesso.listAll();
	}

	public void setDaoAcesso(DaoAcesso daoAcesso) {
		this.daoAcesso = daoAcesso;
	}
	
}
