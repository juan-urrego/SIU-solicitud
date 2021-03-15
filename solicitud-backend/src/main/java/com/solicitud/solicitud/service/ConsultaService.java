package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    public Optional<Consulta> getOne(int id){
        return consultaRepository.findById(id);
    }

    public boolean existsById(final int id){
        return consultaRepository.existsById(id);
    }


    public List<Consulta> getConsulta(){
        final List<Consulta> consultas;
        consultas = consultaRepository.findAll();
        return consultas;
    }

    public void save(final Consulta consulta){
        consultaRepository.save(consulta);
    }

    public void delete(int id){
        consultaRepository.deleteById(id);
    }

}
