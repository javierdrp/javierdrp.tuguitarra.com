package com.tuguitarra.tuguitarraapp.controllers;

import com.tuguitarra.tuguitarraapp.restservices.ServicioContacto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ControladorRestContacto
{
    @PostMapping(path = "/api/contacto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> recibirMensajeContacto(
            @RequestBody Map<String, Object> contacto
    )
    {
        try
        {
            ServicioContacto.nuevoMensaje(contacto);
            return ResponseEntity.ok().build();
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
