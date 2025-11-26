package sv.edu.udb.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import sv.edu.udb.dao.DevolucionDAO;
import sv.edu.udb.dao.PrestamoDAO;
import sv.edu.udb.dao.EjemplarDAO;
import sv.edu.udb.models.Devolucion;
import sv.edu.udb.models.Prestamo;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/devoluciones")
public class DevolucionServlet extends HttpServlet {

    private final PrestamoDAO prestamoDAO = new PrestamoDAO();
    private final EjemplarDAO ejemplarDAO = new EjemplarDAO();
    private final DevolucionDAO devolucionDAO = new DevolucionDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String codigo = req.getParameter("codigoEjemplar");
        String obs = req.getParameter("observaciones");


        Prestamo prestamo = prestamoDAO.obtenerActivoPorCodigo(codigo);

        if (prestamo == null) {
            resp.sendRedirect("devoluciones.jsp?error=NoExistePrestamo");
            return;
        }


        Devolucion d = new Devolucion();
        d.setIdPrestamo(prestamo.getIdPrestamo());
        d.setFechaDevolucion(Date.valueOf(LocalDate.now()));
        d.setObservaciones(obs);

        devolucionDAO.crear(d);


        prestamoDAO.actualizarEstado(prestamo.getIdPrestamo(), "Devuelto");


        ejemplarDAO.actualizarEstado(prestamo.getIdEjemplar(), "Disponible");

        resp.sendRedirect("devoluciones.jsp?success=1");
    }
}
