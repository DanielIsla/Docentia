package com.example.Docentia.controller;

import com.example.Docentia.dto.SolicitarDiaPropioRequest;
import com.example.Docentia.dto.ValidarDiaPropioRequest;
import com.example.Docentia.model.AsuntoPropio;
import com.example.Docentia.service.AsuntoPropioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asuntos-propios")
public class AsuntoPropioController {

    @Autowired
    private AsuntoPropioService asuntoPropioService;

    @PostMapping("/solicitar")
    public AsuntoPropio solicitarDiaPropio(@RequestBody SolicitarDiaPropioRequest request) {
        return asuntoPropioService.solicitarDiaPropio(request);
    }

    @PostMapping("/validar")
    public AsuntoPropio validarDiaPropio(@RequestBody ValidarDiaPropioRequest request) {
        return asuntoPropioService.validarDiaPropio(request);
    }

    @GetMapping("/consultar")
    public List<AsuntoPropio> consultarDiasPropios(@RequestParam Long docenteId, @RequestParam(required = false) String estado) {
        return asuntoPropioService.consultarDiasPropios(docenteId, estado);
    }
}
