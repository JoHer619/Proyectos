/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

/**
 *
 * @author LENOVO
 */
public class ventas {

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }
    
    
    String cantidad;
    String Descripcion;
    String Precio;
    String importe;
    
    public ventas(String cantidad, String Descripcion, String Precio, String importe) {
        this.cantidad = cantidad;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.importe = importe;
    }
    
}
