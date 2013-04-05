package br.absolut.apresentacao.venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.absolut.apresentacao.compra.DtoItem;
import br.absolut.apresentacao.relatorio.DtoRelatorioVenda;
import br.absolut.apresentacao.relatorio.DtoRelatorioVendaPeriodo;
import br.absolut.negocio.cliente.SvnCliente;
import br.absolut.negocio.movimento.SvnMovimento;
import br.absolut.negocio.movimento.SvnTipoMovimento;
import br.absolut.negocio.movimento.estoque.SvnEstoque;
import br.absolut.negocio.movimento.pagamento.SvnPagamento;
import br.absolut.negocio.movimento.venda.SvnVenda;
import br.absolut.negocio.produto.SvnProduto;
import br.absolut.negocio.usuario.SvnUsuario;
import br.absolut.persistencia.cliente.Cliente;
import br.absolut.persistencia.movimento.Movimento;
import br.absolut.persistencia.movimento.TipoMovimento;
import br.absolut.persistencia.movimento.estoque.Estoque;
import br.absolut.persistencia.movimento.pagamento.Pagamento;
import br.absolut.persistencia.movimento.venda.Venda;
import br.absolut.persistencia.produto.Produto;
import br.absolut.persistencia.usuario.Usuario;
import br.absolut.util.function.Funcoes;

public class CtlVenda {

	private SvnEstoque svnEstoque;
	private SvnMovimento svnMovimento;
	private SvnTipoMovimento svnTipoMovimento;
	private SvnProduto svnProduto;
	private SvnUsuario svnUsuario;
	private SvnPagamento svnPagamento;
	private SvnVenda svnVenda;
	private SvnCliente svnCliente;

	public Long salvarVenda(DtoVenda dtoVenda) {
		Long codigo = null;
		Venda venda = new Venda();
		Movimento movimento;

		venda.setDesconto(dtoVenda.getDesconto() != null && !dtoVenda.getDesconto().isEmpty() ? Funcoes.stringToDouble(dtoVenda.getDesconto()) : 0);
		venda.setDtVenda(dtoVenda.getDtPedido());
		venda.setQtdProdutos(new Long(dtoVenda.getQtdProdutos()));
		venda.setSubTotal(Funcoes.stringToDouble(dtoVenda.getSubTotal()));
		venda.setTotal(Funcoes.stringToDouble(dtoVenda.getTotal()));

		if (dtoVenda.getCodCliente() != null) {
			Cliente cliente = svnCliente.recuperaPorId(dtoVenda.getCodCliente());
			venda.setCliente(cliente);
		}
		if (dtoVenda.getCodPagamento() != null) {
			Pagamento pagamento = svnPagamento.recuperaPorId(dtoVenda.getCodPagamento());
			venda.setPagamento(pagamento);
		}
		if (dtoVenda.getCodUsuario() != null) {
			Usuario usuario = svnUsuario.recuperaPorId(dtoVenda.getCodUsuario());
			venda.setUsuario(usuario);
		}

		Venda vendaPersistente = svnVenda.inclui(venda);
		if (vendaPersistente != null) {
			for (DtoItem item : dtoVenda.getListaItem()) {
				movimento = new Movimento();
				movimento.setCodCompra(null);
				movimento.setCodVenda(vendaPersistente.getCod());
				movimento.setDtMovimento(vendaPersistente.getDtVenda());
				movimento.setProduto(svnProduto.recuperaPorId(item.getCodItem()));
				movimento.setQtdMovimento(new Long(item.getQuantidade()));
				movimento.setTipoMovimento(svnTipoMovimento.recuperaPorId(TipoMovimento.MOVIMENTO_SAIDA));
				movimento.setValorVenda(Funcoes.stringToDouble(item.getValVenda()));
				movimento.setValorCompra(Funcoes.stringToDouble(item.getValCompra()));
				movimento.setValorTotal(Funcoes.stringToDouble(item.getTotal()));

				Movimento movimentoPersistente = svnMovimento.inclui(movimento);
				if (movimentoPersistente != null) {
					atualizaEstoque(movimentoPersistente);
				}
			}
			codigo = vendaPersistente.getCod();
		}

		return codigo;
	}

	private void atualizaEstoque(Movimento movimentoPersistente) {
		// atualizar na tabela estoque
		Estoque estoque = new Estoque();
		estoque.setMovimento(movimentoPersistente);
		estoque.setQtdSaldo(movimentoPersistente.getProduto().getSaldoAtual() - movimentoPersistente.getQtdMovimento());

		Estoque estoquePersistente = svnEstoque.inclui(estoque);

		// Atualizar na tabela produto
		if (estoquePersistente != null) {
			Produto produto = movimentoPersistente.getProduto();
			if (produto != null) {
				produto.setSaldoAtual(estoquePersistente.getQtdSaldo());
				svnProduto.altera(produto);
			}
		}
	}

