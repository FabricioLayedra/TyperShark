/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesybuceadores;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;

/**
 *
 * @author Fabricio Layedra
 */
public class TiburonNegro extends Tiburon {

    public TiburonNegro(double velocidad, Double x, Double y, int marca) {
        super(velocidad);
        this.animal = new StackPane();
        this.animal.setLayoutX(x);
        this.animal.setLayoutY(y);
        this.animal.getChildren().add(formarAnimal(Constantes.TIBURON_NEGRO_IMAGE, 300, 150));
        this.marcaDeNacimiento = marca;
        this.palabrasEliminadas = 0;
        

    }

    @Override
    public String obtenerCaracteresActuales() {
        return this.palabrasActuales[this.palabrasEliminadas];
    }

    @Override
    public void aparecerCaracteresActuales(String[] diccionario) {
        Random aleatorio = new Random();
        String[] palabras = diccionario;
        int indicePalabra;
        int maximoPalabras = aleatorio.nextInt(2) + 2;;
        this.palabrasEncima = new Label[maximoPalabras];
        this.palabrasActuales = new String[maximoPalabras];
        for (int i = 0; i < maximoPalabras; i++) {
            indicePalabra = aleatorio.nextInt(palabras.length);
            this.palabrasActuales[i] = palabras[indicePalabra];
        }
        this.palabrasEncima[0] = new Label(this.palabrasActuales[0]);
        this.palabrasEncima[0].setFont(Constantes.FUENTE_LETRAS);
        this.animal.getChildren().add(this.palabrasEncima[0]);

        this.palabrasAEliminar = this.palabrasActuales.length;
    }

    @Override
    public boolean equals(Object o) {
        TiburonNegro tN = (TiburonNegro) o;
        if (tN.getMarcaDeNacimiento() == this.marcaDeNacimiento) {
            return true;
        } else {
            return false;
        }

    }

    public int getMarcaDeNacimiento() {
        return marcaDeNacimiento;
    }

    public void setMarcaDeNacimiento(int marcaDeNacimiento) {
        this.marcaDeNacimiento = marcaDeNacimiento;
    }

}
