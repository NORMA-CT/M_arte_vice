

<%@include file="widgets/header.jsp" %>
<style>
    .card img {
        object-fit: cover;
        width: 100%;
        height: 250px;
    }
</style>
<div class="row">
    <div class="col-md-12">
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="Slide 4"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="4" aria-label="Slide 5"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="5" aria-label="Slide 6"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="<%= request.getServletContext().getContextPath()%>/images/slider/G_A1.jpg" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="<%= request.getServletContext().getContextPath()%>/images/slider/G_A2.jpg" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="<%= request.getServletContext().getContextPath()%>/images/slider/G_A3.jpg" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="<%= request.getServletContext().getContextPath()%>/images/slider/G_A4.jpg" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="<%= request.getServletContext().getContextPath()%>/images/slider/G_A5.jpg" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="<%= request.getServletContext().getContextPath()%>/images/slider/G_A6.jpg" class="d-block w-100" alt="...">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Anterior</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Siguiente</span>
            </button>
        </div>
    </div>
</div>
<h3  style="margin-top:20px">Productos</h3>

<div class="row mt-20">
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
<%@include file="widgets/footer.jsp" %>