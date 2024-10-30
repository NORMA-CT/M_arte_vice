/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;


public class Estado {

    private int codigo;
    private String descripcion;
    private String color;

    public Estado(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLabelEstadoPedido() {
        this.loadEstado();
        return "<span class=\"badge " + this.color + "\">" + this.descripcion + "</span>";
    }

    public void loadEstado() {
        switch (codigo) {
            case 10:
                this.descripcion = "Pendiente";
                this.color = "bg-warning";
                break;
            case 20:
                this.descripcion = "En atencion&Oacute;n";
                this.color = "bg-primary";
                break;
            case 30:
                this.descripcion = "Atendido";
                this.color = "bt-success";
                break;
        }
    }

}
