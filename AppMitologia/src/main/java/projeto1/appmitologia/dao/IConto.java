package projeto1.appmitologia.dao;


import projeto1.appmitologia.model.Conto;

import java.sql.SQLException;
import java.util.List;

public interface IConto {
    public void insere(Conto conto) throws SQLException;
    public void atualiza(Conto conto) throws SQLException;
    public void remove(Conto conto) throws SQLException;
    public List<Conto> listaTodos() throws SQLException;
}