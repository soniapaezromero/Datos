package com.example.gestiondedatos.Ejercicio1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestiondedatos.Ejercicio1.Settings.Settings;
import com.example.gestiondedatos.Ejercicio1.Settings.SettingsFragment;
import com.example.gestiondedatos.R;
import com.example.gestiondedatos.databinding.ActivityMainEjercicio1Binding;
import java.text.DecimalFormat;


public class MainActivityEjercicio1 extends AppCompatActivity implements View.OnClickListener {
    private static final String PREFERENCIAS_COLOR_SETTINGS = "colorfondo_pref" ;
    private ActivityMainEjercicio1Binding binding;
    static Integer backgroundColor;
    public static final String PREFERENCIAS_VALOR_VALUE = "edit_text_preference_dolar_value";
    private static double ratio;
    private static double cambio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ejercicio1);
        binding= ActivityMainEjercicio1Binding.inflate(getLayoutInflater());
        getSupportActionBar().setTitle("Conversor de Monedas");
        View view = binding.getRoot();
        setContentView(view);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,true);
        setRatio(Double.parseDouble(preferences.getString(PREFERENCIAS_VALOR_VALUE, "0")));
        cambioColorFondo(SettingsFragment.cambioColor(guardarColor()));
        setBackgroundColor(view);
        binding.botonconv.setOnClickListener(this);


    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v == binding.botonconv) {
            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
            PreferenceManager.setDefaultValues(this,R.xml.preferences,true);
            String valor=preferences.getString(PREFERENCIAS_VALOR_VALUE, "0");
            ratio=Double.parseDouble(valor);
            try {
                if(ratio != 0) {
                    if (binding.eurosDolares.isChecked()) {
                        binding.dolares.setText("");
                        binding.dolares.setText(convertirDolares(binding.euros.getText().toString(), ratio));
                    } else {
                        binding.euros.setText("");
                        binding.euros.setText(convertirEuros(binding.dolares.getText().toString(), ratio));
                    }
                }else{
                    binding.dolares.setText("");
                    binding.euros.setText("");
                    Toast.makeText(this, "Tienes que introducir el valor del cambio en Configuración, Preferencias generales, Cambio", Toast.LENGTH_SHORT).show();

                }
            }catch(NumberFormatException e){
                    Toast.makeText(this, "Error en la conversión: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }


    @Override
    protected void onResume(){
        super.onResume();
        View view = binding.getRoot();
        setBackgroundColor(view);
    }

    private String guardarColor() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String color = preferences.getString(PREFERENCIAS_COLOR_SETTINGS , "0");
        return color;
    }
    public static void cambioColorFondo(Integer color){
        backgroundColor = color;
    }

    public static void setBackgroundColor(View v){
        if (backgroundColor != null){
            v.setBackgroundColor(backgroundColor);

        }
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.action_confi:
                startActivity(new Intent(this, Settings.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public static  void setRatio(double parseDouble) {
        MainActivityEjercicio1.ratio= ratio;
    }
    public static double getRatio(){
        return ratio;
    }
    public  static String convertirEuros( String cantidad,double cambio)throws NumberFormatException{
        DecimalFormat formato= new DecimalFormat("#0.00");
        double cantEuros;
        cantEuros= Double.parseDouble(cantidad)/cambio;
        String resultado=formato.format(cantEuros);
        return resultado;
    }
    public static String convertirDolares(String cantidad, double cambio)throws NumberFormatException{
        DecimalFormat formato= new DecimalFormat("#0.00");
        double cantEuros;
        cantEuros= Double.parseDouble(cantidad)*cambio;
        String resultado=formato.format(cantEuros);
        return resultado;
    }

}

