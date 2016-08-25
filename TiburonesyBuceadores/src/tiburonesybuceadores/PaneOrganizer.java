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
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * @author Harold Aragon
 */
public class PaneOrganizer {

    private Pane raiz;
    private Mar mar;
    Scene scene;
    Scene scene_pedir_nombre;
    Button[] botones;
    Button iniciar;
    Button instrucciones;
    Button mejoresJugadores;
    Button salir;
    Button regresar;
    VBox buttons;
    VBox nombre;
    Label nivel;
    String nombre_jugador;

     public PaneOrganizer() {
        this.nombre_jugador = pedirNombre();
        this.mar = new Mar(Constantes.FONDO_MAR_LVL_1);
        this.raiz = mar.getPanelInicial();
        this.raiz.getChildren().add(mar.getMar());
        this.configurarBotones();
        this.scene = mar.getMarea();
        this.raiz.getStylesheets().add("styles/styles.css");
        this.raiz.getStyleClass().add("root");
        this.nivel=new Label();
        this.mar.getMar().getChildren().add(this.nivel);
        

    }

    public Scene getScene() {
        return this.scene;
    }
    private void crearPanelBotones() {
        iniciar = new Button("Iniciar");
        instrucciones = new Button("Ver Instrucciones");
        mejoresJugadores = new Button("Mejores Jugadores");
        salir = new Button("Salir");
        this.botones = new Button[]{iniciar, instrucciones, mejoresJugadores, salir};

    }
    public void configurarLabelNivel(int nivel) throws InterruptedException{
        this.nivel.setText("NIVEL "+ String.valueOf(nivel));
        this.nivel.setPrefSize(300, 250);
        this.nivel.setLayoutX(Constantes.TAM_MAR_X/2);
        this.nivel.setLayoutY(Constantes.TAM_MAR_Y/2);
        Thread.sleep(6000);
        this.nivel.setLayoutX(10000);
        this.nivel.setLayoutY(10000);
    }
    private void configurarBotones() {
        
        this.crearPanelBotones();
        buttons = new VBox();

        for (int i = 0; i < botones.length; i++) {

            Button temp = botones[i];
            temp.setPrefSize(Constantes.TAM_BOTON_INI_X, Constantes.TAM_BOTON_INI_Y);
            buttons.getChildren().add(temp);
            buttons.setSpacing(20);

            temp.setOnAction(new AccionBotones(temp.getText()));

        }
        buttons.setLayoutX(570);
        buttons.setLayoutY(300);
        this.raiz.getChildren().add(buttons);
        regresar = new Button("Menu Principal");
        regresar.setOnAction(new AccionBotones("Regresar"));

    }
    private String pedirNombre() {
            
            Stage stage = new Stage();
            Label tag = new Label("Ingrese nombre del jugador: ");
            TextField name = new TextField("");
            Button btn = new Button("OK");
            VBox nombre = new VBox();
            btn.setAlignment(Pos.CENTER);
            nombre.getChildren().addAll(tag,name,btn);
            name.setScaleX(0.85);
            //btn.setLayoutX(0);
            
            btn.setOnAction(e-> {
                    stage.close();
                   
            }
            );
            
            Scene scene = new Scene(nombre, 300,300);
            stage.setScene(scene);
            stage.setHeight(200);
            stage.setWidth(250);
            stage.setAlwaysOnTop(true);
            stage.setTitle("Nombre Jugador");
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
            return name.getText();
        }
    private class AccionBotones implements EventHandler<ActionEvent> {

        String opcion;

        public AccionBotones(String opcion) {
            this.opcion = opcion;
        }

