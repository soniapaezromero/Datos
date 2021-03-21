package com.example.gestiondedatos.Ejercicio4;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Loader;
import android.app.LoaderManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gestiondedatos.Ejercicio2.RecyclerTouchListener;
import com.example.gestiondedatos.Ejercicio3.Agregar_webs;
import com.example.gestiondedatos.Ejercicio3.MainActivityEjercicio3;
import com.example.gestiondedatos.MainActivity;
import com.example.gestiondedatos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivityEjercicio4 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private List<Webs> listaDewebs;
    private RecyclerView recyclerView;
    private AdapterListWeb adaptadorWeb;
    private FloatingActionButton fabAgregarwebs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ejercicio4);
        recyclerView = findViewById(R.id.recyclerViewWebsList);
        fabAgregarwebs = findViewById(R.id.fabAgregarWebsList);
        // Creamos RecyclerView
        listaDewebs = new ArrayList<>();
        adaptadorWeb = new AdapterListWeb(this, listaDewebs);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorWeb);
        getLoaderManager().initLoader(0, null, this);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                EditText nombrewebText,linkWebText,emailWebTeXt,categoriaWEbText;
                ImageView imagenWeb;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityEjercicio4.this);;
                LayoutInflater inflater = LayoutInflater.from(MainActivityEjercicio4.this);
                View dialogo1 = inflater.inflate(R.layout.dialog_webs_details, null);
                nombrewebText = dialogo1.findViewById(R.id.nombreAdd);
                linkWebText= dialogo1.findViewById(R.id.linkAdd);
                emailWebTeXt=dialogo1.findViewById(R.id.emailAdd);
                categoriaWEbText=dialogo1.findViewById(R.id.categoriaAdd);
                imagenWeb=dialogo1.findViewById(R.id.imagenAdd);
                builder.setView(dialogo1);
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String nombre = nombrewebText.getText().toString();
                        String link = linkWebText.getText().toString();
                        String email = emailWebTeXt.getText().toString();
                        String categoria = categoriaWEbText.getText().toString();
                        int imagenWeb;
                        String imagenCategoria = categoria.toLowerCase();
                        if(imagenCategoria.contains("viaj")|| imagenCategoria.contains("hot")|| imagenCategoria.contains("turismo")){
                            imagenWeb = R.drawable.imagen3_min;
                        }else if(imagenCategoria.contains("rrss") || imagenCategoria.contains("red")){
                            imagenWeb = R.drawable.imagen4_min;
                        }else if(imagenCategoria.contains("salu") || imagenCategoria.contains("dent")||imagenCategoria.contains("medi")){
                            imagenWeb = R.drawable.imagen2_min;
                        }else if(imagenCategoria.contains("sho")|| imagenCategoria.contains("tien")|| imagenCategoria.contains("comerc")){
                            imagenWeb = R.drawable.imagen6_min;
                        }else if(imagenCategoria.contains("rest") || imagenCategoria.contains("bar") || imagenCategoria.contains("comi")){
                            imagenWeb = R.drawable.imagen5_min;
                        }else{
                            imagenWeb = R.drawable.imagen1_min;
                        }
                        Webs webEditada = new Webs(nombre, link, email, categoria, imagenWeb);
                        guardarCambios(webEditada);
                        restartLoader();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setTitle("Editar");
                builder.create().show();

            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                final Webs  webEliminar=listaDewebs.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivityEjercicio4.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                eliminar(webEliminar);
                                restartLoader();

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar a la web " + webEliminar.getNombre() + "?")
                        .create();
                dialog.show();

            }
        }));
        fabAgregarwebs.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText nombrewebText,linkWebText,emailWebTeXt,categoriaWEbText;
            ImageView imagenWeb;
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityEjercicio4.this);;
            LayoutInflater inflater = LayoutInflater.from(MainActivityEjercicio4.this);
            View dialogo = inflater.inflate(R.layout.dialog_webs_details, null);

            nombrewebText = dialogo.findViewById(R.id.nombreAdd);
            linkWebText= dialogo.findViewById(R.id.linkAdd);
            emailWebTeXt=dialogo.findViewById(R.id.emailAdd);
            categoriaWEbText=dialogo.findViewById(R.id.categoriaAdd);
            imagenWeb=dialogo.findViewById(R.id.imagenAdd);
            builder.setView(dialogo);
            builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String nombre = nombrewebText.getText().toString();
                    String link= linkWebText.getText().toString();
                    String email= emailWebTeXt.getText().toString();
                    String categoria= categoriaWEbText.getText().toString();
                    int  imagen;

                    String imagenCategoria = categoria.toLowerCase();
                    if(imagenCategoria.contains("viaj")|| imagenCategoria.contains("hot")|| imagenCategoria.contains("turismo")){
                        imagen = R.drawable.imagen3_min;
                    }else if(imagenCategoria.contains("rrss") || imagenCategoria.contains("red")){
                        imagen = R.drawable.imagen4_min;
                    }else if(imagenCategoria.contains("salu") || imagenCategoria.contains("dent")||imagenCategoria.contains("medi")){
                        imagen = R.drawable.imagen2_min;
                    }else if(imagenCategoria.contains("sho")|| imagenCategoria.contains("tien")|| imagenCategoria.contains("comerc")){
                        imagen = R.drawable.imagen6_min;
                    }else if(imagenCategoria.contains("rest") || imagenCategoria.contains("bar") || imagenCategoria.contains("comi")){
                        imagen = R.drawable.imagen5_min;
                    }else{
                        imagen = R.drawable.imagen1_min;
                    }
                    Webs webAñadida=new Webs(nombre,link,email,categoria,imagen);
                    insertarWeb(webAñadida);
                    restartLoader();
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setTitle("Añadir");
            builder.create().show();
        }

            private Dialog requireActivity() {
                return null;
            }
        });


}


    public void insertarWeb(Webs web) {
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", web.getNombre());
        valoresParaInsertar.put("link", web.getLink());
        valoresParaInsertar.put("email", web.getEmail());
        valoresParaInsertar.put("categoria", web.getCategoria());
        valoresParaInsertar.put("imagen", web.getImagen());
        Uri webUri = getContentResolver().insert(WebsProvider.CONTENT_URI, valoresParaInsertar);
        Toast.makeText(this, "Creada WEbt " + web.getNombre(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, "URI " + webUri, Toast.LENGTH_LONG).show();

    }
    public void guardarCambios(Webs webeditada) {
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
        getContentResolver().update(WebsProvider.CONTENT_URI,valoresParaActualizar,campoParaActualizar,argumentosParaActualizar);
        Toast.makeText(this, "Editada WEb " + webeditada.getNombre(), Toast.LENGTH_LONG).show();

    }
    public void  eliminar(Webs web){
        String campoParaActualizar = "id = ?";
        String[] argumentosEliminar = {String.valueOf(web.getId())};
        getContentResolver().delete(WebsProvider.CONTENT_URI,campoParaActualizar,argumentosEliminar);
        Toast.makeText(this, "Eliminada WEb " , Toast.LENGTH_LONG).show();

    }
    private void restartLoader() {
        getLoaderManager().restartLoader(0, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, WebsProvider.CONTENT_URI, null, null, null, null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        List<Webs> lista = new ArrayList<>();
        if (cursor == null) {
            Toast.makeText(MainActivityEjercicio4.this, "Error cursor nulo ", Toast.LENGTH_SHORT).show();
        }
        if (cursor.moveToFirst()){
            do {
                int index1 = cursor.getColumnIndex(WebsHelper.NOMBRE_WEB);
                int index2 = cursor.getColumnIndex(WebsHelper.LINK_WEB);
                int index3 = cursor.getColumnIndex(WebsHelper.EMAIL_WEB);
                int index4 = cursor.getColumnIndex(WebsHelper.CATEGORIA_WEB);
                int index5 = cursor.getColumnIndex(WebsHelper.IMAGEN_WEB);
                String nombre = cursor.getString(index1);
                String link = cursor.getString(index2);
                String email = cursor.getString(index3);
                String categoria = cursor.getString(index4);
                int imagen = cursor.getInt(index5);
                Webs webSeleccionada = new Webs(nombre, link, email, categoria, imagen);
                lista.add(webSeleccionada);
            } while (cursor.moveToNext());
            cursor.close();
            adaptadorWeb = new AdapterListWeb(MainActivityEjercicio4.this, lista);
            recyclerView.setAdapter(adaptadorWeb);
        }


    }



    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }



}
