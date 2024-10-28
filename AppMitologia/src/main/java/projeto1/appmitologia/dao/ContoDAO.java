package projeto1.appmitologia.dao;

import projeto1.appmitologia.model.Conto;
import projeto1.appmitologia.model.Heroi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContoDAO implements IConst, IConto{
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
        sql = "UPDATE conto SET nomeConto = ?, descricaoConto = ?, localizacaoConto = ?, heroiid = ? WHERE nomeConto = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, conto.getNome());
        statement.setString(2, conto.getDescricao());
        statement.setString(3, conto.getLocalizacao());
        Heroi heroi = new Heroi();
        HeroiDAO heroiDAO = new HeroiDAO();
        heroi = heroiDAO.buscaPorNome(conto.getNomeHeroi());
        statement.setInt(4, heroi.getId());
        statement.setString(5, conto.getNome());
        System.out.println("a");
        statement.executeUpdate();
        close();
    }
    public void remove(String nome) throws SQLException {
        open();
        sql = "DELETE FROM conto WHERE nomeConto ~* ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, nome);
        statement.executeUpdate();
        close();
    }

    public Conto buscaPorNome(String nomeConto) throws SQLException {
        open();
        sql = "SELECT * FROM conto WHERE nomeConto ~* ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, nomeConto);
        result = statement.executeQuery();
        if (result.next()) {
            Conto conto = new Conto();
            conto.setId(result.getInt("contoId"));
            conto.setNome(result.getString("nomeConto"));
            conto.setDescricao(result.getString("descricaoConto"));
            conto.setLocalizacao(result.getString("localizacaoConto"));
            Heroi heroi = new Heroi();
            HeroiDAO heroiDAO = new HeroiDAO();
            heroi.setId(result.getInt("heroiId"));
            heroi = heroiDAO.buscaPorId(heroi.getId());
            conto.setNomeHeroi(heroi.getNome());
            close();
            return conto;
        } else{
            close();
            return null;
        }
    }
    public List<Conto> listaTodos() throws SQLException {
        open();
        sql = "SELECT * FROM conto";
        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();
        ArrayList<Conto> contos = new ArrayList<>();
        while (result.next()) {
            Conto conto = new Conto();
            conto.setId(result.getInt("contoId"));
            conto.setNome(result.getString("nomeConto"));
            conto.setDescricao(result.getString("descricaoConto"));
            conto.setLocalizacao(result.getString("localizacaoConto"));
            Heroi heroi = new Heroi();
            HeroiDAO heroiDAO = new HeroiDAO();
            heroi.setId(result.getInt("heroiId"));
            heroi = heroiDAO.buscaPorId(heroi.getId());
            conto.setNomeHeroi(heroi.getNome());
            contos.add(conto);
        }
        close();
        return contos;
    }
}
