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
import android.widget.Toast;

import com.example.gestiondedatos.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class AddAlarma extends AppCompatActivity {
    private Button btnAgregarAlarma, btnCancelarAlarma;
    private EditText minutosText, descripcionText, sonidoText;
    private RadioButton sonido1, sonido2, sonido3, sonido4, sonido5;
    public List<Alarmas> listaAñadicas;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarma);
        listaAñadicas= new ArrayList<>();
        // agrego la vista
        minutosText = findViewById(R.id.minutos);
        descripcionText = findViewById(R.id.descripcion);
        btnAgregarAlarma = findViewById(R.id.btnAgregarAlarma);
        btnCancelarAlarma = findViewById(R.id.btnCancelarNuevaAlarma);
        sonido1 = findViewById(R.id.radiosonido1);
        sonido2 = findViewById(R.id.radiosonido2);
        sonido3 = findViewById(R.id.radiosonido3);
        sonido4 = findViewById(R.id.radiosonido4);
        sonido5 = findViewById(R.id.radiosonido5);

        sonido1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonido1.setChecked(true);
                sonido2.setChecked(false);
                sonido3.setChecked(false);
                sonido4.setChecked(false);
                sonido5.setChecked(false);
            }
        });
        sonido2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonido2.setChecked(true);
                sonido1.setChecked(false);
                sonido3.setChecked(false);
                sonido4.setChecked(false);
                sonido5.setChecked(false);
            }
        });
        sonido3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonido3.setChecked(true);
                sonido1.setChecked(false);
                sonido2.setChecked(false);
                sonido4.setChecked(false);
                sonido5.setChecked(false);
            }
        });
        sonido4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonido4.setChecked(true);
                sonido1.setChecked(false);
                sonido2.setChecked(false);
                sonido3.setChecked(false);
                sonido5.setChecked(false);
            }
        });
        sonido5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonido5.setChecked(true);
                sonido1.setChecked(false);
                sonido2.setChecked(false);
                sonido3.setChecked(false);
                sonido4.setChecked(false);
            }
        });



        // Agregar listener del botón de guardar
        btnAgregarAlarma.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos
                minutosText.setError(null);
                descripcionText.setError(null);
                int minutos = Integer.parseInt(minutosText.getText().toString());
                String descripcion = descripcionText.getText().toString();
                String sonido = "";

                if ("".equals(minutos)) {
                    minutosText.setError("Escribe el minutos ");
                    minutosText.requestFocus();
                    return;
                }
                if ("".equals(descripcion)) {
                    descripcionText.setError("Escribe la descripcion de la alarma");
                    descripcionText.requestFocus();
                    return;
                }
                if (sonido1.isChecked()) {
                    sonido = "brightmorning";
                }
                if (sonido2.isChecked()) {
                    sonido = "cuckooclock";
                }
                if (sonido3.isChecked()) {
                    sonido = "earlytwilight";
                }
                if (sonido4.isChecked()) {
                   sonido = "gentlebreeze";
                }
                if (sonido5.isChecked()) {
                    sonido = "sunshower";
                }

                // Ya pasó la validación
                Alarmas nuevaAlarma = new Alarmas(minutos, descripcion, sonido);
                Alarmas.agregarAlarma(AddAlarma.this,nuevaAlarma);
                Alarmas.TOTALALARMAS++;

                try {
                    Alarmas.listaAlarmasEscribir(AddAlarma.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                onBackPressed();
            }

        });

        // El de cancelar simplemente cierra la actividad
        btnCancelarAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        }

  }
