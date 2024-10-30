
<%@include file="widgets/header.jsp" %>
<script src="assets/js/Chart.min.js"></script>
<h1>Dashboard</h1>
<div class="row">
    <!-- Gráfico de Pedidos por Estado -->
    <div class="col-md-6">
        <h2>Pedidos por Estado</h2>
        <canvas id="pedidosPorEstadoChart"></canvas>
    </div>

    <!-- Gráfico de Ventas en los últimos 6 meses -->
    <div class="col-md-6">
        <h2>Ventas Últimos 6 Meses</h2>
        <canvas id="ventasUltimos6MesesChart"></canvas>
    </div>
</div>

<div class="row">
    <!-- Gráfico Top 10 Productos más vendidos -->
    <div class="col-md-12">
        <h2>Top 10 Productos más Vendidos</h2>
        <canvas id="top10ProductosChart"></canvas>
    </div>
</div>

    <script>
        // Gráfico Pedidos por Estado
        const pedidosPorEstadoData = {
            labels: [
                <c:forEach var="pedido" items="${pedidosPorEstado}">
                    '${pedido.Estado}', 
                </c:forEach>
            ],
            datasets: [{
                label: 'Cantidad de Pedidos',
                data: [
                    <c:forEach var="pedido" items="${pedidosPorEstado}">
                        ${pedido.Cantidad_Pedidos}, 
                    </c:forEach>
                ],
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        };

        const pedidosPorEstadoChart = new Chart(
            document.getElementById('pedidosPorEstadoChart'),
            {
                type: 'bar',
                data: pedidosPorEstadoData,
                options: {
                    scales: {
                        y: { beginAtZero: true }
                    }
                }
            }
        );

        // Gráfico Ventas en los Últimos 6 Meses
        const ventasUltimos6MesesData = {
            labels: [
                <c:forEach var="pago" items="${pagosUltimos6Meses}">
                    'Mes ${pago.Mes}', 
                </c:forEach>
            ],
            datasets: [{
                label: 'Total de Ventas',
                data: [
                    <c:forEach var="pago" items="${pagosUltimos6Meses}">
                        ${pago.Total_Ventas}, 
                    </c:forEach>
                ],
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 1)',
                borderWidth: 1
            }]
        };

        const ventasUltimos6MesesChart = new Chart(
            document.getElementById('ventasUltimos6MesesChart'),
            {
                type: 'line',
                data: ventasUltimos6MesesData,
                options: {
                    scales: {
                        y: { beginAtZero: true }
                    }
                }
            }
        );

        // Gráfico Top 10 Productos más Vendidos
        const top10ProductosData = {
            labels: [
                <c:forEach var="producto" items="${top10ProductosVendidos}">
                    '${producto.Producto}', 
                </c:forEach>
            ],
            datasets: [{
                label: 'Cantidad Vendida',
                data: [
                    <c:forEach var="producto" items="${top10ProductosVendidos}">
                        ${producto.Cantidad_Vendida}, 
                    </c:forEach>
                ],
                backgroundColor: 'rgba(255, 159, 64, 0.2)',
                borderColor: 'rgba(255, 159, 64, 1)',
                borderWidth: 1
            }]
        };

        const top10ProductosChart = new Chart(
            document.getElementById('top10ProductosChart'),
            {
                type: 'bar',
                data: top10ProductosData,
                options: {
                    scales: {
                        y: { beginAtZero: true }
                    }
                }
            }
        );
    </script>
<%@include file="widgets/footer.jsp" %>