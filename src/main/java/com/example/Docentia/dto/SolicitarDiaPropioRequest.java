
package com.example.Docentia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitarDiaPropioRequest {
    private Long docenteId;
    private LocalDate diaSolicitado;
    private String descripcion;
}
