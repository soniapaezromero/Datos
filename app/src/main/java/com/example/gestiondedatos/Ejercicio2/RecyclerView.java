package com.example.gestiondedatos.Ejercicio2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gestiondedatos.databinding.ActivityRecyclerViewBinding;

import java.util.ArrayList;
import java.util.List;


public class RecyclerView extends AppCompatActivity {
    private ActivityRecyclerViewBinding binding;
    private AdapterAlarmas adaptadorAlarmas;
    public static List<Alarmas> listaDeAlarmas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        listaDeAlarmas= Alarmas.getListaDeAlarmas();
        refrescarListaDeAlarmas();
        adaptadorAlarmas=new AdapterAlarmas(this,listaDeAlarmas);
        androidx.recyclerview.widget.RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerViewalarmas.setLayoutManager(mLayoutManager);
        binding.recyclerViewalarmas.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerViewalarmas.setAdapter(adaptadorAlarmas);
        binding.recyclerViewalarmas.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), binding.recyclerViewalarmas, new RecyclerTouchListener.ClickListener() {
            public void onClick(View view, int position) {
                Alarmas alarmaSeleccionada = listaDeAlarmas.get(position);
                Intent intent = new Intent(RecyclerView.this, EditarAlarma.class);
                intent.putExtra("posicion", position);
                intent.putExtra("descripcion", alarmaSeleccionada.getDescripcion());
                intent.putExtra("minutos", alarmaSeleccionada.getMinutos());
                intent.putExtra("sonido", alarmaSeleccionada.getSonido());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                final Alarmas alarmaParaEliminar = listaDeAlarmas.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(RecyclerView.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listaDeAlarmas.remove(position);
                                refrescarListaDeAlarmas();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar la web " + alarmaParaEliminar.getDescripcion() + "?")
                        .create();
                dialog.show();

            }
        }));
    }

    public void refrescarListaDeAlarmas() {
        if (adaptadorAlarmas == null) return;
        listaDeAlarmas=Alarmas.getListaDeAlarmas();
        adaptadorAlarmas.setLlistaAlarmas(Alarmas.getListaDeAlarmas());
        adaptadorAlarmas.notifyDataSetChanged();
    }
}