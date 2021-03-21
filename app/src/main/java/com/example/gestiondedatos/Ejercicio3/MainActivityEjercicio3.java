package com.example.gestiondedatos.Ejercicio3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gestiondedatos.Ejercicio2.RecyclerTouchListener;
import com.example.gestiondedatos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivityEjercicio3 extends AppCompatActivity {
    private List<PáginasWeb> listaDewebs;
    private RecyclerView recyclerView;
    private AdaptadorWeb adaptadorWeb;
    private WebsController websController;
    private FloatingActionButton fabAgregarwebs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ejercicio3);
        recyclerView=findViewById(R.id.recyclerViewWebs);
        fabAgregarwebs=findViewById(R.id.fabAgregarWebs);
        // Creamos RecyclerView
        websController=new WebsController(this);
        listaDewebs = new ArrayList<>();
        for (PáginasWeb pw : listaDewebs) {
            websController.nuevaWeb(pw);

        }
        adaptadorWeb = new AdaptadorWeb(this,listaDewebs);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorWeb);
        //Cargamos los datos de a bases de datos

        refrescarListaDeWebs();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad Editar_Webs.java
                PáginasWeb webSeleccionada= listaDewebs.get(position);
                // Pasar a la actividad Editar_Webs.java
                Intent intent = new Intent(MainActivityEjercicio3.this, Editar_Webs.class);
                intent.putExtra("id", webSeleccionada.getId());
                intent.putExtra("nombre", webSeleccionada.getNombre());
                intent.putExtra("link", webSeleccionada.getId());
                intent.putExtra("email", webSeleccionada.getEmail());
                intent.putExtra("categoria", webSeleccionada.getCategoria());
                startActivity(intent);
            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                //Eliminamos con un alert dialog
                final PáginasWeb webEliminar=listaDewebs.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivityEjercicio3.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                websController.eliminarWeb(webEliminar);
                                 refrescarListaDeWebs();

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


        // Listener del FAB
        fabAgregarwebs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simplemente cambiamos de actividad
                Intent intent = new Intent(v.getContext(),Agregar_webs.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        refrescarListaDeWebs();
    }

    public void refrescarListaDeWebs() {
        if (adaptadorWeb == null) return;
        listaDewebs = websController.obtenerWebs();
        adaptadorWeb.setListaDeWeb(listaDewebs);
        adaptadorWeb.notifyDataSetChanged();
    }
}

