package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    GrupoRepository  repository;

    public Grupo saveGrupo(Grupo grupo){
        return repository.save(grupo);
    }

    public List<Grupo> getGrupos(){
        return repository.findAll();
    }

    public Grupo getGrupoById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteGrupo(int id){
        repository.deleteById(id);
        return "Grupo Eliminado " + id;
    }

    public Grupo updateGrupo(Grupo grupoExistente, int id){
        return repository.findById(id)
                .map(grupo -> {
                    grupo.setNombre(grupoExistente.getNombre());
                    grupo.setCodCol(grupoExistente.getCodCol());
                    grupo.setInvestigadores(grupoExistente.getInvestigadores());
                    return repository.save(grupo);
                })
                .orElse(null);
    }
}
