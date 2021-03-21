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

public class Editar_Webs extends AppCompatActivity {
    private Button btnGuardarCambios, btnCancelarcambios;
    private EditText editandoNombre,editandoLink,editandoEmail,editandoCategoria;
    private ImageView editandoImagen;
    private WebsController websController;
    private PáginasWeb web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__webs);
        Bundle extras = getIntent().getExtras();
        // Si no hay datos (cosa rara) salimos
        if (extras == null) {
            finish();
            return;
        }
        // Instanciar el controlador de las web
        websController=new WebsController(this);

        // Rearmar la mascota
        // Nota: igualmente solamente podríamos mandar el id y recuperar la web de la BD
        long idWeb = extras.getLong("id");
        final String nombreWeb = extras.getString("nombre");
        String linkweb=extras.getString("link");
        String email= extras.getString("email");
        String categoriaWeb=extras.getString("categoria");

        int imagenWeb= extras.getInt("imagen");
        String imagenCategoria = categoriaWeb.toLowerCase();
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

        // Ahora declaramos las vistas
        editandoNombre = findViewById(R.id.EditandoNombre);
        editandoLink = findViewById(R.id.EditandoLink);
        editandoEmail = findViewById(R.id.Editandoemail);
        editandoCategoria = findViewById(R.id.Editandocategoria);
        editandoImagen = findViewById(R.id.Editandoimagen);
        btnCancelarcambios= findViewById(R.id.btnCancelarCAmbios);
        btnGuardarCambios = findViewById(R.id.btnGuardarCambios);


        // Rellenar los EditText con los datos de la web
        editandoNombre.setText(web.getNombre());
        editandoLink.setText(web.getLink());
        editandoEmail.setText(web.getLink());
        editandoCategoria.setText(web.getCategoria());
        editandoImagen.setImageResource(web.getImagen());

        // Listener del click del botón para salir, simplemente cierra la actividad
        btnCancelarcambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Listener del click del botón que guarda cambios
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // Remover previos errores si existen
                editandoNombre.setError(null);
                editandoLink.setError(null);
                editandoEmail.setError(null);
                editandoCategoria.setError(null);

                // Crear la web con los nuevos cambios pero ponerle
                // el id de la anterior
                String nuevoNombre = editandoNombre.getText().toString();
                String nuevoLink = editandoLink.getText().toString();
                String nuevoEmail = editandoEmail.getText().toString();
                String nuevaCategoria = editandoCategoria.getText().toString();
                editandoImagen.setImageDrawable(getDrawable(R.drawable.imagen4_min));
                editandoImagen.setTag(R.drawable.imagen4_min);

                int nuevaimagen= Integer.parseInt(editandoImagen.getTag().toString());


                if (nuevoNombre.isEmpty()) {
                    editandoNombre.setError("Escribe el nombre");
                    editandoNombre.requestFocus();
                    return;
                }
                if (nuevoLink.isEmpty()) {
                    editandoLink.setError("Escribe el link");
                    editandoLink.requestFocus();
                    return;
                }
                if (nuevoEmail.isEmpty()) {
                    editandoEmail.setError("Escribe el email");
                    editandoEmail.requestFocus();
                    return;
                }
                if (nuevaCategoria.isEmpty()) {
                    editandoCategoria.setError("Escribe la categoria");
                    editandoCategoria.requestFocus();
                    return;
                }




                // Si llegamos hasta aquí es porque los datos ya están validados
                PáginasWeb webConCambios = new PáginasWeb(nuevoNombre,nuevoLink,nuevoEmail,nuevaCategoria,nuevaimagen, web.getId());
                int filasModificadas = websController.guardarCambios(webConCambios);
                if (filasModificadas != 1) {
                    // De alguna forma ocurrió un error porque se debió modificar únicamente una fila
                    Toast.makeText(Editar_Webs.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    // Si las cosas van bien, volvemos a la principal
                    // cerrando esta actividad
                    onBackPressed();
                }
            }
        });
    }
}