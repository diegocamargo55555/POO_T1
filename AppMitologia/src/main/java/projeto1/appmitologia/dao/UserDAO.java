package projeto1.appmitologia.dao;

import projeto1.appmitologia.model.Heroi;
import projeto1.appmitologia.model.Session;
import projeto1.appmitologia.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IConst, IUser{

    private static String sql;
    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet result;

    public static void open() throws SQLException {
        connection = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
    }
    public static void close() throws SQLException{
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

    public void remove(String id) throws SQLException {
        open();
        sql = "DELETE FROM usuario WHERE UserID = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, id);
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

    public void favoritarHeroi(int IdHeroi) throws SQLException {
        open();
        sql = "UPDATE usuario SET heroiid = ? WHERE userid = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, IdHeroi);
        statement.setString(2, Session.getCookie());
        statement.executeUpdate();
        close();
    }

    public void favoritarConto(int idConto) throws SQLException {
        open();
        sql = "UPDATE usuario SET contoid = ? WHERE userid = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, idConto);
        statement.setString(2, Session.getCookie());
        statement.executeUpdate();
        close();
    }


    public int getHeroiFav() throws SQLException {
        open();
        sql = "select heroiid from usuario WHERE userid = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, Session.getCookie());
        result = statement.executeQuery();
        Heroi h = new Heroi();

        if (result.next()) {
            h.setId(result.getInt("heroiId"));
        }
        close();
        int resultINT = h.getId();
        return resultINT;
    }



    public int getContoFav() throws SQLException {
        open();
        sql = "select contoid from usuario WHERE userid = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, Session.getCookie());
        result = statement.executeQuery();
        Heroi h = new Heroi();

        if (result.next()) {
            h.setId(result.getInt("contoid"));
        }
        close();

        int resultINT = h.getId();
        return resultINT;
    }






}