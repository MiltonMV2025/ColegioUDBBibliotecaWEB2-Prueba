package sv.edu.udb.dao;

import sv.edu.udb.config.DatabaseConnection;
import sv.edu.udb.models.Prestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public boolean crear(Prestamo p) {
        String sql = "INSERT INTO prestamos (id_usuario, id_ejemplar, fecha_prestamo, fecha_devolucion, estado) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getIdUsuario());
            stmt.setInt(2, p.getIdEjemplar());
            stmt.setDate(3, p.getFechaPrestamo());
            stmt.setDate(4, p.getFechaDevolucion());
            stmt.setString(5, p.getEstado());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error creando préstamo: " + e.getMessage());
            return false;
        }
    }

    public Prestamo obtenerActivoPorCodigo(String codigoEjemplar) {
        String sql = "SELECT p.*, u.nombre_usuario, e.codigo_ejemplar, d.titulo AS titulo_documento " +
                "FROM prestamos p " +
                "JOIN ejemplares e ON p.id_ejemplar = e.id_ejemplar " +
                "JOIN documentos d ON d.id_documento = e.id_documento " +
                "JOIN usuarios u ON u.id_usuario = p.id_usuario " +
                "WHERE e.codigo_ejemplar = ? AND p.estado IN ('Prestado','Retrasado')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoEjemplar);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return mapear(rs);

        } catch (SQLException e) {
            System.err.println("Error obteniendo préstamo activo: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizarEstado(int id, String estado) {
        String sql = "UPDATE prestamos SET estado = ? WHERE id_prestamo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estado);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error cambiando estado del préstamo: " + e.getMessage());
            return false;
        }
    }

    private Prestamo mapear(ResultSet rs) throws SQLException {
        Prestamo p = new Prestamo();
        p.setIdPrestamo(rs.getInt("id_prestamo"));
        p.setIdUsuario(rs.getInt("id_usuario"));
        p.setIdEjemplar(rs.getInt("id_ejemplar"));
        p.setFechaPrestamo(rs.getDate("fecha_prestamo"));
        p.setFechaDevolucion(rs.getDate("fecha_devolucion"));
        p.setEstado(rs.getString("estado"));
        p.setNombreUsuario(rs.getString("nombre_usuario"));
        p.setCodigoEjemplar(rs.getString("codigo_ejemplar"));
        p.setTituloDocumento(rs.getString("titulo_documento"));
        return p;
    }
}
