package com.tuguitarra.tuguitarraapp.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchivoContacto
{
    static List<MensajeContacto> mensajes = new ArrayList<>();

    public static String CSV_FILE = "tuguitarra-api/datos/contacto.csv";

    public static void guardarMensaje(MensajeContacto contacto)
    {
        mensajes.add(contacto);
        try
        {
            File file = new File(CSV_FILE);
            file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.format("\"%s\",\"%s\",\"%s\"\n", contacto.nombre(), contacto.email(), contacto.mensaje()));
            System.out.println(contacto.nombre());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
