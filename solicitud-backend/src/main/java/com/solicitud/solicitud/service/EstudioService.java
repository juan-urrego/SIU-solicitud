package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.enums.Estado;
import com.solicitud.solicitud.repository.EstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudioService {

    @Autowired
    EstudioRepository repository;


    public Estudio saveEstudio(Estudio estudio){
        estudio.setEstado(updateEstado(estudio));
        return repository.save(estudio);
    }

    public List<Estudio> getEstudios(){
        return repository.findAll();
    }

    public Estudio getEstudioById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteEstudio(int id){
        repository.deleteById(id);
        return "Estudio Eliminado " + id;
    }

    public Estudio updateEstudio(Estudio estudioExistente, int id){
        return repository.findById(id)
                .map(estudio -> {
                    estudio.setFirma(estudioExistente.getFirma());
                    estudio.setAcuerdo(estudioExistente.getAcuerdo());
                    estudio.setDirector(estudioExistente.getDirector());
                    estudio.setSolicitud(estudioExistente.getSolicitud());
                    estudio.setEstado(updateEstado(estudioExistente));
                    return repository.save(estudio);
                })
                .orElse(null);
    }

    public Estado updateEstado(Estudio estudio) {
        if(estudio.getFirma() != null) {
            return Estado.FIRMADO;
        }else{
            return Estado.CREADA;
        }
    }
}
