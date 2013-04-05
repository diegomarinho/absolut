package br.absolut.negocio.movimento.venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.absolut.persistencia.movimento.venda.DaoVenda;
import br.absolut.persistencia.movimento.venda.DtoHistoricoVenda;
import br.absolut.persistencia.movimento.venda.Venda;

public class SvnVendaImp implements SvnVenda {
	private DaoVenda daoVenda;
	
	public SvnVendaImp() {}

	public Venda altera(Venda venda) {
		return this.daoVenda.update(venda);
	}

	public List<Venda> consultaPorParametros(DtoParametroConsulta parametros) {
		List<Object[]> listaParam = new ArrayList<Object[]>();
		Object[] param = new Object[2];	
		
		if(parametros.getCod() != null) {
			param[0] = "cod";
			param[1] = parametros.getCod();
			
			listaParam.add(param);
		}
		if(parametros.getCodUsuario() != null) {
			param[0] = "usuario.cod";
			param[1] = parametros.getCodUsuario();
			
			listaParam.add(param);
		}
		if(parametros.getDtVenda() != null) {
			param[0] = "dtVenda";
			param[1] = parametros.getDtVenda();
			
			listaParam.add(param);
		}
		if(parametros.getCodCliente() != null) {
			param[0] = "cliente.cod";
			param[1] = parametros.getCodCliente();
			
			listaParam.add(param);
		}
		
		return  this.daoVenda.recuperaVendasPorParametro(listaParam);	
	}
	
	public List<DtoHistoricoVenda> recuperaHistoricoVenda(Date dtInicial, Date dtFinal) {
		return this.daoVenda.recuperaHistoricoVenda(dtInicial, dtFinal);
	}
	
	public List<Venda> recuperaVendasPorPeriodo(Date dtInicial, Date dtFinal) {
		return this.daoVenda.recuperaVendasPorPeriodo(dtInicial, dtFinal);
	}

	public Venda inclui(Venda venda) {
		return this.daoVenda.save(venda);
	}

	public Venda recuperaPorId(Long codigo) {
		return this.daoVenda.findById(codigo);
	}

	public List<Venda> recuperaTodos() {
		return this.daoVenda.listAll();
	}
	
	public void setDaoVenda(DaoVenda daoVenda) {
		this.daoVenda = daoVenda;
	}
}
