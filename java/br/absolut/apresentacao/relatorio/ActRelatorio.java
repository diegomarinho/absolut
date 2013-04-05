package br.absolut.apresentacao.relatorio;

import java.util.Date;
import java.util.List;

import br.absolut.apresentacao.compra.CtlCompra;
import br.absolut.apresentacao.seguranca.Seguranca;
import br.absolut.apresentacao.venda.CtlVenda;
import br.absolut.util.filter.report.ReportUtil;

public class ActRelatorio extends Seguranca {
	private static final long serialVersionUID = -1715674925137507444L;

	private static final String MSG_ERRO_GERAR_RELATORIO = "Erro ao gerar o relatório";

	private CtlVenda ctlVenda;
	private CtlCompra ctlCompra;
	private Date dtVendaInicial;
	private Date dtVendaFinal;
	private Date dtCompraInicial;
	private Date dtCompraFinal;

	public ActRelatorio() throws Exception {
		super(Seguranca.PERFIL_ACESSO_FUNCIONARIO);
	}

	public String geraRelatorioCompraPorPeriodo() {
		try {
			List<DtoRelatorioCompraPeriodo> listaRelatorio = ctlCompra
					.geraRelatorioCompraPorPeriodo(dtCompraInicial,
							dtCompraFinal);
			ReportUtil.gerarRelatorio("relComprasPorPeriodo", listaRelatorio);
		} catch (Exception e) {
			addMsgErro(MSG_ERRO_GERAR_RELATORIO);
			e.printStackTrace();
		}

		return "";
	}

	public String geraRelatorioVendaPorPeriodo() {
		try {
			List<DtoRelatorioVendaPeriodo> listaRelatorio = ctlVenda
					.geraRelatorioVendaPorPeriodo(dtVendaInicial, dtVendaFinal);
			ReportUtil.gerarRelatorio("relVendasPorPeriodo", listaRelatorio);
		} catch (Exception e) {
			addMsgErro(MSG_ERRO_GERAR_RELATORIO);
			e.printStackTrace();
		}

		return "";
	}

	public Date getDtCompraFinal() {
		return dtCompraFinal;
	}

	public Date getDtCompraInicial() {
		return dtCompraInicial;
	}

	public Date getDtVendaFinal() {
		return dtVendaFinal;
	}

	public Date getDtVendaInicial() {
		return dtVendaInicial;
	}

	public void setCtlCompra(CtlCompra ctlCompra) {
		this.ctlCompra = ctlCompra;
	}

	public void setCtlVenda(CtlVenda ctlVenda) {
		this.ctlVenda = ctlVenda;
	}

	public void setDtCompraFinal(Date dtCompraFinal) {
		this.dtCompraFinal = dtCompraFinal;
	}

	public void setDtCompraInicial(Date dtCompraInicial) {
		this.dtCompraInicial = dtCompraInicial;
	}

	public void setDtVendaFinal(Date dtVendaFinal) {
		this.dtVendaFinal = dtVendaFinal;
	}

	public void setDtVendaInicial(Date dtVendaInicial) {
		this.dtVendaInicial = dtVendaInicial;
	}
}
