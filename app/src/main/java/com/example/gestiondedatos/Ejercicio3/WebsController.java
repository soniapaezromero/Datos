package com.example.gestiondedatos.Ejercicio3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class WebsController {
    private AyudandanteWeb ayudandanteWeb;
    private String NOMBRE_TABLA ="webs";

    public WebsController(Context contexto) {
        ayudandanteWeb = new AyudandanteWeb(contexto);
    }


    public int eliminarWeb(PáginasWeb web) {

        SQLiteDatabase baseDeDatos = ayudandanteWeb.getWritableDatabase();
        String[] argumentos = {String.valueOf(web.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevaWeb(PáginasWeb web) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudandanteWeb.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", web.getNombre());
        valoresParaInsertar.put("link", web.getLink());
        valoresParaInsertar.put("email", web.getEmail());
        valoresParaInsertar.put("categoria", web.getCategoria());
        valoresParaInsertar.put("imagen", web.getImagen());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(PáginasWeb webeditada) {
        SQLiteDatabase baseDeDatos = ayudandanteWeb.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", webeditada.getNombre());
        valoresParaActualizar.put("link", webeditada.getLink());
        valoresParaActualizar.put("email", webeditada.getEmail());
        valoresParaActualizar.put("categoria", webeditada.getCategoria());
        valoresParaActualizar.put("imagen", webeditada.getImagen());
        // where id...
        String campoParaActualizar = "id = ?";
        // ... = idWeb
        String[] argumentosParaActualizar = {String.valueOf(webeditada.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<PáginasWeb> obtenerWebs() {
        ArrayList<PáginasWeb> webs = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudandanteWeb.getReadableDatabase();
        // SELECT nombre, link,email,categoria ,imagen,id
        String[] columnasAConsultar = {"nombre", "link","email","categoria", "imagen","id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,//from webs
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            return webs;
        }
        if (!cursor.moveToFirst()) return webs;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de webs
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, link,email,categoria,imagen,id
            String nombreObtenidoDeBD = cursor.getString(0);
            String linkObtenidaDeBD = cursor.getString(1);
            String emailObtenidaDeBD = cursor.getString(2);
            String categriaObtenidaDeBD = cursor.getString(3);
            int imagenObtenidaDeBD = cursor.getInt(4);
            long idweb= cursor.getLong(5);
            PáginasWeb websObtenidaDeBD = new PáginasWeb(nombreObtenidoDeBD, linkObtenidaDeBD,emailObtenidaDeBD,categriaObtenidaDeBD,imagenObtenidaDeBD,idweb);
            webs.add(websObtenidaDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de paginas webs :)
        cursor.close();
        return webs;
    }
    }


