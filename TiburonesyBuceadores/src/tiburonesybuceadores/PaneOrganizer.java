/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesybuceadores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
import java.io.PrintWriter;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Harold Aragon
 */
public class PaneOrganizer {
    private Stage pcpal;
    private Pane raiz;
    private Mar mar;
    private Scene scene;
    //Arreglo de botones del MENU PRINCIPAL
        private Button[] botones;
    //Botones del MENU PRINCIPAL
        private Button iniciar;
        private Button instrucciones;
        private Button mejoresJugadores;
        private Button leer_juego;
        private Button salir;
    private Button regresar;
    private Button guardar_juego;
    private VBox buttons;
    private String nombre_jugador;
    //Label de nivel y de titulo del menú principal
        private Label titulo, nivel;
    //Labels de Ver Mejores Jugadores
        private Label the_best_players,titulo_mejores_jugadores;
    //Labels de Ver Instrucciones
        private Label instructions;

    public PaneOrganizer() {
        
        this.inicializarLabelsGlobales();
        this.nombre_jugador = pedirNombre();
        
        this.mar = new Mar(Constantes.FONDO_MAR_LVL_1);
        this.mar.getBuceador().setNombre(nombre_jugador);
        this.raiz = mar.getPanelInicial();
        this.raiz.getChildren().add(mar.getMar());
        this.configurarBotones();
        this.scene = mar.getMarea();
        this.raiz.getStylesheets().add("styles/styles.css");
        this.raiz.getStyleClass().add("root");
        this.nivel = new Label();
        this.mar.getMar().getChildren().add(this.nivel);
        this.setTitle("Tiburones y Buceadores");
        this.pcpal = new Stage();
        this.pcpal.setScene(scene);
        

    }

    private void setTitle(String titulo) {
        this.titulo = new Label(titulo);
        this.titulo.setLayoutX(150);
        this.titulo.setLayoutY(40);
        this.titulo.setFont(new Font("Comic Sans MS", 50));
        this.raiz.getChildren().add(this.titulo);
        this.titulo.setTextFill(Color.WHITESMOKE);
    }

    public Stage getStage() {
        return this.pcpal;
    }

    private void crearPanelBotones() {
        iniciar = new Button("Iniciar");
        instrucciones = new Button("Ver Instrucciones");
        mejoresJugadores = new Button("Mejores Jugadores");
        leer_juego= new Button("Leer Juego");
        salir = new Button("Salir");
        this.botones = new Button[]{iniciar, instrucciones, mejoresJugadores,leer_juego,salir};
        

    }

    private void configurarBotones() {

        this.crearPanelBotones();
        buttons = new VBox();

        for (Button temp : botones) {
            //temp.setPrefSize(Constantes.TAM_BOTON_INI_X, Constantes.TAM_BOTON_INI_Y);
            temp.setStyle("-fx-font: 35 Perpetua; -fx-base: #5198CF;");
            temp.setTextFill(Color.WHITE);

            temp.autosize();
            DropShadow shadow = new DropShadow();
            //Adding the shadow when the mouse cursor is on
            temp.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                temp.setEffect(shadow);
            });

