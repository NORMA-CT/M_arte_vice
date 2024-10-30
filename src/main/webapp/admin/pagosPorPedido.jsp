
<%@include file="widgets/header.jsp" %>
<h1>Pagos del Pedido</h1>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>ID Pago</th>
            <th>Tipo de Pago</th>
            <th>Monto</th>
            <th>Fecha de Pago</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="pago" items="${listaPagos}">
            <tr>
                <td>${pago.id}</td>
                <td>${pago.tipoPago}</td>
                <td>${pago.monto}</td>
                <td>${pago.fechaPago}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="widgets/footer.jsp" %>