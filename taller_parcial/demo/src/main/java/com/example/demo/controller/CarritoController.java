package com.example.demo.controller;

import com.example.demo.model.CarritoCompras;
import com.example.demo.model.Producto;
import com.example.demo.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/crear/{usuarioId}")
    public ResponseEntity<CarritoCompras> crearCarrito(@PathVariable Long usuarioId, Principal principal) {
        CarritoCompras carrito = carritoService.crearCarritoParaUsuario(usuarioId);
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("/{carritoId}/agregar/{productoId}")
    public ResponseEntity<CarritoCompras> agregarProducto(
            @PathVariable Long carritoId,
            @PathVariable Long productoId,
            @RequestParam(defaultValue = "1") int cantidad,
            Principal principal) {
        CarritoCompras carrito = carritoService.agregarProductoAlCarrito(carritoId, productoId, cantidad, principal.getName());
        return ResponseEntity.ok(carrito);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoCompras> obtenerCarritoUsuario(@PathVariable Long usuarioId, Principal principal) {
        CarritoCompras carrito = carritoService.obtenerCarritoPorUsuario(usuarioId, principal.getName());
        return ResponseEntity.ok(carrito);
    }

    @GetMapping("/{carritoId}/productos")
    public ResponseEntity<List<Producto>> listarProductosCarrito(@PathVariable Long carritoId, Principal principal) {
        List<Producto> productos = carritoService.obtenerProductosDelCarrito(carritoId, principal.getName());
        return ResponseEntity.ok(productos);
    }

    @DeleteMapping("/{carritoId}/vaciar")
    public ResponseEntity<CarritoCompras> vaciarCarrito(@PathVariable Long carritoId, Principal principal) {
        CarritoCompras carrito = carritoService.vaciarCarrito(carritoId, principal.getName());
        return ResponseEntity.ok(carrito);
    }
}