package com.solicitud.solicitud.entity;


import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "directores")
public class Director {

    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private String cargo;
    private String firma;
}
