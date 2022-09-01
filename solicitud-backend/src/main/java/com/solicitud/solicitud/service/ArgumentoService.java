package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Argumento;
import com.solicitud.solicitud.repository.ArgumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ArgumentoService {

    final
    ArgumentoRepository argumentoRepository;

    @Autowired
    public ArgumentoService(ArgumentoRepository argumentoRepository) {
        this.argumentoRepository = argumentoRepository;
    }

    public Argumento getArgumentoById(int id){
        return argumentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "group does not exist with this id or not found"));
    }

    public List<Argumento> getAll(){
        return argumentoRepository.findAll();

    }

    public void save(Argumento argumento){
        argumentoRepository.save(argumento);
    }

    public void delete(int id){
        argumentoRepository.deleteById(id);
    }

}
