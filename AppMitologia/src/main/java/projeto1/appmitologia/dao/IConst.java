package projeto1.appmitologia.dao;

public interface IConst {
    static final String JDBC_DRIVER = "projeto1.cngqys40c66a.us-east-2.rds.amazonaws.com";
    static String databaseName ="mitologiaapp";
    static  int lport = 5432;

    public static final String stringDeConexao = "jdbc:postgresql://localhost:"+lport+"/"+databaseName;
    public static final String usuario = "postgres";
    public static final String senha = "lolseek001";
}

