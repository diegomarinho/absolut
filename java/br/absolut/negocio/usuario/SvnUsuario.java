package br.absolut.negocio.usuario;

import java.util.List;

import br.absolut.persistencia.usuario.Usuario;


public interface SvnUsuario {
	public Usuario inclui(Usuario usuario);
	public Usuario altera(Usuario usuario);
	public List<Usuario> recuperaTodos();
	public Usuario verificaLoginSenha(String login, String senha);
	public Usuario recuperaPorId(Long codigo);	
	public void exclui(Long codigo);
	public void excluiLogicamente(Long codigo);
}
