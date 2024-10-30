
<%@include file="widgets/header.jsp" %>
<h1>Listado de Empleados</h1>

<table class="table table-bordered" id="tablax">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Horas Asignadas</th>
            <th>Horas Faltantes</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="artesano" items="${listaArtesanos}">
            <tr>
                <td>${artesano.PersonaID}</td>
                <td>${artesano.Nombre}</td>
                <td>${artesano.Apellido}</td>
                <td>${artesano.HorasAsignadas}</td>
                <td>${artesano.HorasFaltantes}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="widgets/footer.jsp" %>