package sv.edu.udb.dao;

import sv.edu.udb.config.DatabaseConnection;
import sv.edu.udb.models.Documento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentoDAO {

    public int crearYobtenerId(Documento documento, Connection conn) throws SQLException {

        String sql = "INSERT INTO documentos (titulo, autor, anio_publicacion, id_categoria, ubicacion_fisica, cantidad_total, cantidad_disponible) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, documento.getTitulo());
            stmt.setString(2, documento.getAutor());
            stmt.setInt(3, documento.getAnioPublicacion());
            stmt.setInt(4, documento.getIdCategoria());
            stmt.setString(5, documento.getUbicacionFisica());
            stmt.setInt(6, documento.getCantidadTotal());
            stmt.setInt(7, documento.getCantidadDisponible());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1;
    }

    public List<Documento> obtenerTodos() {
        List<Documento> documentos = new ArrayList<>();
        String sql = "SELECT * FROM documentos ORDER BY titulo";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                documentos.add(mapearDocumento(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener documentos: " + e.getMessage());
        }

        return documentos;
    }

    private Documento mapearDocumento(ResultSet rs) throws SQLException {
        Documento d = new Documento();
        d.setIdDocumento(rs.getInt("id_documento"));
        d.setTitulo(rs.getString("titulo"));
        d.setAutor(rs.getString("autor"));
        d.setAnioPublicacion(rs.getInt("anio_publicacion"));
        d.setIdCategoria(rs.getInt("id_categoria"));
        d.setUbicacionFisica(rs.getString("ubicacion_fisica"));
        d.setCantidadTotal(rs.getInt("cantidad_total"));
        d.setCantidadDisponible(rs.getInt("cantidad_disponible"));
        return d;
    }
}
