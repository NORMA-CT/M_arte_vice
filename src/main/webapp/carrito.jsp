
<%@include file="widgets/header.jsp" %>
<h1>Carrito de Compras</h1>

<c:if test="${not empty sessionScope.carrito}">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Producto</th>
                <th>Precio Unitario</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${sessionScope.carrito}">
                <tr>
                    <td>${item.nombre}</td>
                    <td>${item.precioUnitario}</td>
                    <td>
                        <input type="number" value="${item.cantidad}" onchange="actualizarCantidad(${item.productoId}, this.value)">
                    </td>
                    <td>${item.subtotal}</td>
                    <td>
                        <button class="btn btn-danger" onclick="eliminarProducto(${item.productoId}, '${item.nombre}')">Eliminar</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!--<button class="btn btn-success" onclick="confirmarPedido()">Confirmar Pedido</button-->
    <a class="btn btn-success" href="CarritoController?op=confirmar">Confirmar Pedido</a>
</c:if>

<c:if test="${empty sessionScope.carrito}">
    <p>No hay productos en el carrito.</p>
</c:if>

<script>
    function actualizarCantidad(productoId, cantidad) {
        $.post("CarritoController", {op: "actualizar", productoId: productoId, cantidad: cantidad}, function () {
            location.reload();
        });
    }

    function eliminarProducto(productoId, nombre) {
        showConfirm({type: 'warning', title: 'Eliminar item', msg: 'Se eliminará el producto ' + nombre + ' del carrito ¿Desea continuar?'}, function () {
            $.post("CarritoController", {op: "eliminar", productoId: productoId}, function () {
                location.reload();
            });
        });

    }

    function confirmarPedido() {
        $.post("CarritoController", {op: "confirmar"}, function () {
            showInfo('success', 'Pedido creado', 'Se ha creado su pedido', function () {
                window.location.href = "index.jsp";
            });
        });
    }
</script>
<%@include file="widgets/footer.jsp" %>