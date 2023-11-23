package com.example.ventas.Models;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "producto")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProducto;
    @Column(length = 50)
    private String nombre;

    @OneToMany

    private List<Ventas> ventas;
    @Column
    private int precio;
    @Column
    
    private int cantidad;
    @Column
    private String categoria;
    @Column
    private String imagen;   
}
