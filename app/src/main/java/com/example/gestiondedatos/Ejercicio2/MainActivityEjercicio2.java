package com.example.gestiondedatos.Ejercicio2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.example.gestiondedatos.R;
import com.example.gestiondedatos.databinding.ActivityMainEjercicio2Binding;

import java.net.URI;

public class MainActivityEjercicio2 extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_CONNECT = 1;
    private ActivityMainEjercicio2Binding binding2;
    boolean Inicio=false;
    public static int NUM_ALARMAS=Alarmas.getListaDeAlarmas().size();
    private static MediaPlayer mediaPlayer;
    private ContadorDescendiente contadorMenos;
    int posicionAlarma =0;

    Contador contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ejercicio2);
        binding2= ActivityMainEjercicio2Binding.inflate(getLayoutInflater());
        View view = binding2.getRoot();
        setContentView(view);
        binding2.botonAnadir.setOnClickListener(this);
        binding2.botonEditar.setOnClickListener(this);
        binding2.botonComenzar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        binding2.numeroAlarmas.setText(String.valueOf(Alarmas.TOTALALARMAS));
        if (comprobarPermiso()) {
            if (v == binding2.botonAnadir) {

                   Intent intento = new Intent(v.getContext(), AddAlarma.class);
                   startActivity(intento);

            }

        if (v == binding2.botonEditar) {
            Intent intento = new Intent(v.getContext(), RecyclerView.class);
            startActivity(intento);


        }
         }
        if(v==binding2.botonComenzar){
            if(!Alarmas.listaDeAlarmas.isEmpty()) {
                if (posicionAlarma > Alarmas.listaDeAlarmas.size()) {
                    AlertDialog.Builder popup = new AlertDialog.Builder(MainActivityEjercicio2.this);
                    popup.setTitle("Fin");
                    popup.setMessage("Has superado el número de Alarmas, tienes que eliminar alguna");
                    popup.setPositiveButton("Ok", null);
                    popup.show();
                    binding2.tiempo.setEnabled(false);
                    binding2.botonComenzar.setEnabled(false);
                    binding2.botonAnadir.setEnabled(false);
                    binding2.botonEditar.setEnabled(true);
                } else {
                    IniciarAlarma(posicionAlarma);
                    binding2.botonComenzar.setEnabled(false);
                    binding2.botonAnadir.setEnabled(false);
                    binding2.botonEditar.setEnabled(true);
                }
            }else{
            AlertDialog.Builder popup=new AlertDialog.Builder(MainActivityEjercicio2.this);
            popup.setTitle("No hay alarmas");
            popup.setMessage("No tienes alarmas , tienes que añadir alguna");
            popup.setPositiveButton("Ok", null);
            popup.show();

        }

        }

    }
    private boolean comprobarPermiso() {
        //https://developer.android.com/training/permissions/requesting?hl=es-419
        String permiso = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        // Manifest.permission.INTERNET
        boolean concedido = false;
        // comprobar los permisos
        if (ActivityCompat.checkSelfPermission(this, permiso) != PackageManager.PERMISSION_GRANTED) {
            // pedir los permisos necesarios, porque no están concedidos
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permiso)) {
                concedido = false;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permiso}, REQUEST_CONNECT);
                // Cuando se cierre el cuadro de diálogo se ejecutará onRequestPermissionsResult
            }
        } else {
            concedido = true;
        }
        return concedido;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        String permiso = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        //Manifest.permission.INTERNET;
        // chequeo los permisos de nuevo
        if (requestCode == REQUEST_CONNECT)
            if (ActivityCompat.checkSelfPermission(this, permiso) == PackageManager.PERMISSION_GRANTED)
                // permiso concedido
                startService(new Intent(MainActivityEjercicio2.this, AddAlarma.class));
            else
                // no hay permiso
                mostrarError("No se ha concedido permiso para conectarse a Internet");
    }

    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }



    public class ContadorDescendiente extends CountDownTimer {
        String mensajeMostrar="";
        public ContadorDescendiente(long startTime, long interval,String mensajeMostrar) {
            super(startTime, interval);
            this.mensajeMostrar=mensajeMostrar;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long min, seg;
            min = (millisUntilFinished / 1000) / 60;
            seg = (millisUntilFinished / 1000) % 60;
            binding2.tiempo.setText(min + ":" + String.format("%02d", seg));

            if (seg == 5){
                mediaPlayer.start();
                Toast.makeText(MainActivityEjercicio2.this, mensajeMostrar, Toast.LENGTH_SHORT).show();

            }
        }
        @Override
        public void onFinish() {
            mediaPlayer.stop();
            Toast.makeText(MainActivityEjercicio2.this, "Alarma terminada", Toast.LENGTH_SHORT).show();
            binding2.tiempo.setText(contador.getTiempo() + ":00");
            posicionAlarma++;
            if( posicionAlarma < Alarmas.listaDeAlarmas.size()) {
                int numeroRestantes = Alarmas.TOTALALARMAS - posicionAlarma;
                String alarmasrestantes = numeroRestantes + "";
                IniciarAlarma((posicionAlarma));
                binding2.numeroAlarmas.setText(alarmasrestantes);
                binding2.botonComenzar.setEnabled(false);
            }else{
                AlertDialog.Builder popup=new AlertDialog.Builder(MainActivityEjercicio2.this);
                popup.setTitle("Fin");
                popup.setMessage("Han sonado todas las alarmas  almacenadas");
                popup.setPositiveButton("Ok", null);
                popup.show();
                binding2.botonComenzar.setEnabled(true);
                binding2.numeroAlarmas.setText("0");
            }

        }
    }
    private void IniciarAlarma(int posicion){

            Alarmas alarma = Alarmas.listaDeAlarmas.get(posicion);
            String mensaje= "Nombre: "+ alarma.getDescripcion()+", Sonido :" +alarma.getSonido();
            int id = 0;
            int sonidoAlarma= obtenerSonido(alarma.getSonido());
            mediaPlayer = MediaPlayer.create(this, sonidoAlarma);
            contadorMenos = new ContadorDescendiente(alarma.getMinutos() * 60 * 1000, 1000,mensaje);
            contador = new Contador((posicion), alarma.getMinutos());
            contadorMenos.start();
        }


 public  int obtenerSonido(String nombre) {
        int sonidoObtenido=0;
     if (nombre.contains("brightmorning")) {
         sonidoObtenido = R.raw.brightmorning;
     } else if (nombre.contains("cuckooclock")){
         sonidoObtenido = R.raw.cuckooclock;
     } else if (nombre.contains("earlytwilight")){
         sonidoObtenido=R.raw.earlytwilight;
     }else if (nombre.contains("gentlebreeze")){
         sonidoObtenido=R.raw.gentlebreeze;
     }else if (nombre.contains("earlytwilight")){
         sonidoObtenido=R.raw.sunshower;
     }
     return sonidoObtenido;
 }

 }


