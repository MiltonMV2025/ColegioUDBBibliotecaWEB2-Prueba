<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Documentos - Biblioteca</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background: #eee; }
    </style>
</head>

<body>

<h2>Listado de Documentos</h2>

<table>
    <tr>
        <th>Título</th>
        <th>Autor</th>
        <th>Categoría</th>
        <th>Disponibles</th>
    </tr>

    <c:forEach var="d" items="${lista}">
        <tr>
            <td>${d.titulo}</td>
            <td>${d.autor}</td>
            <td>${d.nombreCategoria}</td>
            <td>${d.cantidadDisponible}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
