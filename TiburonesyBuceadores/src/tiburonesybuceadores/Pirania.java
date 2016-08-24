/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesybuceadores;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Polygon;


/**
 *
 * @author Fabricio Layedra
 */
public class Pirania extends AnimalMarino{

    private String caracterActual;

    public Pirania(double velocidadInicial,Double x,Double y) { 
        super(velocidadInicial);
        this.palabraEncimaAnimal=new Label();
        this.palabraEncimaAnimal.setTextFill(Color.WHITE
        );
        configurarLetras();
        this.caracterActual="";
        this.animal=new StackPane();
        this.animal.setLayoutX(x);
        this.animal.setLayoutY(y);
        this.animal.getChildren().add(formarAnimal(Constantes.PIRANIA_IMAGE,413/4,318/4));
        this.palabraEncimaAnimal.setText(caracterActual);


    }

    @Override
    public void aparecerCaracteresActuales(String[] abecedario) {
        Random aleatorio = new Random();
        int indiceCaracter = aleatorio.nextInt(abecedario.length);
        this.caracterActual = abecedario[indiceCaracter];
        this.palabraEncimaAnimal.setText(caracterActual);
        ponerPalabraEncima(caracterActual);
    }


    public String obtenerCaracteresActuales() {
        return caracterActual;
    }

    public void setCaracterActual(String caracterActual) {
        this.caracterActual = caracterActual;
    }
    public boolean equals(Pirania p){
        return this.caracterActual.equals(p.obtenerCaracteresActuales());
    }

    @Override
    public void setPalabraActual(String palabra) {
        this.caracterActual=palabra;
    }
   

    
    
}

