package com.example.hamburguesassimulador.models;

import com.example.hamburguesassimulador.Threads.HiloCliente;

import java.util.Queue;

public class Mesero {
    private int meserosLibres;
    private Queue<HiloCliente> colaOrdenes;

    public int getMeserosLibres() {
        return meserosLibres;
    }

    public void setMeserosDisponibles(int meserosLibres) {
        this.meserosLibres = meserosLibres;
    }

    public Queue<HiloCliente> getColaOrdenes() {
        return colaOrdenes;
    }

    public void setColaOrdenes(Queue<HiloCliente> colaOrdenes) {
        this.colaOrdenes = colaOrdenes;
    }
}
