package br.absolut.apresentacao.mantemusuario;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.absolut.apresentacao.DtoUsuario;
import br.absolut.negocio.acesso.SvnAcesso;
import br.absolut.negocio.usuario.SvnUsuario;
import br.absolut.persistencia.acesso.Acesso;
import br.absolut.persistencia.usuario.Usuario;
import br.absolut.util.function.Funcoes;

public class CtlMantemUsuario {
	private SvnUsuario svnUsuario;
	private SvnAcesso svnAcesso;

	public DtoUsuario verificaLoginSenha(String login, String senha) {
		DtoUsuario dtoUsuario = null;
		Usuario usuario = svnUsuario.verificaLoginSenha(login, senha);
		
		if(usuario != null) {
			dtoUsuario = new DtoUsuario();
			dtoUsuario.setLogin(usuario.getLogin());
			dtoUsuario.setCod(usuario.getCod());
			dtoUsuario.setNome(usuario.getNome());
			dtoUsuario.setAcesso(usuario.getAcesso().getCod());
		}
		
		return dtoUsuario;		
	}
	
	public void alteraSenhaUsuario(Long codigo, String novaSenha) {
		Usuario usuario = svnUsuario.recuperaPorId(codigo);
		if(usuario != null) {
			usuario.setSenha(Funcoes.hashMD5(novaSenha));
			svnUsuario.altera(usuario);
		}
	}
	
	public void alteraUsuario(DtoIncluiUsuario dto) {
		Usuario usuario = new Usuario();
		
		usuario.setCod(dto.getCod());
		usuario.setLogin(dto.getLogin());
		usuario.setNome(dto.getNome());
		usuario.setSenha(dto.getSenha());
		
		if(dto.getCodigoAcesso() != null) {
			Acesso acesso = svnAcesso.recuperaPorId(dto.getCodigoAcesso());
			if(acesso != null)
				usuario.setAcesso(acesso);
		}
		
		svnUsuario.altera(usuario);	
		
	}

	public void incluiUsuario(DtoIncluiUsuario dto) {
		Usuario usuario = new Usuario();
		
		usuario.setLogin(dto.getLogin());
		usuario.setNome(dto.getNome());
		usuario.setSenha(dto.getSenha());
		
		if(dto.getCodigoAcesso() != null) {
			Acesso acesso = svnAcesso.recuperaPorId(dto.getCodigoAcesso());
			if(acesso != null)
				usuario.setAcesso(acesso);
		}
		
		svnUsuario.inclui(usuario);
	}
	
	public void excluiUsuario(Long codigo) {
		svnUsuario.excluiLogicamente(codigo);	
	}

	public DtoIncluiUsuario recuperaUsuario(Long codigo) {
		DtoIncluiUsuario dto = new DtoIncluiUsuario();
		Usuario usuario = svnUsuario.recuperaPorId(codigo);
		if(usuario != null)
			dto = populaDtoIncluiUsuario(dto, usuario);
		
		return dto;
	}

	private DtoIncluiUsuario populaDtoIncluiUsuario(DtoIncluiUsuario dto,
			Usuario usuario) {
		dto.setCod(usuario.getCod());
		dto.setCodigoAcesso(usuario.getAcesso().getCod());
		dto.setLogin(usuario.getLogin());
		dto.setNome(usuario.getNome());
		dto.setSenha(null);
		dto.setSenha(null);
		
		return dto;
	}
	
	public List<SelectItem> recuperaListaTipoAcesso() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<Acesso> listaAcesso = svnAcesso.recuperaTodos();
		if(listaAcesso != null) {
			for(Acesso acesso : listaAcesso) {
				lista.add(new SelectItem(acesso.getCod(), acesso.getDescricao()));
			}
		}
		return lista;
	}

	public void setSvnUsuario(SvnUsuario svnUsuario) {
		this.svnUsuario = svnUsuario;
	}

	public void setSvnAcesso(SvnAcesso svnAcesso) {
		this.svnAcesso = svnAcesso;
	}

	public List<DtoConsultaUsuario> recuperaListaUsuario() {
		DtoConsultaUsuario dtoConsulta;
		List<DtoConsultaUsuario> lista = new ArrayList<DtoConsultaUsuario>();
		List<Usuario> listaUsuario = svnUsuario.recuperaTodos();
		
		if(listaUsuario != null) {
			for(Usuario usuario : listaUsuario) {
				if(usuario.getDtExclusao() == null) {
					dtoConsulta = new DtoConsultaUsuario();
					
					dtoConsulta.setCod(usuario.getCod());
					dtoConsulta.setLogin(usuario.getLogin());
					dtoConsulta.setNome(usuario.getNome());
					dtoConsulta.setAcesso(usuario.getAcesso() != null ? usuario.getAcesso().getDescricao() : "-----");
					
					lista.add(dtoConsulta);
				}
			}
		}
		
		return lista;
	}
}
