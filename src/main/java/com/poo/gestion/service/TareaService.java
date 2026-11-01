package com.poo.gestion.service;

import com.poo.gestion.dto.TareaDTO;
import com.poo.gestion.exception.ResourceNotFoundException;
import com.poo.gestion.model.Proyecto;
import com.poo.gestion.model.Tarea;
import com.poo.gestion.repository.ProyectoRepository;
import com.poo.gestion.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TareaService {

    private final TareaRepository tareaRepository;
    private final ProyectoRepository proyectoRepository;

    @Transactional(readOnly = true)
    public List<TareaDTO> listar() {
        return tareaRepository.findAll().stream().map(this::aDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<TareaDTO> listarPorProyecto(Long proyectoId) {
        if (!proyectoRepository.existsById(proyectoId)) {
            throw new ResourceNotFoundException("Proyecto no encontrado con id " + proyectoId);
        }
        return tareaRepository.findByProyectoId(proyectoId).stream().map(this::aDTO).toList();
    }

    @Transactional(readOnly = true)
    public TareaDTO obtener(Long id) {
        return aDTO(buscar(id));
    }

    @Transactional
    public TareaDTO crear(TareaDTO dto) {
        Proyecto proyecto = buscarProyecto(dto.getProyectoId());
        Tarea tarea = Tarea.builder()
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .estado(dto.getEstado())
                .fechaLimite(dto.getFechaLimite())
                .proyecto(proyecto)
                .build();
        return aDTO(tareaRepository.save(tarea));
    }

    @Transactional
    public TareaDTO actualizar(Long id, TareaDTO dto) {
        Tarea tarea = buscar(id);
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setEstado(dto.getEstado());
        tarea.setFechaLimite(dto.getFechaLimite());
        tarea.setProyecto(buscarProyecto(dto.getProyectoId()));
        return aDTO(tareaRepository.save(tarea));
    }

    @Transactional
    public void eliminar(Long id) {
        tareaRepository.delete(buscar(id));
    }

    private Tarea buscar(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con id " + id));
    }

    private Proyecto buscarProyecto(Long id) {
        return proyectoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id " + id));
    }

    private TareaDTO aDTO(Tarea t) {
        return TareaDTO.builder()
                .id(t.getId())
                .titulo(t.getTitulo())
                .descripcion(t.getDescripcion())
                .estado(t.getEstado())
                .fechaLimite(t.getFechaLimite())
                .proyectoId(t.getProyecto().getId())
                .build();
    }
}
