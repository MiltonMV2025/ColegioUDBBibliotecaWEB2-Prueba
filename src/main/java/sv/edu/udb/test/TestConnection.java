package sv.edu.udb.test;

import sv.edu.udb.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnection {
    
    public static void main(String[] args) {
        System.out.println("=== Probando conexión a la base de datos ===\n");
        
        // Mostrar credenciales que se están usando
        System.out.println("URL: " + sv.edu.udb.config.DatabaseConfig.get("db.url"));
        System.out.println("Usuario: " + sv.edu.udb.config.DatabaseConfig.get("db.user"));
        System.out.println("Password: " + sv.edu.udb.config.DatabaseConfig.get("db.password"));
        System.out.println();
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            
            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ Conexión exitosa a la base de datos");
                System.out.println("✓ Database: " + conn.getCatalog());
                System.out.println("✓ URL: " + conn.getMetaData().getURL());
                System.out.println("✓ Usuario: " + conn.getMetaData().getUserName());
                
                // Probar una consulta simple
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM roles");
                
                if (rs.next()) {
                    System.out.println("\n✓ Consulta exitosa");
                    System.out.println("✓ Total de roles en la BD: " + rs.getInt("total"));
                }
                
                rs.close();
                stmt.close();
                
                System.out.println("\n¡Todo funciona correctamente!");
                
            } else {
                System.out.println("✗ Error: No se pudo establecer conexión");
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error al conectar con la base de datos:");
            System.out.println("✗ " + e.getMessage());
            e.printStackTrace();
        }
    }
}
