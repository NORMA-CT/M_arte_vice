
<%@include file="widgets/header.jsp" %>
<style>
    .card {
        margin-bottom: 20px;
    }
    .card img {
        max-height: 200px;
        object-fit: cover;
    }
    .added-to-cart {
        color: green;
        font-weight: bold;
    }
    
    .card img {
        object-fit: cover;
        width: 100%;
        height: 250px;
    }
</style>
<h1 class="my-4">Nuestros Productos</h1>

<div class="row">
    <c:forEach var="producto" items="${listaProductos}">
        <div class="col-md-3">
            <div class="card">
                <img src="<%= request.getServletContext().getContextPath()%>/images/productos/PR_A${producto.id}.jpg" class="card-img-top" alt="${producto.nombre}">
                <div class="card-body">
                    <h5 class="card-title">${producto.nombre}</h5>
                    <p class="card-text">Precio: S/${producto.precio}</p>

                    <!-- Si ya está en el carrito -->
                    <c:if test="${false}">
                        <p class="added-to-cart">Agregado al carrito</p>
                    </c:if>

                    <!-- Si no está en el carrito -->
                    <c:if test="${!true}">
                        <button class="btn btn-primary" onclick="agregarAlCarrito(${producto.id})">Agregar al Carrito</button>
                    </c:if>

                    <a href="ProductoController?op=detalle&id=${producto.id}" class="btn btn-secondary">Ver Detalles</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<script>
    function agregarAlCarrito(productoId) {
        $.post("CarritoController", {op: "agregar", productoId: productoId, cantidad: 1}, function () {
            location.reload();  // Recargamos la página para mostrar que el producto fue agregado al carrito
        });
    }
</script>
<%@include file="widgets/footer.jsp" %>