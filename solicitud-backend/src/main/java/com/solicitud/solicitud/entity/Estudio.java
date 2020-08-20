package com.solicitud.solicitud.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estudios")
public class Estudio {

    @Id
    @GeneratedValue
    private int id;
    private String firma;
    private String estado;
    private String unidad;

    @OneToOne
    private Director director;
    private String acuerdo;

    @OneToOne
    @JoinColumn(name = "solicitud_id_solicitud")
    private Solicitud solicitud;

}
