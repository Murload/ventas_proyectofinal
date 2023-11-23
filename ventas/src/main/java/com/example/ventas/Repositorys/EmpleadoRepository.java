package com.example.ventas.Repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ventas.Models.Empleado;



@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long>{



}

    