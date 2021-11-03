package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.ParametroAcuerdo;
import com.solicitud.solicitud.repository.ParametroAcuerdoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParametroAcuerdoService {

    @Autowired
    ParametroAcuerdoRepository parametroAcuerdoRepository;

    public Optional<ParametroAcuerdo> getOne(int id){
        return parametroAcuerdoRepository.findById(id);
    }

    public boolean existsById(final int id){
        return parametroAcuerdoRepository.existsById(id);
    }

    public Optional<ParametroAcuerdo> getByParametro(final byte parametro){
        return parametroAcuerdoRepository.findByParametro(parametro);
    }

    public List<ParametroAcuerdo> getAcuerdo(){
        final List<ParametroAcuerdo> parametroAcuerdos;
        parametroAcuerdos = parametroAcuerdoRepository.findAll();
        return parametroAcuerdos;
    }

    public void save(final ParametroAcuerdo parametroAcuerdo){
        parametroAcuerdoRepository.save(parametroAcuerdo);
    }

    public void delete(int id){
        parametroAcuerdoRepository.deleteById(id);
    }


}
