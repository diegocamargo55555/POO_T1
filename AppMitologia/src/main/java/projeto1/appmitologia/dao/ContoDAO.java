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
    public void atualiza(Conto conto) throws SQLException {
        open();
        String sql = "UPDATE conto SET nomeConto = ?, descricaoConto = ?, localizacaoConto = ?, heroiId = ? WHERE contoId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, conto.getNome());
        statement.setString(2, conto.getDescricao());
        statement.setString(3, conto.getLocalizacao());

        HeroiDAO heroiDAO = new HeroiDAO();
        Heroi heroi = heroiDAO.buscaPorNome(conto.getNomeHeroi());
        if (heroi != null) {
            statement.setInt(4, heroi.getId());
        } else {
            throw new SQLException("Herói não encontrado para o nome informado.");
        }
        statement.setInt(5, conto.getId());
        statement.executeUpdate();
        close();
    }
    public void remove(int id) throws SQLException {
        open();
        sql = "DELETE FROM conto WHERE contoid = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        close();
    }
    public Conto buscaPorId(int id) throws SQLException {
        open();
        sql = "SELECT * FROM conto WHERE contoid = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
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
        return getArrayContos();
    }

    public ArrayList<Conto> listaTodosNomesHeroi(String nomeHeroi) throws SQLException {
        open();
        Heroi heroi = new Heroi();
        HeroiDAO heroiDAO = new HeroiDAO();
        heroi = heroiDAO.buscaPorNome(nomeHeroi);
        sql = "SELECT * FROM conto WHERE heroiid = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, heroi.getId());
        result = statement.executeQuery();
        ArrayList<Conto> contos = new ArrayList<>();
        while (result.next()) {
            Conto conto = new Conto();
            conto.setId(result.getInt("contoId"));
            conto.setNome(result.getString("nomeConto"));
            conto.setDescricao(result.getString("descricaoConto"));
            conto.setLocalizacao(result.getString("localizacaoConto"));
            heroi.setId(result.getInt("heroiId"));
            heroi = heroiDAO.buscaPorId(heroi.getId());
            conto.setNomeHeroi(heroi.getNome());
            contos.add(conto);
        }
        close();
        return contos;
    }
    public ArrayList<Conto> listaTodosNomeConto(String nomeConto) throws SQLException {
        open();
        sql = "SELECT * FROM conto WHERE nomeConto ~* '" + nomeConto + "'";
        return getArrayContos();
    }
    //  2º Refatoração
    //  Autor: Ana Beatrz
    //  cria método getArrayContos com parte de código em comum usado por metodos
    //  Objetivo: compactação do código
    public ArrayList<Conto> getArrayContos() throws SQLException {
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

    public Conto buscaPorNome(String nomeConto) throws SQLException {
        open();
        sql = "SELECT * FROM conto WHERE nomeConto = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, nomeConto);
        result = statement.executeQuery();

        Conto conto = null;
        if (result.next()) {
            conto = new Conto();
            conto.setId(result.getInt("contoId"));
            conto.setNome(result.getString("nomeConto"));
            conto.setDescricao(result.getString("descricaoConto"));
            conto.setLocalizacao(result.getString("localizacaoConto"));
            HeroiDAO heroiDAO = new HeroiDAO();
            Heroi heroi = heroiDAO.buscaPorId(result.getInt("heroiId"));
            if (heroi != null) {
                conto.setNomeHeroi(heroi.getNome());
            }
        }
        close();
        return conto;
    }
}
