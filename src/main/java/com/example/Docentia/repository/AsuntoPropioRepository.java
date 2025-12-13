
package com.example.Docentia.repository;

import com.example.Docentia.model.AsuntoPropio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsuntoPropioRepository extends JpaRepository<AsuntoPropio, Long> {
    List<AsuntoPropio> findByDocenteIdAndDiaSolicitadoBetweenAndEstado(Long docenteId, LocalDate startDate, LocalDate endDate, String estado);
    List<AsuntoPropio> findByDiaSolicitadoAndEstado(LocalDate diaSolicitado, String estado);
    List<AsuntoPropio> findByDocenteIdAndEstado(Long docenteId, String estado);
    List<AsuntoPropio> findByDocenteId(Long docenteId);
}
