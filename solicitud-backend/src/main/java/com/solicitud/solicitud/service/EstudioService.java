package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.repository.EstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstudioService {

    @Autowired
    EstudioRepository estudioRepository;

    public Optional<Estudio> getOne(int id){
        return estudioRepository.findById(id);
    }

    public boolean existsById(final int id){
        return estudioRepository.existsById(id);
    }

    public List<Estudio> getEstudio(){
        final List<Estudio> estudios;
        estudios = estudioRepository.findAll();
        return estudios;
    }

    public void save(final Estudio estudio){
        estudioRepository.save(estudio);
    }

    public void delete(int id){
        estudioRepository.deleteById(id);
    }

}
