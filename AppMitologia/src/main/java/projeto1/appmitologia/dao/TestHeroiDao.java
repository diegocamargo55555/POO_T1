package projeto1.appmitologia.dao;

import org.junit.Test;
import projeto1.appmitologia.model.Heroi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestHeroiDao {
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
    }
}
