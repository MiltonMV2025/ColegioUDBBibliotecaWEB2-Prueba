package sv.edu.udb.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import sv.edu.udb.dao.DocumentoDAO;
import sv.edu.udb.models.Documento;
import sv.edu.udb.config.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/documentos")
public class DocumentoServlet extends HttpServlet {

    private final DocumentoDAO documentoDAO = new DocumentoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.setAttribute("listaDocumentos", documentoDAO.obtenerTodos());
        request.getRequestDispatcher("documentos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try (Connection conn = DatabaseConnection.getConnection()) {

            Documento doc = new Documento();
            doc.setTitulo(request.getParameter("titulo"));
            doc.setAutor(request.getParameter("autor"));
            doc.setAnioPublicacion(Integer.parseInt(request.getParameter("anio")));
            doc.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
            doc.setUbicacionFisica(request.getParameter("ubicacion"));
            doc.setCantidadTotal(Integer.parseInt(request.getParameter("cantidad")));
            doc.setCantidadDisponible(doc.getCantidadTotal());

            documentoDAO.crearYobtenerId(doc, conn);

            response.sendRedirect("documentos");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
