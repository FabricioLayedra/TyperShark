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
    protected String[] palabrasActuales;
    protected Label[] palabrasEncima;
    protected int marcaDeNacimiento;
    protected int palabrasAEliminar;
    protected int palabrasEliminadas;
    
    //Constructor por defecto
    public AnimalMarino() {

    }

    //Constructor con velocidadIncial
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
                        
                        if(animal.getLayoutX()-velocidadInicial == 100){
                            //Si entra a este if es porque el animal (sea piraña, tiburon o tiburon negro)
                            //ha tocado la zona del buceador
                            System.out.println("Se debio de haber quitado una vida");
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

    //Formar animal con un tamaño de largo y alto, con su respectiva imagen    
    public ImageView formarAnimal(Image imagen, double largo, double alto) {
        ImageView animal = new ImageView();
        animal.setImage(imagen);
        animal.setFitHeight(alto);
        animal.setFitWidth(largo);
        return animal;
    }

    ;
    //Getters and Setters
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
    
    //Colocar palabra sobre la mitad del animal
    public void ponerPalabraEncima(String palabra) {
        this.palabraEncimaAnimal.setText(palabra);
        this.animal.getChildren().add(palabraEncimaAnimal);
        this.palabraEncimaAnimal.setLayoutX(animal.getLayoutX()-(animal.getLayoutX()/2));
        this.palabraEncimaAnimal.setLayoutY(animal.getLayoutY()-(animal.getLayoutY()/2));
    }

    public abstract void setPalabraActual(String palabra);

    //Se setea la fuente de las letras que aparecerán
    public void configurarLetras() {
        this.palabraEncimaAnimal.setFont(Constantes.FUENTE_LETRAS);
    }
      public int getPalabrasAEliminar() {
        return palabrasAEliminar;
    }

    public void setPalabrasAEliminar() {
        this.palabrasAEliminar = this.palabrasAEliminar - 1;
        this.palabrasEliminadas=this.palabrasEliminadas+1;
        if (palabrasAEliminar > 0) {
            this.palabrasEncima[this.palabrasAEliminar - 1] = new Label(this.palabrasActuales[this.palabrasAEliminar - 1]);
            this.palabrasEncima[this.palabrasAEliminar - 1].setFont(Constantes.FUENTE_LETRAS);
            this.animal.getChildren().add(this.palabrasEncima[this.palabrasAEliminar - 1]);
        }
        else{
            this.palabrasEncima[this.palabrasAEliminar - 1] = new Label("");
            this.palabrasEncima[this.palabrasAEliminar - 1].setFont(Constantes.FUENTE_LETRAS);
            this.animal.getChildren().add(this.palabrasEncima[this.palabrasAEliminar - 1]);
        }
    }
    
    public int getMarcaDeNacimiento() {
        return marcaDeNacimiento;
    }

    public void setMarcaDeNacimiento(int marcaDeNacimiento) {
        this.marcaDeNacimiento = marcaDeNacimiento;
    }
    
}
