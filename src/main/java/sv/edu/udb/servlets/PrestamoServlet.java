package sv.edu.udb.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import sv.edu.udb.dao.PrestamoDAO;
import sv.edu.udb.dao.EjemplarDAO;
import sv.edu.udb.models.Prestamo;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/prestamos")
public class PrestamoServlet extends HttpServlet {

    private final PrestamoDAO prestamoDAO = new PrestamoDAO();
    private final EjemplarDAO ejemplarDAO = new EjemplarDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        int idEjemplar = Integer.parseInt(req.getParameter("idEjemplar"));


        Prestamo p = new Prestamo();
        p.setIdUsuario(idUsuario);
        p.setIdEjemplar(idEjemplar);
        p.setFechaPrestamo(Date.valueOf(LocalDate.now()));
        p.setFechaDevolucion(Date.valueOf(LocalDate.now().plusDays(7)));
        p.setEstado("Prestado");

        prestamoDAO.crear(p);


        ejemplarDAO.actualizarEstado(idEjemplar, "Prestado");

        resp.sendRedirect("prestamos.jsp?success=1");
    }
}
