package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DataLoaderService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadData() {
        cargarUsuarios();
        cargarProductosCompletos();
        cargarCategorias();
        cargarComentarios(); // ¡DESCOMENTADO!
    }

    private void cargarUsuarios() {
        if (usuarioRepository.count() == 0) {
            List<Usuario> usuarios = Arrays.asList(
                    new Usuario(null, "Juan Pérez", "juan.perez@email.com",
                            passwordEncoder.encode("Qwerty123"), "Carrera 45 #10-20", "Tarjeta de crédito"),
                    new Usuario(null, "Ana Gómez", "ana.gomez@email.com",
                            passwordEncoder.encode("Pass456"), "Calle 21 #35-50", "PayPal"),
                    new Usuario(null, "Carlos Ruiz", "carlos.ruiz@email.com",
                            passwordEncoder.encode("Segura789"), "Avenida Principal #100", "Transferencia bancaria"),
                    new Usuario(null, "Sofía Martínez", "sofia.martinez@email.com",
                            passwordEncoder.encode("Clave987"), "Calle 8 #20-30", "Efectivo"),
                    new Usuario(null, "Diego Fernández", "diego.fernandez@email.com",
                            passwordEncoder.encode("Contra654"), "Carrera 77 #40-60", "Tarjeta débito")
            );
            usuarioRepository.saveAll(usuarios);
            System.out.println("Usuarios cargados exitosamente");
        }
    }

    private void cargarProductosCompletos() {
        if (productoRepository.count() == 0) {
            List<Producto> productos = Arrays.asList(
                    new Producto(null, "Laptop", "Portátil con pantalla Full HD y SSD de 512GB", 89999, 10),
                    new Producto(null, "Smartphone", "Teléfono con cámara de 108MP y carga rápida", 49950, 20),
                    new Producto(null, "Tablet", "Dispositivo con pantalla táctil de 10 pulgadas", 29999, 15),
                    new Producto(null, "Auriculares", "Audífonos inalámbricos con cancelación de ruido", 12999, 25),
                    new Producto(null, "Teclado", "Teclado mecánico con iluminación RGB", 8999, 30),
                    new Producto(null, "Mouse", "Ratón inalámbrico con sensor óptico de alta precisión", 5999, 50),
                    new Producto(null, "Monitor", "Pantalla LED 4K de 27 pulgadas", 49900, 12),
                    new Producto(null, "Impresora", "Láser multifuncional con Wi-Fi", 17999, 18),
                    new Producto(null, "Cámara", "Cámara digital con lente profesional", 79999, 8),
                    new Producto(null, "Smartwatch", "Reloj inteligente con GPS y monitoreo cardíaco", 19999, 22)
            );
            productoRepository.saveAll(productos);
            System.out.println("Productos cargados exitosamente");
        }
    }

    private void cargarCategorias() {
        if (categoriaRepository.count() == 0) {
            List<Categoria> categorias = Arrays.asList(
                    new Categoria(null, "Electrónicos"),
                    new Categoria(null, "Computación"),
                    new Categoria(null, "Hogar"),
                    new Categoria(null, "Oficina")
            );
            categoriaRepository.saveAll(categorias);
            System.out.println("Categorías cargadas exitosamente");
        }
    }

    private void cargarComentarios() {
        if (comentarioRepository.count() == 0) {
            // Obtener usuarios y productos de la base de datos
            Optional<Usuario> juan = usuarioRepository.findByCorreoElectronico("juan.perez@email.com");
            Optional<Usuario> ana = usuarioRepository.findByCorreoElectronico("ana.gomez@email.com");
            Optional<Usuario> carlos = usuarioRepository.findByCorreoElectronico("carlos.ruiz@email.com");
            Optional<Usuario> sofia = usuarioRepository.findByCorreoElectronico("sofia.martinez@email.com");
            Optional<Usuario> diego = usuarioRepository.findByCorreoElectronico("diego.fernandez@email.com");

            // Obtener productos por nombre
            List<Producto> productos = productoRepository.findAll();
            Producto laptop = productos.stream().filter(p -> p.getNombre().equals("Laptop")).findFirst().orElse(null);
            Producto smartphone = productos.stream().filter(p -> p.getNombre().equals("Smartphone")).findFirst().orElse(null);
            Producto tablet = productos.stream().filter(p -> p.getNombre().equals("Tablet")).findFirst().orElse(null);
            Producto auriculares = productos.stream().filter(p -> p.getNombre().equals("Auriculares")).findFirst().orElse(null);
            Producto teclado = productos.stream().filter(p -> p.getNombre().equals("Teclado")).findFirst().orElse(null);
            Producto mouse = productos.stream().filter(p -> p.getNombre().equals("Mouse")).findFirst().orElse(null);
            Producto monitor = productos.stream().filter(p -> p.getNombre().equals("Monitor")).findFirst().orElse(null);
            Producto impresora = productos.stream().filter(p -> p.getNombre().equals("Impresora")).findFirst().orElse(null);
            Producto camara = productos.stream().filter(p -> p.getNombre().equals("Cámara")).findFirst().orElse(null);
            Producto smartwatch = productos.stream().filter(p -> p.getNombre().equals("Smartwatch")).findFirst().orElse(null);

            if (juan.isPresent() && ana.isPresent() && carlos.isPresent() && sofia.isPresent() && diego.isPresent() &&
                    laptop != null && smartphone != null && tablet != null && auriculares != null && teclado != null) {

                List<Comentario> comentarios = Arrays.asList(
                        new Comentario(null, laptop, juan.get(), "Excelente rendimiento; muy rápida. ¡Me encanta!", "1/05/2025"),
                        new Comentario(null, smartphone, ana.get(), "Buena cámara pero la batería dura poco.", "3/05/2025"),
                        new Comentario(null, tablet, carlos.get(), "No me gustó; pantalla de baja calidad.", "5/05/2025"),
                        new Comentario(null, auriculares, sofia.get(), "Sonido aceptable pero el material parece frágil.", "6/05/2025"),
                        new Comentario(null, teclado, diego.get(), "Muy buen teclado mecánico; excelente respuesta.", "8/05/2025"),
                        new Comentario(null, mouse, ana.get(), "El sensor no es tan preciso como esperaba.", "10/05/2025"),
                        new Comentario(null, monitor, carlos.get(), "Colores vibrantes y buena resolución. Muy satisfecho.", "12/05/2025"),
                        new Comentario(null, impresora, juan.get(), "Tarda mucho en imprimir; no me convence.", "13/05/2025"),
                        new Comentario(null, camara, sofia.get(), "Increíble calidad de imagen; fotos súper nítidas.", "15/05/2025"),
                        new Comentario(null, smartwatch, diego.get(), "Buena batería; pero la pantalla no es muy brillante.", "18/05/2025")
                );

                comentarioRepository.saveAll(comentarios);
                System.out.println("Comentarios cargados exitosamente");
            } else {
                System.out.println("No se pudieron cargar los comentarios: usuarios o productos no encontrados");
            }
        }
    }
}