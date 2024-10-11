package projeto1.appmitologia.dao;

import projeto1.appmitologia.model.Heroi;

import java.sql.SQLException;
import java.util.List;

public interface IHeroi {
    public void insere(Heroi heroi) throws SQLException;
    public void atualiza(Heroi heroi) throws SQLException;
    public void remove(Heroi heroi) throws SQLException;
    public List<Heroi> listaTodos() throws SQLException;
}
