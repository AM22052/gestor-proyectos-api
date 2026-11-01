package com.poo.gestion.controller;

import com.poo.gestion.dto.ProyectoDTO;
import com.poo.gestion.dto.TareaDTO;
import com.poo.gestion.service.ProyectoService;
import com.poo.gestion.service.TareaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;
    private final TareaService tareaService;

    @GetMapping
    public List<ProyectoDTO> listar() {
        return proyectoService.listar();
    }

    @GetMapping("/{id}")
    public ProyectoDTO obtener(@PathVariable Long id) {
        return proyectoService.obtener(id);
    }

    @GetMapping("/{id}/tareas")
    public List<TareaDTO> tareasDelProyecto(@PathVariable Long id) {
        return tareaService.listarPorProyecto(id);
    }

    @PostMapping
    public ResponseEntity<ProyectoDTO> crear(@Valid @RequestBody ProyectoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proyectoService.crear(dto));
    }

    @PutMapping("/{id}")
    public ProyectoDTO actualizar(@PathVariable Long id, @Valid @RequestBody ProyectoDTO dto) {
        return proyectoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
