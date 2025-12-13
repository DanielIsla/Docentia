
package com.example.Docentia.service;

import com.example.Docentia.dto.FaltaHorarioDTO;
import com.example.Docentia.model.Falta;
import com.example.Docentia.repository.FaltaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaltaService {

    @Autowired
    private FaltaRepository faltaRepository;

    public List<FaltaHorarioDTO> getAllFaltasWithHorario() {
        return faltaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private FaltaHorarioDTO convertToDto(Falta falta) {
        return new FaltaHorarioDTO(
                falta.getId(),
                falta.getFecha(),
                falta.getAnotacion(),
                falta.getMaterial(),
                falta.getGuardiaRealizada(),
                falta.getGuardiaAsignada(),
                falta.getHorario().getDia(),
                falta.getHorario().getHora(),
                falta.getHorario().getAula()
        );
    }
}