            temp.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                temp.setEffect(null);
            });
            buttons.getChildren().add(temp);
            buttons.setSpacing(50);
            buttons.setAlignment(Pos.CENTER);
            temp.setOnAction(new AccionBotones(temp.getText()));
        }
        //Configurando panel de botones del MENU PRINCIPAL
            buttons.setLayoutX((Constantes.TAM_MAR_X / 2) - 120);
            buttons.setLayoutY(150);
        //Configurando boton regresar
            regresar = new Button("Regresar");
            regresar.setOnAction(new AccionBotones("Regresar")); 
            regresar.setVisible(false);
        //Configurando boton guardar_juego; mientras el usuario juega
            guardar_juego = new Button("Guardar Juego");
            guardar_juego.setOnAction(ev-> {
                   guardarNivelPuntajeVidasPoderEspecial();
                   crearAlerta("Se ha guardado su juego!!!...");
            }); 
            guardar_juego.setVisible(false);
        

        this.raiz.getChildren().addAll(buttons,guardar_juego,regresar);
        

    }

    private String pedirNombre() {

        Stage stage = new Stage();
        stage.setResizable(false);
        Label tag = new Label("¡Bienvenido a Tiburones y Buceadores!\n"
                + "Ingrese su nombre: ");
        tag.setAlignment(Pos.CENTER);
        TextField name = new TextField("");
        Button btn = new Button("OK");
        VBox nombre = new VBox();
        nombre.setAlignment(Pos.CENTER);
        nombre.getChildren().addAll(tag, name, btn);
        name.setScaleX(0.85);
        nombre.setSpacing(20);
        //btn.setLayoutX(0);

        btn.setOnAction(e -> {
            stage.close();
        }
        );
        name.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
            stage.close();
            }
        }); 
        

        Scene scene = new Scene(nombre, 300, 300);
        stage.setScene(scene);
        stage.setHeight(200);
        stage.setWidth(250);
        stage.setAlwaysOnTop(true);
        stage.setTitle("Inicio");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        return name.getText();
    }

    private void inicializarLabelsGlobales() {
        this.the_best_players=new Label();
        this.titulo=new Label();
        this.nivel=new Label();
        this.titulo_mejores_jugadores=new Label();
        this.instructions=new Label();
    }
    
    private void guardarNivelPuntajeVidasPoderEspecial() {
            /*
            Se guardará en el archivo juego_guardado.txt con el siguiente formato:
                nombreJugador
                vidas
                puntaje
                nivel
                Poder Especial
            Son 5 parámetros que se guardan    
            */
            String path = new File("src/tiburonesybuceadores/juego_guardado.txt").getAbsolutePath();
            FileWriter fichero = null;
            PrintWriter pw = null;
            try
            {
                fichero = new FileWriter(path);
                //pw = new PrintWriter(fichero);
                
                fichero.write(nombre_jugador+";"+String.valueOf(mar.getBuceador().getVidas())+";"+
                           String.valueOf(mar.getPuntos())+";"+mar.getNivelMarea()+";"+
                           String.valueOf(mar.getBuceador().getPorcentajePoderEspecial()));

            } catch (Exception e) {
                System.out.println("Estoy cayendo en la primera excepcion");
                e.printStackTrace();
            } finally {
                // Nuevamente aprovechamos el finally para 
               // asegurarnos que se cierra el fichero.
               try {
                    if (null != fichero)
                        fichero.close();
               } catch (Exception e2) {
                  System.out.println("Estoy cayendo en la segunda excepcion");
                  e2.printStackTrace();
               }
            }  
    }
            
    /*  Lo que hace esta funcion "crearAlerta(String leyenda)"es crear una nueva 
        ventana 1ue muestra una leyenda y un boton salir; ya tiene configurado
        el boton salir que se cerrará tanto con enter como con click
    */
    public void crearAlerta(String leyenda){
            Stage alerta = new Stage();
                BorderPane alertbp = new BorderPane();
                VBox alert = new VBox();
                Button exit = new Button ("Salir");
                Label label = new Label (leyenda);
                alertbp.setCenter(exit);
                alert.getChildren().addAll(label,alertbp);
                 
                 //exit.setAlignment(Pos.CENTER);
                 exit.setLayoutX(400);
                 
                 /*Configurando eventos del boton exit para que funcione con
                      mouse y teclado*/
                 exit.setOnAction(f-> {
                     alerta.close();
                       
                 });
                 exit.setOnKeyPressed(event -> {
                    if(event.getCode() == KeyCode.ENTER){
                        alerta.close();
                    }
                 }); 
                 

                 Scene sceneExit = new Scene(alert,300,100);
                 alerta.setScene(sceneExit);
                 alerta.setTitle("Alerta");
                 alerta.setAlwaysOnTop(true);
                 alerta.setMaxWidth(500);
                 alerta.initModality(Modality.APPLICATION_MODAL);
                 alerta.showAndWait();
    }
    
    
    

    private class AccionBotones implements EventHandler<ActionEvent> {
        Thread juego;
        String opcion; 
        private Label vidas;
        private Label poder_especial;
        private Label puntos;
        private Label gameover;
        private Label level;
        private Boolean ppe =false;//ppe (Porcentaje Poder Especial)

        public AccionBotones(String opcion) {
            this.opcion = opcion;
            
        }

        public void jugar(Buceador buceador) {
           String diccionario_bj[],diccionario[];
           diccionario_bj=leerArchivo("src/tiburonesybuceadores/palabrasprof.txt");
           diccionario=leerArchivo("src/tiburonesybuceadores/diccionario.txt");

            final String[] abecedario = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
            final Random rnd = new Random();
                //Instanciando labels
                this.puntos = new Label(String.valueOf(mar.getPuntos()));
                this.vidas = new Label("VIDAS:   " + String.valueOf(buceador.getVidas()));
                this.level = new Label ("Nivel:   " + String.valueOf(mar.getNivelMarea()));
                this.poder_especial = new Label("\tPODER ESPECIAL\nPresiona enter para activarlo");
                this.gameover = new Label("¡GAME OVER!");
            //Configurar los labels, en que parte del Stage aparecerán con su formato
                configurarLabelsJuego();
            //Añadiendo labels al pane
                raiz.getChildren().add(puntos);
                raiz.getChildren().add(vidas);
                raiz.getChildren().add(level);
                raiz.getChildren().add(poder_especial);
                raiz.getChildren().add(gameover);
            
            juego = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (buceador.getVidas() > 0) {
                        
                        while (!(buceador.llegarAlFondo(mar)) && buceador.getVidas() > 0) {
                            final double yTiburon = rnd.nextInt((int) Constantes.TAM_MAR_Y - 150) + 25;
                            double yPirania = rnd.nextInt((int) Constantes.TAM_MAR_Y - 150) + 25;
                            final double yTiburonNegro = rnd.nextInt((int) Constantes.TAM_MAR_Y - 150) + 50;
                            int maximoAnimales = Constantes.MAX_ANIMALES_LVL_1 + mar.getNivelMarea() + ((mar.getNivelMarea() - 1) * rnd.nextInt(15));
                            final int probabilidades = rnd.nextInt(100) + 1;
                            //en la misma iteracion tiene que salir al menos un animal.
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {                                                                   
                                    for (int i = 0; i < mar.getAnimalesEnMar().size(); i++) {
                                        if (mar.getAnimalesEnMar().get(i).getAnimal().getLayoutX() <= 0) {
                                            AnimalMarino ani=mar.getAnimalesEnMar().get(i);
                                            if (buceador.getVidas() > 0) {
                                                buceador.setVidas(buceador.getVidas() - 1);
                                                mar.getMar().getChildren().remove(ani.getAnimal());
                                                vidas.setText("VIDAS:   " + String.valueOf(buceador.getVidas()));
                                            }
                                            if(buceador.getVidas() == 0){//aqui se muere el buceador
                                                mar.getMar().getChildren().remove(buceador.getPersona());
                                                mar.getMar().visibleProperty().setValue(Boolean.FALSE);
                                                juego.stop();
                                                gameover.setVisible(true);
                                                pedirGuardarPuntaje();
                                                break;
                                            }
                                            if (buceador.getVidas() >= 1) {
                                                vidas.setTextFill(Color.YELLOW);
                                             }
                                            if (buceador.getVidas() == 1) {
                                                /*Para que el nivel le aparezca en rojo;
                                                  es solo para hacerlo mas realista
                                                */
                                                vidas.setTextFill(Color.RED);
                                             }
                                        }

                                    }
                                    
                                    if (buceador.getVidas() <= 0) {
                                        mar.getMar().getChildren().remove(1);
                                        //aqui se muere el bceador
                                    }

                                    if (Constantes.HACER_BUZO) {
                                        Thread buceadorMoviendose = mar.ingresarPersonaAlMar(nombre_jugador);
                                        buceadorMoviendose.start();
                                        Constantes.HACER_BUZO = false;
                                    }
                                    if (probabilidades <= 60) {
                                        Thread piraniaTemp = mar.ingresarAnimalAlMar(probabilidades, Constantes.VELOCIDAD_INI_PIRANIA + (mar.getNivelMarea() / 100), Constantes.TAM_MAR_X, yTiburon, abecedario);
                                        piraniaTemp.start();
                                        int probabilidades = rnd.nextInt(100) + 1;
                                    }
                                    if (probabilidades < 85 && probabilidades > 65) {
                                        Thread bjtemp = mar.ingresarAnimalAlMar(probabilidades, Constantes.VELOCIDAD_INI_TIBU + (mar.getNivelMarea() / 100), Constantes.TAM_MAR_X, yTiburon, diccionario_bj);
                                        bjtemp.start();
                                        int probabilidades = rnd.nextInt(100) + 1;
                                    }
                                     if (probabilidades > 60 && probabilidades < 65) {
                                        Thread tiburonTemp = mar.ingresarAnimalAlMar(probabilidades, Constantes.VELOCIDAD_INI_TIBU + (mar.getNivelMarea() / 100), Constantes.TAM_MAR_X, yTiburon, diccionario);
                                        tiburonTemp.start();
                                        int probabilidades = rnd.nextInt(100) + 1;
                                    }
                                    if (probabilidades >= 85) {
                                        Thread tiburonNegroTemp = mar.ingresarAnimalAlMar(probabilidades, Constantes.VELOCIDAD_INI_TIBU_NEGRO + (mar.getNivelMarea() / 100), Constantes.TAM_MAR_X, yTiburonNegro, diccionario);
                                        tiburonNegroTemp.start();
                                    }
                                    if (mar.getBandera())
                                        poder_especial.setVisible(false);
                                    if (mar.getBuceador().getPorcentajePoderEspecial()>=25){
                                        poder_especial.setVisible(true);  
                                    }
                                            
                                    puntos.setText("Puntos: " + String.valueOf(mar.getPuntos())); 
                                    
                                }
                            });
                            try {
                                Thread.sleep(Constantes.VELOCIDAD_APARICION_LVL_1 -(Constantes.VELOCIDAD_APARICION_LVL_1*(mar.getNivelMarea()/10)));
                                buceador.setDistanciaRecorrida(buceador.getDistanciaRecorrida() + 0.3);

                            } catch (InterruptedException ex) {
                                Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        mar.setNivelMarea(mar.getNivelMarea() + 1);
                        level.setText("Nivel:   " + String.valueOf(mar.getNivelMarea()));
                        mar.setDistanciaAlFondo(mar.getDistanciaAlFondo()+ mar.getDistanciaAlFondo()*mar.getNivelMarea());

                    }
                }
            });
            juego.start();
        }

        @Override
        public void handle(ActionEvent event) {
            buttons.setVisible(false);
            titulo.setVisible(false);
            regresar.setVisible(true);
            
            int retirar=0;
            switch (opcion) {
                case "Iniciar": {
                    //Dando formato al botón regresar
                        darFormatoBoton(regresar,Constantes.TAM_MAR_X - 60,Constantes.TAM_MAR_Y - 20,true);
                    //Dando formato al botón guardar_juego
                        darFormatoBoton(guardar_juego,0,Constantes.TAM_MAR_Y - 20,true);    
                    mar.getMar().visibleProperty().setValue(Boolean.TRUE);
                   // mar.setBuceador(new Buceador(nombre_jugador, 0, Constantes.BUCEADOR_X, Constantes.BUCEADOR_Y - 200));
                    this.jugar(mar.getBuceador());
                    resetearLabelsJuego();
                    break;
                }

                case "Ver Instrucciones": {
                    //Dando formato al botón regresar
                        darFormatoBoton(regresar,(Constantes.TAM_MAR_X - 60),(Constantes.TAM_MAR_Y - 40),true);
                    retirar=this.verInstrucciones();
                    break;
                }

                case "Mejores Jugadores": {
                    //Dando formato al botón regresar
                    
                    darFormatoBoton(regresar,(Constantes.TAM_MAR_X - 60),(Constantes.TAM_MAR_Y - 40),true);
                    retirar=this.verMejJugadores();
                    break;
                }
                case "Leer Juego":{
                    //Dando formato al botón regresar
                        darFormatoBoton(regresar,(Constantes.TAM_MAR_X - 60),(Constantes.TAM_MAR_Y - 20),true);
                    //Dando formato al botón guardar_juego
                        darFormatoBoton(guardar_juego,0,(Constantes.TAM_MAR_Y - 20),true); 
                    mar.getMar().visibleProperty().setValue(Boolean.TRUE);
                    leerJuegoGuardado(mar.getBuceador());
                    this.jugar(mar.getBuceador());
                    resetearLabelsJuego();
                    break;
                }
                case "Salir": {
                    System.exit(0);
                    break;
                }
                
                case "Regresar":{
                    retirar=0; //,
                    //juego.stop();
                    mar.getMar().visibleProperty().setValue(Boolean.FALSE);//Ocultando MAR
                    /*Quitando todos los Labels Mostrados, incluyendo 
                      a los botones regresar y guardar juego  
                     */
                        
                        instructions.setVisible(false);
                        titulo_mejores_jugadores.setVisible(false);
                        the_best_players.setVisible(false);
                        regresar.setVisible(false);
                        guardar_juego.setVisible(false);
                        
                        
                    //Haciendo aparecer MENÚ PRINCIPAL
                        buttons.setVisible(true);
                        titulo.setVisible(true);

                    break;
                }
            }

        }
        private String[] leerArchivo(String direccion){
            //Recibe la dirección del archivo
            Scanner palabras = null;
            Scanner  pal = null;
            File file;
            final String[] diccionario;
            try {
                palabras = new Scanner(new File(direccion));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(palabras!=null)
                System.out.println("He leido correctamente el archivo:\n\t\t"+direccion);
            diccionario = new String[Constantes.NUM_PALABRAS];
            int indice = 0;
            while (palabras.hasNextLine()) {
                diccionario[indice] = palabras.nextLine();
                System.out.println("\t"+diccionario[indice]);
                indice++;
            }
            return diccionario;
            
        }
        
        
        private void leerJuegoGuardado(Buceador buceador){
            String juego_guardado[];
            juego_guardado=leerArchivo("src/tiburonesybuceadores/juego_guardado.txt");
            String[] datos = juego_guardado[0].split(";");
            //Si se imprime lo que lee en el archivo
            System.out.println("\tNOMBRE: "+datos[0]);
            System.out.println("\tVIDAS: "+datos[1]);
            System.out.println("\tPUNTAJE: "+datos[2]);
            System.out.println("\tNIVEL: "+datos[3]);
            System.out.println("\tPODER ESPECIAL: "+datos[4]);
            
            buceador.setNombre(datos[0]);
            buceador.setVidas(Integer.parseInt(datos[1]));
            mar.setPuntos(Integer.parseInt(datos[2]));
            mar.setNivelMarea(Integer.parseInt(datos[3]));
            buceador.setPorcentajePoderEspecial(Double.parseDouble(datos[4]));
        
        }
        
        
        
        private int verMejJugadores() {
            Scanner scores = null;
            String mej_jug = "Nombre\t\tPuntaje\n";
            try {
                scores = new Scanner(new File("src/tiburonesybuceadores/scores.txt"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaneOrganizer.class.getName()).log(Level.SEVERE, null, ex);
            }
            final String[] mejores_jugadores;
            mejores_jugadores = new String[Constantes.TOP_MEJORES_JUGADORES];

            the_best_players = new Label();
                //Configurando Formato y Posición del Label
                the_best_players.setTextAlignment(TextAlignment.CENTER);
                the_best_players.setLayoutX((Constantes.TAM_MAR_X/2) + 10);
                the_best_players.setLayoutY((Constantes.TAM_MAR_Y/2) - 200);
                the_best_players.setTextFill(Color.DARKGREEN);
                the_best_players.setLineSpacing(10);
                the_best_players.setFont(Constantes.MEJORES_JUGADORES_LISTA);
                
            titulo_mejores_jugadores = new Label("MEJORES JUGADORES DE TYPERSHARK");
                //Configurando Formato y Posición del Label
                titulo_mejores_jugadores.setTextAlignment(TextAlignment.CENTER);
                titulo_mejores_jugadores.setLayoutX(50);
                titulo_mejores_jugadores.setLayoutY(50);
                titulo_mejores_jugadores.setTextFill(Color.BLACK);
                titulo_mejores_jugadores.setFont(Constantes.MEJORES_JUGADORES_TITULO);

            int indice = 0;
           
            while (scores.hasNextLine()) {
                mejores_jugadores[indice] = scores.nextLine();
                mej_jug = mej_jug + (indice + 1)+".- "+ mejores_jugadores[indice] + "\n";
                indice++;
            }
            //Haciendo aparecer Labels
                titulo_mejores_jugadores.setVisible(true);
                the_best_players.setVisible(true);
            //Seteando el label de mejores jugadores leidos en el archivo "scores.txt"
                the_best_players.setText(mej_jug);
            //Agregando Labels al root
                raiz.getChildren().addAll(titulo_mejores_jugadores,the_best_players);
            int indice5=raiz.getChildren().indexOf(the_best_players);
            return indice5;

        }

        private int verInstrucciones() {

            instructions = new Label(Constantes.INSTRUCCIONES);
            instructions.setFont(new Font("Comic Sans MS", 20));
            instructions.setTextAlignment(TextAlignment.CENTER);
            instructions.setTextFill(Color.WHITE);
            instructions.setLineSpacing(10);
            instructions.autosize();
            instructions.setLayoutX(80);
            instructions.setLayoutY(100);
            instructions.setVisible(true);
            raiz.getChildren().add(instructions);
            int indice5=raiz.getChildren().indexOf(instructions);
            return indice5;

        }
        
        private void pedirGuardarPuntaje(){
            
            Stage stage = new Stage();
            stage.setResizable(false);
            Label tag = new Label("\t¡Bienvenido "+mar.getBuceador().getNombre()+"!"
                    + "\n¿Desea guardar su puntaje? ");
            tag.setAlignment(Pos.CENTER);
            Button btnok = new Button("SI");
            Button btncancel = new Button ("NO");
            VBox puntaje = new VBox();
            puntaje.setAlignment(Pos.CENTER);
            puntaje.getChildren().addAll(tag,btnok,btncancel);
            puntaje.setSpacing(20);
            
            /*Configurando boton de que desea guardar puntaje tanto con el mouse
                como con el enter del teclado
            */
            btnok.setOnAction(e-> {
                guardarRegistroPuntaje();
                crearAlerta("Se ha guardado su registro correctamente!!");
                 stage.close();
            });
            btnok.setOnKeyPressed(event -> {
                  if(event.getCode() == KeyCode.ENTER){
                      guardarRegistroPuntaje();
                      crearAlerta("Se ha guardado su registro correctamente!!");
                      stage.close();
                  }
              }); 
            
            
             /*Configurando boton de que NO desea guardar puntaje tanto con el mouse
                como con el enter del teclado
            */
              btncancel.setOnAction(e-> {
                      stage.close();   
              });
              btncancel.setOnKeyPressed(event -> {
                  if(event.getCode() == KeyCode.ENTER){
                  stage.close();
                  }
              }); 

            Scene scene = new Scene(puntaje, 400,300);
            stage.setScene(scene);
            stage.setHeight(200);
            stage.setWidth(250);
            stage.setAlwaysOnTop(true);
            stage.setTitle("Game Over");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
           
        }
        
      
    
        private void  guardarRegistroPuntaje(){
            String path = new File("src/tiburonesybuceadores/scores.txt").getAbsolutePath();
            String str= new String(mar.getBuceador().getNombre()+"\t\t"+mar.getPuntos());  
            File archivo = new File(path);
            try{
                BufferedWriter bw;
                if(!archivo.exists()) 
                    archivo.createNewFile();
                bw = new BufferedWriter(new FileWriter(archivo, true));//el true es para que no pise el archivo
                bw.write(str);
                bw.newLine();
                bw.close();
               
            }catch (Exception e) {
                e.printStackTrace();
            } 

    }
            
        
        

        private void configurarLabelsJuego() {
            
             //Configurando Label de puntos
            this.puntos.setLayoutX(Constantes.TAM_MAR_X - 200);
            this.puntos.setLayoutY(0);
            this.puntos.setFont(Constantes.FUENTE_LETRAS);
            this.puntos.autosize();
            this.puntos.setTextFill(Color.YELLOW);
            
            //Configurando Label de nivel
            this.level.setLayoutX(Constantes.TAM_MAR_X - 500);
            this.level.setLayoutY(0);
            this.level.setFont(Constantes.FUENTE_LETRAS);
            this.level.autosize();
            this.level.setTextFill(Color.YELLOW);
            
            //Configurando Label de vidas
            this.vidas.setLayoutX(50);
            this.vidas.setLayoutY(0);
            this.vidas.setFont(Constantes.FUENTE_LETRAS);
            this.vidas.autosize();
            this.vidas.setTextFill(Color.YELLOW);
            
            //Configurando Label de poder especial
            this.poder_especial.setLayoutX(300);
            this.poder_especial.setLayoutY(630);
            this.poder_especial.setFont(Constantes.FUENTE_LETRAS);
            this.poder_especial.autosize();
            this.poder_especial.setTextFill(Color.YELLOW);
            this.poder_especial.setVisible(false);
            
                        
            //Configurando Label de gameover
            gameover.setTextAlignment(TextAlignment.CENTER);
            gameover.setLayoutX(100);
            gameover.setLayoutY(360);
            gameover.setFont(Constantes.GAME_OVER);
            gameover.autosize();
            gameover.setVisible(false);
            

        }
        
        
        private void resetearLabelsJuego() {
            /*
            Esta función lo único que hará será ocultarlos y volverlos a aparecer
            para que de esa manera no aparezcan superpuesto entre un juego y otro
            */
            //Desapareciéndolos
            this.puntos.setVisible(false);
            this.level.setVisible(false);
            this.vidas.setVisible(false);
            this.poder_especial.setVisible(false);
            gameover.setVisible(false);
            
            //Haciéndolos aparecer
            this.puntos.setVisible(true);
            this.level.setVisible(true);
            this.vidas.setVisible(true);
            
        }

        private void darFormatoBoton(Button btn,double x, double y, boolean visible) {
            /* Método que configura el botón pasado por parámetro donde aparecerá,
               es decir en que parte de la pantalla y si estará visible o no  */
            btn.setLayoutX(x);
            btn.setLayoutY(y);
            btn.setVisible(visible);
        }
        
        
        
    }

}
