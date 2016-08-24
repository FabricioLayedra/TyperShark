/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesybuceadores;

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 *
 * @author Uchiha ClanPc
 */
public class Mar {//Pane Organizer

    private Pane mar;
    private Pane panelInicial;
    private Scene marea;
    private double distanciaAlFondo;
    private int nivelMarea;//multiplicador de velociada
    private LinkedList<Buceador> mejoresBuceadores;//cotrol del puntaje;
    private ArrayList<AnimalMarino> animalesEnMar;
    private Buceador buceador;
    private int puntos= 0;

    public int getPuntos() {
        return puntos;
    }


    public Mar(double fondo) {
        this.mar = new Pane();
        this.mar.setPrefSize(Constantes.TAM_MAR_X, Constantes.TAM_MAR_Y);
        this.panelInicial = new Pane();
        this.distanciaAlFondo = fondo;
        this.nivelMarea = 1;
        this.mejoresBuceadores = new LinkedList<Buceador>();
        this.mar.setPrefSize(Constantes.TAM_MAR_X, Constantes.TAM_MAR_Y);
        this.animalesEnMar = new ArrayList<AnimalMarino>();
        this.marea = new Scene(panelInicial);
        MarLimpio marLimpio = new MarLimpio();
        marea.setOnKeyPressed(marLimpio);

    }

    public void aumentarNivel() {
        this.nivelMarea = this.nivelMarea + 1;
    }

    public double getDistanciaAlFondo() {
        return distanciaAlFondo;
    }

    public void setDistanciaAlFondo(double distanciaAlFondo) {
        this.distanciaAlFondo = distanciaAlFondo;
    }

    public Pane getMar() {
        return mar;
    }

    public void setMar(Pane mar) {
        this.mar = mar;
    }

    public int getNivelMarea() {
        return nivelMarea;
    }

    public void setNivelMarea(int NivelMarea) {
        this.nivelMarea = NivelMarea;
    }

    public LinkedList<Buceador> getMejoresBuceadores() {
        return mejoresBuceadores;
    }

    public void setMejoresBuceadores(LinkedList<Buceador> MejoresBuceadores) {
        this.mejoresBuceadores = MejoresBuceadores;
    }

    public Thread ingresarPersonaAlMar(String nombre) {
        this.buceador = new Buceador(nombre, 0, 0.0, 20.0);
        this.mar.getChildren().add(this.buceador.getPersona());

        return new Thread(buceador);

    }

    public Thread ingresarAnimalAlMar(int probabilidades, double velocidad, double posIniX, double posIniY, String[] diccionario) {
        AnimalMarino animalMarino = new AnimalMarino() {
            @Override
            public void aparecerCaracteresActuales(String[] caracteres) {
            }

            @Override
            public String obtenerCaracteresActuales() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setPalabraActual(String palabra) {
            }
        };

        if (probabilidades <= 60) {
            String[] abecedario = diccionario;
            animalMarino = new Pirania(velocidad, posIniX, posIniY);
            animalMarino.aparecerCaracteresActuales(abecedario);
        }
        if (probabilidades > 60 && probabilidades < 85) {
            animalMarino = new Tiburon(velocidad, posIniX, posIniY);
            animalMarino.aparecerCaracteresActuales(diccionario);
        }
        if (probabilidades >= 85) {
            animalMarino = (TiburonNegro) new TiburonNegro(velocidad, posIniX, posIniY);
            animalMarino.aparecerCaracteresActuales(diccionario);
        }

        this.animalesEnMar.add(animalMarino);
        this.mar.getChildren().add(animalMarino.getAnimal());

        return new Thread(animalMarino);

    }

    public Pane getPanelInicial() {
        return this.panelInicial;
    }

    public Scene getMarea() {
        return this.marea;
    }

    public ArrayList<AnimalMarino> getAnimalesEnMar() {
        return animalesEnMar;
    }

    public void setAnimalesEnMar(ArrayList<AnimalMarino> animalesEnMar) {
        this.animalesEnMar = animalesEnMar;
    }

    public Buceador getBuceador() {
        return buceador;
    }

    public void setBuceador(Buceador buceador) {
        this.buceador = buceador;
    }

    private class MarLimpio implements EventHandler<KeyEvent> {

        private String palabrasEscritas = "";
        private String caracterIngresado = "";
        private String palabrasAEscribir;
        private String caracterActual;
        

        public MarLimpio() {

        }

        @Override
        public void handle(KeyEvent teclado) {

            this.caracterIngresado = teclado.getText();
            this.palabrasEscritas += this.caracterIngresado;
            System.out.println(palabrasEscritas);
            System.out.println(animalesEnMar.size());
            boolean iterar = true;
            if (iterar) {
                for (int i = 0; i < animalesEnMar.size(); i++) {
                    if (animalesEnMar.get(i).getClass().getSimpleName().equals("Tiburon")) {
                        Tiburon tiburonTemp = (Tiburon) animalesEnMar.get(i);
                        if (this.palabrasEscritas.contains(tiburonTemp.obtenerCaracteresActuales())) {
                            try {
                                animalesEnMar.remove(tiburonTemp);
                            } catch (ClassCastException e) {
                                //no hacer remove si no se puede
                            }
                            mar.getChildren().remove(tiburonTemp.getTiburon());
                            int puntosAct= this.palabrasEscritas.length();
                            puntos +=puntosAct;
                            buceador.setPuntos(puntos);
                            System.out.print(puntos);
                            this.palabrasEscritas = "";
                            iterar = false;
                            break;
                        }
                    }
                }

            }
            
            if (iterar) {
                for (int i = 0; i < animalesEnMar.size(); i++) {
                    if (animalesEnMar.get(i).getClass().getSimpleName().equals("Pirania")) {
                        Pirania piraniaTemp = (Pirania) animalesEnMar.get(i);
                        if (piraniaTemp.obtenerCaracteresActuales().equals(this.caracterIngresado)) {
                            try {
                                animalesEnMar.remove(piraniaTemp);
                            } catch (ClassCastException e) {
                                //no hacer remove si no se puede
                            }
                            mar.getChildren().remove(piraniaTemp.getAnimal());
                            int puntosAct= this.palabrasEscritas.length();
                            puntos +=puntosAct;
                            buceador.setPuntos(puntos);
                            System.out.print(puntos);
                            this.palabrasEscritas = "";
                            break;
                        }
                    }
                }
            }
        }

    }
}
