package com.tuguitarra.tuguitarraapp.restservices;

import com.tuguitarra.tuguitarraapp.models.ArchivoContacto;
import com.tuguitarra.tuguitarraapp.models.MensajeContacto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ServicioContacto
{
    public static MensajeContacto nuevoMensaje(Map<String, Object> contacto)
    {
        String errores = comprobarContactoValido(contacto);
        if(!errores.equals(""))
            throw new IllegalArgumentException(errores);

        MensajeContacto mensajeContacto = new MensajeContacto((String) contacto.get("nombre"), (String) contacto.get("email"), (String) contacto.get("mensaje"));
        ArchivoContacto.guardarMensaje(mensajeContacto);
        return mensajeContacto;
    }

    public static String comprobarContactoValido(Map<String, Object> contacto)
    {
        String errores = "";
        if(contacto == null)
            errores += "El formulario no puede estar vacío\n";
        else
        {
            if (contacto.get("nombre").equals(""))
                errores += "El nombre es obligatorio\n";
            if (contacto.get("email").equals(""))
                errores += "El email es obligatorio\n";
            else
            {
                String email = (String) contacto.get("email");
                if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
                    errores += "El email no es válido\n";
            }
            if (contacto.get("mensaje").equals(""))
                errores += "El mensaje es obligatorio\n";

        }

        return errores;
    }
}