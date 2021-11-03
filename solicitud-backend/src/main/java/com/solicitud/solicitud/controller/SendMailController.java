package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Mail;

import com.solicitud.solicitud.service.SendMailService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SendMailController {
    

    @Autowired
    private SendMailService sendMailService;


    @GetMapping("/mensaje")
    public String index(){
        return "funcionando email";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody Mail mail) {
        String message = mail.getCuerpo() +"\n\n Datos de contacto: " + "\nNombre: " + mail.getNombre() + "\nE-mail: " + mail.getOriginEmail();
        sendMailService.sendMail("juanurrego21277@gmail.com",mail.getDestinyEmail(), mail.getAsunto(),message);
        return "Mail enviado";
    }
}
