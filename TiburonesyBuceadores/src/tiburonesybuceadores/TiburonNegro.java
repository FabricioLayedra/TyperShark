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
public class TiburonNegro extends Tiburon{
    private String[] palabrasActuales;
    private Label[] palabrasEncima;

    public TiburonNegro(double velocidad, Double x, Double y) {
        super(velocidad);
        this.animal= new StackPane();
        this.animal.setLayoutX(x);
        this.animal.setLayoutY(y);
        this.animal.getChildren().add(formarAnimal(Constantes.TIBURON_NEGRO_IMAGE,300,150));
        
    }
    @Override
    public String obtenerCaracteresActuales(){
        return this.palabrasActuales[0];        
    }
    @Override
    public void aparecerCaracteresActuales(String[] diccionario) {

        Random aleatorio=new Random();        
        String[] palabras=diccionario;
        int indicePalabra=aleatorio.nextInt(palabras.length);
        int maximoPalabras=aleatorio.nextInt(2)+2;;
        this.palabrasEncima=new Label[maximoPalabras];
        this.palabrasActuales=new String[maximoPalabras];
        for(int i=0;i<maximoPalabras;i++){
            this.palabrasActuales[i]=palabras[indicePalabra];
            this.palabrasEncima[i]=new Label();
            this.palabrasEncima[i].setFont(Constantes.FUENTE_LETRAS);
            this.palabrasEncima[i].setText(this.palabrasActuales[i]);
            this.animal.getChildren().add(this.palabrasEncima[i]);
        }
    }


}
