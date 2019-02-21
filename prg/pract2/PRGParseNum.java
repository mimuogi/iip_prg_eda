/**
 * PRGParseNum: Pràctica 2 de PRG.
 * 
 * @author (professors IIP - PRG) 
 * @version (curs 2016-17)
 */
public class PRGParseNum {
    
    /*************************** CODI INICIAL  ***************************/    
    public static final char PUNT = '.';  // Separador part fraccionària.
    public static final char COMA = ',';  // Separador part fraccionària.
    public static final char EXP1 = 'e';  // Separador part exponencial.
    public static final char EXP2 = 'E';  // Separador part exponencial.
    public static final int  BASE = 10;   // Base de numeració per defecte.
    
    /**
     * Torna l'enter associat al caràcter - dígit en c.
     * @param c char.
     * @return int, valor del dígit.
     * PRECONDICIÓ: '9' >= c >= '0'
     */
    public static int parseDig(char c) {
        return c - '0';
        // alternativament, es pot fer servir:
        // return Character.getNumericValue(c);
    }
    
    
    /************************* EXEMPLES INICIALS *************************/    
       
    /** Torna la suma dels dígits continguts en la String que rep.
     *  @param s String, cadena de caràcters sobre els quals s'opera.
     *  @return int. 
     */
    public static int sumaDigits(String s) {
        // Cas base: String buida
        if (s.length() == 0) { return 0; }
        else {
        // Cas general: String no buida. Tractar substring posterior.
            char c = s.charAt(0);
            if (c >= '0' && c <= '9') {
                return (c - '0') + sumaDigits(s.substring(1));
            }
            else { return sumaDigits(s.substring(1)); }
        }
    }
        
    /** Torna la suma dels dígits en la String s des de la posició pos.
     *  PRECONDICIÓ: pos >= 0.
     *  @param s String, cadena de caràcters sobre els quals s'opera.
     *  @param pos int, posició en s des d'on es treballa.
     *  @return int. 
     */
    public static int sumaDigits(String s, int pos) {
        // Cas base: String buida
        if (pos >= s.length()) { return 0; }
        else {
        // Cas general: String no buida. Tractar substring posterior.
            char c = s.charAt(pos);
            if (c >= '0' && c <= '9') {
                return (c - '0') + sumaDigits(s, pos + 1);
            }
            else { return sumaDigits(s, pos + 1); }
        }
    } 
    
    /** Torna la suma dels dígits continguts en la String que rep.
     *  @param s String, cadena de caràcters sobre els quals s'opera.
     *  @return int. 
     */
    public static int sumaDigits2(String s) {
        // Cas base: String buida
        if (s.length() == 0) { return 0; }
        else {
        // Cas general: String no buida. Tractar substring anterior.
            int len = s.length();
            char c = s.charAt(len - 1);
            if (c >= '0' && c <= '9') {
                return (c - '0') + sumaDigits(s.substring(0, len - 1));
            }
            else { return sumaDigits(s.substring(0, len - 1)); }
        }
    }  
    
    /****************** PROBLEMA A: parseInt(String s) ******************/
        
    /** 
     * EXEMPLE, "1234", amb anàlisi DESCENDENT:
     *   s                    s.charAt(s.length() - 1) - '0'   
     * "1234"      123*10   +        4                      = 1234 
     * "123"       12*10    +        3                      =  123 
     * "12"        1*10     +        2                      =   12 
     * "1"         1                                        =    1  
     * 
     * EXEMPLE, "1234", amb anàlisi ASCENDENT:
     *   s    s.charAt(0) - '0'   Math.pow(10, s.length() - 1)
     * "1234"       1         *         10^3                = 1000 +
     *  "234"       2         *         10^2                =  200 +
     *   "34"       3         *         10^1                =   30 +
     *    "4"       4                                       =    4 
     *                                                       _____
     *                                                        1234    
     * PRECONDICIÓ: s conté un enter, >= 0, ben format. 
     */
    public static int parseUnsignedInt(String s) {
        if(s.length() == 1) {return parseDig(s.charAt(s.length()-1));}
        else {return parseDig(s.charAt(0)) * (int)Math.pow(10,s.length()-1) 
            + parseUnsignedInt(s.substring(1)); }
        // A COMPLETAR
    }  
    
    /** 
     * PRECONDICIÓ: s conté un enter, pot ser amb signe, ben format. 
     */    
    public static int parseInt(String s) {
        if (s.charAt(0)== '-') return -1 * parseUnsignedInt(s.substring(1));
        else if(s.charAt(0)== '+')return parseUnsignedInt(s.substring(1));
        else return parseUnsignedInt(s);
      }
    
    /**************** PROBLEMA B: parseDouble(String s) *****************/    
    
    /** 
     * PRECONDICIÓ: s conté un nombre en coma flotant ben format. 
     */
    public static int posFracSep(String s) {
        if (s.length() == 0) {return -1;}
        else if(s.charAt(s.length()-1) == PUNT || s.charAt(s.length()-1) == COMA) 
                 {return s.length()-1;}
             else return posFracSep(s.substring(0, s.length()-1));
    
       }
    
       /** 
     * PRECONDICIÓ: s, conté els dìgits de la part fraccionària
     * d'un valor double ben format. 
     */
    public static double parseFrac(String s) {
        //return (double)parseUnsignedInt(s.substring(posFracSep(s),s.length()))/
        //Math.pow (10,s.length());
        return (double)parseUnsignedInt(s)/Math.pow (10,s.length());
    }
    
    /** 
     * PRECONDICIÓ: s conté un double, ben format, pot ser amb signe, 
     * que pot tenir (o no) part fraccionària. No conté part exponencial.
     */  
    public static double parseDoubleBas(String s)   {
        int pos = posFracSep(s);
        if (pos == -1) return (double)parseInt(s);
        else if (pos == 0) {String f = s.substring(pos+1,s.length());
                            return parseFrac(f);}
             else {
                 String f = s.substring(pos+1,s.length());
                 String e = s.substring(0,pos);
                 double partE = parseInt(e);
                 if (partE > 0) return partE + parseFrac(f);
                 else {return partE - parseFrac(f);}
       }
    }
    /*******************  AMPLIACIONS RECOMANADES  ********************/
    
    
    public static int posE (String s){
    if (s.length() == 0) {return -1;}
        else if(s.charAt(s.length()-1) == EXP1 || s.charAt(s.length()-1) == EXP2) 
                 {return s.length()-1;}
             else return posFracSep(s.substring(0, s.length()-1));
    
    }
    
    
}
