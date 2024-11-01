package projeto1.appmitologia.dao;

public interface IConst {
    static final String JDBC_DRIVER = "projeto1.cngqys40c66a.us-east-2.rds.amazonaws.com";
    static String databaseName ="mitologiaapp_db";
    static  int lport = 5432;

    public static final String stringDeConexao = "jdbc:postgresql://projeto1.cngqys40c66a.us-east-2.rds.amazonaws.com:"+lport+"/"+databaseName;
    public static final String usuario = "postgres";
    public static final String senha = "BiGiDi03";
}

