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
public class Constantes {
    public static final double TAM_MAR_X=1280;
    public static final double TAM_MAR_Y=900;
    public static final double FONDO_MAR_LVL_1=20;
    public static final double VELOCIDAD_INI_TIBU=2.0;
    public static final double VELOCIDAD_INI_PIRANIA=4.0;
    public static final double VELOCIDAD_INI_TIBU_NEGRO=1.0;
    public static final int MAX_ANIMALES_LVL_1=20;   
    public static final Image TIBURON_IMAGE=
        new Image(Tiburon.class.getResourceAsStream("tiburon.gif"));
    public static final Image PIRANIA_IMAGE=
        new Image(Pirania.class.getResourceAsStream("pirana.png"));
    public static final Image BUCEADOR_IMAGE=
        new Image(Buceador.class.getResourceAsStream("diver.gif"));
    public static final Image TIBURON_NEGRO_IMAGE=
        new Image(TiburonNegro.class.getResourceAsStream("tiburonNegro.gif"));
    public static final int NUM_PALABRAS=3;
    public static final double TAM_BOTON_INI_X=200.0;
    public static final double TAM_BOTON_INI_Y=50.0;
    public static final double BUCEADOR_X=20.0;
    public static final double BUCEADOR_Y=0.0;
    public static final long VELOCIDAD_APARICION_LVL_1=1500;
    public static final Font FUENTE_LETRAS = new Font("Cambria",30);
    public static  boolean HACER_BUZO=true;
}
