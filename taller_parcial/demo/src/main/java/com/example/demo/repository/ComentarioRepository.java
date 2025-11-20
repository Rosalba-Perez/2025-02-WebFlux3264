package com.example.demo.repository;

import com.example.demo.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByFechaAfter(String fecha);
    List<Comentario> findAll();
}