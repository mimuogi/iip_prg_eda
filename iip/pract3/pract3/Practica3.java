import java.util.Scanner;
/**
 *  Classe Practica3.
 *  Una primera classe amb lectura de dades des de teclat, 
 *  i us d'operacions amb int, long, Math i String.
 *  Conte tres errors de compilacio.
 *  @author IIP 
 *  @version Curs 2016-17
 */
public class Practica3{
    
    public static void main(String[] args){
        Scanner teclat=new Scanner(System.in);
        System.out.println("Lectura de teclat d'una hora.");
        System.out.print(" -> Introduiu les hores (entre 0 i 23): ");
        int h = teclat.nextInt();
        System.out.print(" -> Introduiu els minuts (entre 0 i 59): ");
        int m = teclat.nextInt();
        System.out.println("Hora introduida: " + h + " i " + m + ".");  
        
        // part 1
        String hh = "0" + h;
        hh = hh.substring (hh.length()-2);
        
        String mm = "0" + m;
        mm = mm.substring (mm.length()-2);
        System.out.println("Hora introduida: " + hh + ":" + mm + ".");
         
        // part 2 
        long tMinTotal = System.currentTimeMillis()/ (60*1000);
        int tMinActual = (int) (tMinTotal%(24*60));
        int minact = tMinActual%60;
        int horact = tMinActual/60;
        String hha = ("0" + horact);
        hha = hha.substring (hha.length()-2);
        String mma = ("0" + minact);
        mma = mma.substring (mma.length()-2);
        System.out.println ("Hora actual: " + hha + ":" + mma + "." + "Temps UTC");
        int minDiferencia = ((horact - h)*60) + (minact-m);
        System.out.println ("Diferencia en minuts: " + Math.abs(minDiferencia));
        
        
    }    
 
}