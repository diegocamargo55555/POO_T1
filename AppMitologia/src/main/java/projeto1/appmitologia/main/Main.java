package projeto1.appmitologia.main;

import projeto1.appmitologia.dao.HeroiDAO;
import projeto1.appmitologia.model.Heroi;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Heroi heroi = new Heroi( "é dois heroi", "aaaaabbbbaa", "É dois heroi");
        HeroiDAO heroiDAO = new HeroiDAO();
        heroiDAO.insere(heroi);
    }
}
