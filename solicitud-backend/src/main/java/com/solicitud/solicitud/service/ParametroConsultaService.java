package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroConsulta;
import com.solicitud.solicitud.repository.ParametroConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ParametroConsultaService {

    final
    ParametroConsultaRepository parametroConsultaRepository;

    @Autowired
    public ParametroConsultaService(ParametroConsultaRepository parametroConsultaRepository) {
        this.parametroConsultaRepository = parametroConsultaRepository;
    }

    public ParametroConsulta getParametroConsultaById(int id){
        return parametroConsultaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "consult parameter does not exist, or not found"));
    }

    public ParametroConsulta getParametroActivo(byte parametro){
        return parametroConsultaRepository.findByParametro(parametro).orElse(new ParametroConsulta("", (byte) 0));
    }

    public List<ParametroConsulta> getAll(){
        return parametroConsultaRepository.findAll();
    }

    public void save(final ParametroConsulta parametroConsulta){
        parametroConsultaRepository.save(parametroConsulta);
    }

    public void saveParametro(ParametroDto parametroDto) {
        ParametroConsulta parametroConsulta = new ParametroConsulta(parametroDto.getDescripcion(), (byte) 0);
        if (getAll().isEmpty())
            parametroConsulta.setParametro((byte) 1);
        save(parametroConsulta);
    }

    public void update(int id, ParametroDto parametroDto) {
        ParametroConsulta parametroConsulta = getParametroConsultaById(id);
        parametroConsulta.setDescripcion(parametroDto.getDescripcion());
        save(parametroConsulta);
    }

    public void activeParameter(int id) {
        ParametroConsulta parametroConsulta = getParametroConsultaById(id);
        ParametroConsulta parametroConsultaActivo = getParametroActivo((byte) 1);
        if (parametroConsultaActivo.getParametro() == (byte) 1) {
            parametroConsultaActivo.setParametro((byte) 0);
            save(parametroConsultaActivo);
        }
        parametroConsulta.setParametro((byte) 1);
        save(parametroConsulta);
    }
    public void disableParameterActive() {
        ParametroConsulta parametroConsultaActivo = getParametroActivo((byte) 1);
        if  (parametroConsultaActivo.getParametro() == (byte) 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "there is not an active parameter");
        parametroConsultaActivo.setParametro((byte) 0);
        save(parametroConsultaActivo);
    }

    public void delete(int id){
        ParametroConsulta parametroConsulta = getParametroConsultaById(id);
        parametroConsultaRepository.delete(parametroConsulta);
    }
}
