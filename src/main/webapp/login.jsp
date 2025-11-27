<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - Biblioteca UDB</title>
    <style>
        body { font-family: Arial; background: #f5f5f5; }
        .login-box {
            width: 350px; margin: 80px auto; padding: 25px;
            background: white; border-radius: 8px; box-shadow: 0 0 10px #ccc;
        }
        button { width: 100%; padding: 10px; margin-top: 10px; }
        input { width: 100%; padding: 8px; margin-top: 5px; }
        .msg { color: red; margin-bottom: 10px; }
    </style>
</head>

<body>
<div class="login-box">
    <h2>Biblioteca UDB</h2>

    <!-- Mensaje de error si existe -->
    <c:if test="${not empty mensajeError}">
        <div class="msg">${mensajeError}</div>
    </c:if>

    <form action="login" method="post">
        <label>Correo:</label>
        <input type="email" name="correo" required placeholder="admin@colegio.edu.sv">

        <label>Contraseña:</label>
        <input type="password" name="contrasena" required placeholder="admin123">

        <button type="submit">Ingresar</button>
    </form>
</div>
</body>
</html>
