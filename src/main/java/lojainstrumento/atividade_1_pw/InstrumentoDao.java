package lojainstrumento.atividade_1_pw;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InstrumentoDao {

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
            //
        }
    }


}
