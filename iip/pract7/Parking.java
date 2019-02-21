import iipUtil.Instant;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
/** Classe Parking: representa l'ocupacio de les plantes d'un parking
 *  junt amb la tarifa en euros per minut.
 *  @author IIP
 *  @version Curs 2016/2017
 */
public class Parking {
    /** Numero de llocs per planta. */
    public static final int LLOCS_X_PLANTA = 5;
    private Planta[] plantes;
    private double tarifa;
 
    /** Crea un parking a partir del numero de plantes
     *  i la tarifa en euros per minut.
     *  El parking, a l'inici, esta buit.
     *  @param numPlt int, el numero de plantes, numPlt > 0.      
     *  @param tf double, la tarifa en euros per minut, tf > 0.
     */
    public Parking(int numPlt, double tf){
        plantes = new Planta[numPlt];
        for (int i = 0; i < plantes.length; i++) {
                plantes[i] = new Planta(i, LLOCS_X_PLANTA);
        }            
        tarifa = tf;
    }
 
    /** Crea un parking a partir de les dades d'un fitxer
     *  el nom del qual se passa com parametre.<br>
     *  Format del fitxer:
     *  <pre>
     *  plantes
     *  tarifa
     *  planta matricula hores minuts
     *  ...
     *  planta matricula hores minuts
     *  </pre>
     *  Les dades son correctes (no hi ha vehicles ni llocs repetits,
     *  les plantes i les hores son correctes).
     *  @param nomFitx String, el nom del fitxer amb les dades.
     */
    public Parking(String nomFitx) {
        Scanner in = null;
        try {
            in = new Scanner(new File(nomFitx)).useLocale(Locale.US);
            int numPlt = in.nextInt();
            double tf = in.nextDouble();
 
            plantes = new Planta[numPlt];
            for (int i = 0; i < plantes.length; i++) {
                plantes[i] = new Planta(i, LLOCS_X_PLANTA);
            }            
            tarifa = tf;
           
            while (in.hasNext()) {
                int plt = in.nextInt();
                String mat = in.next();
                int h = in.nextInt();
                int m = in.nextInt();
                Ticket t = new Ticket(mat, new Instant(h, m));
                t.setPlanta(plt);
                plantes[plt].estacionar(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n***ERROR***: "
                + "No es pot accedir al fitxer " + nomFitx);
        } finally {
            if (in != null) { in.close(); }
        }          
    }
 
    /** Torna el numero de plantes.
     *  @return int, numero de plantes del parking.
     */
    public int getNumPlantes() {
        return plantes.length;
    }
 
    /** Torna la tarifa.
     *  @return double, tarifa del parking en euros per minut.
     */
    public double getTarifa() {
        return tarifa;
    }
 
    /** Actualitza la tarifa.
     *  @param tf double, la nova tarifa
     *  (en euros per minut), tf > 0.
     */
    public void setTarifa(double tf) {
        if (tf > 0) {
            tarifa = tf; }
    }
 
    /** Comprova si el parking esta ple.
     *  @return boolean, true si ple, o false en cas contrari.
     */
    // Usa estaPlena() de Planta
    public boolean estaPle() {
        boolean comprovar = true;
        int i = 0;
        while (comprovar && ( i < plantes.length)) {
            comprovar = plantes[i].estaPlena();
            i++;
        }
        if (i >=  plantes.length) {
            return true;
        }
        else { return false; }
    }
 
    /** Donat el ticket associat a un vehicle, estaciona el vehicle
     *  en la planta de numero menor amb llocs lliures, en el lloc
     *  de numero menor.
     *  Precondicio: parking amb llocs lliures i vehicle no present.
     *  @param t Ticket, el ticket del vehicle a estacionar.
     */
    // Usa estaPlena() i estacionar(Ticket) de Planta i
    // setPlanta(int) de Ticket
    public void estacionar(Ticket t) {
        int i = 0;
        while (plantes[i].estaPlena()) { i++;}
        plantes[i].estacionar(t);
        t.setPlanta(i);
    }
   
    /** Donats el ticket associat a un vehicle i un numero de planta
     *  de preferencia, estaciona el vehicle en aquesta planta, si hi ha
     *  llocs lliures, o si no, en el planta mes propera amb llocs lliures,
     *  seguint l'estrategia del butlleti.  
     *  Precondicio: parking amb llocs lliures i vehicle no present.      
     *  @param t Ticket, el ticket del vehicle a estacionar.      
     *  @param pref int, la planta de preferencia.
     */
    // Usa estaPlena() i estacionar(Ticket) de Planta i
    // setPlanta(int) de Ticket
    public void estacionar(Ticket t, int pref) {
        int mes = pref;
        int menys = pref;
        boolean trobat = false;
        while (!trobat) {
            if (mes < plantes.length && !(plantes[mes].estaPlena())){
                pref = mes;
                trobat = true;
            }
            if (menys > 0 && !(plantes[menys].estaPlena())) {
                pref = menys;
                trobat = true;
            }
            mes++;
            menys--;
        }
        plantes[pref].estacionar(t);
        t.setPlanta(pref);
    }
 
    /** Comprova si un vehicle de matricula donada esta al parking.
     *  @param m String, la matricula del vehicle a buscar.
     *  @return Ticket, el ticket associat al vehicle de matricula
     *  donada, si es troba, o null si no es troba.
     */
    // Usa buscarTicket(String) de Planta
    public Ticket buscarTicket(String m) {
        int i = 0;
        while (i < plantes.length) {
            if (plantes[i].buscarTicket(m) != null) {
                return plantes[i].buscarTicket(m);
            }
            i++;
        }
        return null;
    }
   
    /** Retira del parking, donada una hora d'eixida, el vehicle
     *  associat al ticket donat i torna l'import a pagar.
     *  @param t Ticket, el ticket associat al vehicle a retirar.
     *       Precondicio: sempre esta.
     *  @param hEix Instant, l'hora d'eixida del vehicle.
     *       Precondicio: posterior a l'hora d'entrada.
     *  @return double, import en euros a pagar.
     */
    // Usa retirar(int, Instant) de Planta
    public double retirar(Ticket t, Instant hEix) {
        int plz = t.getLloc();
        int min = plantes[t.getPlanta()].retirar(plz, hEix);
        double res = min * tarifa;
        return res;
    }
   
 
    /** Buida el parking, retirant tots els vehicles, suposant  
     *  que son les 23:59, i torna l'import total.
     *  @return double, import total en euros a pagar per
     *  tots els vehicles retirats del parking.
     */
    // Usa retirarTots(Instant) de Planta
    public double buidarParking() {
        Instant eixida = new Instant(23,59);
        double res = 0;
        for (int i = 0; i < plantes.length; i++) {
            res += plantes[i].retirarTots(eixida);
        }
        return res*tarifa;
    }
 
    /** Torna un String que representa l'ocupacio del parking,
     *  amb 'X' ocupada, amb ' ' lliure.
     *  Ha de col.locar una primera linia amb els numeros de
     *  lloc corresponents.<br>
     *  Exemple: el seguent String representa un parking, amb
     *  3 plantes i 5 llocs per planta, en el que estan ocupats:
     *  en la planta 0, els llocs 0, 1 i 3;
     *  en la planta 1, els llocs 1, 2 i 4;
     *  en la planta 2, els llocs 0 i 1.
     *  <pre>
     *          "      0   1   2   3   4
     *             0   X   X       X    
     *             1       X   X       X
     *             2   X   X             " </pre>
     *  @return String, representacio del parking.
     */
    // Usa toString() de Planta
    public String toString() {
        String res = "      0   1   2   3   4 \n";
        for (int i = 0; i < plantes.length; i++) {
            res += plantes[i].toString();
        }
        return res;
    }
   
}