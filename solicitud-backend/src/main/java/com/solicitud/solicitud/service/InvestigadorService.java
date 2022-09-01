package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.InvestigadorDto;
import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.repository.InvestigadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;


@Service
public class InvestigadorService {

    final
    InvestigadorRepository investigadorRepository;

    @Autowired
    public InvestigadorService(InvestigadorRepository investigadorRepository) {
        this.investigadorRepository = investigadorRepository;
    }

    public Investigador getInvestigadorById(int id){
        return investigadorRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "investigator does not exist, or not found"));
    }

    public boolean existByIdentificacion(String identificacion){
        return investigadorRepository.existsInvestigadorByIdentificacion(identificacion);
    }

    public boolean existByEmail(String email) {
        return investigadorRepository.existsByEmail(email);
    }

    public List<Investigador> getAll(){
        return investigadorRepository.findAll();
    }

    public void save(Investigador investigador){
        investigadorRepository.save(investigador);
    }

    public void saveInvestigador(InvestigadorDto investigadorDto) {
        if(existByIdentificacion(investigadorDto.getIdentificacion()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "investigator with this identification, already exists");
        if(existByEmail(investigadorDto.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "investigator with this email, already exists");
        Investigador investigador = new Investigador(investigadorDto.getIdentificacion(), investigadorDto.getNombre(), investigadorDto.getTelefono(), investigadorDto.getEmail());
        save(investigador);
    }

    public void update(int id,InvestigadorDto investigadorDto) {
        Investigador investigador = getInvestigadorById(id);
        if(existByEmail(investigadorDto.getEmail()) && !investigador.getEmail().equals(investigadorDto.getEmail()))
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already exists");
        investigador.setNombre(investigadorDto.getNombre());
        investigador.setTelefono(investigadorDto.getTelefono());
        investigador.setEmail(investigadorDto.getEmail());
        save(investigador);
    }

    public void delete(int id){
        Investigador investigador = getInvestigadorById(id);
        investigadorRepository.delete(investigador);
    }

}
