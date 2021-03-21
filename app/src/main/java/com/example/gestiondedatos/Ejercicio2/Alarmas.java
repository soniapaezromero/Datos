package com.example.gestiondedatos.Ejercicio2;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Alarmas {
    public  int minutos;
    public  String descripcion;
    public   String sonido;
    public static int TOTALALARMAS=0;

    public static List<Alarmas> listaDeAlarmas = new ArrayList<>();

    public Alarmas(int minutos, String descripcion, String sonido) {
        this.minutos = minutos;
        this.descripcion = descripcion;
        this.sonido = sonido;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSonido() {
        return sonido;
    }

    public void setSonido(String sonido) {
        this.sonido = sonido;
    }

    public static List<Alarmas> getListaDeAlarmas() {
        return listaDeAlarmas;
    }

    public static void setListaDeAlarmas(List<Alarmas> listaDeAlarmas) {
        Alarmas.listaDeAlarmas = listaDeAlarmas;
    }

    public static void agregarAlarma(Context context,Alarmas alarma){
        if(listaDeAlarmas.size() < 5) {
            listaDeAlarmas.add(alarma);
        }else{
            Toast.makeText(context, "Sólopuedes añadir  5 alarmas", Toast.LENGTH_SHORT).show();
        }
    }
    public static void guardarAlarma(Alarmas alarma,int position){
        listaDeAlarmas.get(position).setMinutos(alarma.getMinutos());
        listaDeAlarmas.get(position).setDescripcion(alarma.getDescripcion());
        listaDeAlarmas.get(position).setSonido(alarma.getSonido());

    }

    public static void listaAlarmasEscribir(Context context) throws IOException {
        String listaSeguidas = "";
        for(int i = 0; i < listaDeAlarmas.size(); i++){
            listaSeguidas = listaSeguidas + listaDeAlarmas.get(i).toString();
        }
        if(Memoria.escribirExterna("alarmas.txt", listaSeguidas)){
            Toast.makeText(context, "Fichero añadido.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "No se ha escribir.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String toString() {
        return "Alarmas" +
                " minutos=" + minutos +
                ", descripcion='" + descripcion +
                ", sonido='" + sonido+ "\n";

    }

}
