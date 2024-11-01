package projeto1.appmitologia.dao;

import projeto1.appmitologia.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IConst {

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
    public void insere(User user) throws SQLException {
        open();
        sql = "INSERT INTO usuario(userName, userPassword) VALUES(?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        statement.executeUpdate();
        close();
    }

    public void remove(User user) throws SQLException {
        open();
        sql = "DELETE FROM user WHERE UserName = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUserName());
        statement.executeUpdate();
        close();
    }

}