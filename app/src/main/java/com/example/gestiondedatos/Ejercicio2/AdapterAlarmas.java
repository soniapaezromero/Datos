package com.example.gestiondedatos.Ejercicio2;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestiondedatos.R;

import java.util.List;

public class AdapterAlarmas extends RecyclerView.Adapter<AdapterAlarmas.MyViewHolder> {
    private List<Alarmas> listaAlarmas;
    Context context;


    public void setLlistaAlarmas(List<Alarmas> listaAlarmas) {
        this.listaAlarmas = listaAlarmas;
    }

    public AdapterAlarmas(Context context, List<Alarmas> alarmas) {
        this.context = context;
        this.listaAlarmas = alarmas;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView minutositem;
        public TextView decripcionitem;
        public TextView sonidoitem;
        private View layoutprincipal;

        MyViewHolder(View itemView) {
            super(itemView);
            this.minutositem = itemView.findViewById(R.id.minutositem);
            this.decripcionitem = itemView.findViewById(R.id.descripcionitem);
            this.sonidoitem = itemView.findViewById(R.id.soniditem);
            layoutprincipal = itemView.findViewById(R.id.layoutAlarmas);
        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the custom layout
        View filaAlarmas = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarmas, parent, false);
        return new MyViewHolder(filaAlarmas);

    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
       Alarmas alarmasSeleccionada = listaAlarmas.get(position);
        holder.minutositem.setText(String.valueOf(alarmasSeleccionada.getMinutos()));
        holder.decripcionitem.setText(alarmasSeleccionada.getDescripcion());
        holder.sonidoitem.setText(alarmasSeleccionada.getSonido());

    }
    @Override
    public int getItemCount() {
        return listaAlarmas.size();
    }


}
