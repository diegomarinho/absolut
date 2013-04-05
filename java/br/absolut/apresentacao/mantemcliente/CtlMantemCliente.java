package br.absolut.apresentacao.mantemcliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import br.absolut.negocio.cliente.DtoParametroConsulta;
import br.absolut.negocio.cliente.SvnCliente;
import br.absolut.negocio.unidadefederativa.SvnUnidadeFederativa;
import br.absolut.persistencia.cliente.Cliente;
import br.absolut.persistencia.unidadefederativa.UnidadeFederativa;
import br.absolut.util.function.Funcoes;

public class CtlMantemCliente {

	private SvnCliente svnCliente;

	public List<SelectItem> recuperaListaUf() throws Exception {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SvnUnidadeFederativa svn = new SvnUnidadeFederativa();
		SelectItem item;

		List<UnidadeFederativa> listaUF = svn.recuperaUfs();

		if (!listaUF.isEmpty()) {
			for (UnidadeFederativa uf : listaUF) {
				item = new SelectItem();

				item.setValue(uf.getSigla());
				item.setLabel(uf.getNome());

				lista.add(item);
			}
		}
		return lista;
	}

	public void incluiCliente(DtoCliente dto) throws Exception {
		Cliente cliente = new Cliente();

		cliente.setBairro(dto.getBairro());
		cliente.setCelular(Funcoes.retiraCaracteresFormatacao(dto.getCelular()));
		cliente.setCep(Funcoes.retiraCaracteresFormatacao(dto.getCep()));
		cliente.setCidade(dto.getCidade());
		cliente.setCpfCnpj(Funcoes.retiraCaracteresFormatacao(dto.getCpfCnpj()));
		cliente.setEndereco(dto.getEndereco());
		cliente.setEstado(dto.getEstado());
		cliente.setIdentidade(dto.getIdentidade());
		cliente.setNome(dto.getNome());
		cliente.setTelefone(Funcoes.retiraCaracteresFormatacao(dto.getTelefone()));
		cliente.setDtInclusao(new Date());

		svnCliente.inclui(cliente);
	}

	public void alteraCliente(DtoCliente dto) throws Exception {
		Cliente cliente = new Cliente();

		cliente.setBairro(dto.getBairro());
		cliente.setCelular(Funcoes.retiraCaracteresFormatacao(dto.getCelular()));
		cliente.setCep(Funcoes.retiraCaracteresFormatacao(dto.getCep()));
		cliente.setCidade(dto.getCidade());
		cliente.setCpfCnpj(Funcoes.retiraCaracteresFormatacao(dto.getCpfCnpj()));
		cliente.setEndereco(dto.getEndereco());
		cliente.setEstado(dto.getEstado());
		cliente.setIdentidade(dto.getIdentidade());
		cliente.setNome(dto.getNome());
		cliente.setTelefone(Funcoes.retiraCaracteresFormatacao(dto.getTelefone()));
		cliente.setDtInclusao(Funcoes.StringDDMMYYYYToUtilDate(dto.getDtInclusao()));
		cliente.setCod(dto.getCod());

		svnCliente.altera(cliente);
	}

	public void excluiCliente(Long cod) {
		svnCliente.excluiLogicamente(cod);
	}

	public List<DtoResultadoConsulta> consultaCliente(DtoConsultaCliente dtoConsulta) throws Exception {
		List<DtoResultadoConsulta> listaResultado = new ArrayList<DtoResultadoConsulta>();
		List<Cliente> listaCliente;
		DtoResultadoConsulta dtoResultado;

		DtoParametroConsulta dtoParametro = new DtoParametroConsulta();
		dtoParametro.setCpfCnpj(Funcoes.retiraCaracteresFormatacao(dtoConsulta.getCpf()));
		dtoParametro.setNome(dtoConsulta.getNome());
		dtoParametro.setRg(dtoConsulta.getRg());

		listaCliente = svnCliente.consultaPorParametros(dtoParametro);
		if (listaCliente != null && !listaCliente.isEmpty()) {
			for (Cliente cliente : listaCliente) {
				dtoResultado = new DtoResultadoConsulta();

				dtoResultado.setCod(cliente.getCod());
				dtoResultado.setCelular(Funcoes.aplicaMascara(cliente.getCelular(), Funcoes.MASCARA_TELEFONE));
				dtoResultado.setCpf(Funcoes.aplicaMascara(cliente.getCpfCnpj(), Funcoes.MASCARA_CPF));
				dtoResultado.setDtInclusao(Funcoes.utilDateToStringDDMMYYYY(cliente.getDtInclusao()));
				dtoResultado.setNome(cliente.getNome());
				dtoResultado.setTelefone(Funcoes.aplicaMascara(cliente.getTelefone(), Funcoes.MASCARA_TELEFONE));

				listaResultado.add(dtoResultado);
			}
		}
		return listaResultado;
	}

	public DtoCliente recuperaCliente(Long codCliente) throws Exception {
		DtoCliente dto = new DtoCliente();
		Cliente cliente = svnCliente.recuperaPorId(codCliente);

		if (cliente != null) {
			dto = populaDtoCliente(dto, cliente);
		}
		return dto;
	}

	public void setSvnCliente(SvnCliente svnCliente) {
		this.svnCliente = svnCliente;
	}

	public DtoCliente recuperaClientePorCpf(String cpfCliente) throws Exception {
		DtoCliente dtoCliente = new DtoCliente();
		DtoParametroConsulta dtoParametro = new DtoParametroConsulta();
		dtoParametro.setCpfCnpj(cpfCliente);

		List<Cliente> listaCliente = svnCliente.consultaPorParametros(dtoParametro);
		if (listaCliente != null && !listaCliente.isEmpty()) {
			Cliente cliente = listaCliente.get(0);
			if (cliente != null) {
				dtoCliente = populaDtoCliente(dtoCliente, cliente);

			}
		}

		return dtoCliente;
	}

	private DtoCliente populaDtoCliente(DtoCliente dto, Cliente cliente) throws Exception {
		dto.setBairro(cliente.getBairro());

		dto.setCelular(cliente.getCelular() != null && !cliente.getCelular().isEmpty() ? Funcoes.aplicaMascara(cliente.getCelular(), Funcoes.MASCARA_TELEFONE) : cliente.getCelular());

		dto.setCep(cliente.getCep() != null && !cliente.getCep().isEmpty() ? Funcoes.aplicaMascara(cliente.getCep(), Funcoes.MASCARA_CEP) : cliente.getCep());

		dto.setCidade(cliente.getCidade());

		dto.setCod(cliente.getCod());

		dto.setCpfCnpj(cliente.getCpfCnpj() != null && !cliente.getCpfCnpj().isEmpty() ? Funcoes.aplicaMascara(cliente.getCpfCnpj(), Funcoes.MASCARA_CPF) : cliente.getCpfCnpj());

		dto.setDtExclusao(Funcoes.utilDateToStringDDMMYYYY(cliente.getDtInclusao()));
		dto.setDtInclusao(Funcoes.utilDateToStringDDMMYYYY(cliente.getDtInclusao()));
		dto.setEndereco(cliente.getEndereco());
		dto.setEstado(cliente.getEstado());
		dto.setIdentidade(cliente.getIdentidade());
		dto.setNome(cliente.getNome());
		dto.setTelefone(cliente.getTelefone() != null && !cliente.getTelefone().isEmpty() ? Funcoes.aplicaMascara(cliente.getTelefone(), Funcoes.MASCARA_TELEFONE) : cliente.getTelefone());

		return dto;
	}

}
