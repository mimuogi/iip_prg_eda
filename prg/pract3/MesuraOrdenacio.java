import java.util.*;
/** Classe MesuraOrdenacio: Estudi empiric de costs dels metodes d'ordenacio.
 *  @author PRG - ETSInf
 *  @version Curs 2016-2017
 */
class MesuraOrdenacio {
    // Constants que defineixen els parametres de mesura
    public static final int MAXTALLA = 10000, INITALLA = 1000; 
    public static final int INCRTALLA = 1000, REPETICIONS = 200,
    REP_CASMILLOR = 2000000;
    public static final double NMS = 1e3;
  
    /* Genera un array d'int de talla t amb valors compresos entre 0 i t-1.
     * @param t int, la talla.
     * @result int[], l'array generat.
     */
    private static int[] crearArrayAleatori(int t) { 
        // Completar  
        int[] a = new int[t];
        for (int i = 0; i < a.length; i++)
          a[i]= (int) (Math.random()*t); 
        return a;
    }
  
    /* Genera un array d'int de talla t ordenat de forma creixent.
     * @param t int, la talla.
     * @result int[], l'array generat.
     */
    private static int[] crearArrayOrdCreixent(int t) { 
        // Completar 
        int[] a = new int[t]; 
        
        for (int i = 0; i < a.length; i++)
         a[i]= i;
        return a;
    }

    /* Genera un array d'int de talla t ordenat de forma decreixent.
     * @param t int, la talla.
     * @result int[], l'array generat.
     */
    private static int[] crearArrayOrdDecreixent(int t) { 
        // Completar   
        int[] a = new int[t]; 

        for (int i = 0, j = t-1; i < a.length; i++, j--)
         a[i]= j;
        return a;
    }

    public static void mesuraSeleccio() { 
        // Completarlong 
        long ti = 0, tf = 0, tt = 0;
        System.out.println("# Selecció. Temps en microsegons");
        System.out.printf("# Talla      Promedi\n");
        System.out.printf("#------------------\n");
        int[]a = crearArrayAleatori(100);
        
         for (int talla=INITALLA; talla<= MAXTALLA; talla+=INCRTALLA){
          for(int i=1; i <=REPETICIONS; i++){
          a = crearArrayAleatori(talla);
          ti = System.nanoTime();
          AlgorismesMesurables.seleccio(a);
          tf = System.nanoTime();
          tt += (tf-ti);
         }
         double tm= (double)tt/NMS;
         System.out.printf(Locale.US, "%8d %10.3f \n", 
                              talla, tm/REPETICIONS);
         }
      
    }

    public static void mesuraInsercio() { 
        // Completar
        long ti = 0, tf = 0, tt = 0; 
        System.out.println("# Selecció. Temps en microsegons");
        System.out.printf("# Talla    tMillor    tPitjor    tPromedi\n");
        System.out.printf("#-----------------------------------------\n");
       //int[]a = crearArrayAleatori(100);
        //CAS MILLOR
         for (int talla=INITALLA; talla<= MAXTALLA; talla+=INCRTALLA){
             tt = 0;
         for(int i=1; i <=REPETICIONS*10; i++){
          int[]a = crearArrayOrdCreixent(talla);
          ti = System.nanoTime();
          AlgorismesMesurables.insercio(a);
          tf = System.nanoTime();
          tt += (tf-ti);
          
         }
         double tmillor= (double)tt/NMS;
        //CAS PITJOR
         tt = 0;
        for(int i=1; i <=REPETICIONS; i++){
          int[]a  = crearArrayOrdDecreixent(talla);
          ti = System.nanoTime();
          AlgorismesMesurables.insercio(a);
          tf = System.nanoTime();
          tt += (tf-ti);
          
         }
         double tpitjor= (double)tt/NMS;
         
        //CAS PROMEDI
        tt = 0;
          for(int i=1; i <=REPETICIONS; i++){
          int[]a = crearArrayAleatori(talla);
          ti = System.nanoTime();
          AlgorismesMesurables.insercio(a);
          tf = System.nanoTime();
          tt += (tf-ti);
          
         }
         double tm= (double)tt/NMS;
         
        
         System.out.printf(Locale.US, "%8d %10.3f %10.3f %10.3f\n", 
                              talla, tmillor/(REPETICIONS*10), tpitjor/REPETICIONS, tm/REPETICIONS);
        }
        
      }
    

  
    public static void mesuraMergeSort() { 
        // Completar
    }

    public static void us() {
        System.out.println("Us: java MesuraOrdenacio numero_algorisme");
        System.out.println("   on numero_algorisme es:");
        System.out.println("   1 -> Insercio");
        System.out.println("   2 -> Seleccio");
        System.out.println("   3 -> MergeSort");
    }

    public static void main(String[] args) {        
        if (args.length != 1) {
            us();            
        }
        else {
            try {
                int a = Integer.parseInt(args[0]);
                switch (a) {
                    case 1: 
                        mesuraInsercio();
                        break;
                    case 2: 
                        mesuraSeleccio();
                        break;
                    case 3: 
                        mesuraMergeSort();
                        break;
                    default: 
                        us();
                }
            } catch (Exception e) {
                us(); 
            }
        }
    }
}
