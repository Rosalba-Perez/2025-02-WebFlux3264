package com.example.demo.controller;

import com.example.demo.model.Comentario;
import com.example.demo.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Comentario>> listarComentarios(@RequestParam String fechaDesde) {
        List<Comentario> comentarios = comentarioService.findByFechaAfter(fechaDesde);
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping
    public ResponseEntity<List<Comentario>> listarTodosComentarios() {
        List<Comentario> comentarios = comentarioService.findAll();
        return ResponseEntity.ok(comentarios);
    }
}