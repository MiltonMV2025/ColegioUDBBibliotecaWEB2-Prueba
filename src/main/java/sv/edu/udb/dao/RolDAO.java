package sv.edu.udb.dao;

import sv.edu.udb.config.DatabaseConnection;
import sv.edu.udb.models.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {


    public boolean crear(Rol rol) {
        String sql = "INSERT INTO roles (nombre_rol) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rol.getNombreRol());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear rol: " + e.getMessage());
            return false;
        }
    }


    public List<Rol> obtenerTodos() {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT * FROM roles ORDER BY nombre_rol";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearRol(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener roles: " + e.getMessage());
        }

        return lista;
    }


    public Rol obtenerPorId(int idRol) {
        String sql = "SELECT * FROM roles WHERE id_rol = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRol);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearRol(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener rol: " + e.getMessage());
        }

        return null;
    }


    public boolean actualizar(Rol rol) {
        String sql = "UPDATE roles SET nombre_rol = ? WHERE id_rol = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rol.getNombreRol());
            stmt.setInt(2, rol.getIdRol());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar rol: " + e.getMessage());
            return false;
        }
    }


    public boolean eliminar(int idRol) {
        String sql = "DELETE FROM roles WHERE id_rol = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRol);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar rol: " + e.getMessage());
            return false;
        }
    }


    private Rol mapearRol(ResultSet rs) throws SQLException {
        Rol rol = new Rol();
        rol.setIdRol(rs.getInt("id_rol"));
        rol.setNombreRol(rs.getString("nombre_rol"));
        return rol;
    }
}
