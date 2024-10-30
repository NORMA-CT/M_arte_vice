
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Arte Vice</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<%= request.getServletContext().getContextPath()%>/admin">Inicio <span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${session.admin}">
            <li class="nav-item">
                <a class="nav-link" href="UsuarioController?op=listar">Usuarios</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="AdminCategoriaController?op=listar">Categorías</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="AdminProductoController?op=listar">Productos</a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link" href="PersonaController?op=listarEmpleados">Empleados</a>
            </li>            
            <li class="nav-item">
                <a class="nav-link" href="AdminPedidoController?op=listarPendientes">Pendientes</a>
            </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="AdminPedidoController?op=listar">Pedidos</a>
            </li>
        </ul>
        <!--        <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>-->
        <form class="form-inline my-2 my-lg-0">
            <c:if test="${!session.logged}">
            <a class="btn btn-link" href="LoginController?op=login">Iniciar sesión</a>
            </c:if>
            <c:if test="${session.logged}">
            <a class="btn btn-link" href="LoginController?op=logout">Cerrar sesión</a>
            </c:if>
            <!--            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>-->
        </form>
    </div>
</nav>