package br.absolut.negocio.movimento.compra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.absolut.persistencia.movimento.compra.Compra;
import br.absolut.persistencia.movimento.compra.DaoCompra;

public class SvnCompraImp implements SvnCompra {
	private DaoCompra daoCompra;
	
	public SvnCompraImp() {}

	public Compra altera(Compra compra) {
		return this.daoCompra.update(compra);
	}

	public List<Compra> consultaPorParametros(DtoParametroConsulta parametros) {
		List<Object[]> listaParam = new ArrayList<Object[]>();
		Object[] param;	
		
		if(parametros.getCod() != null) {
			param = new Object[2];
			param[0] = "cod";
			param[1] = parametros.getCod();
			
			listaParam.add(param);
		}
		if(parametros.getCodUsuario() != null) {
			param = new Object[2];
			param[0] = "usuario.cod";
			param[1] = parametros.getCodUsuario();
			
			listaParam.add(param);
		}
		if(parametros.getDtCompra() != null) {
			param = new Object[2];
			param[0] = "dtCompra";
			param[1] = parametros.getDtCompra();
			
			listaParam.add(param);
		}
		if(parametros.getCodFornecedor() != null) {
			param = new Object[2];
			param[0] = "fornecedor.cod";
			param[1] = parametros.getCodFornecedor();
			
			listaParam.add(param);
		}
		if(parametros.getNotaFiscal() != null) {
			param = new Object[2];
			param[0] = "notaFiscal";
			param[1] = parametros.getNotaFiscal();
			
			listaParam.add(param);
		}
		
		return  this.daoCompra.recuperaComprasPorParametro(listaParam);	
	}
	
	public List<Compra> recuperaComprasPorPeriodo(Date dtInicial, Date dtFinal) {
		return this.daoCompra.recuperaComprasPorPeriodo(dtInicial, dtFinal);
	}
	
	/* (non-Javadoc)
	 * @see br.absolut.negocio.movimento.compra.SvnCompra#recuperaComprasParceladas()
	 */
	@Override
	public List<Compra> recuperaComprasParceladas() {
		return daoCompra.recuperaComprasParceladas();
	}

	public Compra inclui(Compra compra) {
		return this.daoCompra.save(compra);
	}

	public Compra recuperaPorId(Long codigo) {
		return this.daoCompra.findById(codigo);
	}

	public List<Compra> recuperaTodos() {
		return this.daoCompra.listAll();
	}

	public void setDaoCompra(DaoCompra daoCompra) {
		this.daoCompra = daoCompra;
	}

}
