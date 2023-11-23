        package com.example.ventas.Controllers;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ventas.Models.Cliente;
import com.example.ventas.Repositorys.ClienteRepository;

@RestController
public class ClienteController {
    
    
    @Autowired 
    private ClienteRepository clienteRepository;

    @GetMapping("/cliente")
    public ResponseEntity<List<Cliente>> findAllClientes(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        clienteRepository.findAll().forEach(clientes::add);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable("id") Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
			return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente){
        Cliente cliente2 = clienteRepository.save(cliente);

        return new ResponseEntity<>(cliente2, HttpStatus.OK);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> putCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteA = clienteRepository.findById(id);

        if(clienteA.isPresent()){
            Cliente clienteAnt = clienteA.get();
            clienteAnt.setDireccion(cliente.getDireccion());
            clienteAnt.setNombre(cliente.getNombre());
            clienteAnt.setTelefono(cliente.getTelefono());
            
            return new ResponseEntity<Cliente>(clienteRepository.save(clienteAnt), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()){
            clienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
