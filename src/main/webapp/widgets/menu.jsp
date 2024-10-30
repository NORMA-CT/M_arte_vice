
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="<%= request.getServletContext().getContextPath()%>/">Arte Vice</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<%= request.getServletContext().getContextPath()%>/">Inicio <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ProductoController?op=listar">Productos</a>
            </li>
            <!--            <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Productos
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Something else here</a>
                            </div>
                        </li>-->
            <li class="nav-item">
                <a class="nav-link" href="CarritoController?op=ver">Carrito</a>
            </li>
            <c:if test="${session.cliente}">
                <li class="nav-item">
                    <a class="nav-link" href="TiendaPedidoController?op=listar">Pedidos</a>
                </li>
            </c:if>
            <!--            <li class="nav-item">
                            <a class="nav-link disabled" href="#">Disabled</a>
                        </li>-->
        </ul>
        <!--        <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>-->
        <form class="form-inline my-2 my-lg-0">
            <c:if test="${!session.logged}">
            <a class="btn btn-link" href="LoginController?op=login">Iniciar sesión </a>
            </c:if>
            <c:if test="${session.cliente}">
            <a class="btn btn-link" href="LoginController?op=verMiCuenta">Mi cuenta</a>
            </c:if>
            <c:if test="${session.logged}">
            <a class="btn btn-link" href="LoginController?op=logout">Cerrar sesión</a>
            </c:if>
            <!--            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>-->
        </form>
    </div>
</nav>