
<%@include file="widgets/header.jsp" %>
<h1>Crear Nueva Cuenta</h1>
<form action="LoginController?op=registrar" method="POST">
    <div class="mb-3">
        <label for="tipoDoc" class="form-label">Tipo de Documento</label>
        <input type="text" class="form-control" id="tipoDoc" name="tipoDoc" value="DNI" required readonly>
    </div>
    <div class="mb-3">
        <label for="numDoc" class="form-label">Número de Documento</label>
        <input type="text" class="form-control" id="numDoc" name="numDoc" required>
    </div>
    <div class="mb-3">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" required>
    </div>
    <div class="mb-3">
        <label for="apellido" class="form-label">Apellido</label>
        <input type="text" class="form-control" id="apellido" name="apellido" required>
    </div>
    <div class="mb-3">
        <label for="usuario" class="form-label">Nombre de Usuario</label>
        <input type="text" class="form-control" id="usuario" name="usuario" required>
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Contraseña</label>
        <input type="password" class="form-control" id="password" name="password" required autocomplete="new-password">
    </div>
    <button type="submit" class="btn btn-primary">Registrar</button>
</form>

<%@include file="widgets/footer.jsp" %>