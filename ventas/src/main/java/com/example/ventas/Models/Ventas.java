package com.example.ventas.Models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
// import java.util.Date;
// import jakarta.persistence.Temporal;
// import jakarta.persistence.TemporalType;
// import lombok.Data;

@Entity
@Table(name = "venta")
public class Ventas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVenta;

    @ManyToOne
     @JoinColumn(name = "idProducto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

  
    @Column
    private int cantidad;
    @Column
    private double total; 

      
        private String fechaVenta;


        public Long getIdVenta() {
            return idVenta;
        }

        public void setIdVenta(Long idVenta) {
            this.idVenta = idVenta;
        }

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getFechaVenta() {
            return fechaVenta;
        }

        public void setFechaVenta(String fechaVenta) {
            this.fechaVenta = fechaVenta;
        }

        public Ventas(Long idVenta, Producto producto, Cliente cliente, int cantidad, double total, String fechaVenta) {
            this.idVenta = idVenta;
            this.producto = producto;
            this.cliente = cliente;
            this.cantidad = cantidad;
            this.total = total;
            this.fechaVenta = fechaVenta;
        }

        public Ventas() {
        }



    
}
