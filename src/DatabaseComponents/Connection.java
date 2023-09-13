package DatabaseComponents;

import java.sql.DriverManager;

public abstract class Connection{
    private static final String DB_URL ="jdbc:sqlserver://localhost;databaseName=GMS;encrypt=true;trustServerCertificate=true";
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_USERNAME = "user";
    private static final String DB_PASSWORD = "user";
    private static java.sql.Connection connection = null;
    static {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection Successful!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static java.sql.Connection getConnection() {
        return connection;
    }
}
