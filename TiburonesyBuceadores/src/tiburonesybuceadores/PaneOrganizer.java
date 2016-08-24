/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesybuceadores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 *
 * @author Harold Aragon
 */
public class PaneOrganizer {

    private Pane raiz;
    private Mar mar;
    Scene scene;
    Button [] botones;
    Button iniciar;
    Button instrucciones;
    Button mejoresJugadores;
    Button salir;

    public PaneOrganizer() {
        
        
        this.mar = new Mar(Constantes.FONDO_MAR_LVL_1);
        this.raiz = mar.getPanelInicial();
        this.raiz.getChildren().add(mar.getMar());
        this.configurarBotones();
        this.scene = mar.getMarea();
        
        this.raiz.getStylesheets().add("styles/styles.css");
        this.raiz.getStyleClass().add("root");

    }

    public Scene getScene() {
        return this.scene;
    }

    private void crearPanelBotones(){
        iniciar = new Button("Iniciar");
        instrucciones =  new Button("Ver Instrucciones");
        mejoresJugadores = new Button("Mejores Jugadores");
        salir = new Button ("Salir");
        this.botones = new Button[]{iniciar, instrucciones, mejoresJugadores,salir};
    }
    private void configurarBotones() {
        this.crearPanelBotones();
        VBox buttons = new VBox();
        
        for (int i = 0; i<botones.length;i++){
            
            Button temp = botones[i];
            temp.setPrefSize(Constantes.TAM_BOTON_INI_X, Constantes.TAM_BOTON_INI_Y);
            buttons.getChildren().add(temp);
            buttons.setPadding(new Insets(80, 80, 80, 80));
            temp.setOnAction(new AccionBotones(i));
        }
        buttons.setAlignment(Pos.CENTER_RIGHT);
        this.raiz.getChildren().add(buttons);
        

    }

    private class AccionBotones implements EventHandler<ActionEvent> {
        int opcion;
        public AccionBotones(int opcion) {
            this.opcion = opcion;
        }
        public void jugar(){
            iniciar.setLayoutX(10000);
            iniciar.setLayoutY(10000);
            Scanner palabras = null;
            try {
                palabras = new Scanner(new File("src/tiburonesvsbuceadores/diccionario.txt"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
            }
            final String[] diccionario;
            diccionario = new String[Constantes.NUM_PALABRAS];

            //Thread buceaador=new Thread(new Runnable());
            int indice = 0;
            while (palabras.hasNextLine()) {
                diccionario[indice] = palabras.nextLine();
                System.out.println(diccionario[indice]);
                indice++;
            }
            final String[] abecedario = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
            final Random rnd = new Random();
            final Buceador buceador = new Buceador("fulano", 0, Constantes.BUCEADOR_X, Constantes.BUCEADOR_Y - 200);
            Thread juego;
            juego = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    while (!buceador.llegarAlFondo(mar)) {
                        final double yTiburon = rnd.nextInt((int) Constantes.TAM_MAR_Y - 150) + 25;                      
                        double yPirania = rnd.nextInt((int) Constantes.TAM_MAR_Y - 150) + 25;
                        final double yTiburonNegro = rnd.nextInt((int) Constantes.TAM_MAR_Y - 150) + 50;
                        int maximoAnimales = Constantes.MAX_ANIMALES_LVL_1 + ((mar.getNivelMarea() - 1) * rnd.nextInt(15));
                        final int probabilidades = rnd.nextInt(100) + 1;
                        //en la misma iteracion tiene que salir al menos un animal.
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                
                                if (Constantes.HACER_BUZO) {                                   
                                    Thread buceadorMoviendose = mar.ingresarPersonaAlMar("fulano");
                                    buceadorMoviendose.start();
                                    Constantes.HACER_BUZO=false;

                                }
                                if (probabilidades <= 60) {
                                    Thread piraniaTemp = mar.ingresarAnimalAlMar(probabilidades, Constantes.VELOCIDAD_INI_PIRANIA, Constantes.TAM_MAR_X, yTiburon, abecedario);
                                    piraniaTemp.start();
                                    int probabilidades = rnd.nextInt(100) + 1;
                                }
                                if (probabilidades < 85 && probabilidades > 60) {
                                    Thread tiburonTemp = mar.ingresarAnimalAlMar(probabilidades, Constantes.VELOCIDAD_INI_TIBU, Constantes.TAM_MAR_X, yTiburon, diccionario);
                                    tiburonTemp.start();
                                    int probabilidades = rnd.nextInt(100) + 1;
                                }
                                if (probabilidades >= 85) {
                                     Thread tiburonNegroTemp = mar.ingresarAnimalAlMar(probabilidades, Constantes.VELOCIDAD_INI_TIBU_NEGRO, Constantes.TAM_MAR_X, yTiburonNegro, diccionario);
                                    tiburonNegroTemp.start();
                                }
                            }
                        });
                        try {
                            Thread.sleep(Constantes.VELOCIDAD_APARICION_LVL_1);
                            buceador.setDistanciaRecorrida(buceador.getDistanciaRecorrida() + 1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //mar.getBuceador().setDistanciaRecorrida(60 + mar.getBuceador().getDistanciaRecorrida());
                        
                    }
                }
            });
            juego.start();        
        }
        
        
        @Override
        public void handle(ActionEvent event) {
            switch(opcion)
            {
                case 0:
                raiz.getStylesheets().add("styles/styles.css");
                raiz.getStyleClass().add("juego");
                this.jugar();
                
                case 1:
                raiz.getStylesheets().add("styles/styles.css");
                raiz.getStyleClass().add("instrucciones");
                this.verInstrucciones();
                
                case 2:
                
            }
                
        }

        private void verInstrucciones() {
        }
        
        
    }
}
