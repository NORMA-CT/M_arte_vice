
<%@include file="../widgets/header.jsp" %>
<h1>Detalles del Pedido</h1>
<table class="table table-bordered">
    <tr>
        <th>ID del Pedido</th>
        <td>${pedido.id}</td>
    </tr>
    <tr>
        <th>Cliente ID</th>
        <td>${pedido.clienteId}</td>
    </tr>
    <tr>
        <th>Precio Total</th>
        <td>${pedido.precioTotal}</td>
    </tr>
    <tr>
        <th>Horas Totales</th>
        <td>${pedido.horasTotales}</td>
    </tr>
    <tr>
        <th>Complejidad</th>
        <td>${pedido.complejidad}</td>
    </tr>
    <tr>
        <th>Estado</th>
        <td>${pedido.proceso}</td>
    </tr>
    <tr>
        <th>Fecha Programada</th>
        <td>${pedido.fechaProgramada}</td>
    </tr>
</table>
<%@include file="../widgets/footer.jsp" %>