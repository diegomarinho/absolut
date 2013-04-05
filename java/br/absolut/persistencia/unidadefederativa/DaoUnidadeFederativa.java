package br.absolut.persistencia.unidadefederativa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.absolut.persistencia.fabricaConexao.ConectaBanco;

import com.mysql.jdbc.Connection;

public class DaoUnidadeFederativa {
	Connection con;

	public DaoUnidadeFederativa() throws SQLException {
		con = ConectaBanco.getConexaoJDBC();
	}

	public List<UnidadeFederativa> recuperaUFs() throws SQLException {
		List<UnidadeFederativa> lista = new ArrayList<UnidadeFederativa>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UnidadeFederativa uf;
		String sql = "select * from estado;";

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				uf = new UnidadeFederativa();

				uf.setCodigo(rs.getLong("cod"));
				uf.setNome(rs.getString("nome"));
				uf.setSigla(rs.getString("sigla"));

				lista.add(uf);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return lista;
	}
}
