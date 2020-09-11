package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.enums.Estado;
import com.solicitud.solicitud.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService {

    @Autowired
    SolicitudRepository repository;
    @Autowired
    EstudioService estudioService;
    @Autowired 
    ConsultaService consultaService;

    public Solicitud saveSolicitud(Solicitud solicitud) {        
        solicitud.setEstado(updateEstado(solicitud));
        return repository.save(solicitud);
    }

    public List<Solicitud> getSolicitudes() {
        return repository.findAll();
    }

    public Solicitud getSolicitudById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteSolicitud(int id) {
        repository.deleteById(id);
        return "Solicitud Eliminada " + id;
    }

    public Solicitud updateSolicitud(Solicitud newSolicitud, int id) {
        if(newSolicitud.getEstado() != Estado.DOCUMENTADA) {
            newSolicitud.setEstado(updateEstado(newSolicitud));
        }
        return repository.findById(id).map(solicitud -> {
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
        }).orElse(null);
    }

    public Estado updateEstado(Solicitud solicitud) {
        if(solicitud.getRubro() != "" && solicitud.getSubrubro() != "" && solicitud.getFinanciador() != ""
                && solicitud.getCentroCostos() != "") {
            return Estado.VERIFICADA;
        }else{
            return Estado.CREADA;
        }
    }

    public String createDocuments(int id){
        Solicitud solicitud = repository.findById(id).orElse(null);
        if (solicitud.getEstado() == Estado.VERIFICADA){
            Estudio estudio = new Estudio(null, Estado.CREADA, null, null, null, solicitud);
            Consulta consulta = new Consulta(null, null, Estado.CREADA, null, solicitud);
            consultaService.saveConsulta(consulta);
            estudioService.saveEstudio(estudio);
            solicitud.setEstado(Estado.DOCUMENTADA);
            updateSolicitud(solicitud, solicitud.getIdSolicitud());
            return "Estudio previo y consulta de precios creados correctamente";
        }
        return "No se pudieron crear los documentos";
    }
}