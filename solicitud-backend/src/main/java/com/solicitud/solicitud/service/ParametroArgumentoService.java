package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.ParametroArgumento;
import com.solicitud.solicitud.repository.ParametroArgumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParametroArgumentoService {

    @Autowired
    ParametroArgumentoRepository parametroArgumentoRepository;

    public Optional<ParametroArgumento> getOne(int id){
        return parametroArgumentoRepository.findById(id);
    }

    public boolean existsById(final int id){
        return parametroArgumentoRepository.existsById(id);
    }


    public List<ParametroArgumento> getArgumento(){
        final List<ParametroArgumento> parametroArgumentos;
        parametroArgumentos = parametroArgumentoRepository.findAll();
        return parametroArgumentos;
    }

    public void save(final ParametroArgumento parametroArgumento){
        parametroArgumentoRepository.save(parametroArgumento);
    }

    public void delete(int id){
        parametroArgumentoRepository.deleteById(id);
    }
}
