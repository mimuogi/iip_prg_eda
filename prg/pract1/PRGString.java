
/** 
 * @author Arnau, pract 
 * @version 24/02/2017
 */
public class PRGString
{
    /** Una cadena a és prefixe de una altra cadena b quan tots els caràcters 
     * de a estan consecutius, en el mateix ordre original,
     * al començament de b.
     * 
     * S'ha de implementar el mètode esPrefixe, mitjançant el qual es podrà 
     * comprovar si una cadena és prefixe d’una altra.
     * 
     * El mètode retornarà true en cas de que a siga prefixe de b,
     * i false en cas contrari.
     * 
     * PRECONDICIÓ: una cadena no pot ser prefixe d’una altra si
     * la primera és de longitud més gran que la segona.
     * 
     * @param String a, b.
     */
    public static boolean esPrefixe(String a, String b){
        //Cas base: quan a no té cap caràcter
        if(a.length() == 0){return true;}
        else if(a.length() > b.length()){return false;}
        //Cas general
        else{
            if(a.charAt(0) == b.charAt(0)){return esPrefixe(a.substring(1), b.substring(1));}
            else{return false;}
        }
    }
    
    /** Una cadena a és subcadena de una altra cadena b quan tots els caràcters de a estan consecutius, en el mateix ordre
     * original, en algun lloc de b.
     * 
     * El mètode retornarà true en cas de que a siga subcadena de b,
     * i false en cas contrari.
     * 
     * El mètode esSubcadena es realitza en funció de esPrefixe. 
     * 
     * PRECONDICIÓ: una cadena no pot ser subcadena d’una altra si la primera és de 
     * longitud més gran que la segona.
     * 
     * @param String a, b.
     */
    public static boolean esSubcadena(String a, String b){
        //Cas base: quan a no té cap caràcter || a té major longitud que b
        if(a.length() == 0){return true;}
        else if(a.length() > b.length()){return false;}
        //Cas general
        else{
            if(esPrefixe(a,b)){return true;}
            else{return esSubcadena(a,b.substring(1));}
        }
    }
    
    /** Un String és un palíndrom quan aquest es llegeix igualment en un sentit
     * (d’esquerra a dreta) que en l’altre (de dreta a esquerra).
     * 
     * L’existència d’espais en blanc, signes de puntuació, apòstrofs i guions, 
     * accents, així com caràcters en majúscules i minúscules no són
     * significatius per determinar si una frase és o no un palíndrom.
     * 
     * El mètode torna true si el String donat és un palíndrom,
     * i false en cas contrari.
     * 
     * CONSIDERACIÓ: Els caràcters amb accents, espais en blanc i caràcters no
     * pronunciable no es tenen en compte.
     * 
     * @param String a
     */ 
    public static boolean esPalindromSA(String a){
        //Cas base: quan a no té cap caràcter
        if(a.length() <= 1){return true;}
        //Cas general
        else{
            if(!Character.isLetter(a.charAt(a.length() - 1))){return esPalindromSA(a.substring(0,a.length() - 1));}
            else if(!Character.isLetter(a.charAt(0))){return esPalindromSA(a.substring(1));}
            else{
                char izq = Character.toUpperCase(a.charAt(0));
                char der = Character.toUpperCase(a.charAt(a.length()-1));
                if(izq == der){return esPalindromSA(a.substring(1,a.length()-1));}
                else{return false;}
            }
        }
    }
    
    private static final String AMB = "ÁÉÍÓÚÀÈÏÒÜ";
    private static final String SENSE = "AEIOUAEIOU";
    public static boolean esPalindrom(String a){
        if(a.length() <= 1){return true;}
        return false;
    }
    
}
