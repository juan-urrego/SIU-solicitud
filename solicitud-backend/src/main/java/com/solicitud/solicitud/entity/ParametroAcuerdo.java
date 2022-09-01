package com.solicitud.solicitud.entity;

import javax.persistence.*;

@Entity
@Table(name = "ma_parametros_acuerdo")
public class ParametroAcuerdo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "par_acu_id")
    private int id;
    @Column(name = "par_acu_descipcion", nullable = false)
    private String descripcion;
    @Column(name = "par_acu_parametro", nullable = false)
    private byte parametro;



    public ParametroAcuerdo(String descripcion, byte parametro) {
        this.descripcion = descripcion;
        this.parametro = parametro;
    }

    public ParametroAcuerdo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte getParametro() {
        return parametro;
    }

    public void setParametro(byte parametro) {
        this.parametro = parametro;
    }
}
