package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.GrupoDto;
import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.repository.GrupoRepository;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class GrupoService {

    final
    GrupoRepository grupoRepository;

    @Autowired
    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    public Grupo getGrupoById(int id){
        return grupoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "group does not exist with this id or not found"));
    }

    public boolean existsByCodigoGrupo(String codigoGrupo) {
        return grupoRepository.existsByCodigoGrupo(codigoGrupo);
    }

    public List<Grupo> getAll(){
        return grupoRepository.findAll();
    }

    public void save(final Grupo grupo){
        grupoRepository.save(grupo);
    }

    public void saveGrupo(GrupoDto grupoDto) {
        if (existsByCodigoGrupo(grupoDto.getCodigoGrupo()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "group code already exists");
        Grupo grupo = new Grupo(grupoDto.getCodigoGrupo(), grupoDto.getNombre(), grupoDto.getCodigoColciencia());
        save(grupo);
    }

    public void update(int id, GrupoDto grupoDto) {
        Grupo grupo = getGrupoById(id);
        if (existsByCodigoGrupo(grupoDto.getCodigoGrupo()) && !grupo.getCodigoGrupo().equals(grupoDto.getCodigoGrupo()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "group code already exists");
        grupo.setCodigoGrupo(grupoDto.getCodigoGrupo());
        grupo.setNombre(grupoDto.getNombre());
        grupo.setCodigoColciencia(grupoDto.getCodigoColciencia());
        save(grupo);
    }

    public void delete(int id) throws DataException {
        Grupo grupo = getGrupoById(id);
        grupoRepository.delete(grupo);
    }
}
