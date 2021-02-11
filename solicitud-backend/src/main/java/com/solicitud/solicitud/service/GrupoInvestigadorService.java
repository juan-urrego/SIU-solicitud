package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.GrupoInvestigador;
import com.solicitud.solicitud.repository.GrupoInvestigadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GrupoInvestigadorService {

    @Autowired
    GrupoInvestigadorRepository grupoInvestigadorRepository;

    public Optional<GrupoInvestigador> getOne(int id){
        return grupoInvestigadorRepository.findById(id);
    }

    public List<GrupoInvestigador> getGrupoInvestigador(){
        final List<GrupoInvestigador> grupoInvestigadors;
        grupoInvestigadors = grupoInvestigadorRepository.findAll();
        return grupoInvestigadors;
    }

    public void save(final GrupoInvestigador grupoInvestigador){
        grupoInvestigadorRepository.save(grupoInvestigador);
    }

    public void delete(int id){
        grupoInvestigadorRepository.deleteById(id);
    }
}
