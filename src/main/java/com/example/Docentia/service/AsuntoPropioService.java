
package com.example.Docentia.service;

import com.example.Docentia.dto.SolicitarDiaPropioRequest;
import com.example.Docentia.dto.ValidarDiaPropioRequest;
import com.example.Docentia.model.AsuntoPropio;
import com.example.Docentia.model.Docente;
import com.example.Docentia.repository.AsuntoPropioRepository;
import com.example.Docentia.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
public class AsuntoPropioService {

    @Autowired
    private AsuntoPropioRepository asuntoPropioRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    private static final int MAX_APROBADOS_POR_DIA = 2;

    public AsuntoPropio solicitarDiaPropio(SolicitarDiaPropioRequest request) {
        //Si el día está en el futuro, se puede solicitar
        if (request.getDiaSolicitado().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("El día solicitado no puede ser en el pasado.");
        }

        //Si aún no se ha disfrutado un día propio en este trimestre, se puede solicitar
        if (hasEnjoyedDayInQuarter(request.getDocenteId(), request.getDiaSolicitado())) {
            throw new IllegalArgumentException("El docente ya ha disfrutado de un día propio en este trimestre.");
        }

        //Checkeamos que el docente exista mediante el id de la petición
        Docente docente = docenteRepository.findById(request.getDocenteId())
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado."));

        //Creamos el asunto propio
        AsuntoPropio asuntoPropio = new AsuntoPropio();
        asuntoPropio.setDocente(docente);
        asuntoPropio.setDiaSolicitado(request.getDiaSolicitado());
        asuntoPropio.setDescripcion(request.getDescripcion());
        asuntoPropio.setFechaTramitacion(LocalDateTime.now());
        asuntoPropio.setEstado("PENDIENTE_VALIDAR");
        asuntoPropio.setAprobado(false);

        //Guardamos el asunto propio
        return asuntoPropioRepository.save(asuntoPropio);
    }

    public AsuntoPropio validarDiaPropio(ValidarDiaPropioRequest request) {
        //Checkeamos que el asunto propio exista mediante el id de la petición
        AsuntoPropio asuntoPropio = asuntoPropioRepository.findById(request.getAsuntoPropioId())
                .orElseThrow(() -> new IllegalArgumentException("Asunto propio no encontrado."));

        //Si en la petición se indica que el dia se debe aceptar, procedemos
        if (request.isAceptado()) {
            if (canApprove(asuntoPropio.getDiaSolicitado())) {
                asuntoPropio.setEstado("ACEPTADO");
                asuntoPropio.setAprobado(true);
            } else {
                throw new IllegalStateException("Límite de días propios aprobados para esta fecha alcanzado.");
            }
        } else {
            asuntoPropio.setEstado("RECHAZADO");
            asuntoPropio.setAprobado(false);
        }

        return asuntoPropioRepository.save(asuntoPropio);
    }

    //Consultar dia propio según el id del docente
    public List<AsuntoPropio> consultarDiasPropios(Long docenteId, String estado) {
        if (estado != null) {
            return asuntoPropioRepository.findByDocenteIdAndEstado(docenteId, estado);
        }
        return asuntoPropioRepository.findByDocenteId(docenteId);
    }

    //Recupera si se ha disfrutado un día propio en este trimestre
    private boolean hasEnjoyedDayInQuarter(Long docenteId, LocalDate diaSolicitado) {
        Month month = diaSolicitado.getMonth();
        int quarter = (month.getValue() - 1) / 3 + 1;
        LocalDate startDate = LocalDate.of(diaSolicitado.getYear(), (quarter - 1) * 3 + 1, 1);
        LocalDate endDate = startDate.plusMonths(3).minusDays(1);

        List<AsuntoPropio> asuntos = asuntoPropioRepository.findByDocenteIdAndDiaSolicitadoBetweenAndEstado(docenteId, startDate, endDate, "ACEPTADO");
        return !asuntos.isEmpty();
    }

    //Si ese día se han aprobado menos de 2 días propios, se puede aprobar
    private boolean canApprove(LocalDate diaSolicitado) {
        List<AsuntoPropio> aprobados = asuntoPropioRepository.findByDiaSolicitadoAndEstado(diaSolicitado, "ACEPTADO");
        return aprobados.size() < MAX_APROBADOS_POR_DIA;
    }
}
