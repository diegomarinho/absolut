package br.absolut.apresentacao.mantemproduto;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.absolut.apresentacao.compra.DtoItem;
import br.absolut.negocio.produto.DtoParametroConsulta;
import br.absolut.negocio.produto.SvnProduto;
import br.absolut.negocio.produto.SvnTipoProduto;
import br.absolut.persistencia.produto.Produto;
import br.absolut.persistencia.produto.TipoProduto;
import br.absolut.util.function.Funcoes;

public class CtlMantemProduto {

	private SvnTipoProduto svnTipoProduto;
	private SvnProduto svnProduto;

	public DtoProduto recuperaProduto(Long codProduto) {
		DtoProduto dtoProduto = new DtoProduto();
		Produto produto = svnProduto.recuperaPorId(codProduto);

		if (produto != null) {
			dtoProduto.setCod(produto.getCod());
			dtoProduto.setCodBarra(produto.getCodBarra());
			dtoProduto.setCodTipoProduto(produto.getTipoProduto().getCod());
			dtoProduto.setDescricao(produto.getDescricao());
			dtoProduto.setSaldoAtual(produto.getSaldoAtual());
			dtoProduto.setFabricante(produto.getFabricante());
			dtoProduto.setValorCompra(Funcoes.formataValor(produto.getValorCompra()));
			dtoProduto.setValorVenda(Funcoes.formataValor(produto.getValorVenda()));
		}

		return dtoProduto;
	}

	public DtoItem recuperaProdutoPorId(Long codItem) {
		DtoItem item = null;
		Produto produto = svnProduto.recuperaPorId(codItem);
		if (produto != null) {
			item = new DtoItem();
			item = populaDtoItem(item, produto);
		}
		return item;
	}

	public DtoItem recuperaProdutoPorCodigoBarra(String codBarra) {
		DtoItem item = null;
		DtoParametroConsulta dtoParametro = new DtoParametroConsulta();
		dtoParametro.setCodBarra(codBarra);
		Produto produto = (svnProduto.consultaPorParametros(dtoParametro) != null && !svnProduto.consultaPorParametros(dtoParametro).isEmpty() ? svnProduto.consultaPorParametros(dtoParametro).get(0) : null);
		if (produto != null) {
			item = new DtoItem();
			item = populaDtoItem(item, produto);
		}

		return item;
	}

	private DtoItem populaDtoItem(DtoItem item, Produto produto) {
		item.setCodBarra(produto.getCodBarra());
		item.setCodItem(produto.getCod());
		item.setDescricao(produto.getDescricao());
		item.setCodTipo(produto.getTipoProduto() != null ? produto.getTipoProduto().getCod() : null);
		item.setDescrTipo(produto.getTipoProduto() != null ? produto.getTipoProduto().getDescricao() : "-----");
		item.setFabricante(produto.getFabricante() != null ? produto.getFabricante() : "-----");
		item.setValVenda(Funcoes.formataValor(produto.getValorVenda()));
		item.setValCompra(produto.getValorCompra() + "");
		item.setEstoque(produto.getSaldoAtual().toString());
		item.setQuantidade("1");

		return item;
	}

	public void alteraProduto(DtoProduto dtoProduto) {
		Produto produto = new Produto();

		produto.setCod(dtoProduto.getCod());
		produto.setCodBarra(dtoProduto.getCodBarra());
		produto.setDescricao(dtoProduto.getDescricao());
		produto.setSaldoAtual(dtoProduto.getSaldoAtual());

		TipoProduto tipo = svnTipoProduto.recuperaPorId(dtoProduto.getCodTipoProduto());
		produto.setTipoProduto(tipo != null ? tipo : null);

		produto.setValorVenda(Funcoes.stringToDouble(dtoProduto.getValorVenda()));

		produto.setValorCompra(Funcoes.stringToDouble(dtoProduto.getValorCompra()));

		produto.setFabricante(dtoProduto.getFabricante());

		svnProduto.altera(produto);
	}

	public void incluiProduto(DtoProduto dtoProduto) {
		Produto produto = new Produto();

		produto.setCodBarra(dtoProduto.getCodBarra());
		produto.setDescricao(dtoProduto.getDescricao());
		produto.setSaldoAtual(dtoProduto.getSaldoAtual());
		produto.setValorVenda(Funcoes.stringToDouble(dtoProduto.getValorVenda()));
		produto.setValorCompra(Funcoes.stringToDouble(dtoProduto.getValorCompra()));

		TipoProduto tipo = svnTipoProduto.recuperaPorId(dtoProduto.getCodTipoProduto());
		if (tipo != null) {
			produto.setTipoProduto(tipo);
		}

		produto.setFabricante(dtoProduto.getFabricante());

		//		//Imagem
		//		Imagem imagem = new Imagem();
		//		imagem.setNome(dtoProduto.getDtoImagem().getNome());
		//		imagem.setTamanho(dtoProduto.getDtoImagem().getTamanho());

		svnProduto.inclui(produto);
	}

	public List<DtoResultadoConsulta> consultaProduto(DtoConsultaProduto dtoConsulta) {
		List<DtoResultadoConsulta> listaResultado = new ArrayList<DtoResultadoConsulta>();
		List<Produto> listaProduto;
		DtoResultadoConsulta dtoResultado;

		DtoParametroConsulta dtoParametro = new DtoParametroConsulta();
		dtoParametro.setCodBarra(dtoConsulta.getCodBarra());
		dtoParametro.setCodTipoProduto(dtoConsulta.getCodTipoProduto());
		dtoParametro.setDescricao(dtoConsulta.getDescricao());
		dtoParametro.setFabricante(dtoConsulta.getFabricante());

		listaProduto = svnProduto.consultaPorParametros(dtoParametro);
		if (listaProduto != null && !listaProduto.isEmpty()) {
			for (Produto produto : listaProduto) {
				dtoResultado = new DtoResultadoConsulta();
				dtoResultado.setCod(produto.getCod());
				dtoResultado.setCodBarras(produto.getCodBarra() != null ? produto.getCodBarra() : "-----");
				dtoResultado.setDescricao(produto.getDescricao() != null ? produto.getDescricao() : "-----");
				dtoResultado.setSaldoAtual(produto.getSaldoAtual() != null ? produto.getSaldoAtual().toString() : "-----");
				dtoResultado.setTipoProduto(produto.getTipoProduto() != null ? produto.getTipoProduto().getDescricao() : "-----");
				dtoResultado.setValorVenda(Funcoes.formataValor(produto.getValorVenda()));
				dtoResultado.setValorCompra(Funcoes.formataValor(produto.getValorCompra()));
				dtoResultado.setFabricante(produto.getFabricante() != null ? produto.getFabricante() : "-----");

				listaResultado.add(dtoResultado);
			}
		}
		return listaResultado;
	}

	public void excluiProduto(Long cod) {
		svnProduto.excluiLogicamente(cod);
	}

	public List<SelectItem> recuperaListaTipoProduto() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<TipoProduto> listaTipoProduto = svnTipoProduto.recuperaTodos();
		SelectItem item;
		if (listaTipoProduto != null) {
			for (TipoProduto tipo : listaTipoProduto) {
				item = new SelectItem();

				item.setValue(tipo.getCod());
				item.setLabel(tipo.getDescricao());

				lista.add(item);
			}
		}

		return lista;
	}

	public void setSvnTipoProduto(SvnTipoProduto svnTipoProduto) {
		this.svnTipoProduto = svnTipoProduto;
	}

	public void setSvnProduto(SvnProduto svnProduto) {
		this.svnProduto = svnProduto;
	}

}
