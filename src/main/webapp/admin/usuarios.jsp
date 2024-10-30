
<%@include file="widgets/header.jsp" %>
<h1>Listado de Usuarios</h1>
<table class="table table-bordered" id="tablax">
    <thead>
        <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Perfil</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="usuario" items="${listaUsuarios}">
            <tr>
                <td>${usuario.id}</td>
                <td>${usuario.usuario}</td>
                <td>${usuario.perfilId}</td>
                <td>
                    <button class="btn btn-warning" onclick="modificarUsuario(${usuario.id})">Modificar</button>
                    <button class="btn btn-danger" onclick="eliminarUsuario(${usuario.id})">Eliminar</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Modal para Agregar/Modificar -->
<div class="modal fade" id="usuarioModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Agregar/Modificar Usuario</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="usuarioForm">
                    <input type="hidden" name="id" id="usuarioId">
                    <div class="mb-3">
                        <label for="usuario" class="form-label">Usuario</label>
                        <input type="text" class="form-control" id="usuario" name="usuario">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="mb-3">
                        <label for="perfilId" class="form-label">Perfil</label>
                        <input type="number" class="form-control" id="perfilId" name="perfilId">
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function eliminarUsuario(id) {
        $.ajax({
            url: "UsuarioController",
            type: "POST",
            data: {
                op: "eliminar",
                id: id
            },
            success: function () {
                location.reload();
            }
        });
    }

    $('#usuarioForm').submit(function (e) {
        e.preventDefault();
        $.ajax({
            url: "UsuarioController",
            type: "POST",
            data: $(this).serialize(),
            success: function () {
                $('#usuarioModal').modal('hide');
                location.reload();
            }
        });
    });

    function modificarUsuario(id) {
        $.get("UsuarioController", {op: "buscar", id: id}, function (data) {
            $('#usuarioId').val(data.id);
            $('#usuario').val(data.usuario);
            $('#password').val(data.password);
            $('#perfilId').val(data.perfilId);
            $('#usuarioModal').modal('show');
        });
    }
</script>
<%@include file="widgets/footer.jsp" %>