/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;


public class PedidoDetalle {

    private Integer id;
    private Integer pedidoId;
    private Integer refId;
    private Integer productoId;
    private Double precioUnitario;
    private Integer cantidad;
    private Double precio;
    private Integer horas;
    private Integer complejidad;
    private Short proceso;

    public PedidoDetalle() {
    }

    public PedidoDetalle(Integer id, Integer pedidoId, Integer refId, Integer productoId, Double precioUnitario, Integer cantidad, Double precio, Integer horas, Integer complejidad, Short proceso) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.refId = refId;
        this.productoId = productoId;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.precio = precio;
        this.horas = horas;
        this.complejidad = complejidad;
        this.proceso = proceso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(Integer complejidad) {
        this.complejidad = complejidad;
    }

    public Short getProceso() {
        return proceso;
    }

    public void setProceso(Short proceso) {
        this.proceso = proceso;
    }
}
