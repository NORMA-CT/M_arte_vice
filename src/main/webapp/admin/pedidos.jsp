
<%@include file="widgets/header.jsp" %>
<h1>Pedidos</h1>
<table class="table table-bordered" id="tablax">
    <thead>
        <tr>
            <th>ID</th>
            <th>Cliente ID</th>
            <th>Precio Total</th>
            <th>Horas Totales</th>
            <th>Complejidad</th>
            <th>Estado</th>
            <th>Fecha Programada</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="pedido" items="${listaPedidos}">
            <tr>
                <td>${pedido.id}</td>
                <td>${pedido.clienteId}</td>
                <td>${pedido.precioTotal}</td>
                <td>${pedido.horasTotales}</td>
                <td>${pedido.complejidad}</td>
                <td>${pedido.proceso}</td>
                <td>${pedido.fechaProgramada}</td>
                
                <td>
                    <c:if test="${pedido.proceso == 20}">
                    <a href="AdminPedidoController?op=marcarAtendido&pedidoId=${pedido.id}" class="btn btn-success">Marcar como Atendido</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="widgets/footer.jsp" %>
