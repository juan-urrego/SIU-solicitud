package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.LineaEspecifica;
import com.solicitud.solicitud.entity.LineaGeneral;
import com.solicitud.solicitud.repository.LineaEspecificaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LineaEspecificaService {

    @Autowired
    LineaEspecificaRepository lineaEspesificaRepository;

    public Optional<LineaEspecifica> getOne(int id){
        return lineaEspesificaRepository.findById(id);
    }

    public boolean existsById(final int id){
        return lineaEspesificaRepository.existsById(id);
    }

    public List<LineaEspecifica> getLineaEspecifica(){
        final List<LineaEspecifica> lineaEspecificas;
        lineaEspecificas = lineaEspesificaRepository.findAll();
        return lineaEspecificas;
    }

    public void save(final LineaEspecifica lineaEspecifica){
        lineaEspesificaRepository.save(lineaEspecifica);
    }

    public void delete(int id){
        lineaEspesificaRepository.deleteById(id);
    }
}
