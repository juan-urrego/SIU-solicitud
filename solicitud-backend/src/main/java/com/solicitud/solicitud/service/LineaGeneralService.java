package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.LineaGeneral;
import com.solicitud.solicitud.repository.LineaGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LineaGeneralService {

    @Autowired
    LineaGeneralRepository lineaGeneralRepository;

    public Optional<LineaGeneral> getOne(int id){
        return lineaGeneralRepository.findById(id);
    }

    public boolean existsById(final int id){
        return lineaGeneralRepository.existsById(id);
    }

    public List<LineaGeneral> getLineaGeneral(){
        final List<LineaGeneral> lineaGenerals;
        lineaGenerals = lineaGeneralRepository.findAll();
        return lineaGenerals;
    }

    public void save(final LineaGeneral lineaGeneral){
        lineaGeneralRepository.save(lineaGeneral);
    }

    public void delete(int id){
        lineaGeneralRepository.deleteById(id);
    }
}
