package com.example.ventas.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.ventas.Models.Cliente;
import com.example.ventas.Models.Producto;
// import com.example.ventas.Models.Cliente;
// import com.example.ventas.Models.Producto;
// import com.example.ventas.Models.Producto;
// import com.example.ventas.Models.Producto;
import com.example.ventas.Models.Ventas;
// import com.example.ventas.Controllers.ProductController;
import com.example.ventas.Repositorys.VentasRepository;
import com.example.ventas.Repositorys.ClienteRepository;
import com.example.ventas.Repositorys.ProductoRepository;
// import ch.qos.logback.core.model.Model;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
// import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class VentaController {

    @Autowired

    private VentasRepository ventarepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @CrossOrigin
    @GetMapping("/ventas")
    public ResponseEntity<List<Ventas>> findAllVentas() {
        List<Ventas> ventas = new ArrayList<Ventas>();
        ventarepository.findAll().forEach(ventas::add);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    // @PostMapping("/venta")
    // public ResponseEntity<String> postProducto(@RequestBody Ventas venta) {
    //     Ventas ven = ventarepository.save(venta);
    //     String mensaje = "La venta con el ID " + ven.getIdVenta() + " fue creado con éxito!";
    //     return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    // }
    @CrossOrigin
    @PostMapping("/venta")
    public ResponseEntity<String> postVentass(@RequestBody Ventas venta) {
       Producto vpro = venta.getProducto();
       Cliente cli = venta.getCliente();
        // Long idProducto = pro.getIdProducto();
        // Long idCliente = cli.getIdCliente();
        Date fecha = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy " + " HH:mm:ss");

        // Formatear la fecha
        String fechaFormateada = sdf.format(fecha);

        Optional<Cliente> cliente = clienteRepository.findById(cli.getIdCliente());
        Optional<Producto> producto = productoRepository.findById(vpro.getIdProducto());

        if (cliente.isPresent() && producto.isPresent()) {
            Cliente clientes = cliente.get();
            Producto productos = producto.get();
            
            if (productos.getCantidad() >= venta.getCantidad()) {
            
                productos.setCantidad(productos.getCantidad() - venta.getCantidad());
                Producto prod = productoRepository.save(productos);
                venta.setTotal(productos.getPrecio() * venta.getCantidad());
                venta.setFechaVenta(fechaFormateada);
                Ventas ven = ventarepository.save(venta);
                
                return new ResponseEntity<>("El producto y la venta se actualizaron correctamente", HttpStatus.OK);
                
            } else{

                return new ResponseEntity<>("No hay suficiente cantidad del producto.", HttpStatus.BAD_REQUEST);
            }

        }else {
            
            return new ResponseEntity<>("Cliente o Producto no encontrado :(", HttpStatus.NOT_FOUND);
        }         
    }

    @CrossOrigin
    @DeleteMapping("/venta/{id}")
    public ResponseEntity<String> deleteVentas(@PathVariable("id") Long id) {
        Optional<Ventas> ven = ventarepository.findById(id);
        Ventas ventas = new Ventas();

        if (ven.isPresent()) {
            ventarepository.deleteById(id);
            String mensaje = "¡La venta con el " + ventas.getIdVenta() + "fue creado con éxito!";
            return new ResponseEntity<>(mensaje, HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
