package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService {

    @Autowired
    SolicitudRepository repository;

    public Solicitud saveSolicitud(Solicitud solicitud){
        return repository.save(solicitud);
    }

    public List<Solicitud> getSolicitudes(){
        return repository.findAll();
    }

    public Solicitud getSolicitudById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteSolicitud(int id){
        repository.deleteById(id);
        return "Solicitud Eliminada " + id;
    }

    public Solicitud updateSolicitud(Solicitud newSolicitud, int id){
        return repository.findById(id)
                .map(solicitud -> {
                    solicitud.setGrupo(newSolicitud.getGrupo());
                    solicitud.setInvestigador(newSolicitud.getInvestigador());
                    solicitud.setCargo(newSolicitud.getCargo());
                    solicitud.setNombreProyecto(newSolicitud.getNombreProyecto());
                    solicitud.setFecha(newSolicitud.getFecha());
                    solicitud.setRubro(newSolicitud.getRubro());
                    solicitud.setSubrubro(newSolicitud.getSubrubro());
                    solicitud.setFinanciador(newSolicitud.getFinanciador());
                    solicitud.setCentroCostos(newSolicitud.getCentroCostos());
                    solicitud.setNecesidad(newSolicitud.getNecesidad());
                    solicitud.setDescripcion(newSolicitud.getDescripcion());
                    solicitud.setValor(newSolicitud.getValor());
                    solicitud.setVerificacion(newSolicitud.getVerificacion());
                    solicitud.setPrecotizaciones((newSolicitud.getPrecotizaciones()));
                    solicitud.setObservacion(newSolicitud.getObservacion());
                    solicitud.setEstado(newSolicitud.getEstado());
                    return repository.save(solicitud);
                })
                .orElse(null);
    }
}
