package projeto1.appmitologia.dao;

import org.junit.Test;
import projeto1.appmitologia.model.Heroi;
import projeto1.appmitologia.model.Session;
import projeto1.appmitologia.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TestUserDAO implements IConst {
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

    @Test
    public void Testgetinsere() throws SQLException {
        UserDAO u = new UserDAO();
        User usuario = new User("IHWA", "YEON");
        User userTest = new User();
        u.remove("IHWA");
        u.insere(usuario);
        open();
        sql = "SELECT UserID FROM usuario WHERE UserID = 'IHWA';";
        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();
        Heroi h = new Heroi();
        if (result.next()) {
            userTest.setUserName(result.getString("UserID")); // Se a coluna for uma String
        }
        close();
        assertEquals("IHWA", userTest.getUserName());
    }
}
