package com.example.hamburguesassimulador.Threads;

import com.example.hamburguesassimulador.models.MonitorMesero;
import com.example.hamburguesassimulador.models.MonitorRecepcionista;

public class HiloCliente extends Thread{
    private static int contadorClientes = 0;
    private MonitorRecepcionista monitorRecepcionista;

    public HiloCliente(MonitorRecepcionista monitorRecepcionista, MonitorMesero monitorMesero) {
        this.monitorRecepcionista = monitorRecepcionista;
        this.setName("Cliente-" + contadorClientes++);
    }

    public void entrarRestaurante(){
        System.out.println("El Cliente " + getId() + " entró al restaurante y su mesa.");
    }

    public void ordenar() {
        System.out.println("El Cliente " + getId() + " pidio una hamburguesa.");
    }

    public void esperar(){
        System.out.println("El Cliente " + getId() + " esperando su Hamburguesa.");
    }

    public void entregarOrden() {
        monitorRecepcionista.entregar(this);
    }

    public void comer(){
        System.out.println("El Cliente " + getId() + " esta comiendo su hamburguesa");
    }

    @Override
    public void run() {
        monitorRecepcionista.llegadaCliente(this);
        entrarRestaurante();
        ordenar();
        esperar();
        entregarOrden();
        comer();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monitorRecepcionista.abandonarRestaurante(this);
    }
}
