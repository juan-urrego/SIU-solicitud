package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.LineaGeneralDto;
import com.solicitud.solicitud.entity.LineaEspecifica;
import com.solicitud.solicitud.entity.LineaGeneral;
import com.solicitud.solicitud.repository.LineaGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class LineaGeneralService {

    final
    LineaGeneralRepository lineaGeneralRepository;

    @Autowired
    public LineaGeneralService(LineaGeneralRepository lineaGeneralRepository) {
        this.lineaGeneralRepository = lineaGeneralRepository;
    }

    public LineaGeneral getLineaGeneralById(int id){
        return lineaGeneralRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "general line does not exist with this id, or not found"));
    }

    public List<LineaGeneral> getAll(){
        return lineaGeneralRepository.findAll();
    }

    public void save(LineaGeneral lineaGeneral){
        lineaGeneralRepository.save(lineaGeneral);
    }

    public void saveLineaGeneral(LineaGeneralDto lineaGeneralDto) {
        LineaGeneral lineaGeneral = new LineaGeneral(lineaGeneralDto.getNombre());
        Set<LineaEspecifica> lineaEspecificas = new HashSet<>();
        lineaGeneralDto.getLineaEspecificas().forEach(lineaEspecificax -> {
            LineaEspecifica lineaEspecifica = new LineaEspecifica(lineaEspecificax.getNombre(), lineaGeneral);
            lineaEspecificas.add(lineaEspecifica);
        });
        lineaGeneral.setLineaEspecificas(lineaEspecificas);
        save(lineaGeneral);
    }

    public void update(int id, LineaGeneralDto lineaGeneralDto) {
        LineaGeneral lineaGeneral = getLineaGeneralById(id);
        lineaGeneral.setNombre(lineaGeneralDto.getNombre());
        Set<LineaEspecifica> lineaEspecificas = new HashSet<>();
        lineaGeneralDto.getLineaEspecificas().forEach(lineaEspecificax -> {
            LineaEspecifica lineaEspecifica = new LineaEspecifica(lineaEspecificax.getNombre(), lineaGeneral);
            lineaEspecificas.add(lineaEspecifica);
        });
        lineaGeneral.setLineaEspecificas(lineaEspecificas);
        save(lineaGeneral);
    }

    public void delete(int id){
        LineaGeneral lineaGeneral = getLineaGeneralById(id);
        lineaGeneralRepository.delete(lineaGeneral);
    }
}
