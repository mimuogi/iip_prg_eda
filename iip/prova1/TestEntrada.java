import java.util.Scanner;
import iipUtil.Instant;
/**
 * Clase TestEntrada. 
 * 
 *  @author IIP - ETSINF - UPV 
 *  @version Curs 2016-17
 */
public class TestEntrada {
    public static void main(String[] args) {
        // 1. Crea una entrada (EntradaProf) per a la pel.licula "Inferno" projectada 
        //    als cines "Lys" en la sessio de les 18:30.
        EntradaProf E = new EntradaProf ("Inferno", "Lys", 18, 30);
        /* COMPLETAR */
        
        // Lectura d'hores i minuts
        Scanner teclat = new Scanner(System.in);
        System.out.print("   -> Introdueix les hores (entre 0 i 23): ");
        int h = teclat.nextInt();
        System.out.print("   -> Introdueix els minuts (entre 0 i 59): ");
        int m = teclat.nextInt();
        // 2. Una vegada llegides les dades d'hores i minuts des del teclat:
        // (a) Comprove si son correctes (0 <= h < 24 i 0 <= m < 60) 
        // (b) Si ho son, ha de crear un Instant a partir d'aquestes dades 
        //     que represente l'hora d'arribada, cridar al metode 
        //     preuProporcional i mostrar por pantalla el preu calculat
        //     precedit del missatge "El preu es: ".
        // (c) Si les dades no foren correctes, ha de mostrar per pantalla
    	//     el missatge "Dades incorrectes!!"
        if (0 <= h && h < 24 && 0 <= m && m < 60){
          double preu;
          Instant horaArribada = new Instant(h,m);
          preu = E.preuProporcional(horaArribada);
          System.out.println("El preu es: " + preu + " €");}
          
        else { System.out.println("Dades incorrectes!!");}
    	/* COMPLETAR */   
    	      
    }
}
