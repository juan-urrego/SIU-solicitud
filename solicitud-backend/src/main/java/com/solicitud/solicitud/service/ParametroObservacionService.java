package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroObservacion;
import com.solicitud.solicitud.repository.ParametroObservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ParametroObservacionService {

    final
    ParametroObservacionRepository parametroObservacionRepository;

    @Autowired
    public ParametroObservacionService(ParametroObservacionRepository parametroObservacionRepository) {
        this.parametroObservacionRepository = parametroObservacionRepository;
    }

    public ParametroObservacion getParametroObservacionById(int id){
        return parametroObservacionRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "observation parameter does not exist, or not found"));
    }

    public ParametroObservacion getParametroActivo(byte parametro){
        return parametroObservacionRepository.findByParametro(parametro).orElse(new ParametroObservacion("", (byte) 0));
    }

    public List<ParametroObservacion> getAll(){
       return parametroObservacionRepository.findAll();
    }

    public void save(final ParametroObservacion parametroObservacion){
        parametroObservacionRepository.save(parametroObservacion);
    }

    public void saveParametro(ParametroDto parametroDto) {
        ParametroObservacion parametroObservacion = new ParametroObservacion(parametroDto.getDescripcion(), (byte) 0);
        if (getAll().isEmpty())
            parametroObservacion.setParametro((byte) 1);
        save(parametroObservacion);
    }

    public void update(int id, ParametroDto parametroDto) {
        ParametroObservacion parametroObservacion = getParametroObservacionById(id);
        parametroObservacion.setDescripcion(parametroDto.getDescripcion());
        save(parametroObservacion);
    }

    public void activeParameter(int id) {
        ParametroObservacion parametroObservacion = getParametroObservacionById(id);
        ParametroObservacion parametroObservacionActivo = getParametroActivo((byte) 1);
        if (parametroObservacionActivo.getParametro() == (byte) 1) {
            parametroObservacionActivo.setParametro((byte) 0);
            save(parametroObservacionActivo);
        }
        parametroObservacion.setParametro((byte) 1);
        save(parametroObservacion);
    }

    public void disableParameterActive() {
        ParametroObservacion parametroObservacionActivo = getParametroActivo((byte) 1);
        if  (parametroObservacionActivo.getParametro() == (byte) 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "there is not an active parameter");
        parametroObservacionActivo.setParametro((byte) 0);
        save(parametroObservacionActivo);
    }

    public void delete(int id){
        ParametroObservacion parametroObservacion = getParametroObservacionById(id);
        parametroObservacionRepository.delete(parametroObservacion);
    }
}
