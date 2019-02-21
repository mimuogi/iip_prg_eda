import java.util.Scanner;
/**
 *  Classe Practica3.
 *  Una primera classe amb lectura de dades des de teclat, 
 *  i us d'operacions amb int, long, Math i String.
 *  Conte tres errors de compilacio.
 *  @author IIP 
 *  @version Curs 2016-17
 */
{ public class Practica3
    
    public static void main(String[] args){
        Scanner teclat=new Scanner(System.in);
        System.out.println("Lectura de teclat d'una hora.");
        system.out.print(" -> Introduiu les hores (entre 0 i 23): ");
        int h = teclat.nextInt();
        System.out.print(" -> Introduiu els minuts (entre 0 i 59): ");
        int m = teclat.nextInt();
        System.out.println("Hora introduida: "  h + " i " + m + ".");      
    }    
 
}
