package projeto1.appmitologia.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import org.junit.Test;
import projeto1.appmitologia.model.Conto;
import projeto1.appmitologia.model.Heroi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestContoDAO {
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
    //Teste 1
    //Autor: Ana Beatiz
    //verifica se a inserção está funcionando de forma correta.
    @Test
    public void TestInsere() throws SQLException {
        ContoDAO contoDAO = new ContoDAO();
        HeroiDAO heroiDAO = new HeroiDAO();
        Heroi heroi = new Heroi();
        heroi.setNome("heroi");
        heroi.setId(4);
        heroi.setDescricao("heroi");
        heroi.setUrl("naoHa");
        heroiDAO.insere(heroi);
        Conto conto = new Conto();
        conto.setDescricao("conto");
        conto.setNome("conto");
        conto.setNomeHeroi("heroi");
        contoDAO.insere(conto);
        open();
        Heroi heroi2 = new Heroi();
        heroi2 = heroiDAO.buscaPorNome(conto.getNomeHeroi());
        sql = "SELECT * FROM conto WHERE heroiid = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, heroi.getId());
        result = statement.executeQuery();
        assertNotNull(heroi2.getId());
    }
    //Teste 2
    //Autor: Ana Beatrz
    //Verifica se a busca funciona como o esperado quando é pesquisado um conto não existente.
    @Test
    public void TestaBusca() throws SQLException {
        ContoDAO contoDAO = new ContoDAO();
        Conto conto = new Conto();
        conto = contoDAO.buscaPorNome("nao ha");
        assertNull(conto);
    }
}