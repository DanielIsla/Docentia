
package com.example.Docentia.controller;

import com.example.Docentia.dto.DocenteDTO;
import com.example.Docentia.model.Docente;
import com.example.Docentia.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public List<DocenteDTO> getAllDocentes() {
        return docenteService.getAllDocentes();
    }

    @GetMapping("/ordenados-apellidos")
    public List<Docente> getAllDocentesSortedByApellidos() {
        return docenteService.getAllDocentesSortedByApellidos();
    }

    @GetMapping("/departamento/{nombreDepartamento}")
    public List<Docente> getDocentesByDepartamento(@PathVariable String nombreDepartamento) {
        return docenteService.getDocentesByDepartamento(nombreDepartamento);
    }
}
