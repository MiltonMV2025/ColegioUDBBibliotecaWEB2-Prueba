package sv.edu.udb.dao;

import sv.edu.udb.config.DatabaseConnection;
import sv.edu.udb.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public boolean crear(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre_usuario, correo, contrasena, id_rol, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getContrasena());
            stmt.setInt(4, usuario.getIdRol());
            stmt.setString(5, usuario.getEstado());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
            return false;
        }
    }


    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = """
                SELECT u.*, r.nombre_rol 
                FROM usuarios u 
                LEFT JOIN roles r ON u.id_rol = r.id_rol 
                ORDER BY u.nombre_usuario
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }

        return usuarios;
    }


    public Usuario obtenerPorId(int idUsuario) {
        String sql = """
                SELECT u.*, r.nombre_rol 
                FROM usuarios u 
                LEFT JOIN roles r ON u.id_rol = r.id_rol 
                WHERE u.id_usuario = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearUsuario(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuario: " + e.getMessage());
        }

        return null;
    }


    public Usuario obtenerPorCorreo(String correo) {
        String sql = """
                SELECT u.*, r.nombre_rol 
                FROM usuarios u 
                LEFT JOIN roles r ON u.id_rol = r.id_rol 
                WHERE u.correo = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearUsuario(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por correo: " + e.getMessage());
        }

        return null;
    }


    public Usuario validarLogin(String correo, String contrasena) {
        String sql = """
                SELECT u.*, r.nombre_rol 
                FROM usuarios u 
                LEFT JOIN roles r ON u.id_rol = r.id_rol 
                WHERE u.correo = ? AND u.contrasena = ? AND u.estado = 'Activo'
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearUsuario(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al validar login: " + e.getMessage());
        }

        return null;
    }


    public boolean actualizar(Usuario usuario) {
        String sql = """
                UPDATE usuarios 
                SET nombre_usuario = ?, correo = ?, id_rol = ?, estado = ?
                WHERE id_usuario = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getCorreo());
            stmt.setInt(3, usuario.getIdRol());
            stmt.setString(4, usuario.getEstado());
            stmt.setInt(5, usuario.getIdUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }


    public boolean actualizarContrasena(int idUsuario, String nuevaContrasena) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevaContrasena);
            stmt.setInt(2, idUsuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar contraseña: " + e.getMessage());
            return false;
        }
    }


    public boolean eliminar(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }


    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNombreUsuario(rs.getString("nombre_usuario"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setContrasena(rs.getString("contrasena"));
        usuario.setIdRol(rs.getInt("id_rol"));
        usuario.setEstado(rs.getString("estado"));
        usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        usuario.setNombreRol(rs.getString("nombre_rol"));

        return usuario;
    }
}
