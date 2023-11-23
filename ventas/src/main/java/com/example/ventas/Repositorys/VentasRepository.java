package com.example.ventas.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ventas.Models.Ventas;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Long>{
    
}
