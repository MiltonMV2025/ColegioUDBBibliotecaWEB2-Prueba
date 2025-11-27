package sv.edu.udb.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://hopper.proxy.rlwy.net:33559/biblioteca_udb?useSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "MZOdbgiMxTTfxDEsITgQeZmQaQxxjvtC";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo conexión a la BD: " + e.getMessage());
        }
    }
}
