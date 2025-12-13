
package com.example.Docentia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaltaHorarioDTO {
    private Long id;
    private LocalDate fecha;
    private String anotacion;
    private String material;
    private Boolean guardiaRealizada;
    private Boolean guardiaAsignada;
    private Integer dia;
    private Integer hora;
    private String aula;
}
