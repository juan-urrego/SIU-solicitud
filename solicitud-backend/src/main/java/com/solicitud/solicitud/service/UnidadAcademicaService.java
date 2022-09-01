package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.UnidadAcademica;
import com.solicitud.solicitud.repository.UnidadAcademicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class UnidadAcademicaService {

    final
    UnidadAcademicaRepository unidadAcademicaRepository;

    @Autowired
    public UnidadAcademicaService(UnidadAcademicaRepository unidadAcademicaRepository) {
        this.unidadAcademicaRepository = unidadAcademicaRepository;
    }

    public UnidadAcademica getUnidadAcademicaById(int id){
        return unidadAcademicaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "unit does not exist with this id, or not found"));
    }

    public List<UnidadAcademica> getAll(){
        return unidadAcademicaRepository.findAll();
    }

    public void save(UnidadAcademica unidadAcademica){
        unidadAcademicaRepository.save(unidadAcademica);
    }

    public void saveUnidadAcademica(ParametroDto parametroDto) {
        UnidadAcademica unidadAcademica = new UnidadAcademica(parametroDto.getDescripcion());
        save(unidadAcademica);
    }

    public  void update(int id, ParametroDto parametroDto) {
        UnidadAcademica unidadAcademica = getUnidadAcademicaById(id);
        unidadAcademica.setDescripcion(parametroDto.getDescripcion());
        save(unidadAcademica);
    }

    public void delete(int id){
        UnidadAcademica unidadAcademica = getUnidadAcademicaById(id);
        unidadAcademicaRepository.delete(unidadAcademica);
    }
}
