package br.absolut.persistencia.usuario;

import br.absolut.persistencia.generico.DaoGenerico;

public interface DaoUsuario extends DaoGenerico<Usuario, Long> {
	public Usuario login(String login, String hash);
}
