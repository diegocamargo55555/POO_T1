package projeto1.appmitologia.dao;

import projeto1.appmitologia.model.Conto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*public class ContoDAO implements IConto, IConst{
    private String sql;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    public void open() throws SQLException {
        connection = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
    }
    public void close() throws SQLException{
        connection.close();
    }
    public void insere(Conto conto) throws SQLException{
        open();
        sql = "INSERT INTO conto(contoId, nomeConto, descricaoConto, anoConto, localizacaoConto, heroiId) VALUES(?,?,?,?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, estudanteDisciplina.getEstudante().getEstudanteId());
        statement.setInt(2, estudanteDisciplina.getDisciplina().getDisciplinaId());
        statement.executeUpdate();
        close();
    }
}
*/