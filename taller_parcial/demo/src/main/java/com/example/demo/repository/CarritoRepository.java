package com.example.demo.repository;

import com.example.demo.model.CarritoCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoCompras, Long> {
    List<CarritoCompras> findByUsuarioIdUsuario(Long idUsuario);
    Optional<CarritoCompras> findByIdCarritoAndUsuarioIdUsuario(Long idCarrito, Long idUsuario);
}