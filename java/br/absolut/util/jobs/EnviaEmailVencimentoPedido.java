/*
 * Direitos autorais 2010 Diego Marinho Almeida
 *
 * Este sistema é confidencial e de propriedade da Diego Marinho Almeida Solu��es em Inform�tica. A divulgação das informações
 * contidas aqui somente serão aceitas mediante acordo prévio estabelecido para com o proprietário
 * do sistema.
 */
package br.absolut.util.jobs;

import java.util.Date;
import java.util.List;

import br.absolut.negocio.movimento.compra.SvnCompra;
import br.absolut.negocio.movimento.venda.SvnVenda;
import br.absolut.persistencia.movimento.compra.Compra;
import br.absolut.util.function.Constantes;
import br.absolut.util.function.Funcoes;

/**
 * Job de envio de pedidos prestes a vencer.
 * 
 * @author <a href="mailto:diegomarinhoalmeida@gmail.com">Diego Marinho
 *         Almeida</a>
 * @since 1.0 - 22/02/2011
 */
public class EnviaEmailVencimentoPedido {

	private static final int TRINTA_DIAS = 2;
	private static final int UM_TRINTA_DIAS = 3;
	private static final int UM_TRINTA_SESSENTA_DIAS = 4;
	private static final int UM_TRINTA_SESSENTA_NOVENTA_DIAS = 5;

	private SvnCompra svnCompra;
	private SvnVenda svnVenda;

	/**
	 * Prepara e-mails das compras
	 */
	private void emailCompra() {
		List<Compra> listCompras = svnCompra.recuperaComprasParceladas();

		if ((listCompras != null) && !listCompras.isEmpty()) {
			for (Compra compra : listCompras) {
				Date dataVencimento = null;
				switch (compra.getPagamento().getCod().intValue()) {
				case TRINTA_DIAS:
					dataVencimento = validaDataAviso(compra.getDtCompra(), 1);
					break;
				case UM_TRINTA_DIAS:
					dataVencimento = validaDataAviso(compra.getDtCompra(), 2);
					break;
				case UM_TRINTA_SESSENTA_DIAS:
					dataVencimento = validaDataAviso(compra.getDtCompra(), 3);
					break;
				case UM_TRINTA_SESSENTA_NOVENTA_DIAS:
					dataVencimento = validaDataAviso(compra.getDtCompra(), 4);
					break;
				}

				if (dataVencimento != null) {

				}
			}
		}

	}

	/**
	 * Prepara e-mails das vendas
	 */
	private void emailVenda() {
		// TODO Auto-generated method stub

	}

	public synchronized void enviaEmailVencimentoPedido() {
		try {
			emailCompra();
			emailVenda();
		} catch (Exception e) {

		}

	}

	/**
	 * Valida se é a data para enviar e-mail
	 * 
	 * @param dtCompra
	 * @param cont
	 */
	private Date validaDataAviso(Date dtCompra, int cont) {
		Date dataAviso = null;
		Date dataAtual = new Date();
		Date dataVencimento = null;
		for (int i = 1; i <= cont; i++) {
			if (i == 1) {
				dataAviso = Funcoes.gerarVariacaoData(dtCompra, 20,
						Constantes.TIPO_CALENDAR_DIA);
			} else {
				dataAviso = Funcoes.gerarVariacaoData(dtCompra, i,
						Constantes.TIPO_CALENDAR_MES);
				dataAviso = Funcoes.gerarVariacaoData(dtCompra, 20,
						Constantes.TIPO_CALENDAR_DIA);
			}

			if (dataAtual.equals(dataAviso)) {
				dataVencimento = Funcoes.gerarVariacaoData(dtCompra, (i + 1),
						Constantes.TIPO_CALENDAR_MES);
				break;
			}

		}

		return dataVencimento;

	}

}
