
package com.example.Docentia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Falta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String anotacion;
    private String material;
    private Boolean guardiaRealizada;
    private Boolean guardiaAsignada;

    @ManyToOne
    @JoinColumn(name = "docente_ausente_id")
    private Docente docenteAusente;

    @ManyToOne
    @JoinColumn(name = "docente_cubridor_id")
    private Docente docenteCubridor;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;
}
