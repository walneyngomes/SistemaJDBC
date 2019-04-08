package br.com.sistemabancario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SantaderJDBCDAO implements SistemaBancarioDAO {

	private static final float VALOR_TAXA = 0.023f;
	private static final int DIAS_Vencimento = 7;
	private static final float VALOR_ACRESCENTADO = 0.021f;
	Connection conn = null;

	public SantaderJDBCDAO() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");

		String url = "jdbc:postgresql://localhost:5432/SistemaBancario"; // Nome
																			// da
		// base de
		// dados
		String user = "postgres"; // 
		String password = "123"; // 

		conn = DriverManager.getConnection(url, user, password);
	}

	// WALNEY

	public void cadastrarAluno(Aluno aluno) {
		PreparedStatement st = null;
		try {// WALNEY
			st = conn.prepareStatement("INSERT INTO Alunos"
					+ "(id_aluno,nome,matricula, valor )" + "VALUES " + "(?,?,?,?)");
			st.setLong(1, aluno.getIdAluno());
			st.setString(2, aluno.getNome());
			st.setLong(3, aluno.getMatricula());
			st.setFloat(4, calculoTaxa(aluno.getValor()));
			st.executeQuery();

		} catch (SQLException e) {

		}

	}

	public ArrayList<Aluno> obterTodosAlunos() {

		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		Statement stmt;

		try {
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Alunos ");
			while (rs.next()) {

				Aluno aluno = new Aluno();
				aluno.setIdAluno(rs.getInt("id_aluno"));
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setValor(rs.getFloat("valor"));
				alunos.add(aluno);

				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return alunos;
	}

	public float calculoTaxa(float valor) {
		float taxa = valor * VALOR_TAXA + VALOR_ACRESCENTADO;
		return taxa;

	}

	public float getValor() {
		float valorTotal= 0f;
		Statement stmt;

		try {
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Alunos ");
			while (rs.next()) { 

				Aluno aluno = new Aluno();
				
				aluno.setValor(rs.getFloat("valor"));
				valorTotal+=aluno.getValor();
				

				// WALNEY
			}}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return valorTotal;
	

}}
