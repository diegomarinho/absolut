package br.absolut.persistencia.fabricaConexao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConectaBanco {
	public static Connection getConexaoJDBC() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Conectando...");
			return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/systembe", "root", "123456");

		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}

	public static void main(String[] args) throws SQLException {
		getConexaoJDBC();
	}
}
