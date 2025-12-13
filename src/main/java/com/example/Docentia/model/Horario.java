
package com.example.Docentia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer dia;
    private Integer hora;
    private String aula;

    @ManyToMany(mappedBy = "horarios")
    @JsonIgnore
    private List<Asignatura> asignaturas;
}
