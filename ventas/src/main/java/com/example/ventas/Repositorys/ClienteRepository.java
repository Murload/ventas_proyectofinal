package com.example.ventas.Repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ventas.Models.Cliente;



@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
    
}
