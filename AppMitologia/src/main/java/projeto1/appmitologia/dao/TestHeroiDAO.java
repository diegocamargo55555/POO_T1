package projeto1.appmitologia.dao;

import javafx.scene.SubScene;
import org.junit.Test;
import projeto1.appmitologia.model.Heroi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static org.junit.Assert.assertEquals;

public class TestHeroiDAO {
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
    //Teste 3
    //Autor: Ana Beatriz
    //Verifica se o id de um heroi enviado posteriormente é menor do que um enviado depois, verificando seu funcionamento correto.
    @Test
    public void TestaIdEmInsercao() throws SQLException {
        HeroiDAO heroiDao = new HeroiDAO();
        Heroi heroi = new Heroi();
        heroi.setNome("teste");
        heroi.setDescricao("testando");
        heroi.setUrl("não há");
        heroiDao.insere(heroi);
        Heroi heroi2 = new Heroi();
        heroi2.setNome("teste2");
        heroi2.setDescricao("testando2");
        heroi.setUrl("nao");
        heroiDao.insere(heroi2);
        Heroi heroi3 = new Heroi();
        Heroi heroi4 = new Heroi();
        heroi3 = heroiDao.buscaPorNome(heroi.getNome());
        heroi4 = heroiDao.buscaPorNome(heroi2.getNome());
        assert(heroi3.getId() < heroi4.getId());
        heroiDao.remove(heroi3.getId());
        heroiDao.remove(heroi4.getId());
    }
    //Teste 4
    //Autor: Ana Beatrz
    //Verifica se a atualização está funcionando corretamente.
    @Test
    public void TestAtualiza() throws SQLException{
        HeroiDAO heroiDAO = new HeroiDAO();
        Heroi heroi = new Heroi();
        heroi.setNome("testar");
        heroi.setDescricao("testara");
        heroi.setUrl("nao");
        heroiDAO.insere(heroi);
        heroi = heroiDAO.buscaPorNome(heroi.getNome());
        heroi.setNome("aaaaaa");
        Heroi heroi2 = new Heroi();
        heroi2 = heroiDAO.buscaPorNome("testar");
        heroiDAO.atualiza(heroi);
        heroi = heroiDAO.buscaPorNome("aaaaaa");
        assertEquals(heroi.getId(), heroi2.getId());
        heroiDAO.remove(heroi.getId());
    }
}
