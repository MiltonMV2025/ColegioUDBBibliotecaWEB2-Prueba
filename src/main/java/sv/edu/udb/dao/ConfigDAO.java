package sv.edu.udb.dao;

import sv.edu.udb.config.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;

public class ConfigDAO {

    public Integer getMaxPrestamosPorUsuario() {
        String sql = "SELECT valor FROM config_sistema WHERE clave = 'max_prestamos_por_usuario'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return Integer.parseInt(rs.getString("valor"));
            }

        } catch (Exception e) {
            System.err.println("Error leyendo configuración: " + e.getMessage());
        }

        return 3;
    }

    public BigDecimal getMoraDiariaPorAnio(int anio) {
        String sql = "SELECT monto_diario FROM mora_config WHERE anio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, anio);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("monto_diario");
            }

        } catch (SQLException e) {
            System.err.println("Error obteniendo mora: " + e.getMessage());
        }

        return BigDecimal.ZERO;
    }
}
