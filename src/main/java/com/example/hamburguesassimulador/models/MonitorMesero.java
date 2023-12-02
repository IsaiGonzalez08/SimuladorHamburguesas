package com.example.hamburguesassimulador.models;

import com.example.hamburguesassimulador.Threads.HiloCliente;
import com.example.hamburguesassimulador.Threads.HiloMesero;

import java.util.LinkedList;
import java.util.Queue;

public class MonitorMesero {
    private int meserosDisponibles;
    private Queue<HiloCliente> colaPedidos;

    public MonitorMesero(int cantidadMeseros) {
        this.meserosDisponibles = cantidadMeseros;
        this.colaPedidos = new LinkedList<>();
    }

    public synchronized void tomarPedido(HiloCliente cliente) {

        if (meserosDisponibles == 0) {
            System.out.println("Todos los meseros estan ocupados, el cliente " + cliente.getId() + " pasa a la cola de pedidos.");
            colaPedidos.add(cliente);
            while (meserosDisponibles == 0 || colaPedidos.peek() != cliente) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Cliente " + cliente.getId() + " sale de la cola de pedidos.");
            colaPedidos.remove();
        }

        meserosDisponibles--;
        System.out.println("Pedido del Cliente " + cliente.getId() + "es atendido por un mesero.");
        meserosDisponibles++;
        notifyAll();
    }

    public synchronized void desocuparMesero(HiloMesero hiloMesero) {
        if (meserosDisponibles < 6) {
            meserosDisponibles++;
        }

        if (colaPedidos.size() > 0) {
            notify();
        }
    }

}
