package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Argumento;
import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.repository.ArgumentoRepository;
import com.solicitud.solicitud.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ArgumentoService {

    @Autowired
    ArgumentoRepository argumentoRepository;

    public Optional<Argumento> getOne(int id){
        return argumentoRepository.findById(id);
    }

    public boolean existsById(final int id){
        return argumentoRepository.existsById(id);
    }

    public List<Argumento> getArgumento(){
        final List<Argumento> argumentos;
        argumentos = argumentoRepository.findAll();
        return argumentos;
    }

    public void saveAll(final Set<Argumento> argumentos) {
        argumentoRepository.saveAll(argumentos);
    }

    public void save(final Argumento argumento){
        argumentoRepository.save(argumento);
    }

    public void delete(int id){
        argumentoRepository.deleteById(id);
    }

}
