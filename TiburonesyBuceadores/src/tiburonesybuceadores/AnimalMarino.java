/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesybuceadores;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

/**
 *
 * @author Harold Aragon
 */
public abstract class AnimalMarino implements Runnable {

    protected double velocidadInicial;
    protected StackPane animal;
    protected Label palabraEncimaAnimal;

    public AnimalMarino() {

    }

    public AnimalMarino(double velocidadInicial) {
        this.velocidadInicial = velocidadInicial;

    }
    

    @Override
    public void run() {
        boolean meta = true;
        while (meta) {
            //Double avanceX= getPtoInX() -10.0;             
            Platform.runLater(new Runnable() {
                //Double avance = avanceX;
                public void run() {
                    int sube_baja;
                    for (int i = 0; i < 5; i++) {
                        Random rnd = new Random();
                        if (rnd.nextInt(2) == 1) {
                            sube_baja = 1;
                        } else {
                            sube_baja = -1;
                        }
                        animal.setLayoutX(animal.getLayoutX() - velocidadInicial);
                        animal.setLayoutY(animal.getLayoutY() + sube_baja);
                    }
                }
            });

            try {
                Thread.sleep(50);

            } catch (InterruptedException ex) {
                Logger.getLogger(Tiburon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public abstract void aparecerCaracteresActuales(String[] caracteres);

    public abstract String obtenerCaracteresActuales();

    public ImageView formarAnimal(Image imagen, double largo, double alto) {
        ImageView animal = new ImageView();
        animal.setImage(imagen);
        animal.setFitHeight(alto);
        animal.setFitWidth(largo);
        return animal;
    }

    ;

    public double getVelocidadInicial() {
        return velocidadInicial;
    }

    public void setVelocidadInicial(double velocidadInicial) {
        this.velocidadInicial = velocidadInicial;
    }

    public Pane getAnimal() {
        return animal;
    }

    public void setAnimal(StackPane animal) {
        this.animal = animal;
    }

    public void ponerPalabraEncima(String palabra) {
        this.palabraEncimaAnimal.setText(palabra);
        this.animal.getChildren().add(palabraEncimaAnimal);
        this.palabraEncimaAnimal.setLayoutX(animal.getLayoutX()-(animal.getLayoutX()/2));
        this.palabraEncimaAnimal.setLayoutY(animal.getLayoutY()-(animal.getLayoutY()/2));
    }

    public abstract void setPalabraActual(String palabra);

    public void configurarLetras() {
        this.palabraEncimaAnimal.setFont(Constantes.FUENTE_LETRAS);
    }
}
