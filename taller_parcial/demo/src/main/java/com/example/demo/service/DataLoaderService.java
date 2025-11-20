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
                    new Producto(null, "Smartwatch", "Reloj inteligente con GPS y monitoreo cardíaco", 19999, 22),

                    new Producto(null, "Silla Gamer", "Silla ergonómica ajustable con soporte lumbar", 29999, 14),
                    new Producto(null, "Microondas", "Horno microondas con múltiples funciones", 12999, 40),
                    new Producto(null, "Refrigerador", "Frigorífico doble puerta con sistema No Frost", 119999, 5),
                    new Producto(null, "Lavadora", "Lavadora automática con capacidad de 10kg", 59999, 7),
                    new Producto(null, "Cafetera", "Cafetera express con vaporizador de leche", 14999, 35),
                    new Producto(null, "Drone", "Drone con cámara 4K y estabilizador", 69999, 9),
                    new Producto(null, "Bocina Bluetooth", "Altavoz portátil con sonido envolvente", 8999, 33),
                    new Producto(null, "Videocámara", "Videocámara profesional con grabación en 4K", 99999, 6),
                    new Producto(null, "TV LED", "Televisor inteligente de 55 pulgadas con HDR", 74999, 11),
                    new Producto(null, "Batería Externa", "Batería de 20000mAh con carga rápida", 3999, 45),

                    new Producto(null, "Disco Duro", "Disco duro externo de 2TB", 12999, 28),
                    new Producto(null, "Memoria USB", "Pendrive de 128GB", 2999, 60),
                    new Producto(null, "Router", "Router Wi-Fi 6 de alta velocidad", 19999, 16),
                    new Producto(null, "Joystick", "Control inalámbrico para videojuegos", 7999, 20),
                    new Producto(null, "Fuente de Poder", "Fuente de alimentación para PC de 750W", 8999, 17),
                    new Producto(null, "SSD", "Unidad de almacenamiento SSD de 1TB", 14999, 32),
                    new Producto(null, "Altavoces", "Par de bocinas estéreo con subwoofer", 13999, 23),
                    new Producto(null, "Webcam", "Cámara web Full HD con micrófono integrado", 6999, 37),
                    new Producto(null, "Procesador", "CPU Intel i7 de última generación", 34999, 9),
                    new Producto(null, "Motherboard", "Placa base compatible con procesadores modernos", 19999, 13),

                    new Producto(null, "Memoria RAM", "Módulo de RAM DDR4 de 16GB", 7999, 41),
                    new Producto(null, "Fuente Solar", "Panel solar portátil con batería integrada", 24999, 4),
                    new Producto(null, "Control Remoto", "Mando universal para TV y dispositivos", 2499, 50),
                    new Producto(null, "Termostato", "Termostato digital programable", 9999, 22),
                    new Producto(null, "Smart Lock", "Cerradura electrónica con huella digital", 19999, 6),
                    new Producto(null, "Proyector", "Proyector LED con resolución Full HD", 29999, 12),
                    new Producto(null, "Switch Ethernet", "Switch de red de 8 puertos", 5999, 38),
                    new Producto(null, "Reloj Digital", "Reloj inteligente con pantalla AMOLED", 8999, 26),
                    new Producto(null, "Luces LED", "Tiras LED RGB con control remoto", 3999, 55),
                    new Producto(null, "Estabilizador", "Estabilizador de voltaje para dispositivos electrónicos", 15999, 10),

                    new Producto(null, "Cargador Inalámbrico", "Base de carga inalámbrica rápida", 4999, 30),
                    new Producto(null, "HDD Externo", "Disco duro portátil de 4TB", 17999, 15),
                    new Producto(null, "Micrófono", "Micrófono profesional para grabación", 14999, 7),
                    new Producto(null, "Altavoz Inteligente", "Asistente de voz con altavoz integrado", 12999, 20),
                    new Producto(null, "Antena Wi-Fi", "Amplificador de señal inalámbrico", 7999, 33),
                    new Producto(null, "Climatizador", "Aire acondicionado portátil con control remoto", 29999, 5),
                    new Producto(null, "Raspberry Pi", "Kit de desarrollo con Raspberry Pi 4", 12999, 19),
                    new Producto(null, "Capturadora", "Placa de captura de video en alta resolución", 19999, 8),
                    new Producto(null, "Smart Plug", "Enchufe inteligente compatible con asistentes virtuales", 3999, 42),
                    new Producto(null, "Timbre Inteligente", "Timbre con cámara y conexión a Wi-Fi", 14999, 10)
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

            // Se creo mas usuario debido a que un comentario esta relacionado con el usuario y el producto, si tengo 50 comentario algunos usuarios no existe


            Optional<Usuario> lucia = usuarioRepository.findByCorreoElectronico("lucia.rodriguez@email.com");
            Optional<Usuario> andres = usuarioRepository.findByCorreoElectronico("andres.ramirez@email.com");
            Optional<Usuario> maria = usuarioRepository.findByCorreoElectronico("maria.garcia@email.com");
            Optional<Usuario> javier = usuarioRepository.findByCorreoElectronico("javier.martinez@email.com");
            Optional<Usuario> carolina = usuarioRepository.findByCorreoElectronico("carolina.lopez@email.com");
            Optional<Usuario> daniel = usuarioRepository.findByCorreoElectronico("daniel.castro@email.com");
            Optional<Usuario> paola = usuarioRepository.findByCorreoElectronico("paola.herrera@email.com");
            Optional<Usuario> esteban = usuarioRepository.findByCorreoElectronico("esteban.rojas@email.com");
            Optional<Usuario> fernanda = usuarioRepository.findByCorreoElectronico("fernanda.sanchez@email.com");
            Optional<Usuario> camilo = usuarioRepository.findByCorreoElectronico("camilo.torres@email.com");

            Optional<Usuario> gabriela = usuarioRepository.findByCorreoElectronico("gabriela.suarez@email.com");
            Optional<Usuario> raul = usuarioRepository.findByCorreoElectronico("raul.espinosa@email.com");
            Optional<Usuario> veronica = usuarioRepository.findByCorreoElectronico("veronica.mendoza@email.com");
            Optional<Usuario> fabio = usuarioRepository.findByCorreoElectronico("fabio.jimenez@email.com");
            Optional<Usuario> ricardo = usuarioRepository.findByCorreoElectronico("ricardo.vargas@email.com");
            Optional<Usuario> silvia = usuarioRepository.findByCorreoElectronico("silvia.gomez@email.com");
            Optional<Usuario> martin = usuarioRepository.findByCorreoElectronico("martin.aguilar@email.com");
            Optional<Usuario> valentina = usuarioRepository.findByCorreoElectronico("valentina.perez@email.com");
            Optional<Usuario> jose = usuarioRepository.findByCorreoElectronico("jose.ramirez@email.com");
            Optional<Usuario> natalia = usuarioRepository.findByCorreoElectronico("natalia.correa@email.com");


            Optional<Usuario> julio = usuarioRepository.findByCorreoElectronico("julio.fernandez@email.com");
            Optional<Usuario> amanda = usuarioRepository.findByCorreoElectronico("amanda.castro@email.com");
            Optional<Usuario> pedro = usuarioRepository.findByCorreoElectronico("pedro.duarte@email.com");
            Optional<Usuario> isabela = usuarioRepository.findByCorreoElectronico("isabela.medina@email.com");
            Optional<Usuario> oscar = usuarioRepository.findByCorreoElectronico("oscar.rodriguez@email.com");
            Optional<Usuario> cristina = usuarioRepository.findByCorreoElectronico("cristina.vargas@email.com");
            Optional<Usuario> mario = usuarioRepository.findByCorreoElectronico("mario.hernandez@email.com");
            Optional<Usuario> andrea = usuarioRepository.findByCorreoElectronico("andrea.gutierrez@email.com");
            Optional<Usuario> pablo = usuarioRepository.findByCorreoElectronico("pablo.medina@email.com");
            Optional<Usuario> patricia = usuarioRepository.findByCorreoElectronico("patricia.lopez@email.com");


            Optional<Usuario> gonzalo = usuarioRepository.findByCorreoElectronico("gonzalo.espinoza@email.com");
            Optional<Usuario> elena = usuarioRepository.findByCorreoElectronico("elena.herrera@email.com");
            Optional<Usuario> miguel = usuarioRepository.findByCorreoElectronico("miguel.rojas@email.com");
            Optional<Usuario> estefania = usuarioRepository.findByCorreoElectronico("estefania.carrillo@email.com");
            Optional<Usuario> manuel = usuarioRepository.findByCorreoElectronico("manuel.vargas@email.com");
            Optional<Usuario> luisa = usuarioRepository.findByCorreoElectronico("luisa.mejia@email.com");
            Optional<Usuario> victoria = usuarioRepository.findByCorreoElectronico("victoria.torres@email.com");
            Optional<Usuario> federico = usuarioRepository.findByCorreoElectronico("federico.montoya@email.com");


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


            Producto sillaGamer = productos.stream().filter(p -> p.getNombre().equals("Silla Gamer")).findFirst().orElse(null);
            Producto microondas = productos.stream().filter(p -> p.getNombre().equals("Microondas")).findFirst().orElse(null);
            Producto refrigerador = productos.stream().filter(p -> p.getNombre().equals("Refrigerador")).findFirst().orElse(null);
            Producto lavadora = productos.stream().filter(p -> p.getNombre().equals("Lavadora")).findFirst().orElse(null);
            Producto cafetera = productos.stream().filter(p -> p.getNombre().equals("Cafetera")).findFirst().orElse(null);
            Producto drone = productos.stream().filter(p -> p.getNombre().equals("Drone")).findFirst().orElse(null);
            Producto bocinaBluetooth = productos.stream().filter(p -> p.getNombre().equals("Bocina Bluetooth")).findFirst().orElse(null);
            Producto videocamara = productos.stream().filter(p -> p.getNombre().equals("Videocámara")).findFirst().orElse(null);
            Producto tvLed = productos.stream().filter(p -> p.getNombre().equals("TV LED")).findFirst().orElse(null);
            Producto bateriaExterna = productos.stream().filter(p -> p.getNombre().equals("Batería Externa")).findFirst().orElse(null);


            Producto discoDuro = productos.stream().filter(p -> p.getNombre().equals("Disco Duro")).findFirst().orElse(null);
            Producto memoriaUsb = productos.stream().filter(p -> p.getNombre().equals("Memoria USB")).findFirst().orElse(null);
            Producto router = productos.stream().filter(p -> p.getNombre().equals("Router")).findFirst().orElse(null);
            Producto joystick = productos.stream().filter(p -> p.getNombre().equals("Joystick")).findFirst().orElse(null);
            Producto fuentePoder = productos.stream().filter(p -> p.getNombre().equals("Fuente de Poder")).findFirst().orElse(null);
            Producto ssd = productos.stream().filter(p -> p.getNombre().equals("SSD")).findFirst().orElse(null);
            Producto altavoces = productos.stream().filter(p -> p.getNombre().equals("Altavoces")).findFirst().orElse(null);
            Producto webcam = productos.stream().filter(p -> p.getNombre().equals("Webcam")).findFirst().orElse(null);
            Producto procesador = productos.stream().filter(p -> p.getNombre().equals("Procesador")).findFirst().orElse(null);
            Producto motherboard = productos.stream().filter(p -> p.getNombre().equals("Motherboard")).findFirst().orElse(null);


            Producto memoriaRam = productos.stream().filter(p -> p.getNombre().equals("Memoria RAM")).findFirst().orElse(null);
            Producto fuenteSolar = productos.stream().filter(p -> p.getNombre().equals("Fuente Solar")).findFirst().orElse(null);
            Producto controlRemoto = productos.stream().filter(p -> p.getNombre().equals("Control Remoto")).findFirst().orElse(null);
            Producto termostato = productos.stream().filter(p -> p.getNombre().equals("Termostato")).findFirst().orElse(null);
            Producto smartLock = productos.stream().filter(p -> p.getNombre().equals("Smart Lock")).findFirst().orElse(null);
            Producto proyector = productos.stream().filter(p -> p.getNombre().equals("Proyector")).findFirst().orElse(null);
            Producto switchEthernet = productos.stream().filter(p -> p.getNombre().equals("Switch Ethernet")).findFirst().orElse(null);
            Producto relojDigital = productos.stream().filter(p -> p.getNombre().equals("Reloj Digital")).findFirst().orElse(null);
            Producto lucesLed = productos.stream().filter(p -> p.getNombre().equals("Luces LED")).findFirst().orElse(null);
            Producto estabilizador = productos.stream().filter(p -> p.getNombre().equals("Estabilizador")).findFirst().orElse(null);


            Producto cargadorInalambrico = productos.stream().filter(p -> p.getNombre().equals("Cargador Inalámbrico")).findFirst().orElse(null);
            Producto hddExterno = productos.stream().filter(p -> p.getNombre().equals("HDD Externo")).findFirst().orElse(null);
            Producto microfono = productos.stream().filter(p -> p.getNombre().equals("Micrófono")).findFirst().orElse(null);
            Producto altavozInteligente = productos.stream().filter(p -> p.getNombre().equals("Altavoz Inteligente")).findFirst().orElse(null);
            Producto antenaWifi = productos.stream().filter(p -> p.getNombre().equals("Antena Wi-Fi")).findFirst().orElse(null);
            Producto climatizador = productos.stream().filter(p -> p.getNombre().equals("Climatizador")).findFirst().orElse(null);
            Producto raspberryPi = productos.stream().filter(p -> p.getNombre().equals("Raspberry Pi")).findFirst().orElse(null);
            Producto capturadora = productos.stream().filter(p -> p.getNombre().equals("Capturadora")).findFirst().orElse(null);
            Producto smartPlug = productos.stream().filter(p -> p.getNombre().equals("Smart Plug")).findFirst().orElse(null);
            Producto timbreInteligente = productos.stream().filter(p -> p.getNombre().equals("Timbre Inteligente")).findFirst().orElse(null);


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
                        new Comentario(null, smartwatch, diego.get(), "Buena batería; pero la pantalla no es muy brillante.", "18/05/2025"),


                        new Comentario(null, sillaGamer, lucia.get(), "Comodidad espectacular; perfecto para largas sesiones de juego.", "20/05/2025"),
                        new Comentario(null, microondas, andres.get(), "Calienta bien pero hace mucho ruido.", "22/05/2025"),
                        new Comentario(null, refrigerador, maria.get(), "Espacioso y enfría rápido; muy recomendado.", "24/05/2025"),
                        new Comentario(null, lavadora, javier.get(), "Lava bien pero el ciclo es muy largo.", "26/05/2025"),
                        new Comentario(null, cafetera, carolina.get(), "Hace café delicioso; fácil de usar.", "28/05/2025"),
                        new Comentario(null, drone, daniel.get(), "Muy divertido pero la batería dura poco.", "30/05/2025"),
                        new Comentario(null, bocinaBluetooth, paola.get(), "Sonido potente y buena conexión Bluetooth.", "1/06/2025"),
                        new Comentario(null, videocamara, esteban.get(), "Perfecta para grabaciones profesionales.", "3/06/2025"),
                        new Comentario(null, tvLed, fernanda.get(), "Imagen excelente pero el sonido podría mejorar.", "5/06/2025"),
                        new Comentario(null, bateriaExterna, camilo.get(), "Carga bien pero es un poco pesada.", "7/06/2025"),


                        new Comentario(null, discoDuro, gabriela.get(), "Gran capacidad de almacenamiento; funciona rápido.", "9/06/2025"),
                        new Comentario(null, memoriaUsb, raul.get(), "Buen tamaño pero la velocidad de transferencia es baja.", "11/06/2025"),
                        new Comentario(null, router, veronica.get(), "Señal potente; cubre toda la casa.", "13/06/2025"),
                        new Comentario(null, joystick, fabio.get(), "Cómodo y resistente; ideal para gaming.", "15/06/2025"),
                        new Comentario(null, fuentePoder, ricardo.get(), "Funciona bien pero los cables son muy cortos.", "17/06/2025"),
                        new Comentario(null, ssd, silvia.get(), "Velocidad increíble; mi PC va mucho más rápido ahora.", "19/06/2025"),
                        new Comentario(null, altavoces, martin.get(), "Sonido envolvente; muy buena compra.", "21/06/2025"),
                        new Comentario(null, webcam, valentina.get(), "Imagen clara pero el micrófono es deficiente.", "23/06/2025"),
                        new Comentario(null, procesador, jose.get(), "Rendimiento impecable; ideal para gaming y diseño.", "25/06/2025"),
                        new Comentario(null, motherboard, natalia.get(), "Buenas prestaciones pero la instalación fue complicada.", "27/06/2025"),


                        new Comentario(null, memoriaRam, julio.get(), "Expande muy bien el rendimiento del sistema.", "29/06/2025"),
                        new Comentario(null, fuenteSolar, amanda.get(), "Energía confiable pero la batería es pequeña.", "1/07/2025"),
                        new Comentario(null, controlRemoto, pedro.get(), "Fácil de usar; reconoce muchos dispositivos.", "3/07/2025"),
                        new Comentario(null, termostato, isabela.get(), "Regula bien la temperatura; intuitivo de usar.", "5/07/2025"),
                        new Comentario(null, smartLock, oscar.get(), "Seguridad y tecnología en un solo dispositivo.", "7/07/2025"),
                        new Comentario(null, proyector, cristina.get(), "Imagen nítida pero requiere una sala oscura.", "9/07/2025"),
                        new Comentario(null, switchEthernet, mario.get(), "Buena velocidad de conexión; estable.", "11/07/2025"),
                        new Comentario(null, relojDigital, sofia.get(), "Pantalla atractiva pero la batería dura poco.", "13/07/2025"),
                        new Comentario(null, lucesLed, andrea.get(), "Buenas opciones de colores; buen diseño.", "15/07/2025"),
                        new Comentario(null, estabilizador, pablo.get(), "Protege bien contra variaciones de voltaje.", "17/07/2025"),


                        new Comentario(null, cargadorInalambrico, patricia.get(), "Carga rápido pero requiere posicionamiento preciso.", "19/07/2025"),
                        new Comentario(null, hddExterno, gonzalo.get(), "Mucho espacio; resistente y confiable.", "21/07/2025"),
                        new Comentario(null, microfono, elena.get(), "Calidad de sonido profesional; ideal para podcast.", "23/07/2025"),
                        new Comentario(null, altavozInteligente, diego.get(), "Responde bien a comandos de voz; útil en casa.", "25/07/2025"),
                        new Comentario(null, antenaWifi, miguel.get(), "Amplifica bien la señal pero el rango es limitado.", "27/07/2025"),
                        new Comentario(null, climatizador, estefania.get(), "Enfría rápido pero es algo ruidoso.", "29/07/2025"),
                        new Comentario(null, raspberryPi, manuel.get(), "Perfecto para proyectos electrónicos y programación.", "31/07/2025"),
                        new Comentario(null, capturadora, luisa.get(), "Ideal para streaming y grabaciones en alta calidad.", "2/08/2025"),
                        new Comentario(null, smartPlug, victoria.get(), "Muy práctico; fácil de conectar y configurar.", "4/08/2025"),
                        new Comentario(null, timbreInteligente, federico.get(), "Buena cámara; útil para seguridad.", "6/08/2025")
                );

                comentarioRepository.saveAll(comentarios);
                System.out.println("Comentarios cargados exitosamente");
            } else {
                System.out.println("No se pudieron cargar los comentarios: usuarios o productos no encontrados");
            }
        }
    }
}