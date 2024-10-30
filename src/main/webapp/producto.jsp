

<%@include file="widgets/header.jsp" %>
<style>
    .product-image {
        max-width: 100%;
        height: auto;
    }
    .quantity-control {
        display: flex;
        align-items: center;
    }
    .quantity-control input {
        width: 60px;
        text-align: center;
    }
    .quantity-control button {
        font-size: 20px;
    }
</style>

<h1 class="my-4">${producto.nombre}</h1>

<div class="row">
    <div class="col-md-6">
        <img src="<%= request.getServletContext().getContextPath()%>/images/productos/PR_A${producto.id}.jpg" class="product-image" alt="${producto.nombre}">
    </div>
    <div class="col-md-6">
        <h3>Precio: S/${producto.precio}</h3>
        <p>${producto.descripcion}</p>

        <div class="quantity-control mb-3">
            <button class="btn btn-secondary" onclick="cambiarCantidad(-1)">-</button>
            <input type="number" id="cantidad" value="1" min="1" max="99">
            <button class="btn btn-secondary" onclick="cambiarCantidad(1)">+</button>
        </div>

        <button class="btn btn-primary" onclick="agregarAlCarrito(${producto.id})">Agregar al Carrito</button>
    </div>
</div>
<script>
    function cambiarCantidad(delta) {
        let cantidad = parseInt($('#cantidad').val());
        cantidad = Math.max(1, cantidad + delta);  // La cantidad no puede ser menor que 1
        $('#cantidad').val(cantidad);
    }

    function agregarAlCarrito(productoId) {
        let cantidad = $('#cantidad').val();
        $.post("CarritoController", {op: "agregar", productoId: productoId, cantidad: cantidad}, function () {
            showConfirm({
                type: 'info',
                title: 'Carrito',
                msg: 'Producto agregado al carrito',
                cancelText: 'Continuar Comprando',
                confirmText: 'Ver carrito'
            }, function () {
                window.location.href = "CarritoController?op=ver";
            });


        });
    }
</script>
<%@include file="widgets/footer.jsp" %>