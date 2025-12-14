package com.example.Docentia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String email;
    private String siglas;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany(mappedBy = "docente")
    @JsonIgnore
    private Set<Rol> roles = new HashSet<>();

    @ManyToMany(mappedBy = "docentesImparten")
    @JsonIgnore
    private Set<Asignatura> asignaturasImpartidas = new HashSet<>();

    @OneToMany(mappedBy = "docente")
    @JsonIgnore
    private Set<AsuntoPropio> asuntosPropios = new HashSet<>();

    @OneToMany(mappedBy = "docenteCubridor")
    @JsonIgnore
    private Set<Falta> faltasCubiertas = new HashSet<>();
}