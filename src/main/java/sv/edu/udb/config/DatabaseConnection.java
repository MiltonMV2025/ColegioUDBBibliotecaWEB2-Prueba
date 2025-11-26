package sv.edu.udb.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = DatabaseConfig.get("db.url");
    private static final String USER = DatabaseConfig.get("db.user");
    private static final String PASS = DatabaseConfig.get("db.password");

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo conexión a la BD: " + e.getMessage());
        }
    }
}
