import iipUtil.Instant;

/** Classe Planta: representa l'ocupacio d'una planta de parking,
 *  amb els tickets associats als vehicles estacionats en
 *  la mateixa.
 *  @author IIP
 *  @version Curs 2016/2017
 */
public class Planta {
    private Ticket[] llocs;
    private int llocsLliures;
    private int numPlanta;

    /** Crea una Planta donats un numero de planta i un numero 
     *  de llocs.
     *  La planta, a l'inici, esta buida (sense vehicles).
     *  @param numP int, el numero de planta, numP >= 0.
     *  @param numLlocs int, el numero de llocs, numLlocs > 0.
     */
    public Planta(int numP, int numLlocs) {
        llocs = new Ticket[numLlocs];
        llocsLliures = numLlocs;
        numPlanta = numP;        
    }
    
    /** Torna el numero de minuts trancorreguts des de l'entrada del
     *  vehicle que ocupa un lloc donat fins una hora d'eixida donada,
     *  actualitzant la planta.
     *  @param plz int, el numero de lloc. 
     *    Precondicio: 0 <= plz < llocs.length i llocs[plz] != null.
     *  @param hEix Instant, l'hora d'eixida. 
     *    Precondicio: posterior a l'hora d'entrada del vehicle.
     *  @return int, numero de minuts que el vehicle ha estat
     *  al parking.
     */
    public int retirar(int plz, Instant hEix) {
        int numMin = hEix.aMinuts() - llocs[plz].getEntrada().aMinuts();
        llocs[plz] = null;
        llocsLliures++;
        return numMin;
    }

    /** Allibera tots els llocs ocupats per aquells vehicles 
     *  la matricula dels quals conte una sequencia de lletres  
     *  donada (lletresMat), tornant el numero total de minuts  
     *  que els vehicles han estat en la planta fins una hora 
     *  d'eixida donada (hEix).
     *  @param lletresMat String, sequencia de lletres d'una matricula.
     *  @param hEix Instant, l'hora d'eixida. 
     *    Precondicio: posterior a l'hora d'entrada de tots 
     *    els vehicles de la planta.
     *  @return int, el numero total de minuts transcorreguts.
     */
    public int alliberar(String lletresMat, Instant hEix) {        
        int totMin = 0;    
        for(int i=0; i<llocs.length;i++){
         if (llocs[i]!=null && llocs[i].getMatricula().contains(lletresMat)) {
            totMin += retirar(i,hEix);
            }
        }     
        return totMin;
    }
}
