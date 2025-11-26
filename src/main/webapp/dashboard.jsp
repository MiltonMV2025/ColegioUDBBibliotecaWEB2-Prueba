<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Biblioteca UDB</title>
    <style>
        body { font-family: Arial; background: #f5f5f5; }
        .dashboard-box {
            width: 350px; margin: 80px auto; padding: 30px;
            background: white; border-radius: 8px; box-shadow: 0 0 10px #ccc;
            text-align: center;
        }
        .menu a {
            display: inline-block;
            margin: 10px 10px 0 0;
            text-decoration: none;
            color: #0c63e4;
            font-weight: bold;
        }
        .menu { margin-top: 20px; }
        h2 { color: #0c63e4; }
    </style>
</head>
<body>

<div class="dashboard-box">
    <h2>Bienvenido, <c:out value="${usuario.nombreUsuario}" /></h2>

    <div class="menu">
        <a href="documentos.jsp">Documentos</a>
        <a href="prestamos.jsp">Préstamos</a>
        <a href="logout">Cerrar sesión</a>
    </div>
</div>

</body>
</html>
