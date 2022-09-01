package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.ProyectoDto;
import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.entity.Proyecto;
import com.solicitud.solicitud.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ProyectoService {

    final
    ProyectoRepository proyectoRepository;

    final
    GrupoService grupoService;

    @Autowired
    public ProyectoService(ProyectoRepository proyectoRepository, GrupoService grupoService) {
        this.proyectoRepository = proyectoRepository;
        this.grupoService = grupoService;
    }

    public Proyecto getProyectoById(int id){
        return proyectoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "project does not exist with this id, or not found"));
    }

    public boolean existsByCodigo(String codigoGrupo){
        return proyectoRepository.existsByCodigoProyecto(codigoGrupo);
    }

    public List<Proyecto> getAll(){
        return proyectoRepository.findAll();
    }

    public void save(Proyecto proyecto){
        proyectoRepository.save(proyecto);
    }

    public void saveProyecto(ProyectoDto proyectoDto) {
        if(existsByCodigo(proyectoDto.getCodigoProyecto()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "project with this code, already exists");
        Grupo grupo = grupoService.getGrupoById(proyectoDto.getGrupo());
        Proyecto proyecto = new Proyecto(proyectoDto.getNombre(), proyectoDto.getCodigoProyecto(), proyectoDto.getCentroCostos(), grupo);
        save(proyecto);
    }

    public void update(int id, ProyectoDto proyectoDto) {
        Grupo grupo = grupoService.getGrupoById(proyectoDto.getGrupo());
        Proyecto proyecto = getProyectoById(id);
        if(existsByCodigo(proyectoDto.getCodigoProyecto()) && !proyecto.getCodigoProyecto().equals(proyectoDto.getCodigoProyecto()))
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "project already exists");
        proyecto.setNombre(proyectoDto.getNombre());
        proyecto.setCodigoProyecto(proyectoDto.getCodigoProyecto());
        proyecto.setCentroCostos(proyectoDto.getCentroCostos());
        proyecto.setGrupo(grupo);
        save(proyecto);
    }
    public void delete(int id){
        Proyecto proyecto = getProyectoById(id);
        proyectoRepository.delete(proyecto);
    }
}
