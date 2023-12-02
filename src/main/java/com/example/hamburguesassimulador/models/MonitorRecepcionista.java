package com.example.hamburguesassimulador.models;

import com.example.hamburguesassimulador.Threads.HiloCliente;

import java.util.*;

public class MonitorRecepcionista {
    private boolean restauranteOcupado; //Indica si el restaurante tiene espacio
    private int mesasOcupadas;
    private final int capacidadMesas;
    private Queue<HiloCliente> colaClientes;
    private Map<HiloCliente, Integer> asignarMesas;
    private MonitorMesero monitorMesero;

    public MonitorRecepcionista(int capacidadMesas) {
        this.restauranteOcupado = false;
        this.mesasOcupadas = 0;
        this.capacidadMesas = capacidadMesas;
        this.colaClientes = new LinkedList<>();
        this.asignarMesas = new HashMap<>();
    }

    public void setMonitorMesero(MonitorMesero monitorMesero) {
        this.monitorMesero = monitorMesero;
    }

    public synchronized void llegadaCliente(HiloCliente cliente) {
        System.out.println("El Cliente " + cliente.getId() + " llegó al restaurante.");

        if (mesasOcupadas == capacidadMesas) {
            System.out.println("El Restaurante esta lleno. Cliente: " + cliente.getId() + " en cola de espera.");
            colaClientes.add(cliente);
            while (restauranteOcupado || colaClientes.peek() != cliente) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Cliente " + cliente.getId() + " sale de la cola de espera");
            colaClientes.remove();
        }

        int mesaAsignada;
        Set<Integer> mesasDisponibles = new HashSet<>();
        for (int i = 1; i <= capacidadMesas; i++) {
            mesasDisponibles.add(i);
        }
        for (Integer mesaOcupada : asignarMesas.values()) {
            mesasDisponibles.remove(mesaOcupada);
        }

        if (!mesasDisponibles.isEmpty()) {
            mesaAsignada = mesasDisponibles.iterator().next();
        } else {
            mesasOcupadas++;
            mesaAsignada = mesasOcupadas;
        }

        asignarMesas.put(cliente, mesaAsignada);

        if (mesasOcupadas == capacidadMesas) {
            restauranteOcupado = true;
        }

        System.out.println("Recepcionista asignó al Cliente: " + cliente.getId() + " a la mesa #" + mesaAsignada);

        notifyAll();

    }

    public synchronized void abandonarRestaurante(HiloCliente cliente) {
        mesasOcupadas--;

        if (mesasOcupadas < capacidadMesas) {
            restauranteOcupado = false;
            notifyAll();
        }

        asignarMesas.remove(cliente);
        System.out.println("El Cliente " + cliente.getId() + " abandonó el restaurante.");
    }
    public synchronized void entregar(HiloCliente cliente) {
        System.out.println("Pedido entregado al cliente " + cliente.getId());
        notify();

    }
}
