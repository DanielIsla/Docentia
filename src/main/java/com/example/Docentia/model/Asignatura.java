package com.example.Docentia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set; // Usamos Set en lugar de List

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String siglas;
    private Integer curso;

    @ManyToMany
    @JoinTable(
            name = "asignatura_horario",
            joinColumns = @JoinColumn(name = "asignatura_id"),
            inverseJoinColumns = @JoinColumn(name = "horario_id")
    )
    @JsonIgnore
    private Set<Horario> horarios = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ciclo_id")
    private Ciclo ciclo;

    @ManyToMany
    @JoinTable(
            name = "DOCENTE_ASIGNATURA",
            joinColumns = @JoinColumn(name = "asignatura_id"),
            inverseJoinColumns = @JoinColumn(name = "docente_id")
    )
    @JsonIgnore
    private Set<Docente> docentesImparten = new HashSet<>();
}