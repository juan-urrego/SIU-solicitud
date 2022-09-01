package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.LineaEspecificaDto;
import com.solicitud.solicitud.entity.LineaEspecifica;
import com.solicitud.solicitud.repository.LineaEspecificaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class LineaEspecificaService {

    final
    LineaEspecificaRepository lineaEspesificaRepository;

    @Autowired
    public LineaEspecificaService(LineaEspecificaRepository lineaEspesificaRepository) {
        this.lineaEspesificaRepository = lineaEspesificaRepository;
    }

    public LineaEspecifica getLineaEspecificaById(int id){
        return lineaEspesificaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "specific line does not exist with ths id, or not found"));
    }

    public List<LineaEspecifica> getAll(){
        return lineaEspesificaRepository.findAll();
    }

    public void save(final LineaEspecifica lineaEspecifica){
        lineaEspesificaRepository.save(lineaEspecifica);
    }

    public void saveLinea(LineaEspecificaDto lineaEspecificaDto) {
        LineaEspecifica lineaEspecifica = new LineaEspecifica(
                lineaEspecificaDto.getNombre(),
                null
        );
        save(lineaEspecifica);
    }

    public void update(int id, LineaEspecificaDto lineaEspecificaDto) {
        LineaEspecifica lineaEspecifica = getLineaEspecificaById(id);
        lineaEspecifica.setNombre(lineaEspecificaDto.getNombre());
        save(lineaEspecifica);
    }

    public void delete(int id){
        LineaEspecifica lineaEspecifica = getLineaEspecificaById(id);
        lineaEspesificaRepository.delete(lineaEspecifica);
    }
}
