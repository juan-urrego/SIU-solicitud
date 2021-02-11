package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.repository.InvestigadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigadorService {

    @Autowired
    InvestigadorRepository investigadorRepository;

    public Optional<Investigador> getOne(int id){
        return investigadorRepository.findById(id);
    }

    public boolean existsById(final int id){
        return investigadorRepository.existsById(id);
    }

    public List<Investigador> getInvestigador(){
        final List<Investigador> investigadors;
        investigadors = investigadorRepository.findAll();
        return investigadors;
    }

    public void save(final Investigador investigador){
        investigadorRepository.save(investigador);
    }

    public void delete(int id){
        investigadorRepository.deleteById(id);
    }

}
