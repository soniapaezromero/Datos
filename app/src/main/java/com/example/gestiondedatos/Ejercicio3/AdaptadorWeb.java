package com.example.gestiondedatos.Ejercicio3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondedatos.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorWeb extends RecyclerView.Adapter<AdaptadorWeb.MyViewHolder> {
    private List<PáginasWeb> listaDeWeb;
    Context context;
    WebsController websController;

    public void setListaDeWeb(List<PáginasWeb> listaDeWeb) {
        this.listaDeWeb = listaDeWeb;
    }

    public AdaptadorWeb(Context context, List<PáginasWeb> webs) {
        this.context = context;
        this.listaDeWeb = webs;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView nombreTextview;
        public TextView linkTextView;
        public TextView emaillTextView;
        public TextView categoriaTextView;
        public ImageView webImageView;
        private View layoutprincipal;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombreTextview = itemView.findViewById(R.id.nombreweb);
            this.linkTextView = itemView.findViewById(R.id.link);
            this.emaillTextView = itemView.findViewById(R.id.email);
            this.categoriaTextView = itemView.findViewById(R.id.categoria);
            this.webImageView = itemView.findViewById(R.id.fotoWeb);
            layoutprincipal = itemView.findViewById(R.id.layout);
             }

        }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate the custom layout
        View filWeb = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_web, parent, false);
        return new MyViewHolder(filWeb);

    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        PáginasWeb webSeleccionada = listaDeWeb.get(position);
        holder.nombreTextview.setText(webSeleccionada.getNombre());
        holder.linkTextView.setText(webSeleccionada.getLink());
        holder.emaillTextView.setText(webSeleccionada.getEmail());
        holder.categoriaTextView.setText(webSeleccionada.getCategoria());
        holder.webImageView.setImageResource(webSeleccionada.getImagen()); ;
    }
    @Override
    public int getItemCount() {
        return listaDeWeb.size();
    }


}














