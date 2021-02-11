package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.ParametroConsulta;
import com.solicitud.solicitud.repository.ParametroConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParametroConsultaService {

    @Autowired
    ParametroConsultaRepository parametroConsultaRepository;

    public Optional<ParametroConsulta> getOne(int id){
        return parametroConsultaRepository.findById(id);
    }

    public boolean existsById(final int id){
        return parametroConsultaRepository.existsById(id);
    }

    public Optional<ParametroConsulta> getByParametro(final byte parametro){
        return parametroConsultaRepository.findByParametro(parametro);
    }

    public List<ParametroConsulta> getConsulta(){
        final List<ParametroConsulta> parametroConsultas;
        parametroConsultas = parametroConsultaRepository.findAll();
        return parametroConsultas;
    }

    public void save(final ParametroConsulta parametroConsulta){
        parametroConsultaRepository.save(parametroConsulta);
    }

    public void delete(int id){
        parametroConsultaRepository.deleteById(id);
    }
}
