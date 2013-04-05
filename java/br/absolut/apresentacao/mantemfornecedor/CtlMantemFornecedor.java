package br.absolut.apresentacao.mantemfornecedor;

import java.util.ArrayList;
import java.util.List;

import br.absolut.negocio.fornecedor.DtoParametroConsulta;
import br.absolut.negocio.fornecedor.SvnFornecedor;
import br.absolut.persistencia.fornecedor.Fornecedor;
import br.absolut.util.function.Funcoes;

public class CtlMantemFornecedor {
	private SvnFornecedor svnFornecedor;

	public DtoFornecedor recuperaFornecedor(Long codFornecedor) throws Exception {
		DtoFornecedor dtoFornecedor = new DtoFornecedor();
		Fornecedor fornecedor = svnFornecedor.recuperaPorId(codFornecedor);
		
		if(fornecedor != null) {
			dtoFornecedor.setCod(fornecedor.getCod());
			dtoFornecedor.setCnpj(Funcoes.aplicaMascara(fornecedor.getCnpj(), Funcoes.MASCARA_CNPJ));
			dtoFornecedor.setNome(fornecedor.getNome());
			dtoFornecedor.setTelefone(Funcoes.aplicaMascara(fornecedor.getTelefone(), Funcoes.MASCARA_TELEFONE));
			dtoFornecedor.setAgencia(fornecedor.getAgencia());
			dtoFornecedor.setBairro(fornecedor.getBairro());
			dtoFornecedor.setBanco(fornecedor.getBanco());
			dtoFornecedor.setCep(Funcoes.aplicaMascara(fornecedor.getCep(), Funcoes.MASCARA_CEP));
			dtoFornecedor.setCidade(fornecedor.getCidade());
			dtoFornecedor.setContaCorrente(fornecedor.getContaCorrente());
			dtoFornecedor.setEndereco(fornecedor.getEndereco());
			dtoFornecedor.setEstado(fornecedor.getEstado());
		}
		return dtoFornecedor;
	}

	public void incluiFornecedor(DtoFornecedor dtoFornecedor) {
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setCnpj(Funcoes.retiraCaracteresFormatacao(dtoFornecedor.getCnpj()));
		fornecedor.setNome(dtoFornecedor.getNome());
		fornecedor.setTelefone(dtoFornecedor.getTelefone() != null ? Funcoes.retiraCaracteresFormatacao(dtoFornecedor.getTelefone()) : null);
		fornecedor.setAgencia(dtoFornecedor.getAgencia());
		fornecedor.setBairro(dtoFornecedor.getBairro());
		fornecedor.setBanco(dtoFornecedor.getBanco());
		fornecedor.setCep(dtoFornecedor.getCep() != null ? Funcoes.retiraCaracteresFormatacao(dtoFornecedor.getCep()) : null);
		fornecedor.setCidade(dtoFornecedor.getCidade());
		fornecedor.setContaCorrente(dtoFornecedor.getContaCorrente());
		fornecedor.setEndereco(dtoFornecedor.getEndereco());
		fornecedor.setEstado(dtoFornecedor.getEstado());
		
		svnFornecedor.inclui(fornecedor);
	}

	public void alteraFornecedor(DtoFornecedor dtoFornecedor) {
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setCod(dtoFornecedor.getCod());
		fornecedor.setCnpj(Funcoes.retiraCaracteresFormatacao(dtoFornecedor.getCnpj()));
		fornecedor.setNome(dtoFornecedor.getNome());
		fornecedor.setTelefone(Funcoes.retiraCaracteresFormatacao(dtoFornecedor.getTelefone()));
		fornecedor.setAgencia(dtoFornecedor.getAgencia());
		fornecedor.setBairro(dtoFornecedor.getBairro());
		fornecedor.setBanco(dtoFornecedor.getBanco());
		fornecedor.setCep(Funcoes.retiraCaracteresFormatacao(dtoFornecedor.getCep()));
		fornecedor.setCidade(dtoFornecedor.getCidade());
		fornecedor.setContaCorrente(dtoFornecedor.getContaCorrente());
		fornecedor.setEndereco(dtoFornecedor.getEndereco());
		fornecedor.setEstado(dtoFornecedor.getEstado());
		
		svnFornecedor.altera(fornecedor);
	}
	
	public List<DtoResultadoConsulta> consultaFornecedor(
			DtoConsultaFornecedor dtoConsulta) throws Exception {
		List<DtoResultadoConsulta> listaResultado = new ArrayList<DtoResultadoConsulta>();
		List<Fornecedor> listaFornecedor;
		DtoResultadoConsulta dtoResultado;
		
		DtoParametroConsulta dtoParametro = new DtoParametroConsulta();
		dtoParametro.setCnpj(Funcoes.retiraCaracteresFormatacao(dtoConsulta.getCnpj()));
		dtoParametro.setNome(dtoConsulta.getNome());
		
		listaFornecedor = svnFornecedor.consultaPorParametros(dtoParametro);
		
		if(listaFornecedor != null && !listaFornecedor.isEmpty()) {
			for(Fornecedor fornecedor : listaFornecedor) {
				dtoResultado = new DtoResultadoConsulta();
				
				dtoResultado.setCnpj(fornecedor.getCnpj() != null ? Funcoes.aplicaMascara(fornecedor.getCnpj(), Funcoes.MASCARA_CNPJ) : "-----");
				dtoResultado.setCod(fornecedor.getCod());
				dtoResultado.setNome(fornecedor.getNome() != null ? fornecedor.getNome() : "-----");
				dtoResultado.setTelefone(fornecedor.getTelefone() != null ? Funcoes.aplicaMascara(fornecedor.getTelefone(), Funcoes.MASCARA_TELEFONE) : "-----");
				dtoResultado.setAgencia(fornecedor.getAgencia() != null ? fornecedor.getAgencia() : "-----");
				dtoResultado.setContaCorrente(fornecedor.getContaCorrente() != null ? fornecedor.getContaCorrente() : "-----");
				dtoResultado.setBanco(fornecedor.getBanco() != null ? fornecedor.getBanco(): "-----");
				
				listaResultado.add(dtoResultado);
			}
		}
		return listaResultado;
	}

	public void excluiFornecedor(Long cod) {
		svnFornecedor.excluiLogicamente(cod);
		
	}

	public void setSvnFornecedor(SvnFornecedor svnFornecedor) {
		this.svnFornecedor = svnFornecedor;
	}
}
