package sv.edu.udb.dao;

import sv.edu.udb.config.DatabaseConnection;
import sv.edu.udb.models.Historial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAO {

    public boolean registrar(Historial h) {
        String sql = "INSERT INTO historial (id_usuario, accion) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, h.getIdUsuario());
            stmt.setString(2, h.getAccion());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error registrando historial: " + e.getMessage());
            return false;
        }
    }

    public List<Historial> obtenerTodo() {
        List<Historial> lista = new ArrayList<>();
        String sql = "SELECT h.*, u.nombre_usuario " +
                "FROM historial h " +
                "LEFT JOIN usuarios u ON u.id_usuario = h.id_usuario " +
                "ORDER BY fecha DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            System.err.println("Error obteniendo historial: " + e.getMessage());
        }

        return lista;
    }

    private Historial mapear(ResultSet rs) throws SQLException {
        Historial h = new Historial();
        h.setIdHistorial(rs.getInt("id_historial"));
        h.setIdUsuario(rs.getInt("id_usuario"));
        h.setAccion(rs.getString("accion"));
        h.setFecha(rs.getTimestamp("fecha"));
        h.setNombreUsuario(rs.getString("nombre_usuario"));
        return h;
    }
}
