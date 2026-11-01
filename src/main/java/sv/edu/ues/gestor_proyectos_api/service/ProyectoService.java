package sv.edu.ues.gestor_proyectos_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.ues.gestor_proyectos_api.dto.ProyectoRequestDTO;
import sv.edu.ues.gestor_proyectos_api.dto.ProyectoResponseDTO;
import sv.edu.ues.gestor_proyectos_api.entity.Proyecto;
import sv.edu.ues.gestor_proyectos_api.exception.ResourceNotFoundException;
import sv.edu.ues.gestor_proyectos_api.repository.ProyectoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public List<ProyectoResponseDTO> listar() {
        return proyectoRepository.findAll().stream().map(this::aDTO).toList();
    }

    public ProyectoResponseDTO obtenerPorId(Long id) {
        return aDTO(buscar(id));
    }

    public ProyectoResponseDTO crear(ProyectoRequestDTO dto) {
        Proyecto proyecto = new Proyecto();
        copiarDatos(dto, proyecto);
        return aDTO(proyectoRepository.save(proyecto));
    }

    public ProyectoResponseDTO actualizar(Long id, ProyectoRequestDTO dto) {
        Proyecto proyecto = buscar(id);
        copiarDatos(dto, proyecto);
        return aDTO(proyectoRepository.save(proyecto));
    }

    public void eliminar(Long id) {
        proyectoRepository.delete(buscar(id));
    }

    private Proyecto buscar(Long id) {
        return proyectoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id " + id));
    }

    private void copiarDatos(ProyectoRequestDTO dto, Proyecto proyecto) {
        proyecto.setNombre(dto.getNombre());
        proyecto.setDescripcion(dto.getDescripcion());
        proyecto.setFechaInicio(dto.getFechaInicio());
    }

    private ProyectoResponseDTO aDTO(Proyecto p) {
        return new ProyectoResponseDTO(p.getId(), p.getNombre(), p.getDescripcion(), p.getFechaInicio());
    }
}