package com.example.gestiondedatos.Ejercicio4;

public class Webs {
    private long id; // El ID de la BD
    private String nombre;
    private String link;
    private String email;
    private String categoria;
    private int  imagen;

    public Webs(String nombre, String link, String email, String categoria, int imagen) {
        this.nombre = nombre;
        this.link = link;
        this.email = email;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public Webs(String nombre, String link, String email, String categoria, int imagen, long id) {
        this.id = id;
        this.nombre = nombre;
        this.link = link;
        this.email = email;
        this.categoria = categoria;
        this.imagen = imagen;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "PáginasWeb{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", link='" + link + '\'' +
                ", email='" + email + '\'' +
                ", categoria='" + categoria + '\'' +
                ", imagen=" + imagen +
                '}';
    }
}


