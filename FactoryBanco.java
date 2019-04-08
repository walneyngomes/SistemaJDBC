package br.com.sistemabancario;

import java.sql.SQLException;

public class FactoryBanco {
	public SistemaBancarioDAO obterBanco(String atraso, Aluno aluno) throws ClassNotFoundException, SQLException {
		SistemaBancarioDAO resultado = null;

		if ("6 dias".equals(atraso)) {
			resultado = new BancoBrasilJDBC();
			resultado.cadastrarAluno(aluno);

		} else if ("7 dias".equals(atraso)) {
			resultado = new SantaderJDBCDAO();
			resultado.cadastrarAluno(aluno);

		} else if ("8 dias".equals(atraso)) {
			resultado = new SantaderJDBCDAO();
			resultado.cadastrarAluno(aluno);

		} else if ("20 dias".equals(atraso)) {
			resultado = new ItauJDBCDAO();
			resultado.cadastrarAluno(aluno);

		} else if ("10 dias".equals(atraso)) {
			resultado = new TrindadeDAO();
			resultado.cadastrarAluno(aluno);

		}
		return resultado;

	}

}
