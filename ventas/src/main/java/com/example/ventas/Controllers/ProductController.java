package com.example.ventas.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.ventas.Models.Producto;
import com.example.ventas.Repositorys.ProductoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class ProductController {
    @Autowired
    private ProductoRepository productoRepository;

    @CrossOrigin
    @GetMapping("/producto")
    public ResponseEntity<List<Producto>> findAllProductos() {
        List<Producto> productos = new ArrayList<Producto>();
        productoRepository.findAll().forEach(productos::add);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/producto/{id}")
    @ResponseBody
    public ResponseEntity<Producto> findProductById(@PathVariable("id") Long id) {
        Optional<Producto> producto = productoRepository.findById(id);

        if (producto.isPresent()) {
            return new ResponseEntity<>(producto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin    
    @PostMapping("/producto")
    public ResponseEntity<String> postProducto(@RequestBody Producto producto) {
        Producto pro = productoRepository.save(producto);
        String mensaje = "¡El producto con el ID " + pro.getIdProducto() + " fue creado con éxito!";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }
    @CrossOrigin
    @PutMapping("/producto/{id}")
    public ResponseEntity<Producto> UpdateProduct(@PathVariable("id") Long id, @RequestBody Producto producto) {
        Optional<Producto> proAnt = productoRepository.findById(id);

        if (proAnt.isPresent()) {
            Producto productoAnt = proAnt.get();
            productoAnt.setNombre(producto.getNombre());
            productoAnt.setCantidad(producto.getCantidad());
            productoAnt.setPrecio(producto.getPrecio());
            productoAnt.setCategoria(producto.getCategoria());
            // productoAnt.setImagen(producto.getImagen());

            return new ResponseEntity<Producto>(productoRepository.save(productoAnt), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
/* 
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        Optional<Producto> pro = productoRepository.findById(id);

        if (pro.isPresent()) {
            productoRepository.deleteById(id);
            String mensaje = "¡El producto con el ID " + id + " fue eliminado con éxito!";
            return new ResponseEntity<>(mensaje, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Producto con ID " + id + " no encontrado", HttpStatus.NOT_FOUND);
        }
    }
*/
    @CrossOrigin
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        Optional<Producto> pro = productoRepository.findById(id);
        Producto prod = new Producto();

        if (pro.isPresent()) {
            productoRepository.deleteById(id);
            String mensaje = "¡El producto con el" + prod.getIdProducto() +"fue eliminado con éxito!";
            return new ResponseEntity<>(mensaje,HttpStatus.NO_CONTENT);
            
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            
        }


    }

}
