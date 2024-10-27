package projeto1.appmitologia.dao;

import projeto1.appmitologia.model.Conto;
import projeto1.appmitologia.model.Heroi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContoDAO implements IConst{
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
        sql = "INSERT INTO conto(contoId, nomeConto, descricaoConto, localizacaoConto, heroiId) VALUES(DEFAULT, ?,?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, conto.getNome());
        statement.setString(2, conto.getDescricao());
        statement.setString(3, conto.getLocalizacao());
        Heroi heroi = new Heroi();
        HeroiDAO heroiDAO = new HeroiDAO();
        heroi = heroiDAO.buscaPorNome(conto.getNomeHeroi());
        statement.setInt(4, heroi.getId());
        statement.executeUpdate();
        close();
    }
    public void atualiza(Conto conto) throws SQLException{
        open();
        System.out.println("a");
        sql = "UPDATE conto SET nomeConto = ?, descricaoConto = ?, localizacaoConto = ? WHERE nomeConto = ?";
        System.out.println("a");
        statement = connection.prepareStatement(sql);
        System.out.println("a");
        statement.setString(1, conto.getNome());
        statement.setString(2, conto.getDescricao());
        statement.setString(3, conto.getDescricao());
        statement.setString(4, conto.getLocalizacao());
        Heroi heroi = new Heroi();
        System.out.println("a");
        HeroiDAO heroiDAO = new HeroiDAO();
        heroi = heroiDAO.buscaPorNome(conto.getNomeHeroi());
        //statement.setInt(5, heroi.getId());
        System.out.println("a");
        statement.executeUpdate();
        close();
    }

}
