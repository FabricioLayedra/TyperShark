/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesybuceadores;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 *
 * @author Harold Aragon
 */
 
 
//Clase de constantes del proyecto 
public class Constantes {
    public static final double TAM_MAR_X=840;
    public static final double TAM_MAR_Y=720;
    public static final double FONDO_MAR_LVL_1=20;
    public static final double VELOCIDAD_INI_TIBU=0.5;
    public static final double VELOCIDAD_INI_PIRANIA=1.0;
    public static final double VELOCIDAD_INI_TIBU_NEGRO=0.25;
    public static final int MAX_ANIMALES_LVL_1=10;   
    public static final Image TIBURON_IMAGE=
        new Image(Tiburon.class.getResourceAsStream("tiburon.gif"));
    public static final Image PIRANIA_IMAGE=
        new Image(Pirania.class.getResourceAsStream("pirana.png"));
    public static final Image BUCEADOR_IMAGE=
        new Image(Buceador.class.getResourceAsStream("diver.gif"));
    public static final Image TIBURON_NEGRO_IMAGE=
        new Image(TiburonNegro.class.getResourceAsStream("tiburonNegro.gif"));
    public static final Image BALLENA_JOROBADA=
        new Image(TiburonNegro.class.getResourceAsStream("bj.png"));
    public static final int NUM_PALABRAS=15;
    public static final double TAM_BOTON_INI_X=150.0;
    public static final double TAM_BOTON_INI_Y=40.0;
    public static final double BUCEADOR_X=0.0;
    public static final double BUCEADOR_Y=0.0;
    public static final long VELOCIDAD_APARICION_LVL_1=5000;
    public static final Font FUENTE_LETRAS = new Font("Comic Sans MS",35);
    public static Font GAME_OVER= new Font("Comic Sans MS",100);
    public static Font MEJORES_JUGADORES_TITULO= new Font("Monotype Corsiva",40);
    public static Font MEJORES_JUGADORES_LISTA= new Font("Comic Sans MS",30);
    public static  boolean HACER_BUZO=true;
    public static final String INSTRUCCIONES = "Instrucciones: \n\n "
            + "1. Para ganar puntos: Teclea las palabras que aparezcan\n en los animales marinos, "
            + "para hacerlos desaparecer \n\n"
            + "2. Si alguno de ellos te toca, pierdes una vida. Sólo posees 3 vidas. \n\n"
            + "3. Cuando tengas más de 25 puntos, tendrás un poder especial. "
            + "DESCÚBRELO\n\n";
    
    public static int TOP_MEJORES_JUGADORES=10;
    public static int marcaDeNacimiento=0;
    public static int PAL_PROF=10;
    public static int VIDAS_BUCEADOR=1;
}
