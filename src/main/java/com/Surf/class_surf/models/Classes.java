package com.Surf.class_surf.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Debes proporcionar el nombre de la clase")
    private String nameClass;

    @NotBlank(message = "Describe el nivel de la clase")
    @Pattern(regexp = "^(Nivel 0 a 1|Nivel 2|Nivel 3|Nivel 4)$",
            message = "El nivel debe ser: Principiante: Nivel 0 a 1, Intermedio: Nivel 2, Avanzado: Nivel 3 y Competición: Nivel 4")
    private String level;

    @Future
    private LocalDate date;
}
