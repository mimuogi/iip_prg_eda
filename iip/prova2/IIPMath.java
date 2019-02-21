/**
 * Classe IIPMath. Exercici 1 - Torn 1
 *
 * @author IIP
 * @version Parcial 2 - Curs 2016-17
 */
public class IIPMath {
    /** Torna el numero de termes que s'han de 
     *  calcular de la serie seguent:
     *     a_1 = Arrel quadrada de 2 
     *     a_i = Arrel quadrada de 2 * a_(i-1)
     *  fins obtenir-ne un amb valor major o 
     *  igual que 2 - epsilon.
     *  PRECONDICIO: 0 < epsilon
     */    
    public static int numTermes(double epsilon) {
        double tant = Math.sqrt(2);
        double tact = Math.sqrt(2 * tant);
        int i = 2;
        while (tact < 2-epsilon) {
            tant = tact;
            tact = Math.sqrt(2 * tant);
            i++;
        }  
        return i;
}
}