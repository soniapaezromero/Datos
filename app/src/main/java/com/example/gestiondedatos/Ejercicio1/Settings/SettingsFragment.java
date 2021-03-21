package com.example.gestiondedatos.Ejercicio1.Settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.gestiondedatos.Ejercicio1.MainActivityEjercicio1;
import com.example.gestiondedatos.R;

public class SettingsFragment extends PreferenceFragment {
    public static final String PREFERENCIAS_COLOR_SETTINGS = "colorfondo_pref";
    public static final String PREFERECIAS_VALOR_VALUE = "edit_text_preference_dolar_value";
    public static final String AZUL_COLOR = "Azul";
    public static final String VERDE_COLOR = "Verde";
    public static final String AMARILLO_COLOR = "Amarillo claro";
    public static final String INICIAL_COLOR = "Color Inicial";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    private EditTextPreference preferenceValor;
    private static ListPreference preferenceColor;
    private SharedPreferences sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        String settings = getArguments().getString("settings");

        if (settings.equals("general")){
            addPreferencesFromResource(R.xml.preferences);
        }
        else if(settings.equals("about")){
            addPreferencesFromResource(R.xml.info_preferences);
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        preferenceColor = (ListPreference) getPreferenceScreen().findPreference(PREFERENCIAS_COLOR_SETTINGS);
        preferenceValor = (EditTextPreference) getPreferenceScreen().findPreference(PREFERECIAS_VALOR_VALUE);

        PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);
        preferenceChangeListener = (sharedPreferences, key) -> {

            if (key.equals(PREFERENCIAS_COLOR_SETTINGS)){
                String color = sharedPreferences.getString(PREFERENCIAS_COLOR_SETTINGS, "0");
                MainActivityEjercicio1.cambioColorFondo(cambioColor(color));
                preferenceColor.setDefaultValue(color);

            }
            if (key.equals(PREFERECIAS_VALOR_VALUE)){
                MainActivityEjercicio1.setRatio(Double.parseDouble(sharedPreferences.getString(PREFERECIAS_VALOR_VALUE, "0")));
                Toast.makeText(getActivity(), "Valor del cambio de dólares a Euros: " + sharedPreferences.getString(PREFERECIAS_VALOR_VALUE, "0"), Toast.LENGTH_SHORT).show();
                preferenceValor.setDefaultValue(sharedPreferences.getString(PREFERECIAS_VALOR_VALUE, "0"));
            }
        };
    }

    @SuppressLint("Range")
    public static Integer cambioColor(String color){
        Integer colorID = null;
        switch (color){
            case INICIAL_COLOR:
                colorID = Color.parseColor("#00FFFFFF");
                break;
            case AZUL_COLOR:
                colorID = Color.parseColor("#6D84C8");
                break;
            case VERDE_COLOR:
                colorID = Color.parseColor("#7AC666");
                break;
            case AMARILLO_COLOR:
                colorID =Color.parseColor("#FAD02C");
                break;
            default:
                break;

        }
        return colorID;
    }


    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }



}
