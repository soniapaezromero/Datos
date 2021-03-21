package com.example.gestiondedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gestiondedatos.Ejercicio1.MainActivityEjercicio1;
import com.example.gestiondedatos.Ejercicio2.MainActivityEjercicio2;
import com.example.gestiondedatos.Ejercicio3.MainActivityEjercicio3;
import com.example.gestiondedatos.Ejercicio4.MainActivityEjercicio4;

public class MainActivity extends AppCompatActivity {
    Button ejercicio1;
    Button ejercicio2;
    Button ejercicio3;
    Button ejercicio4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ejercicio1=(Button)findViewById(R.id.botonEjecicio1);
        ejercicio2= (Button)findViewById(R.id.botonEjercicio2);
        ejercicio3=(Button)findViewById(R.id.botonEjercicio3);
        ejercicio4= (Button)findViewById(R.id.botonEjercicio4);
        ejercicio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento =new Intent(v.getContext(), MainActivityEjercicio1.class);
                startActivity(intento);
            }
        });
        ejercicio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento =new Intent(v.getContext(), MainActivityEjercicio2.class);
                startActivity(intento);
            }
        });
        ejercicio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento =new Intent(v.getContext(), MainActivityEjercicio3.class);
                startActivity(intento);
            }
        });
        ejercicio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento =new Intent(v.getContext(), MainActivityEjercicio4.class);
                startActivity(intento);
            }
        });
    }
}