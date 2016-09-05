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
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author Fabricio Layedra
 */
public class TiburonNegro extends Tiburon {

    public TiburonNegro(double velocidad, Double x, Double y, int marca) {
        super(velocidad);
        this.animal = new StackPane();
         this.palabraEncimaAnimal=new Label();
        this.palabraEncimaAnimal.setTextFill(Color.RED);
        configurarLetras();
        this.palabraActual="";
        palabraEncimaAnimal.setText(palabraActual);
        this.animal.setLayoutX(x);
        this.animal.setLayoutY(y);
        this.animal.getChildren().add(formarAnimal(Constantes.TIBURON_NEGRO_IMAGE, 300, 150));
       
        this.marcaDeNacimiento = marca;
        

    }

    @Override
    public String obtenerCaracteresActuales() {
        String palabra="";
        for(int i=0;i<this.palabraActual.length();i++){        
            if(this.palabraActual.charAt(i)!=' '){
                palabra=palabra+this.palabraActual.charAt(i);
            }
        }   
        return palabra;
    }

    @Override
    public void aparecerCaracteresActuales(String[] diccionario) {
        Random aleatorio = new Random();
        String[] palabras = diccionario;
        int indicePalabra;
        int maximoPalabras = aleatorio.nextInt(2) + 2;;
        for (int i = 0; i < maximoPalabras; i++) {
            indicePalabra = aleatorio.nextInt(palabras.length);
            this.palabraActual = this.palabraActual+" "+palabras[indicePalabra];
        }
        ponerPalabraEncima(palabraActual);

    }

    @Override
    public boolean equals(Object o) {
        TiburonNegro tN = (TiburonNegro) o;
        return tN.obtenerCaracteresActuales().equals(this.obtenerCaracteresActuales());

    }

    @Override
    public int getMarcaDeNacimiento() {
        return marcaDeNacimiento;
    }

    @Override
    public void setMarcaDeNacimiento(int marcaDeNacimiento) {
        this.marcaDeNacimiento = marcaDeNacimiento;
    }
    @Override
    public Pane getAnimal(){
        return this.animal;
    }

}
