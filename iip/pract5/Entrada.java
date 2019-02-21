import iipUtil.Instant;
/**
 *  Classe Entrada.   
 *  
 *  @author (IIP. Grau en Informatica. ETSINF, UPV)   
 *  @version Curs 2016/17
 */
public class Entrada {
    // Definicio CONSTANTS per a calcular el preu de l'entrada:
    /** Preu Base a partir del qual es calcula el preu final */
    public static final double PREU_BASE = 7.60;
   
    /** Edat senior */
    public static final int SENIOR = 65;
	/** Descompte per espectador major de 65 anys (SENIOR) */
    public static final double DESCOMPTE_SENIOR = 0.3;
   
    /** Descompte per ser el dia de l'espectador */
    public static final double DESCOMPTE_DIA_ESPECTADOR = 0.8;
  
    /** Descompte per ser festiu */
    /* A COMPLETAR */
    public static final double DESCOMPTE_FESTIU = 1.2;
    /** Descompte per ser vespra de festiu */
    /* A COMPLETAR */
    public static final double DESCOMPTE_VESPRA = 1.1;
    /** Descompte per ser client */
    /* A COMPLETAR */
    public static final double DESCOMPTE_CLIENT = 0.2;
    // Definicio d'ATRIBUTS d'INSTANCIA PRIVATS
    /* A COMPLETAR */ 
    private String titol;
    private String cinema;
    private Instant horaSessio;
    /** Crea una Entrada amb titol t, cinema c, hores hs,
     *  i minuts ms de la sessio.
     */
    public Entrada(String t, String c, int hs, int ms) {
      titol = t;
      cinema = c;
      horaSessio = new Instant (hs,ms);
     
      
      /* A COMPLETAR */
   
    }  

    /** Torna el titol de la pel.licula de l'Entrada. */
    public String getTitol() { return titol; }
    
    /** Torna el cinema de l'Entrada. */
    public String getCinema() { /* A COMPLETAR */ return cinema;}
 
    /** Torna l'hora de la sessio de l'Entrada. */
    public Instant getHoraSessio() { return horaSessio; }
   
    /** Actualitza a t el titol de la pelicula de l'Entrada. */
    public void setTitol(String t) { titol = t; }
    
    /** Actualitza a c el cinema de l'Entrada. */
    public void setCinema(String c) { /* A COMPLETAR */cinema = c;}
    
    /** Actualitza a hs l'hora de la sessio de l'Entrada. */
    public void setHoraSessio(Instant hs) { horaSessio = hs; }
    
    /** Torna un String amb les dades de l'Entrada. 
     *  El format de l'eixida es com el de l'exemple seguent:
     *   "Anochece en La India", projectada en Cines Babel, a les 22:30
     *   Preu base: 7.60 euros
     */
    public String toString() {
        return "\"" + titol + "\", projectada en " + cinema + ", a les " 
            + horaSessio + "\nPreu base: " + PREU_BASE + " euros";
    }

    /** Comprova si o es una Entrada amb les mateixes dades 
     *  que l'Entrada actual. */
    public boolean equals(Object o) {
      /* A COMPLETAR */ boolean resposta = o instanceof Entrada
      && ((Entrada)o).titol == this.titol
      && ((Entrada)o).cinema == this.cinema
      && ((Entrada)o).horaSessio == this.horaSessio;
      return resposta;
       
    }
   
    /** Calcula el preu final de l'Entrada aplicant al preu base 
     *  els descomptes segons l'edat de l'espectador, el dia de 
     *  la sessio (espectador, festiu o vespra) i si te targeta 
     *  de client o no.
     */ 
    public double preuFinal(int edat, boolean diaEspec, boolean festiu, 
        boolean vespra, boolean tClient) {
            double preuFinal;
    if (edat >= 65) { preuFinal = PREU_BASE * DESCOMPTE_SENIOR;}
    else if (diaEspec) {preuFinal = PREU_BASE * DESCOMPTE_DIA_ESPECTADOR;}
         else if (festiu) {preuFinal = PREU_BASE * DESCOMPTE_FESTIU;}
              if (vespra) {preuFinal = PREU_BASE * DESCOMPTE_VESPRA;}
              else {preuFinal = PREU_BASE;}
                  if (tClient) {preuFinal = preuFinal * DESCOMPTE_CLIENT;}
                  else {preuFinal = preuFinal;}
    return preuFinal;
      /* A COMPLETAR */ 
   }  
}
