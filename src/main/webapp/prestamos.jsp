<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Préstamos</title>
    <style>
        table { width: 100%; margin-top: 20px; border-collapse: collapse; }
        th, td { border: 1px solid #ccc; padding: 10px; }
        th { background: #eee; }
    </style>
</head>
<body>

<h2>Préstamos Activos</h2>

<table>
    <tr>
        <th>Ejemplar</th>
        <th>Documento</th>
        <th>Usuario</th>
        <th>Fecha Préstamo</th>
        <th>Fecha Devolución</th>
        <th>Estado</th>
    </tr>

    <c:forEach var="p" items="${prestamos}">
        <tr>
            <td>${p.codigoEjemplar}</td>
            <td>${p.tituloDocumento}</td>
            <td>${p.nombreUsuario}</td>
            <td>${p.fechaPrestamo}</td>
            <td>${p.fechaDevolucion}</td>
            <td>${p.estado}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
