package com.example.gestiondedatos.Ejercicio4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Layout;

import com.example.gestiondedatos.R;

public class WebsHelper extends SQLiteOpenHelper {

    public static  final String NOMBRE_BASE= "websList_db";
    public static  final String NOMBRE_TABLA ="websList";
    private static final int VERSION_BASE_DE_DATOS = 1;
    public static final String WEB_ID = "_id";
    public static final String NOMBRE_WEB = "nombre";
    public static final String LINK_WEB = "link";
    public static final String EMAIL_WEB = "email";
    public static final String CATEGORIA_WEB = "categoria";
    public static final String IMAGEN_WEB = "imagen";
    public static final String[] ALL_COLUMNS ={"nombre", "link","email","categoria", "imagen","id"};
    private static final String CREATE_TABLE ="CREATE TABLE " + NOMBRE_TABLA + " (" +
            WEB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOMBRE_WEB + " TEXT, " +
            LINK_WEB + " TEXT, " +
            EMAIL_WEB + " TEXT, " +
            CATEGORIA_WEB + " TEXT, " +
            IMAGEN_WEB+"INTEGER"+
            ")";
    public WebsHelper(Context context) {
        super(context, NOMBRE_BASE, null, VERSION_BASE_DE_DATOS);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, nombre text, link text,email text, categoria text,imagen int)", NOMBRE_TABLA));
        loadData(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA);
        onCreate(sqLiteDatabase);
    }

    private void loadData(SQLiteDatabase database) {
        Webs webInsertada1=new Webs("Fotocasa","https://www.fotocasa.es/es/","comunicacion@fotocasa.es","Inmobiliaria", R.drawable.imagen3_min);
        ContentValues values = new ContentValues();
        values.put("nombre", webInsertada1.getNombre());
        values.put("link",webInsertada1 .getLink());
        values.put("email", webInsertada1.getEmail());
        values.put("categoria", webInsertada1.getCategoria());
        values.put("imagen", webInsertada1.getImagen());
        database.insert(NOMBRE_TABLA, null, values);
        Webs webInsertada2=new Webs("Los mejores restaurantes","https://losmejoresrestaurantes.net/"," info@losmejoresrestaurantes.net","Gastronomia",R.drawable.imagen5_min);
        values.put("nombre", webInsertada2.getNombre());
        values.put("link",webInsertada2 .getLink());
        values.put("email", webInsertada2.getEmail());
        values.put("categoria", webInsertada2.getCategoria());
        values.put("imagen", webInsertada2.getImagen());
        database.insert(NOMBRE_TABLA, null, values);
    }
}