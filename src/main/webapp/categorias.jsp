
<%@include file="widgets/header.jsp" %>
<h1>Listado de Categorías</h1>
<table class="table table-bordered">
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
                    <button class="btn btn-danger" onclick="eliminarCategoria(${categoria.id})">Eliminar</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@include file="widgets/footer.jsp" %>