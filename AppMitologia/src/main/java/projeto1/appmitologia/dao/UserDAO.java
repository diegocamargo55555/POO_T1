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
        sql = "INSERT INTO usuario(UserID, senha) VALUES(?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        statement.executeUpdate();
        close();
    }

    public void remove(User user) throws SQLException {
        open();
        sql = "DELETE FROM usuario WHERE UserID = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUserName());
        statement.executeUpdate();
        close();
    }

    public boolean authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM usuario WHERE UserID =? AND senha = ?";
        open();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}