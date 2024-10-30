
<%@include file="../widgets/header.jsp" %>
<h1>Mi Cuenta</h1>
<form action="LoginController?op=modificarCuenta" method="POST">
    <div class="mb-3">
        <label for="tipoDoc" class="form-label">Tipo de Documento</label>
        <input type="text" class="form-control" id="tipoDoc" name="tipoDoc" value="${persona.tipoDoc}" required>
    </div>
    <div class="mb-3">
        <label for="numDoc" class="form-label">Número de Documento</label>
        <input type="text" class="form-control" id="numDoc" name="numDoc" value="${persona.numDoc}" required>
    </div>
    <div class="mb-3">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" value="${persona.nombre}" required>
    </div>
    <div class="mb-3">
        <label for="apellido" class="form-label">Apellido</label>
        <input type="text" class="form-control" id="apellido" name="apellido" value="${persona.apellido}" required>
    </div>
    <button type="submit" class="btn btn-primary">Actualizar</button>
</form>
<%@include file="../widgets/footer.jsp" %>