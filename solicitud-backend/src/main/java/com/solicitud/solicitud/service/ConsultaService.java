package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.enums.Estado;
import com.solicitud.solicitud.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository repository;

  
    public Consulta saveConsulta(Consulta consulta){
        consulta.setEstado(updateEstado(consulta));
        return repository.save(consulta);
    }

    public List<Consulta> getConsultas(){
        return repository.findAll();
    }

    public Consulta getConsultaById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteConsulta(int id){
        repository.deleteById(id);
        return "Consulta Eliminado " + id;
    }

    public Consulta updateConsulta(Consulta consultaExistente, int id){
        return repository.findById(id)
                .map(consulta -> {
                    consulta.setPorque(consultaExistente.getPorque());
                    consulta.setPrecotizacion(consultaExistente.getPrecotizacion());
                    consulta.setSolicitud(consultaExistente.getSolicitud());
                    consulta.setEstado(updateEstado(consultaExistente));
                    return repository.save(consulta);
                })
                .orElse(null);
    }

    public Estado updateEstado(Consulta consulta) {
        if(consulta.getPrecotizacion() != null && consulta.getPorque() != "" &&consulta.getAcuerdo() != "") {
            return Estado.DILIGENCIADO;
        }else{
            return Estado.CREADA;
        }
    }
}
