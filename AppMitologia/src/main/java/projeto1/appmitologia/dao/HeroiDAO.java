package projeto1.appmitologia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto1.appmitologia.model.Heroi;

public class HeroiDAO implements IHeroi, IConst{
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
    public void insere(Heroi heroi) throws SQLException {
        open();
        sql = "INSERT INTO heroi(heroiId, descricaoHeroi, imagemHeroi, nomeHeroi) VALUES(?,?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, heroi.getId());
        statement.setString(2, heroi.getDescricao());
        statement.setString(3, heroi.getImagem());
        statement.setString(4, heroi.getNome());
        statement.executeUpdate();
        close();
    }

    public void atualiza(Heroi heroi) throws SQLException {
        open();
        sql = "UPDATE heroi SET descricaoHeroi = ?, imagemHeroi = ?, nomeHeroi = ?, WHERE estudante_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, heroi.getDescricao());
        statement.setString(2, heroi.getImagem());
        statement.setString(3, heroi.getNome());
        statement.executeUpdate();
        close();
    }

    public void remove(Heroi heroi) throws SQLException {
        open();
        sql = "DELETE FROM heroi WHERE heroiId = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, heroi.getId());
        statement.executeUpdate();
        close();
    }

    public Heroi buscaPorCodigo(int heroiId) throws SQLException {
        open();
        sql = "SELECT * FROM heroi WHERE heroiId = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, heroiId);
        result = statement.executeQuery();
        if (result.next()) {
            Heroi heroi = new Heroi();
            heroi.setId(result.getInt("heroiId"));
            heroi.setNome(result.getString("nomeHeroi"));
            heroi.setDescricao(result.getString("heroiDescricao"));
            heroi.setImagem(result.getString("imagemHeroi"));
            close();
            return heroi;
        } else{
            close();
            return null;
        }
    }

    public List<Heroi> listaTodos() throws SQLException {
        open();
        sql = "SELECT * FROM heroi";
        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();
        ArrayList<Heroi> herois = new ArrayList<>();
        while (result.next()) {
            Heroi heroi = new Heroi();
            heroi.setId(result.getInt("heroiId"));
            heroi.setNome(result.getString("nomeHeroi"));
            heroi.setDescricao(result.getString("heroiDescricao"));
            heroi.setImagem(result.getString("imagemHeroi"));
            herois.add(heroi);
        }
        close();
        return herois;
    }
}
