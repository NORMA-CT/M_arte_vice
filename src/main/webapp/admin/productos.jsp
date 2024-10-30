
<%@include file="widgets/header.jsp" %>
<h1>Listado de Productos</h1>
<div class="row">
    <div class="col-md-8"></div>
    <div class="text-right col-md-4">
        <button class="btn btn-primary" onclick="modalProducto()"><i class="fa fa-solid fa-plus" ></i> Nuevo</button>
    </div>
</div>
<table class="table table-bordered" id="tablax">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Categoría</th>
            <th>Horas</th>
            <th>Complejidad</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="producto" items="${listaProductos}">
            <tr>
                <td>${producto.id}</td>
                <td>${producto.nombre}</td>
                <td>${producto.precio}</td>
                <td><c:forEach var="categoria" items="${listaCategorias}">
                        <c:if test="${producto.catId == categoria.id}">
                            ${categoria.nombre}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${producto.horas}</td>
                <td>${producto.complejidad}</td>
                <td>
                    <button class="btn btn-warning" onclick="modificarProducto(${producto.id})">Modificar</button>
                    <a class="btn btn-danger" href="AdminProductoController?op=eliminar&id=${producto.id}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Modal para Agregar/Modificar -->
<div class="modal fade" id="productoModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Agregar/Modificar Producto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="productoForm" action="AdminProductoController" method="POST">
                    <input type="hidden" id="opProducto" name="op" id="modificar">
                    <input type="hidden" name="id" id="productoId">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre">
                    </div>
                    <div class="mb-3">
                        <label for="precio" class="form-label">Precio</label>
                        <input type="number" step="0.01" class="form-control" id="precio" name="precio">
                    </div>
                    <div class="mb-3">
                        <label for="catId" class="form-label">Categoría</label>
                        <!--<input type="number" class="form-control" id="catId" name="catId">-->
                        <select class="form-control" name="catId" id="catId">
                            <c:forEach var="categoria" items="${listaCategorias}">
                                <option value="${categoria.id}">${categoria.nombre})</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="horas" class="form-label">Horas</label>
                        <input type="number" class="form-control" id="horas" name="horas">
                    </div>
                    <div class="mb-3">
                        <label for="complejidad" class="form-label">Complejidad</label>
                        <input type="number" class="form-control" id="complejidad" name="complejidad">
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>

    // Función para eliminar producto
    function eliminarProducto(id) {
        $.ajax({
            url: "AdminProductoController",
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

    /*// Agregar/Modificar Producto usando Modal
     $('#productoForm').submit(function (e) {
     e.preventDefault();
     $.ajax({
     url: "ProductoController",
     type: "POST",
     data: $(this).serialize(),
     success: function () {
     $('#productoModal').modal('hide');
     location.reload();
     }
     });
     });*/

    // Función para abrir el modal de modificación con datos
    function modalProducto() {
        //$.get("AdminProductoController", {op: "agregar", id: id}, function (data) {
//        console.log(data)
        $('#opProducto').val("agregar");
        $('#productoId').val("");
        $('#nombre').val("");
        $('#precio').val("");
        $('#catId').val("");
        $('#horas').val("");
        $('#complejidad').val("");
        $('#productoModal').modal('show');

        $('#nombre').focus();
        //});
    }

    // Función para abrir el modal de modificación con datos
    function modificarProducto(id) {
        $.get("AdminProductoController", {op: "buscar", id: id}, function (data) {
//            console.log(data)
            $('#opProducto').val("modificar");
            $('#productoId').val(data.id);
            $('#nombre').val(data.nombre);
            $('#precio').val(data.precio);
            $('#catId').val(data.catId);
            $('#horas').val(data.horas);
            $('#complejidad').val(data.complejidad);
            $('#productoModal').modal('show');
        });
    }
</script>
<%@include file="widgets/footer.jsp" %>