	public List<DtoRelatorioVenda> geraRelatorioVenda(Long codigoVenda) {
		List<DtoRelatorioVenda> lista = new ArrayList<DtoRelatorioVenda>();
		DtoRelatorioVenda dtoRelatorio;
		Venda venda = svnVenda.recuperaPorId(codigoVenda);
		if (venda != null) {
			dtoRelatorio = new DtoRelatorioVenda();
			dtoRelatorio.setCliente(venda.getCliente() != null ? venda.getCliente().getNome() : "");
			dtoRelatorio.setCodigo(venda.getCod().toString());
			dtoRelatorio.setData(Funcoes.utilDateToStringDDMMYYYY(venda.getDtVenda()));
			dtoRelatorio.setDesconto(Funcoes.formataValor(Funcoes.calcularValorDesconto(venda.getDesconto(), venda.getSubTotal())));
			dtoRelatorio.setQuantidade(venda.getQtdProdutos().toString());
			dtoRelatorio.setSubtotal(Funcoes.formataValor(venda.getSubTotal()));
			dtoRelatorio.setTotal(Funcoes.formataValor(venda.getTotal()));

			dtoRelatorio.setVendedor(venda.getUsuario() != null ? venda.getUsuario().getNome() : "");

			List<DtoItem> listaItem = new ArrayList<DtoItem>();
			DtoItem item;
			if (venda.getMovimentos() != null && !venda.getMovimentos().isEmpty()) {
				for (Movimento movimento : venda.getMovimentos()) {
					if (movimento.getProduto() != null) {
						item = new DtoItem();
						item.setCodBarra(movimento.getProduto().getCodBarra() != null ? movimento.getProduto().getCodBarra() : "-----");
						item.setDescricao(movimento.getProduto().getDescricao() != null ? movimento.getProduto().getDescricao() : "-----");
						item.setDescrTipo(movimento.getProduto().getTipoProduto() != null && movimento.getProduto().getTipoProduto().getDescricao() != null ? movimento.getProduto().getTipoProduto().getDescricao() : "-----");
						item.setFabricante(movimento.getProduto().getFabricante() != null ? movimento.getProduto().getFabricante() : "-----");
						item.setQuantidade(movimento.getQtdMovimento().toString());
						item.setValVenda(Funcoes.formataValor(movimento.getValorVenda()));
						item.setTotal(Funcoes.formataValor(movimento.getValorTotal()));
						item.setCliente(venda.getCliente().getNome());

						listaItem.add(item);
					}
				}

				dtoRelatorio.setListaItem(listaItem);
			}

			lista.add(dtoRelatorio);
		}
		return lista;
	}

	public List<DtoRelatorioVendaPeriodo> geraRelatorioVendaPorPeriodo(Date dtInicial, Date dtFinal) {
		double subTotal = 0;
		double desconto = 0;
		double total = 0;
		DtoItem item;
		List<DtoRelatorioVendaPeriodo> lista = new ArrayList<DtoRelatorioVendaPeriodo>();
		List<DtoItem> listaItem = new ArrayList<DtoItem>();
		DtoRelatorioVendaPeriodo dtoRelatorio = new DtoRelatorioVendaPeriodo();
		List<Venda> listaVenda = svnVenda.recuperaVendasPorPeriodo(dtInicial, dtFinal);

		dtoRelatorio.setDtInicio(Funcoes.utilDateToStringDDMMYYYY(dtInicial));
		dtoRelatorio.setDtFinal(Funcoes.utilDateToStringDDMMYYYY(dtFinal));

		if (listaVenda != null && !listaVenda.isEmpty()) {
			for (Venda venda : listaVenda) {
				desconto += Funcoes.calcularValorDesconto(venda.getDesconto(), venda.getSubTotal());
				subTotal += venda.getSubTotal();
				total += venda.getTotal();

				item = new DtoItem();

				item.setCodVenda(venda.getCod());
				item.setData(Funcoes.utilDateToStringDDMMYYYY(venda.getDtVenda()));
				item.setCliente(venda.getCliente() != null ? venda.getCliente().getNome() : "-----");
				item.setQuantidade(venda.getQtdProdutos().toString());
				item.setTotal(Funcoes.formataValor(venda.getTotal()));

				listaItem.add(item);
			}

			dtoRelatorio.setDesconto(Funcoes.formataValor(desconto));
			dtoRelatorio.setSubTotal(Funcoes.formataValor(subTotal));
			dtoRelatorio.setTotal(Funcoes.formataValor(total));
			dtoRelatorio.setListaItem(listaItem);

			lista.add(dtoRelatorio);

		}

		return lista;
	}

	public void setSvnVenda(SvnVenda svnVenda) {
		this.svnVenda = svnVenda;
	}

	public void setSvnCliente(SvnCliente svnCliente) {
		this.svnCliente = svnCliente;
	}

	public void setSvnPagamento(SvnPagamento svnPagamento) {
		this.svnPagamento = svnPagamento;
	}

	public void setSvnUsuario(SvnUsuario svnUsuario) {
		this.svnUsuario = svnUsuario;
	}

	public void setSvnProduto(SvnProduto svnProduto) {
		this.svnProduto = svnProduto;
	}

	public void setSvnTipoMovimento(SvnTipoMovimento svnTipoMovimento) {
		this.svnTipoMovimento = svnTipoMovimento;
	}

	public void setSvnMovimento(SvnMovimento svnMovimento) {
		this.svnMovimento = svnMovimento;
	}

	public void setSvnEstoque(SvnEstoque svnEstoque) {
		this.svnEstoque = svnEstoque;
	}
}
