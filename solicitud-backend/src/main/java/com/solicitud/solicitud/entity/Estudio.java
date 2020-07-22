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

    @OneToOne
    private Director director;
    private String acuerdo;

    @OneToOne
    private Solicitud solicitud;

}
