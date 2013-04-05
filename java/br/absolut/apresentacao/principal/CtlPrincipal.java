package br.absolut.apresentacao.principal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.absolut.apresentacao.mantemproduto.DtoProduto;
import br.absolut.negocio.movimento.venda.SvnVenda;
import br.absolut.negocio.produto.SvnProduto;
import br.absolut.persistencia.movimento.venda.DtoHistoricoVenda;
import br.absolut.persistencia.produto.Produto;
import br.absolut.util.function.Funcoes;

public class CtlPrincipal {
	private SvnVenda svnVenda;
	private SvnProduto svnProduto;

	public List<DtoHistVenda> recuperaListaHistoricoVenda() {
		List<DtoHistVenda> lista = new ArrayList<DtoHistVenda>();
		Date dtFinal = new Date();
		Date dtInicial = Funcoes.somarData(dtFinal, -7);
		DtoHistVenda dto;
		
		List<DtoHistoricoVenda> listaHistorico = svnVenda.recuperaHistoricoVenda(dtInicial, dtFinal);
		if(listaHistorico != null) {
			for(DtoHistoricoVenda historico : listaHistorico) {
				dto = new DtoHistVenda();
				dto.setData(Funcoes.utilDateToStringDDMMYYYY(historico.getData()));
				dto.setQuantidade(historico.getQuantidade().toString());
				dto.setTotal(Funcoes.formataValor(historico.getTotal()));
				
				lista.add(dto);
			}
		}
		
		return lista;
	}
	
	public List<DtoProduto> recuperaListaProdutoBaixoEstoque() {
		List<DtoProduto> lista = new ArrayList<DtoProduto>();
		DtoProduto dto;
		
		List<Produto> listaProduto = svnProduto.recuperaProdutosComBaixoEstoque();
		if(listaProduto != null) {
			for(Produto produto : listaProduto) {
				dto = new DtoProduto();
				dto.setDescricao(produto.getDescricao());
				dto.setCodBarra(produto.getCodBarra());
				dto.setSaldoAtual(produto.getSaldoAtual());
				
				lista.add(dto);
			}
		}
		return lista;
	}

	public void setSvnVenda(SvnVenda svnVenda) {
		this.svnVenda = svnVenda;
	}

	public void setSvnProduto(SvnProduto svnProduto) {
		this.svnProduto = svnProduto;
	}
}
