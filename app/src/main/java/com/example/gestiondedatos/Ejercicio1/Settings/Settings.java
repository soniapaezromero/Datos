package com.example.gestiondedatos.Ejercicio1.Settings;

import android.preference.PreferenceActivity;

import com.example.gestiondedatos.R;
import com.example.gestiondedatos.Ejercicio1.Settings.SettingsFragment;

import java.util.List;

public class Settings extends PreferenceActivity {


    @Override
    public void onBuildHeaders(List<Header> target){
        loadHeadersFromResource(R.xml.head_preference, target);
    }
    @Override
    protected boolean isValidFragment(String FragmentName){
        return SettingsFragment.class.getName().equals(FragmentName);
    }

}