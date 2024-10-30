/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import java.time.LocalDateTime;

public class Pedido {
    private Integer id;
    private Integer clienteId;
    private Double precioTotal;
    private Integer horasTotales;
    private Integer complejidad;
    private Short proceso;
    private LocalDateTime fechaProgramada;

    public Pedido() {}

    public Pedido(Integer id, Integer clienteId, Double precioTotal, Integer horasTotales, Integer complejidad, Short proceso, LocalDateTime fechaProgramada) {
        this.id = id;
        this.clienteId = clienteId;
        this.precioTotal = precioTotal;
        this.horasTotales = horasTotales;
        this.complejidad = complejidad;
        this.proceso = proceso;
        this.fechaProgramada = fechaProgramada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Integer getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(Integer horasTotales) {
        this.horasTotales = horasTotales;
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

    public LocalDateTime getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }
}
