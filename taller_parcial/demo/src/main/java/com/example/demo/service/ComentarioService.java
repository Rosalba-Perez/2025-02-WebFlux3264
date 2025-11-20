package com.example.demo.service;

import com.example.demo.model.Comentario;
import com.example.demo.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> findByFechaAfter(String fecha) {
        return comentarioRepository.findByFechaAfter(fecha);
    }

    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }
}