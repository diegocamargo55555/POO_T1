package projeto1.appmitologia.dao;

import projeto1.appmitologia.model.User;

import java.sql.SQLException;

public interface IUser {
// Adição dos metodos a interface
    public void insere(User user) throws SQLException;
    public boolean authenticateUser(String username, String password) throws SQLException;
    public void favoritarHeroi(int IdHeroi) throws SQLException;
    public void favoritarConto(int idConto) throws SQLException;
    public void remove(String id) throws SQLException;
    public  int getContoFav() throws SQLException;
    public int getHeroiFav() throws SQLException;
}
