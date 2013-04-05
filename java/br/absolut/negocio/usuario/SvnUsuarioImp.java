package br.absolut.negocio.usuario;

import java.util.Date;
import java.util.List;

import br.absolut.persistencia.usuario.DaoUsuario;
import br.absolut.persistencia.usuario.Usuario;
import br.absolut.util.function.Funcoes;

public class SvnUsuarioImp implements SvnUsuario {
	private DaoUsuario daoUsuario;
	
	public SvnUsuarioImp() {}

	public Usuario altera(Usuario usuario) {
		usuario.setSenha(Funcoes.hashMD5(usuario.getSenha()));
		return this.daoUsuario.update(usuario);
	}

	public void exclui(Long codigo) {
		Usuario usuario = this.daoUsuario.findById(codigo);
		
		if(usuario != null)
			this.daoUsuario.delete(usuario);
		
	}

	public void excluiLogicamente(Long codigo) {
		Usuario usuario = this.daoUsuario.findById(codigo);
		
		if(usuario != null) {
			usuario.setDtExclusao(new Date());
			this.daoUsuario.update(usuario);
		}
		
	}

	public Usuario inclui(Usuario usuario) {
		usuario.setSenha(Funcoes.hashMD5(usuario.getSenha()));
		return this.daoUsuario.save(usuario);
	}

	public Usuario recuperaPorId(Long codigo) {
		return this.daoUsuario.findById(codigo);
	}

	public List<Usuario> recuperaTodos() {
		return this.daoUsuario.listAll();
	}

	public Usuario verificaLoginSenha(String login, String senha) {
		String hash = Funcoes.hashMD5(senha);
		return this.daoUsuario.login(login, hash);	
	}

	public void setDaoUsuario(DaoUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

}
