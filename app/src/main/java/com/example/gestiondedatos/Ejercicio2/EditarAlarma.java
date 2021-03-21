package com.example.gestiondedatos.Ejercicio2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.gestiondedatos.Ejercicio3.Editar_Webs;
import com.example.gestiondedatos.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class EditarAlarma extends AppCompatActivity {

    private Button btnEditaarAlarma, btnCancelarEcicion;
    private EditText minutosEdit, descripcionEdit;
    private RadioButton sonido1E, sonido2E, sonido3E, sonido4E, sonido5E;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alarma);
        Bundle extras = getIntent().getExtras();
        // Si no hay datos (cosa rara) salimos
        if (extras == null) {
            finish();
            return;
        }
        minutosEdit = findViewById(R.id.minutosEdit);
        descripcionEdit = findViewById(R.id.descripcionEdit);
        btnEditaarAlarma = findViewById(R.id.btnEditarAlarma);
        btnCancelarEcicion = findViewById(R.id.btnCancelarEdicion);
        sonido1E = findViewById(R.id.sonido1Edit);
        sonido2E = findViewById(R.id.sonido2Edit);
        sonido3E= findViewById(R.id.sonido3Edit);
        sonido4E = findViewById(R.id.sonido4Edit);
        sonido5E= findViewById(R.id.sonido5Edit);
         int minutosAlarma = extras.getInt("minutos");
        String desctripcionAlarma=extras.getString("descripcion");
        String sonidoAlarma= extras.getString("sonido");
        int poscionAlarma=extras.getInt("posicion");
        Alarmas alarma= new Alarmas(minutosAlarma,desctripcionAlarma,sonidoAlarma);
        minutosEdit.setText(String.valueOf(alarma.getMinutos()));
        descripcionEdit.setText(alarma.getDescripcion());
         if(alarma.getSonido().equals(sonido1E.getText().toString())){
             sonido1E.setChecked(true);
             sonido2E.setChecked(false);
             sonido3E.setChecked(false);
             sonido4E.setChecked(false);
             sonido5E.setChecked(false);
         }
        if(alarma.getSonido().equals(sonido2E.getText().toString()) ){
            sonido1E.setChecked(false);
            sonido2E.setChecked(true);
            sonido3E.setChecked(false);
            sonido4E.setChecked(false);
            sonido5E.setChecked(false);
        }
        if(alarma.getSonido().equals(sonido3E.getText().toString()) ){
            sonido1E.setChecked(false);
            sonido2E.setChecked(false);
            sonido3E.setChecked(true);
            sonido4E.setChecked(false);
            sonido5E.setChecked(false);
        }
        if(alarma.getSonido().equals(sonido1E.getText().toString() )){
            sonido1E.setChecked(false);
            sonido2E.setChecked(false);
            sonido3E.setChecked(false);
            sonido4E.setChecked(true);
            sonido5E.setChecked(false);
        }
        if(alarma.getSonido().equals(sonido5E.getText().toString())){
            sonido1E.setChecked(false);
            sonido2E.setChecked(false);
            sonido3E.setChecked(false);
            sonido4E.setChecked(false);
            sonido5E.setChecked(true);
        }

        // agrego la vista


        sonido1E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonido2E.setChecked(false);
                sonido3E.setChecked(false);
                sonido4E.setChecked(false);
                sonido5E.setChecked(false);
            }
        });
        sonido2E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonido1E.setChecked(false);
                sonido3E.setChecked(false);
                sonido4E.setChecked(false);
                sonido5E.setChecked(false);
            }
        });
        sonido3E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonido1E.setChecked(false);
                sonido2E.setChecked(false);
                sonido4E.setChecked(false);
                sonido5E.setChecked(false);
            }
        });
        sonido4E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonido1E.setChecked(false);
                sonido2E.setChecked(false);
                sonido3E.setChecked(false);
                sonido5E.setChecked(false);
            }
        });
        sonido5E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonido1E.setChecked(false);
                sonido2E.setChecked(false);
                sonido3E.setChecked(false);
                sonido4E.setChecked(false);
            }
        });


        // Agregar listener del botón de guardar
        btnEditaarAlarma.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos
                minutosEdit.setError(null);
                descripcionEdit.setError(null);
                int minutosnuevos =Integer.parseInt(minutosEdit.getText().toString()) ;
                String nuevaDescripcion = descripcionEdit.getText().toString();
                String nuevoSonido ="";

                if ("".equals(minutosnuevos)) {
                    minutosEdit.setError("Escribe el minutos ");
                    minutosEdit.requestFocus();
                    return;
                } else if ("".equals(nuevaDescripcion)) {
                    descripcionEdit.setError("Escribe el link de la web");
                    descripcionEdit.requestFocus();
                    return;
                } else if (sonido1E.isChecked()) {
                    nuevoSonido = "brightmorning";
                } else if (sonido2E.isChecked()) {
                    nuevoSonido = "cuckooclock";
                }else if (sonido3E.isChecked()) {
                    nuevoSonido = "earlytwilight";
                }else if (sonido4E.isChecked()) {
                    nuevoSonido = "gentlebreeze";
                }else if (sonido5E.isChecked()) {
                    nuevoSonido = "sunshower";
                }


                // Ya pasó la validación
                Alarmas alarmaeditada = new Alarmas(minutosnuevos,nuevaDescripcion , nuevoSonido);
                Alarmas.guardarAlarma(alarmaeditada,poscionAlarma);

                try {
                    Alarmas.listaAlarmasEscribir(EditarAlarma.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                onBackPressed();
            }

        });

        // El de cancelar simplemente cierra la actividad
        btnCancelarEcicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


}