package sv.edu.udb.dao;

import sv.edu.udb.config.DatabaseConnection;
import sv.edu.udb.models.Ejemplar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EjemplarDAO {

    public boolean crear(Ejemplar ej) {
        String sql = "INSERT INTO ejemplares (id_documento, codigo_ejemplar, estado) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ej.getIdDocumento());
            stmt.setString(2, ej.getCodigoEjemplar());
            stmt.setString(3, ej.getEstado());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear ejemplar: " + e.getMessage());
            return false;
        }
    }

    public Ejemplar obtenerPorCodigo(String codigo) {
        String sql = "SELECT e.*, d.titulo AS titulo_documento " +
                "FROM ejemplares e " +
                "LEFT JOIN documentos d ON e.id_documento = d.id_documento " +
                "WHERE codigo_ejemplar = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return mapear(rs);

        } catch (SQLException e) {
            System.err.println("Error al obtener ejemplar: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizarEstado(int id, String estado) {
        String sql = "UPDATE ejemplares SET estado = ? WHERE id_ejemplar = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estado);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }

    private Ejemplar mapear(ResultSet rs) throws SQLException {
        Ejemplar e = new Ejemplar();
        e.setIdEjemplar(rs.getInt("id_ejemplar"));
        e.setIdDocumento(rs.getInt("id_documento"));
        e.setCodigoEjemplar(rs.getString("codigo_ejemplar"));
        e.setEstado(rs.getString("estado"));
        e.setTituloDocumento(rs.getString("titulo_documento"));
        return e;
    }
}
