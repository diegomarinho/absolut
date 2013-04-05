package br.absolut.apresentacao.compra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import br.absolut.apresentacao.relatorio.DtoRelatorioCompraPeriodo;
import br.absolut.negocio.fornecedor.SvnFornecedor;
import br.absolut.negocio.movimento.SvnMovimento;
import br.absolut.negocio.movimento.SvnTipoMovimento;
import br.absolut.negocio.movimento.compra.SvnCompra;
import br.absolut.negocio.movimento.estoque.SvnEstoque;
import br.absolut.negocio.movimento.pagamento.SvnPagamento;
import br.absolut.negocio.produto.SvnProduto;
import br.absolut.negocio.usuario.SvnUsuario;
import br.absolut.persistencia.fornecedor.Fornecedor;
import br.absolut.persistencia.movimento.Movimento;
import br.absolut.persistencia.movimento.TipoMovimento;
import br.absolut.persistencia.movimento.compra.Compra;
import br.absolut.persistencia.movimento.estoque.Estoque;
import br.absolut.persistencia.movimento.pagamento.Pagamento;
import br.absolut.persistencia.produto.Produto;
import br.absolut.persistencia.usuario.Usuario;
import br.absolut.util.function.Funcoes;

public class CtlCompra {

	private SvnFornecedor svnFornecedor;
	private SvnProduto svnProduto;
	private SvnPagamento svnPagamento;
	private SvnCompra svnCompra;
	private SvnMovimento svnMovimento;
	private SvnTipoMovimento svnTipoMovimento;
	private SvnUsuario svnUsuario;
	private SvnEstoque svnEstoque;

	public String salvarCompra(DtoCompra dtoCompra) {
		String codigo = null;
		Movimento movimento;
		Compra compra = new Compra();

		//Salvar na tabela compra
		compra.setNotaFiscal(dtoCompra.getNotaFiscal());
		compra.setDtCompra(dtoCompra.getDtCompra());

		if (dtoCompra.getCodFornecedor() != null && !dtoCompra.getCodFornecedor().isEmpty()) {
			Fornecedor fornecedor = svnFornecedor.recuperaPorId(new Long(dtoCompra.getCodFornecedor()));
			compra.setFornecedor(fornecedor);
		}

		if (dtoCompra.getCodPagamento() != null && !dtoCompra.getCodPagamento().isEmpty()) {
			Pagamento pagamento = svnPagamento.recuperaPorId(new Long(dtoCompra.getCodPagamento()));
			compra.setPagamento(pagamento);
		}

		compra.setQtdProduto(new Long(dtoCompra.getQtdProduto()));
		compra.setTotal(Funcoes.stringToDouble(dtoCompra.getTotal()));

		if (dtoCompra.getCodUsuario() != null) {
			Usuario usuario = svnUsuario.recuperaPorId(dtoCompra.getCodUsuario());
			compra.setUsuario(usuario);
		}

		Compra compraPersistente = svnCompra.inclui(compra);

		//Salvar na tabela Movimento
		if (compraPersistente != null) {
			for (DtoItem item : dtoCompra.getListaItens()) {
				movimento = new Movimento();
				movimento.setCodCompra(compraPersistente.getCod());
				movimento.setCodVenda(null);
				movimento.setDtMovimento(compraPersistente.getDtCompra());
				movimento.setProduto(svnProduto.recuperaPorId(item.getCodItem()));
				movimento.setQtdMovimento(new Long(item.getQuantidade()));
				movimento.setTipoMovimento(svnTipoMovimento.recuperaPorId(TipoMovimento.MOVIMENTO_ENTRADA));
				movimento.setValorCompra(Funcoes.stringToDouble(item.getValCompra()));
				movimento.setValorVenda(Funcoes.stringToDouble(item.getValVenda()));
				movimento.setValorTotal(Funcoes.stringToDouble(item.getTotal()));

				//atualizar estoque e produto
				Movimento movimentoPersistente = svnMovimento.inclui(movimento);
				if (movimentoPersistente != null) atualizaEstoque(movimentoPersistente);

			}
			codigo = compraPersistente.getCod().toString();
		}
		return codigo;
	}

	private void atualizaEstoque(Movimento movimentoPersistente) {
		//Atualizar na tabela estoque
		Estoque estoque = new Estoque();
		estoque.setMovimento(movimentoPersistente);
		estoque.setQtdSaldo(movimentoPersistente.getProduto().getSaldoAtual() + movimentoPersistente.getQtdMovimento());

		Estoque estoquePersistente = svnEstoque.inclui(estoque);

		//Atualizar na tabela produto
		if (estoquePersistente != null) {
			Produto produto = movimentoPersistente.getProduto();
			if (produto != null) {
				produto.setSaldoAtual(estoquePersistente.getQtdSaldo());
				produto.setValorCompra(movimentoPersistente.getValorCompra());
				produto.setValorVenda(movimentoPersistente.getValorVenda());
				svnProduto.altera(produto);
			}
		}

	}

