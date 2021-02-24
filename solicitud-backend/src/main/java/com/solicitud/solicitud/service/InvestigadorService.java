package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.repository.InvestigadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class InvestigadorService {

    @Autowired
    InvestigadorRepository investigadorRepository;

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

    public static byte[] decompressBytes (byte [] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try{
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe){

        } catch (DataFormatException e){

        }
        return outputStream.toByteArray();
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()){
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0 , count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toByteArray();
    }

}
