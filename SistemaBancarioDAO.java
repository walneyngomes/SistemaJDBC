package br.com.sistemabancario;

import java.util.ArrayList;

public interface SistemaBancarioDAO {
	public void cadastrarAluno(Aluno aluno);
	public ArrayList<Aluno> obterTodosAlunos();
	public float getValor();

	

}
