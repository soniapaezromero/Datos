package com.example.gestiondedatos.Ejercicio3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gestiondedatos.R;

public class Agregar_webs extends AppCompatActivity {

    private Button btnAgregarWeb, btnCancelarNuevaWeb;
    private EditText nombrewebText,linkWebText,emailWebTeXt,categoriaWEbText;
    private ImageView imagenWeb;
    private WebsController websController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_webs);


        // agrego la vista
        nombrewebText = findViewById(R.id.nombrewebText);
        linkWebText=findViewById(R.id.linkWebText);
        emailWebTeXt=findViewById(R.id.emailWebTeXt);
        categoriaWEbText=findViewById(R.id.categoriaWEbText);
        imagenWeb= findViewById(R.id.imagenWEbText);
        btnAgregarWeb=findViewById(R.id.btnAgregarWeb);
        btnCancelarNuevaWeb=findViewById(R.id.btnCancelarNuevaWeb);
        websController= new WebsController(this);
        // Agregar listener del botón de guardar
        btnAgregarWeb.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos
                nombrewebText.setError(null);
                linkWebText.setError(null);
                emailWebTeXt.setError(null);
                categoriaWEbText.setError(null);



                String nombre = nombrewebText.getText().toString();
                String link= linkWebText.getText().toString();
                String email= emailWebTeXt.getText().toString();
                String categoria= categoriaWEbText.getText().toString();
                String imagenCategoria = categoria.toLowerCase();
                int imagen;
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



                if ("".equals(nombre)) {
                    nombrewebText.setError("Escribe el nombre de la web");
                    nombrewebText.requestFocus();
                    return;
                }
                if ("".equals(link)) {
                    linkWebText.setError("Escribe el link de la web");
                    linkWebText.requestFocus();
                    return;
                }
                if ("".equals(categoria)) {
                    categoriaWEbText.setError("Escribe la categoria de la web de la web");
                    nombrewebText.requestFocus();
                    return;
                }
                if ("".equals(email)) {
                    emailWebTeXt.setError("Escribe el email de la web");
                    emailWebTeXt.requestFocus();
                    return;
                }


                // Ya pasó la validación
                PáginasWeb nuevaWeb = new PáginasWeb(nombre,link,email,categoria,imagen);
                long id = websController.nuevaWeb(nuevaWeb);

                if (id == -1) {
                    // De alguna manera ocurrió un error
                    Toast.makeText(Agregar_webs.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    // Terminar
                    onBackPressed();
                }
            }
        });

        // El de cancelar simplemente cierra la actividad
        btnCancelarNuevaWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}