        public void jugar() {
            
            Scanner palabras = null;
            try {
                palabras = new Scanner(new File("src/tiburonesybuceadores/diccionario.txt"));
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
            final Label puntos = new Label(String.valueOf(mar.getPuntos()));
            Thread juego;
            juego = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (buceador.getVidas() > 0) {
                        try {                        
                            configurarLabelNivel(mar.getNivelMarea());
                            System.out.println("Entro al nivel");
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        while (!(buceador.llegarAlFondo(mar))) {
                            final double yTiburon = rnd.nextInt((int) Constantes.TAM_MAR_Y - 150) + 25;
                            double yPirania = rnd.nextInt((int) Constantes.TAM_MAR_Y - 150) + 25;
                            final double yTiburonNegro = rnd.nextInt((int) Constantes.TAM_MAR_Y - 150) + 50;
                            int maximoAnimales = Constantes.MAX_ANIMALES_LVL_1*mar.getNivelMarea() + ((mar.getNivelMarea() - 1) * rnd.nextInt(15));
                            final int probabilidades = rnd.nextInt(100) + 1;
                            //en la misma iteracion tiene que salir al menos un animal.
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    if (Constantes.HACER_BUZO) {
                                        Thread buceadorMoviendose = mar.ingresarPersonaAlMar(nombre_jugador);
                                        buceadorMoviendose.start();
                                        Constantes.HACER_BUZO = false;

                                    }
                                    if (probabilidades <= 60) {
                                        Thread piraniaTemp = mar.ingresarAnimalAlMar(probabilidades, Constantes.VELOCIDAD_INI_PIRANIA*mar.getNivelMarea(), Constantes.TAM_MAR_X, yTiburon, abecedario);
                                        piraniaTemp.start();
                                        int probabilidades = rnd.nextInt(100) + 1;
                                    }
                                    if (probabilidades < 85 && probabilidades > 60) {
                                        Thread tiburonTemp = mar.ingresarAnimalAlMar(probabilidades, Constantes.VELOCIDAD_INI_TIBU*mar.getNivelMarea(), Constantes.TAM_MAR_X, yTiburon, diccionario);
                                        tiburonTemp.start();
                                        int probabilidades = rnd.nextInt(100) + 1;
                                    }
                                    if (probabilidades >= 85) {
                                        Thread tiburonNegroTemp = mar.ingresarAnimalAlMar(probabilidades, Constantes.VELOCIDAD_INI_TIBU_NEGRO*mar.getNivelMarea(), Constantes.TAM_MAR_X, yTiburonNegro, diccionario);
                                        tiburonNegroTemp.start();
                                    }
                                    puntos.setText(String.valueOf(mar.getPuntos()));
                                    puntos.setLayoutX(Constantes.TAM_MAR_X - 20);
                                    puntos.setLayoutY(0);
                                    puntos.setFont(Constantes.FUENTE_LETRAS);
                                    puntos.autosize();
                                    puntos.setTextFill(Color.RED);
                                    raiz.getChildren().add(puntos);
                                    mar.setNivelMarea(mar.getNivelMarea()+1);
                                }
                            });
                            try {
                                Thread.sleep(Constantes.VELOCIDAD_APARICION_LVL_1/mar.getNivelMarea());
                                buceador.setDistanciaRecorrida(buceador.getDistanciaRecorrida() + 0.5);

                            } catch (InterruptedException ex) {
                                Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //mar.getBuceador().setDistanciaRecorrida(60 + mar.getBuceador().getDistanciaRecorrida());
                            //bajar vidas
                        }
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        mar.setDistanciaAlFondo(mar.getDistanciaAlFondo()*mar.getNivelMarea());
                        
                      
                    }
                }
            });
            juego.start();
        }

        @Override
        public void handle(ActionEvent event) {
            raiz.getChildren().remove(buttons);
            
            
            raiz.getChildren().add(regresar);
            switch (opcion) {
                
                case "Iniciar": {
                    regresar.setLayoutX(0);
                    regresar.setLayoutY(Constantes.TAM_MAR_Y-20);
                    raiz.getStylesheets().add("styles/styles.css");
                    raiz.getStyleClass().add("juego");
                    this.jugar();
                    break;
                }

                case "Ver Instrucciones": {
                    regresar.setLayoutX(Constantes.TAM_MAR_X-60);
                    regresar.setLayoutY(Constantes.TAM_MAR_Y-40);
                    raiz.getStylesheets().add("styles/styles.css");    
                    raiz.getStyleClass().add("instrucciones");
                    this.verInstrucciones();
                    break;
                }

                case "Mejores Jugadores": {
                    regresar.setLayoutX(Constantes.TAM_MAR_X-60);
                    regresar.setLayoutY(Constantes.TAM_MAR_Y-40);
                    raiz.getStylesheets().add("styles/styles.css");
                    raiz.getStyleClass().add("bestPlayers");
                    this.verMejJugadores();
                    break;
                }

                case "Salir": {
                    System.exit(0);
                    break;
                }

            }

        }

        
        private void verMejJugadores() {
            Scanner scores = null;
            String mej_jug = "";
            try {
                scores = new Scanner(new File("src/tiburonesybuceadores/scores.txt"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
            }
            final String[] mejores_jugadores;
            mejores_jugadores = new String[Constantes.TOP_MEJORES_JUGADORES];
            
            raiz.getChildren().add(iniciar);

            Label the_best_players = new Label();
            the_best_players.setTextAlignment(TextAlignment.CENTER);
            the_best_players.setLayoutX((Constantes.TAM_MAR_X / 2)+150);
            the_best_players.setLayoutY((Constantes.TAM_MAR_Y / 2)-100);
            the_best_players.setTextFill(Color.WHITE);
            the_best_players.setLineSpacing(10);
                      
            int indice = 0;
            while (scores.hasNextLine()) {
                mejores_jugadores[indice] = scores.nextLine();
                mej_jug=mej_jug+mejores_jugadores[indice]+"\n";
                System.out.println(mejores_jugadores[indice]);
                indice++;
            }
            the_best_players.setText(mej_jug);
            raiz.getChildren().add(the_best_players);
            
                
        }

        private void verInstrucciones() {


            Label instructions = new Label(Constantes.INSTRUCCIONES);
            instructions.setTextAlignment(TextAlignment.LEFT);
            instructions.setLayoutX((Constantes.TAM_MAR_X / 2)+30);
            instructions.setLayoutY((Constantes.TAM_MAR_Y / 2)-100);
            instructions.setTextFill(Color.RED);
            instructions.setLineSpacing(10);
            raiz.getChildren().add(instructions);
            
        }

        

    }


}
