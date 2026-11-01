package com.poo.gestion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareaDTO {

    private Long id;

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 100, message = "El titulo no puede superar 100 caracteres")
    private String titulo;

    @Size(max = 500, message = "La descripcion no puede superar 500 caracteres")
    private String descripcion;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "PENDIENTE|EN_PROGRESO|COMPLETADA",
            message = "El estado debe ser PENDIENTE, EN_PROGRESO o COMPLETADA")
    private String estado;

    private LocalDate fechaLimite;

    @NotNull(message = "El proyectoId es obligatorio")
    private Long proyectoId;
}
