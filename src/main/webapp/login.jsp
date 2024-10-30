
<%@include file="widgets/header.jsp" %>
<h1>Iniciar Sesión</h1>
<form action="LoginController" method="POST">
    <input type="hidden" name="op" value="iniciarSesion">
    <div class="mb-3">
        <label for="usuario" class="form-label">Usuario</label>
        <input type="text" class="form-control" id="usuario" name="usuario" required>
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Contraseña</label>
        <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <button type="submit" class="btn btn-primary">Iniciar Sesión</button>

    <div class="mb-3 text-center">
        <a class="btn btn-link" href="LoginController?op=register">Registar nuevo cliente</a>
    </div>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

</form>
<%@include file="widgets/footer.jsp" %>