package sv.edu.udb.config;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private static final Properties props = new Properties();

    static {
        try {
            InputStream input = DatabaseConfig.class.getClassLoader()
                    .getResourceAsStream("config/database.properties");

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo config/database.properties");
            }

            props.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Error cargando database.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
