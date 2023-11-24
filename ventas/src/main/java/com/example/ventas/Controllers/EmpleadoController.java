package com.example.ventas.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.hibernate.mapping.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import com.example.ventas.Models.Empleado;
import com.example.ventas.Repositorys.EmpleadoRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class EmpleadoController {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @CrossOrigin
    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> findAllEmpleado(){
        List<Empleado> empleado = new ArrayList<Empleado>();
        empleadoRepository.findAll().forEach(empleado::add);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }    

    @CrossOrigin
    @GetMapping("/empleado/{id}")
    @ResponseBody
    public ResponseEntity<Empleado> findEmpleadoByID(@PathVariable("id") Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);

        if (empleado.isPresent()) {
            return new ResponseEntity<>(empleado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping("/empleado")
    public ResponseEntity<String> postEmpleado(@RequestBody Empleado empleado){
        Empleado emp = empleadoRepository.save(empleado);
        String mensaje = "El empleado con el ID" + emp.getIdEmpleado() + "fue creado con exito";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);

    }

    @CrossOrigin
    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<String> deleteEmpleado(@PathVariable("id") Long id){
        Optional<Empleado> emp = empleadoRepository.findById(id);
        Empleado emple = new Empleado();

        if (emp.isPresent()) {
            empleadoRepository.deleteById(id);
            String mensaje = "¡El producto con el" + emple.getIdEmpleado() +"fue eliminado con éxito!";
            return new ResponseEntity<>(mensaje,HttpStatus.NO_CONTENT);
            
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            
        }


    }

}
