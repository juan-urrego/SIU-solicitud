package com.solicitud.solicitud.dto;

public class Mail {
    private String nombre;
    private String originEmail;
    private String destinyEmail;
    private String asunto;
    private String cuerpo;


    public Mail(String nombre, String originEmail, String destinyEmail, String asunto, String cuerpo) {
        this.nombre = nombre;
        this.originEmail = originEmail;
        this.destinyEmail = destinyEmail;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public Mail() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOriginEmail() {
        return originEmail;
    }

    public void setOriginEmail(String originEmail) {
        this.originEmail = originEmail;
    }

    public String getDestinyEmail() {
        return destinyEmail;
    }

    public void setDestinyEmail(String destinyEmail) {
        this.destinyEmail = destinyEmail;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
    

}
