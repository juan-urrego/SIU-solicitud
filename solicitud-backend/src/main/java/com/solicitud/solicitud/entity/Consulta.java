package com.solicitud.solicitud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue
    private int id;
    private String porque;
    private String estado;

    @OneToOne
    private Precotizacion precotizacion;
    @OneToOne
    private Solicitud solicitud;

}
