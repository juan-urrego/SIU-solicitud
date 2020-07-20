package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.repository.InvestigadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestigadorService {

    @Autowired
    InvestigadorRepository repository;

    public Investigador saveInvestigador(Investigador investigador){
        return repository.save(investigador);
    }

    public List<Investigador> getInvestigadores(){
        return repository.findAll();
    }

    public Investigador getInvestigadorById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteInvestigador(int id){
        repository.deleteById(id);
        return "Investigador Eliminado " + id;
    }

    public Investigador updateInvestigador(Investigador investigadorExistente, int id){
        return repository.findById(id)
                .map(investigador -> {
                    investigador.setIdentificacion(investigadorExistente.getIdentificacion());
                    investigador.setNombre(investigadorExistente.getNombre());
                    investigador.setTelefono(investigadorExistente.getTelefono());
                    investigador.setGrupos(investigadorExistente.getGrupos());
                    investigador.setEmail(investigadorExistente.getEmail());
                    return repository.save(investigador);
                })
                .orElse(null);
    }
}
