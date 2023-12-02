package com.example.hamburguesassimulador.models;

import com.example.hamburguesassimulador.Threads.HiloCliente;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cliente extends ImageView{
    private HiloCliente hiloCliente;

    public Cliente(MonitorRecepcionista monitorRecepcionista, MonitorMesero monitorMesero, Image image) {
        this.hiloCliente = new HiloCliente(monitorRecepcionista, monitorMesero);
        this.setImage(image);
        setTranslateX(0);
        setTranslateY(0);
    }

    public void entrarRestaurante() {
        setTranslateX(600);
        setTranslateY(200);
    }

    public void salirRestaurante() {
        setTranslateX(1000);
        setTranslateY(300);
    }
}
