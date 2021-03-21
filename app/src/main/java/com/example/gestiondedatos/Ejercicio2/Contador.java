package com.example.gestiondedatos.Ejercicio2;

public class Contador {

        private int alarmas;
        private int tiempo;
        private  static final int PAUSA = 5;


        public Contador() {
            this.alarmas = 0;
            this.tiempo = PAUSA;
        }

        public Contador(int c, int t) {
            this.alarmas = c;
            this.tiempo = t;
        }

        public void sestAlarmas(int alarmas) {
            this.alarmas = alarmas;
        }

        public int getAlarmas() {
            return alarmas;
        }

        public String  contadorCero(){
            this.alarmas= 0;
            return String.valueOf(this.alarmas);

        }

        public String aumentarTiempo(){
            this.tiempo += 1;
            return String.valueOf(this.tiempo) + ":00";
        }

        public String reducirTiempo(){
            this.tiempo -= 1;
            if (this.tiempo < 1)
                tiempo = 1;
            return String.valueOf(this.tiempo) + ":00";
        }


        public int getTiempo(){
            return  this.tiempo;
        }

    }

