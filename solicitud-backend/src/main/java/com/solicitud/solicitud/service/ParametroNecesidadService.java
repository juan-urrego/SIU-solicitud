package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroNecesidad;
import com.solicitud.solicitud.repository.ParametroNecesidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ParametroNecesidadService {

    final
    ParametroNecesidadRepository parametroNecesidadRepository;

    @Autowired
    public ParametroNecesidadService(ParametroNecesidadRepository parametroNecesidadRepository) {
        this.parametroNecesidadRepository = parametroNecesidadRepository;
    }

    public ParametroNecesidad getParametroNecesidadById(int id){
        return parametroNecesidadRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "necessity  parameter does not exist, or not found"));
    }

    public ParametroNecesidad getParametroActivo(byte parametro){
        return parametroNecesidadRepository.findByParametro(parametro).orElse(new ParametroNecesidad("", (byte) 0));
    }

    public List<ParametroNecesidad> getAll(){
        return parametroNecesidadRepository.findAll();
    }

    public void save(final ParametroNecesidad parametroNecesidad){
        parametroNecesidadRepository.save(parametroNecesidad);
    }

    public void saveParametro(ParametroDto parametroDto) {
        ParametroNecesidad parametroNecesidad = new ParametroNecesidad(parametroDto.getDescripcion(), (byte) 0);
        if (getAll().isEmpty())
            parametroNecesidad.setParametro((byte) 1);
        save(parametroNecesidad);
    }

    public void update(int id, ParametroDto parametroDto) {
        ParametroNecesidad parametroNecesidad = getParametroNecesidadById(id);
        parametroNecesidad.setDescripcion(parametroDto.getDescripcion());
        save(parametroNecesidad);
    }

    public void activeParameter(int id) {
        ParametroNecesidad parametroNecesidad = getParametroNecesidadById(id);
        ParametroNecesidad parametroNecesidadActivo = getParametroActivo((byte) 1);
        if (parametroNecesidadActivo.getParametro() == (byte) 1) {
            parametroNecesidadActivo.setParametro((byte) 0);
            save(parametroNecesidadActivo);
        }
        parametroNecesidad.setParametro((byte) 1);
        save(parametroNecesidad);
    }

    public void disableParameterActive() {
        ParametroNecesidad parametroNecesidadActivo = getParametroActivo((byte) 1);
        if  (parametroNecesidadActivo.getParametro() == (byte) 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "there is not an active parameter");
        parametroNecesidadActivo.setParametro((byte) 0);
        save(parametroNecesidadActivo);
    }

    public void delete(int id){
        ParametroNecesidad parametroNecesidad = getParametroNecesidadById(id);
        parametroNecesidadRepository.delete(parametroNecesidad);
    }
}
