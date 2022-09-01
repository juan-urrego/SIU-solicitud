package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroAcuerdo;
import com.solicitud.solicitud.repository.ParametroAcuerdoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ParametroAcuerdoService {

    final
    ParametroAcuerdoRepository parametroAcuerdoRepository;

    @Autowired
    public ParametroAcuerdoService(ParametroAcuerdoRepository parametroAcuerdoRepository) {
        this.parametroAcuerdoRepository = parametroAcuerdoRepository;
    }

    public ParametroAcuerdo getParametroAcuerdoById(int id){
        return parametroAcuerdoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "agreement parameter does not exist, or not found"));
    }

    public ParametroAcuerdo getParametroActivo(byte parametro){
        return parametroAcuerdoRepository.findByParametro(parametro).orElse(new ParametroAcuerdo("", (byte) 0));
    }

    public List<ParametroAcuerdo> getAll(){
        return parametroAcuerdoRepository.findAll();
    }

    public void save(ParametroAcuerdo parametroAcuerdo){
        parametroAcuerdoRepository.save(parametroAcuerdo);
    }

    public void saveParametro(ParametroDto parametroDto) {
        ParametroAcuerdo parametroAcuerdo = new ParametroAcuerdo(parametroDto.getDescripcion(), (byte) 0);
        if (getAll().isEmpty())
            parametroAcuerdo.setParametro((byte) 1);
        save(parametroAcuerdo);
    }

    public void update(int id, ParametroDto parametroDto) {
        ParametroAcuerdo parametroAcuerdo = getParametroAcuerdoById(id);
        parametroAcuerdo.setDescripcion(parametroDto.getDescripcion());
        save(parametroAcuerdo);
    }

    public void activeParameter(int id) {
        ParametroAcuerdo parametroAcuerdo = getParametroAcuerdoById(id);
        ParametroAcuerdo parametroAcuerdoActivo = getParametroActivo((byte) 1);
        if (parametroAcuerdoActivo.getParametro() == (byte) 1) {
            parametroAcuerdoActivo.setParametro((byte) 0);
            save(parametroAcuerdoActivo);
        }
        parametroAcuerdo.setParametro((byte) 1);
        save(parametroAcuerdo);
    }

    public void disableParameterActive() {
        ParametroAcuerdo parametroAcuerdoActivo = getParametroActivo((byte) 1);
        if  (parametroAcuerdoActivo.getParametro() == (byte) 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "there is not an active parameter");
        parametroAcuerdoActivo.setParametro((byte) 0);
        save(parametroAcuerdoActivo);
    }

    public void delete(int id){
        ParametroAcuerdo parametroAcuerdo = getParametroAcuerdoById(id);
        parametroAcuerdoRepository.delete(parametroAcuerdo);
    }


}
