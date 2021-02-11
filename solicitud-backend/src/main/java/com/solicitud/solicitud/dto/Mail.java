package com.solicitud.solicitud.dto;

public class Mail {
    private String nombre;
    private String email;
    private String asunto;
    private String cuerpo;

    public Mail(String nombre, String mail, String asunto, String cuerpo) {
        this.nombre = nombre;
        this.email = mail;
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

    public String getEmail() {
        return email;
    }

    public void setEMail(String mail) {
        this.email = mail;
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
