package projeto1.appmitologia.main;

import projeto1.appmitologia.dao.ContoDAO;
import projeto1.appmitologia.dao.HeroiDAO;
import projeto1.appmitologia.model.Conto;
import projeto1.appmitologia.model.Heroi;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
       ContoDAO contoDAO = new ContoDAO();
       Conto c1 = new Conto("contoo,","b","c", "gg");
       contoDAO.atualiza(c1);
    }
}
