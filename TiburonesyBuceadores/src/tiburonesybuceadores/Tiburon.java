/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesybuceadores;
import com.sun.javafx.iio.ImageFrame;
import com.sun.javafx.iio.ImageStorage;
import com.sun.javafx.iio.ImageStorageException;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Shape;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Fabricio Layedra
 */
public class Tiburon extends AnimalMarino {   
    private String palabraActual;
    

    public Tiburon(double velocidad){
        super(velocidad);
    }
    public Tiburon(double velocidad,Double x, Double y){
        super(velocidad);
        this.palabraEncimaAnimal=new Label();
        this.palabraEncimaAnimal.setTextFill(Color.RED);
        configurarLetras();
        this.palabraActual="";
        this.animal= new StackPane();
        this.animal.setLayoutX(x);
        this.animal.setLayoutY(y);
        this.animal.getChildren().add(formarAnimal(Constantes.TIBURON_IMAGE,300,150));
        this.palabraEncimaAnimal.setText(palabraActual);
    }    

    @Override
    public void aparecerCaracteresActuales(String[] diccionario) {
        String[] palabras=diccionario;
        Random aleatorio=new Random();
        int indicePalabra=aleatorio.nextInt(palabras.length);
        this.palabraActual=palabras[indicePalabra];
        this.palabraEncimaAnimal.setText(palabraActual);
        ponerPalabraEncima(palabraActual);
    }
    
    
    @Override
    public String obtenerCaracteresActuales() {
        return this.palabraActual;
    }

    public void setPalabraActual(String palabraActual) {
        this.palabraActual = palabraActual;
    }
    
    @Override
    public boolean equals(Object o){
        Tiburon t=(Tiburon) o;
        return this.palabraActual.equals(t.obtenerCaracteresActuales());       
    } 
    
    public Pane getTiburon(){
        return this.animal;
    }
    
    
}
