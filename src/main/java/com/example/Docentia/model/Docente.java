
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
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String email;
    private String siglas;
    private String tipoFuncionario;
    private Integer antiguedadCentro;
    private Double notaOposicion;
    private String codigoProfesor;
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @OneToMany(mappedBy = "docente")
    @JsonIgnore
    private List<AsuntoPropio> asuntosPropios;

    @OneToMany(mappedBy = "docenteCubridor")
    @JsonIgnore
    private List<Falta> faltasCubiertas;
}
