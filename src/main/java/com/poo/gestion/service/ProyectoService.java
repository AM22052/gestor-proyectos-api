package com.poo.gestion.service;

import com.poo.gestion.dto.ProyectoDTO;
import com.poo.gestion.exception.ResourceNotFoundException;
import com.poo.gestion.model.Proyecto;
import com.poo.gestion.repository.ProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    @Transactional(readOnly = true)
    public List<ProyectoDTO> listar() {
        return proyectoRepository.findAll().stream().map(this::aDTO).toList();
    }

    @Transactional(readOnly = true)
    public ProyectoDTO obtener(Long id) {
        return aDTO(buscar(id));
    }

    @Transactional
    public ProyectoDTO crear(ProyectoDTO dto) {
        Proyecto proyecto = Proyecto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .build();
        return aDTO(proyectoRepository.save(proyecto));
    }

    @Transactional
    public ProyectoDTO actualizar(Long id, ProyectoDTO dto) {
        Proyecto proyecto = buscar(id);
        proyecto.setNombre(dto.getNombre());
        proyecto.setDescripcion(dto.getDescripcion());
        proyecto.setFechaInicio(dto.getFechaInicio());
        proyecto.setFechaFin(dto.getFechaFin());
        return aDTO(proyectoRepository.save(proyecto));
    }

    @Transactional
    public void eliminar(Long id) {
        proyectoRepository.delete(buscar(id));
    }

    private Proyecto buscar(Long id) {
        return proyectoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id " + id));
    }

    private ProyectoDTO aDTO(Proyecto p) {
        return ProyectoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .fechaInicio(p.getFechaInicio())
                .fechaFin(p.getFechaFin())
                .build();
    }
}
