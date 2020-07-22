package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Director;
import com.solicitud.solicitud.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    @Autowired
    DirectorRepository repository;

    public Director saveDirector(Director director){
        return repository.save(director);
    }

    public List<Director> getDirectors(){
        return repository.findAll();
    }

    public Director getDirectorById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteDirector(int id){
        repository.deleteById(id);
        return "Director Eliminado " + id;
    }

    public Director updateDirector(Director directorExistente, int id){
        return repository.findById(id)
                .map(director -> {
                    director.setNombre(directorExistente.getNombre());
                    director.setCargo(directorExistente.getCargo());
                    director.setFirma(directorExistente.getFirma());
                    return repository.save(director);
                })
                .orElse(null);
    }
}

