/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesybuceadores;

import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author Daniel Santacruz
 */
public class Buceador implements Runnable {

    private String nombre;
    private Pane persona;
    private LinkedList<Integer> puntosPorNivel;
    private int puntos;
    private int vidas;
    private double distanciaRecorrida;
    private double porcentajePoderEspecial;
    private boolean llegarAlFondo;

    public Buceador(String nombre, double distanciaInicial, Double x, Double y) {
        this.vidas=3;
        this.llegarAlFondo = false;
        this.nombre = nombre;
        this.distanciaRecorrida = distanciaInicial;
        this.persona = new Pane();
        this.persona.setLayoutX(x);
        this.persona.setLayoutY(y);
        this.persona.getChildren().add(formarPersona(Constantes.BUCEADOR_IMAGE, 744 / 4, 381 / 4));
    }

    public ImageView formarPersona(Image imagen, double largo, double alto) {
        ImageView persona = new ImageView();
        persona.setImage(imagen);
        persona.setFitHeight(alto);
        persona.setFitWidth(largo);

        return persona;
    }

    public boolean getLlegarAlFondo() {
        return llegarAlFondo;
    }
    


    public void setLlegarAlFondo(boolean llegaoAlFondo) {
        this.llegarAlFondo = llegaoAlFondo;
    }

    public boolean llegarAlFondo(Mar mar) {
        if(this.distanciaRecorrida>=mar.getDistanciaAlFondo())
        {
            return true;
        }
        return false;
        //las palabras extras, event teclado
    }

    public void EliminarAnimalsMarinos() {
        //inclutte un evnto del teclado, private class o fantasma
    }

    public Pane getPersona() {
        return persona;
    }

    public void setPersona(Pane persona) {
        this.persona = persona;
    }

    public LinkedList<Integer> getPuntosPorNivel() {
        return puntosPorNivel;
    }

    public void setPuntosPorNivel(LinkedList<Integer> puntosPorNivel) {
        this.puntosPorNivel = puntosPorNivel;
    }

    public int getPuntos() {

        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void setDistanciaRecorrida(double distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }

    public double getPorcentajePoderEspecial() {
        return porcentajePoderEspecial;
    }

    public void setPorcentajePoderEspecial(double porcentajePoderEspecial) {
        this.porcentajePoderEspecial = porcentajePoderEspecial;
    }

    @Override
    public void run() {
        boolean meta = true;
        while (meta) {
            Platform.runLater(new Runnable() {
                public void run() {
                    double baja = 0.3;
                    if (llegarAlFondo == false) {
                        for (int i = 0; i < 5; i++) {
                            persona.setLayoutY(persona.getLayoutY() + baja);
                        }
                        if (persona.getLayoutY()>=Constantes.TAM_MAR_Y) {
                            llegarAlFondo=true;
                            distanciaRecorrida=10000;

                        }
                    }
                    else{
                        persona.setLayoutY(Constantes.BUCEADOR_Y-25);
                        llegarAlFondo=false;
                    }
                }
            });

            try {
                Thread.sleep(100);

            } catch (InterruptedException ex) {
                Logger.getLogger(Buceador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        Buceador b = (Buceador) o;
        return this.nombre.equals(b.getNombre());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}