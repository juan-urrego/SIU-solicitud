package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.repository.FileSystemRepository;
import com.solicitud.solicitud.repository.InvestigadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;


import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Service
public class InvestigadorService {

    @Autowired
    InvestigadorRepository investigadorRepository;

    @Autowired
    FileSystemRepository fileSystemRepository;

    public Optional<Investigador> getOne(int id){
        return investigadorRepository.findById(id);
    }

    public Optional<Investigador> getByFirma(String firma){
        return investigadorRepository.findByFirma(firma);
    }


    public boolean existsById(final int id){
        return investigadorRepository.existsById(id);
    }

    public boolean existByIdentificacion(final String identificacion){
        return investigadorRepository.existsInvestigadorByIdentificacion(identificacion);
    }

    public List<Investigador> getInvestigador(){
        final List<Investigador> investigadors;
        investigadors = investigadorRepository.findAll();
        return investigadors;
    }

    public void save(final Investigador investigador){
        investigadorRepository.save(investigador);
    }

    public void delete(int id){
        investigadorRepository.deleteById(id);
    }



    //Guardar imagen en los archivos del sistema y retorna su ubicacion
    public String saveImage(byte[] bytes, String nombreInvestigador) throws Exception{
        return fileSystemRepository.saveImageFileSystem(bytes, nombreInvestigador);
    }

    //encontrar imagen por Id de investigador
    public FileSystemResource findImageById(int id) {
        Investigador investigador = investigadorRepository.findById(id).get();
        return fileSystemRepository.findInFileSystem(investigador.getFirma());
    }

}
