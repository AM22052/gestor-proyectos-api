package com.poo.gestion.controller;

import com.poo.gestion.dto.TareaDTO;
import com.poo.gestion.service.TareaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;

    @GetMapping
    public List<TareaDTO> listar() {
        return tareaService.listar();
    }

    @GetMapping("/{id}")
    public TareaDTO obtener(@PathVariable Long id) {
        return tareaService.obtener(id);
    }

    @PostMapping
    public ResponseEntity<TareaDTO> crear(@Valid @RequestBody TareaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.crear(dto));
    }

    @PutMapping("/{id}")
    public TareaDTO actualizar(@PathVariable Long id, @Valid @RequestBody TareaDTO dto) {
        return tareaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