	public List<SelectItem> recuperaListaFornecedor() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item;
		List<Fornecedor> listaFornecedor = svnFornecedor.consultaPorParametros(null);

		if (listaFornecedor != null && !listaFornecedor.isEmpty()) {
			for (Fornecedor fornecedor : listaFornecedor) {
				item = new SelectItem();

				item.setValue(fornecedor.getCod().toString());
				item.setLabel(fornecedor.getNome());

				lista.add(item);
			}
		}
		return lista;
	}

	public List<SelectItem> recuperaListaProduto() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item;
		List<Produto> listaProduto = svnProduto.consultaPorParametros(null);

		if (listaProduto != null && !listaProduto.isEmpty()) {
			for (Produto produto : listaProduto) {
				item = new SelectItem();

				item.setValue(produto.getCod().toString());
				item.setLabel(produto.getDescricao());

				lista.add(item);
			}
		}
		return lista;
	}

	public List<SelectItem> recuperaListaPagamento() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item;
		List<Pagamento> listaPagamento = svnPagamento.recuperaTodos();

		if (listaPagamento != null && !listaPagamento.isEmpty()) {
			for (Pagamento pagamento : listaPagamento) {
				item = new SelectItem();

				item.setValue(pagamento.getCod().toString());
				item.setLabel(pagamento.getDescricao());

				lista.add(item);
			}
		}
		return lista;
	}

	public DtoItem recuperaProduto(Long codProduto) {
		DtoItem item = null;
		Produto produto = svnProduto.recuperaPorId(codProduto);
		if (produto != null) {
			item = new DtoItem();

			item.setCodItem(produto.getCod());
			item.setCodBarra(produto.getCodBarra());
			item.setFabricante(produto.getFabricante() != null ? produto.getFabricante() : "-----");
			item.setDescricao(produto.getDescricao());
			if (produto.getTipoProduto() != null) {
				item.setCodTipo(produto.getTipoProduto().getCod());
				item.setDescrTipo(produto.getTipoProduto().getDescricao());
			}
		}
		return item;
	}

	public List<DtoRelatorioCompraPeriodo> geraRelatorioCompraPorPeriodo(Date dtInicial, Date dtFinal) {
		double total = 0;
		DtoItem item;
		List<DtoRelatorioCompraPeriodo> lista = new ArrayList<DtoRelatorioCompraPeriodo>();
		List<DtoItem> listaItem = new ArrayList<DtoItem>();
		DtoRelatorioCompraPeriodo dtoRelatorio = new DtoRelatorioCompraPeriodo();
		List<Compra> listaCompra = svnCompra.recuperaComprasPorPeriodo(dtInicial, dtFinal);

		dtoRelatorio.setDtInicial(Funcoes.utilDateToStringDDMMYYYY(dtInicial));
		dtoRelatorio.setDtFinal(Funcoes.utilDateToStringDDMMYYYY(dtFinal));

		if (listaCompra != null && !listaCompra.isEmpty()) {
			for (Compra compra : listaCompra) {
				total += compra.getTotal();

				item = new DtoItem();

				item.setCodCompra(compra.getCod());
				item.setData(Funcoes.utilDateToStringDDMMYYYY(compra.getDtCompra()));
				item.setFornecedor(compra.getFornecedor() != null && compra.getFornecedor().getNome() != null ? compra.getFornecedor().getNome() : "-----");
				item.setQuantidade(compra.getQtdProduto().toString());
				item.setTotal(Funcoes.formataValor(compra.getTotal()));

				listaItem.add(item);
			}

			dtoRelatorio.setTotal(Funcoes.formataValor(total));
			dtoRelatorio.setListaItem(listaItem);

			lista.add(dtoRelatorio);
		}

		return lista;
	}

	public void setSvnFornecedor(SvnFornecedor svnFornecedor) {
		this.svnFornecedor = svnFornecedor;
	}

	public void setSvnProduto(SvnProduto svnProduto) {
		this.svnProduto = svnProduto;
	}

	public void setSvnPagamento(SvnPagamento svnPagamento) {
		this.svnPagamento = svnPagamento;
	}

	public void setSvnCompra(SvnCompra svnCompra) {
		this.svnCompra = svnCompra;
	}

	public void setSvnUsuario(SvnUsuario svnUsuario) {
		this.svnUsuario = svnUsuario;
	}

	public void setSvnMovimento(SvnMovimento svnMovimento) {
		this.svnMovimento = svnMovimento;
	}

	public void setSvnTipoMovimento(SvnTipoMovimento svnTipoMovimento) {
		this.svnTipoMovimento = svnTipoMovimento;
	}

	public void setSvnEstoque(SvnEstoque svnEstoque) {
		this.svnEstoque = svnEstoque;
	}
}
