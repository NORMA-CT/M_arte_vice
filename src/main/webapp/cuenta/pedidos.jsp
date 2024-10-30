
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../widgets/header.jsp" %>
<h1>Mis Pedidos</h1>
<table class="table table-bordered" id="tablax">
    <thead>
        <tr>
            <th>ID</th>
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
                <td>${pedido.precioTotal}</td>
                <td>${pedido.horasTotales}</td>
                <td>${pedido.complejidad}</td>
                <td>${pedido.proceso}</td>
                <td>${st.setCodigo(pedido.proceso)}${st.labelEstadoPedido}</td>
                <td>${pedido.fechaProgramada}</td>
                <td>
                    <a href="TiendaPedidoController?op=detalles&pedidoId=${pedido.id}" class="btn btn-info">Ver Detalles</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@include file="../widgets/footer.jsp" %>
