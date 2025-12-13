
package com.example.Docentia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsuntoPropio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate diaSolicitado;
    private String descripcion;
    private LocalDateTime fechaTramitacion;
    private Boolean aprobado;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
}
