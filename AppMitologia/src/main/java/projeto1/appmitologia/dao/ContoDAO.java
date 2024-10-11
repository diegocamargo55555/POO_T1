package projeto1.appmitologia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContoDAO implements IConto, IConst{
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

}
