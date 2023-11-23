package com.example.ventas.Repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ventas.Models.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long>{
    
}
