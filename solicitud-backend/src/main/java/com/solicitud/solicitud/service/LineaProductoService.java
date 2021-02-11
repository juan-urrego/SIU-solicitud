package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.LineaProducto;
import com.solicitud.solicitud.repository.LineaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LineaProductoService {

    @Autowired
    LineaProductoRepository lineaProductoRepository;

    public Optional<LineaProducto> getOne(int id){
        return lineaProductoRepository.findById(id);
    }

    public boolean existsById(final int id){
        return lineaProductoRepository.existsById(id);
    }

    public List<LineaProducto> getLineaProducto(){
        final List<LineaProducto> lineaProductos;
        lineaProductos = lineaProductoRepository.findAll();
        return lineaProductos;
    }

    public void save(final LineaProducto lineaProducto){
        lineaProductoRepository.save(lineaProducto);
    }

    public void delete(int id){
        lineaProductoRepository.deleteById(id);
    }
}
