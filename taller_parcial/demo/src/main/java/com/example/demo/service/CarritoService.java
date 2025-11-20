package com.example.demo.service;

import com.example.demo.model.CarritoCompras;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CarritoRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public CarritoCompras crearCarritoParaUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar si ya existe algún carrito para el usuario
        List<CarritoCompras> carritosExistentes = carritoRepository.findByUsuarioIdUsuario(usuarioId);
        if (!carritosExistentes.isEmpty()) {
            return carritosExistentes.get(0); // Retorna el primer carrito encontrado
        }

        CarritoCompras carrito = new CarritoCompras();
        carrito.setUsuario(usuario);
        carrito.setSubtotal(0.0);
        carrito.setImpuestos(0.0);

        return carritoRepository.save(carrito);
    }

    public CarritoCompras agregarProductoAlCarrito(Long carritoId, Long productoId, int cantidad, String emailUsuario) {
        CarritoCompras carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Validar que el carrito pertenezca al usuario
        if (!carrito.getUsuario().getCorreoElectronico().equals(emailUsuario)) {
            throw new RuntimeException("No tienes permisos para modificar este carrito");
        }

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Validar stock
        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente. Stock disponible: " + producto.getStock());
        }

        // Agregar productos al carrito
        for (int i = 0; i < cantidad; i++) {
            carrito.getProductos().add(producto);
        }

        // Actualizar stock
        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);

        // Recalcular totales
        recalcularTotales(carrito);

        return carritoRepository.save(carrito);
    }

    public CarritoCompras obtenerCarritoPorUsuario(Long usuarioId, String emailUsuario) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getCorreoElectronico().equals(emailUsuario)) {
            throw new RuntimeException("No autorizado");
        }

        // Buscar carritos del usuario
        List<CarritoCompras> carritos = carritoRepository.findByUsuarioIdUsuario(usuarioId);

        if (carritos.isEmpty()) {
            // Si no existe, crear uno nuevo
            return crearCarritoParaUsuario(usuarioId);
        } else {
            // Retornar el primer carrito encontrado
            return carritos.get(0);
        }
    }

    public List<Producto> obtenerProductosDelCarrito(Long carritoId, String emailUsuario) {
        CarritoCompras carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Validar que el carrito pertenezca al usuario
        if (!carrito.getUsuario().getCorreoElectronico().equals(emailUsuario)) {
            throw new RuntimeException("No tienes permisos para ver este carrito");
        }

        return carrito.getProductos();
    }

    private void recalcularTotales(CarritoCompras carrito) {
        double subtotal = carrito.getProductos().stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        carrito.setSubtotal(subtotal);
        carrito.setImpuestos(subtotal * 0.19); // 19% de impuestos
    }

    public CarritoCompras vaciarCarrito(Long carritoId, String emailUsuario) {
        CarritoCompras carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        if (!carrito.getUsuario().getCorreoElectronico().equals(emailUsuario)) {
            throw new RuntimeException("No tienes permisos para modificar este carrito");
        }

        // Devolver productos al stock
        for (Producto producto : carrito.getProductos()) {
            producto.setStock(producto.getStock() + 1);
            productoRepository.save(producto);
        }

        carrito.getProductos().clear();
        carrito.setSubtotal(0.0);
        carrito.setImpuestos(0.0);

        return carritoRepository.save(carrito);
    }
}