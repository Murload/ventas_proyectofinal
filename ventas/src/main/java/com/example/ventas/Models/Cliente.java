package com.example.ventas.Models;

import java.util.List;

//import org.hibernate.mapping.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToMany;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import lombok.Data;
import lombok.Data;

@Entity
@Data
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCliente;
    @Column
    private String nombre;
    @OneToMany
    private List<Ventas> ventas;
    @Column
    private String direccion;
    @Column
    private Long telefono;


    

}
