package sv.edu.ues.gestor_proyectos_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.gestor_proyectos_api.dto.ProyectoRequestDTO;
import sv.edu.ues.gestor_proyectos_api.dto.ProyectoResponseDTO;
import sv.edu.ues.gestor_proyectos_api.service.ProyectoService;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;

    @GetMapping
    public List<ProyectoResponseDTO> listar() {
        return proyectoService.listar();
    }

    @GetMapping("/{id}")
    public ProyectoResponseDTO obtener(@PathVariable Long id) {
        return proyectoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<ProyectoResponseDTO> crear(@Valid @RequestBody ProyectoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proyectoService.crear(dto));
    }

    @PutMapping("/{id}")
    public ProyectoResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody ProyectoRequestDTO dto) {
        return proyectoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
