package br.com.sistemabancario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItauJDBCDAO implements SistemaBancarioDAO {
	private static final float VALOR_TAXA = 0.054f;
	private static final int DIAS_Vencimento = 6;
	private static final float VALOR_ACRESCENTADO = 0.013f;
	Connection conn = null;
	public ItauJDBCDAO() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");// WALNEY

		String url = "jdbc:postgresql://localhost:5432/SistemaBancario"; // Nome da
																	// base de
		// dados
		String user = "postgres"; // nome do usuário do MySQL
		String password = "123"; // senha do MySQL

		
		conn = DriverManager.getConnection(url, user, password);
	}

	// WALNEY

		public void cadastrarAluno(Aluno aluno) {
			PreparedStatement st = null;
			try {// WALNEY
				st = conn.prepareStatement("INSERT INTO Alunos"// WALNEY
						+ "(id_aluno,nome,matricula, valor )" + "VALUES " + "(?,?,?,?)");
				st.setLong(1, aluno.getIdAluno());
				st.setString(2, aluno.getNome());// WALNEY
				st.setLong(3, aluno.getMatricula());// WALNEY
				st.setFloat(4,calculoTaxa( aluno.getValor()));// WALNEY
				st.executeQuery();

			} catch (SQLException e) {

			}

		}

		public ArrayList<Aluno> obterTodosAlunos() {

				ArrayList<Aluno> alunos = new ArrayList<Aluno>();
				Statement stmt;

				try {// WALNEY
					stmt = conn.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT * FROM Alunos ");
					while (rs.next()) { // WALNEY NEGREIROS

						Aluno aluno = new Aluno();
						aluno.setIdAluno(rs.getInt("id_aluno"));
						aluno.setMatricula(rs.getInt("matricula"));
						aluno.setNome(rs.getString("nome"));
						aluno.setValor(rs.getFloat("valor"));
						alunos.add(aluno);

						// WALNEY
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // WALNEY NEGREIROS

				return alunos;
			}
		

		public float calculoTaxa(float valor) {
			float taxa = valor * VALOR_TAXA + VALOR_ACRESCENTADO;
			return taxa;

		}

		public float getValor() {
			 float valorTotal= 0f;
				Statement stmt;

				try {// WALNEY
					stmt = conn.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT * FROM Alunos ");
					while (rs.next()) { // WALNEY NEGREIROS

						Aluno aluno = new Aluno();
						
						aluno.setValor(rs.getFloat("valor"));
						valorTotal+=aluno.getValor();
						

						// WALNEY
					}}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // WALNEY NEGREIROS
					return valorTotal;
		}}
