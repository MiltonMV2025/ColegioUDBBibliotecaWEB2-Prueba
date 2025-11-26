package sv.edu.udb.dao;

import sv.edu.udb.config.DatabaseConnection;
import sv.edu.udb.models.Devolucion;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevolucionDAO {

    private final ConfigDAO configDAO = new ConfigDAO();

    public boolean crear(Devolucion d) {


        Date fechaVenc = null;
        String sqlPrestamo = "SELECT fecha_devolucion FROM prestamos WHERE id_prestamo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlPrestamo)) {

            ps.setInt(1, d.getIdPrestamo());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) fechaVenc = rs.getDate("fecha_devolucion");

        } catch (SQLException e) {
            System.err.println("Error en préstamo: " + e.getMessage());
        }


        int atraso = 0;
        BigDecimal mora = BigDecimal.ZERO;

        if (fechaVenc != null && d.getFechaDevolucion() != null) {
            long diff = d.getFechaDevolucion().toLocalDate().toEpochDay()
                    - fechaVenc.toLocalDate().toEpochDay();
            atraso = (int) Math.max(0, diff);

            if (atraso > 0) {
                BigDecimal tarifa = configDAO.getMoraDiariaPorAnio(
                        d.getFechaDevolucion().toLocalDate().getYear()
                );
                mora = tarifa.multiply(BigDecimal.valueOf(atraso));
            }
        }

        String sql = "INSERT INTO devoluciones (id_prestamo, fecha_devolucion, observaciones," +
                " dias_atraso, mora_generada, mora_pagada) VALUES (?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, d.getIdPrestamo());
            ps.setDate(2, d.getFechaDevolucion());
            ps.setString(3, d.getObservaciones());
            ps.setInt(4, atraso);
            ps.setBigDecimal(5, mora);
            ps.setBigDecimal(6, d.getMoraPagada() == null ? BigDecimal.ZERO : d.getMoraPagada());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear devolución: " + e.getMessage());
            return false;
        }
    }

    public Devolucion obtenerPorPrestamo(int idPrestamo) {
        String sql = "SELECT * FROM devoluciones WHERE id_prestamo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPrestamo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return mapear(rs);

        } catch (SQLException e) {
            System.err.println("Error leyendo devolución: " + e.getMessage());
        }

        return null;
    }

    public List<Devolucion> obtenerTodas() {
        List<Devolucion> lista = new ArrayList<>();
        String sql = "SELECT * FROM devoluciones ORDER BY fecha_devolucion DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) lista.add(mapear(rs));

        } catch (SQLException e) {
            System.err.println("Error listando devoluciones: " + e.getMessage());
        }

        return lista;
    }

    private Devolucion mapear(ResultSet rs) throws SQLException {
        Devolucion d = new Devolucion();
        d.setIdDevolucion(rs.getInt("id_devolucion"));
        d.setIdPrestamo(rs.getInt("id_prestamo"));
        d.setFechaDevolucion(rs.getDate("fecha_devolucion"));
        d.setObservaciones(rs.getString("observaciones"));
        d.setDiasAtraso(rs.getInt("dias_atraso"));
        d.setMoraGenerada(rs.getBigDecimal("mora_generada"));
        d.setMoraPagada(rs.getBigDecimal("mora_pagada"));
        return d;
    }
}
