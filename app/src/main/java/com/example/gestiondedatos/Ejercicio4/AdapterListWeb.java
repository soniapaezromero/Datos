package com.example.gestiondedatos.Ejercicio4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondedatos.Ejercicio3.AdaptadorWeb;
import com.example.gestiondedatos.Ejercicio3.PáginasWeb;
import com.example.gestiondedatos.Ejercicio3.WebsController;
import com.example.gestiondedatos.R;

import java.util.List;

public class AdapterListWeb extends RecyclerView.Adapter<AdapterListWeb.MyViewHolder> {
    private List<Webs> listaDeWeb;
    Context context;


    public void setListaDeWeb(List<Webs> listaDeWeb) {
        this.listaDeWeb = listaDeWeb;
    }

    public AdapterListWeb(Context context, List<Webs> webs) {
        this.context = context;
        this.listaDeWeb = webs;
    }
    public AdapterListWeb(Context context){
        this.context=context;
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
            this.nombreTextview = itemView.findViewById(R.id.nombrList);
            this.linkTextView = itemView.findViewById(R.id.linkList);
            this.emaillTextView = itemView.findViewById(R.id.emailList);
            this.categoriaTextView = itemView.findViewById(R.id.categoriaList);
            this.webImageView = itemView.findViewById(R.id.imagenList);
            layoutprincipal = itemView.findViewById(R.id.layoutList);
        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate the custom layout
        View filWebList = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listweb, parent, false);
        return new MyViewHolder(filWebList);

    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Webs webSeleccionada = listaDeWeb.get(position);
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
