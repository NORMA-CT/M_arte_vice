
<%@include file="widgets/header.jsp" %>
<h1>Listado de Categorías</h1>
<div class="row">
    <div class="col-md-8"></div>
    <div class="text-right col-md-4">
        <button class="btn btn-primary" onclick="modalCategoria()"><i class="fa fa-solid fa-plus" ></i> Nuevo</button>
    </div>
</div>
<table class="table table-bordered" id="tablax">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="categoria" items="${listaCategorias}">
            <tr>
                <td>${categoria.id}</td>
                <td>${categoria.nombre}</td>
                <td>
                    <button class="btn btn-warning" onclick="modificarCategoria(${categoria.id})">Modificar</button>
                    <a class="btn btn-danger" href="AdminCategoriaController?op=eliminar&id=${categoria.id}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Modal para Agregar/Modificar -->
<div class="modal fade" id="categoriaModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Agregar/Modificar Categoría</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="categoriaForm">
                    <input type="hidden" id="opCategoria" name="op" id="modificar">
                    <input type="hidden" name="id" id="categoriaId">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre">
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function eliminarCategoria(id) {
        $.ajax({
            url: "CategoriaController",
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

//    $('#categoriaForm').submit(function (e) {
//        e.preventDefault();
//        $.ajax({
//            url: "CategoriaController",
//            type: "POST",
//            data: $(this).serialize(),
//            success: function () {
//                $('#categoriaModal').modal('hide');
//                location.reload();
//            }
//        });
//    });

    function modalCategoria(id) {
//        $.get("CategoriaController", {op: "buscar", id: id}, function (data) {
        $('#opCategoria').val("agregar");
        $('#categoriaId').val("");
        $('#nombre').val("");
        $('#categoriaModal').modal('show');
//        });
    }
    function modificarCategoria(id) {
        $.get("AdminCategoriaController", {op: "buscar", id: id}, function (data) {
            $('#opCategoria').val("modificar");
            $('#categoriaId').val(data.id);
            $('#nombre').val(data.nombre);
            $('#categoriaModal').modal('show');
        });
    }
</script>
<%@include file="widgets/footer.jsp" %>