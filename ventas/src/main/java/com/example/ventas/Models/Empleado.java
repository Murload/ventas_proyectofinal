package com.example.ventas.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "empleado")
public class Empleado {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEmpleado;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String cargo;
    @Column
    private String contrasena;
    @Column
    private int telefono;
    @Column
    private boolean activo;
    
}
