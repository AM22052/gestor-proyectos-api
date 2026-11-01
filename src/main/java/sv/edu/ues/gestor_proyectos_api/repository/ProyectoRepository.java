package sv.edu.ues.gestor_proyectos_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.gestor_proyectos_api.entity.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}