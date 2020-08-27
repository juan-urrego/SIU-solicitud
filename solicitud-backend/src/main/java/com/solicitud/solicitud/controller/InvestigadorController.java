package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.service.InvestigadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class InvestigadorController {

    @Autowired
    InvestigadorService service;

    @PostMapping("/investigadores/agregar")
    public Investigador addInvestigador(@RequestBody Investigador investigador){
        return  service.saveInvestigador(investigador);
    }

    @GetMapping("/investigadores")
    public List<Investigador> findAllInvestigador(){
        return  service.getInvestigadores();
    }

    @GetMapping("/investigadores/{id}")
    public Investigador findInvestigadorById(@PathVariable int id){
        return service.getInvestigadorById(id);
    }

    @PutMapping("/investigadores/actualizar/{id}")
    public Investigador updateInvestigador(@RequestBody Investigador investigador, @PathVariable int id){
        return service.updateInvestigador(investigador, id);
    }

    @DeleteMapping("/investigadores/eliminar/{id}")
    public String deleteInvestigador (@PathVariable int id){
        return service.deleteInvestigador(id);
    }
}
