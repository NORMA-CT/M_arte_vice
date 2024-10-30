
<%@include file="widgets/header.jsp" %>
<h1>Asignación de Pedidos a Empleados</h1>
<table class="table table-bordered" id="tablax">
    <thead>
        <tr>
            <th>ID Pedido</th>
            <th>Cliente</th>
            <th>Fecha Programada</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="pedido" items="${listaPedidos}">
        <tr>
            <td>${pedido.id}</td>
            <td>${pedido.clienteId}</td>
            <td>${pedido.fechaProgramada}</td>
            <td>
                <form action="AdminPedidoController?op=asignarEmpleado" method="POST">
                    <input type="hidden" name="pedidoId" value="${pedido.id}">
                    <label for="empleadoId">Empleado:</label>
                    <select name="empleadoId" id="empleadoId">                        
                        <c:forEach var="personal" items="${listaPersonal}">
                        <option value="${personal.PersonaID}">${personal.Nombre} ${personal.Apellido} (Pendiente: ${personal.HorasFaltantes})</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-success">Asignar</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</tbody>
</table>
<%@include file="widgets/footer.jsp" %>