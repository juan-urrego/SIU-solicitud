package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroArgumento;
import com.solicitud.solicitud.repository.ParametroArgumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
@Transactional
public class ParametroArgumentoService {

    final
    ParametroArgumentoRepository parametroArgumentoRepository;

    @Autowired
    public ParametroArgumentoService(ParametroArgumentoRepository parametroArgumentoRepository) {
        this.parametroArgumentoRepository = parametroArgumentoRepository;
    }

    public ParametroArgumento getParametroArgumentoById(int id){
        return parametroArgumentoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "argument parameter does not exist, or not found"));
    }

    public List<ParametroArgumento> getAll(){
        return parametroArgumentoRepository.findAll();
    }

    public void save(final ParametroArgumento parametroArgumento){
        parametroArgumentoRepository.save(parametroArgumento);
    }

    public void saveParametroArgumento(ParametroDto parametroDto) {
        ParametroArgumento parametroArgumento = new ParametroArgumento(parametroDto.getDescripcion());
        parametroArgumentoRepository.save(parametroArgumento);
    }

    public void update(int id, ParametroDto parametroDto) {
        ParametroArgumento parametroArgumento = getParametroArgumentoById(id);
        parametroArgumento.setDescripcion(parametroDto.getDescripcion());
        save(parametroArgumento);
    }

    public void delete(int id){
        ParametroArgumento parametroArgumento = getParametroArgumentoById(id);
        parametroArgumentoRepository.delete(parametroArgumento);
    }
}
