package lojainstrumento.atividade_1_pw;

import java.net.URISyntaxException;
import java.sql.*;


public class InstrumentoDao {

    public Instrumento getInstrumentoById(Integer id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Instrumento t = null;

        try {
            connection = Conexao.getConnection();

            stmt = connection.prepareStatement("select * from instrumento_tbl where id=?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                t = new Instrumento(new Date(rs.getLong("datacadastro")), rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setQtd(rs.getInt("qtd"));

            }
            connection.close();

        } catch (SQLException | URISyntaxException ex) {
            // response.getWriter().append("Connection Failed! Check output console");
        }
        return t;
    }

    public void cadastrarInstrumento(Instrumento inst){
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Conexao.getConnection();
            stmt = connection.prepareStatement("insert into instrumento_tbl(nome,qtd, dataCadastro) values(?,?,?)");
            stmt.setString(1, inst.getNome());
            stmt.setInt(2, inst.getQtd());
            stmt.setFloat(3, inst.getDataCadastro().getTime());

            stmt.executeUpdate();
            connection.close();


        }catch (SQLException | URISyntaxException ex){
            System.out.println("Erro ao cadastrar: " + ex);
        }
    }


}
