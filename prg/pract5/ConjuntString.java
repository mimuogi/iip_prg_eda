/**
 * Classe ConjuntString. Implementacio d'un
 * conjunt de String mitjancant una sequencia
 * enllasada ordenada lexicograficament.
 *
 * @author (PRG. ETSINF. UPV)
 * @version (Curs 2016/17)
 */
public class ConjuntString {
    
    private NodeString primer;
    private int talla;
    
    /**
     * Crea un conjunt buit.
     */
    public ConjuntString() {
        primer = null;
        talla = 0;
    }

    /**
     * Insereix s en el conjunt.
     * Si s ja pertany al conjunt, aquest no canvia.
     *
     * @param s String. Element que s'insereix en el conjunt.
     */
    public void inserir(String s) {
        NodeString aux = this.primer;
        NodeString ant = null;
        int compara = -1;
        while (aux!= null && compara < 0){
            compara=aux.dada.compareTo(s);
               if(compara < 0){
                    ant=aux;
                    aux=aux.seguent;
            }
        }
        
        if(aux == null){
            if(ant != null){
             NodeString ns = new NodeString(s,null);
             ant.seguent = ns; talla++;}
            else{
                NodeString ns = new NodeString(s,null);
                this.primer = ns; talla++;}
        }
        else {
            if(compara>0){
                if(ant == null){
                    NodeString na = new NodeString(s,aux);
                    this.primer = na; talla++;
                }
               else { 
                 NodeString na = new NodeString(s,aux);
                 ant.seguent = na; talla++;
                }
            }
        }   
    }
    
    /**
     * Comprova si s pertany al conjunt.
     *
     * @param s String.
     * @return true sii s pertany al conjunt.
     */
    public boolean pertany(String s) {
        NodeString aux = this.primer;
        NodeString ant = null;
        boolean trobat = false;
        int compara = -1;
        while (aux!= null && !trobat && compara < 0){
           compara=aux.dada.compareTo(s);
            if(compara<0 ){
               ant=aux;
               aux=aux.seguent;
                }else if (compara==0) {
                trobat=true;
                }
        }
        return trobat;
    }

    
    /**
     * Elimina s del conjunt.
     * Si s no pertany al conjunt, el conjunt no canvia.
     *
     * @param s String.
     */
    public void eliminar(String s) {
        NodeString aux = this.primer;
        NodeString ant = null;
        boolean trobat = false;
        int compara = -1;
        while (aux!= null && !trobat && compara < 0 ){
           compara=aux.dada.compareTo(s);
           if(compara<0 ){
               ant=aux;
               aux=aux.seguent;
            }
           if (compara==0) {
                trobat=true;
                }
        }
        if(trobat){
                if(this.primer == aux){
                    this.primer= aux.seguent;
                    talla--;
                }
               else { 
                 ant.seguent=aux.seguent;
                 talla--;
                }
            }
    }
    
    /**
     * Retorna la talla o cardinal del conjunt.
     * @return la talla del conjunt.
     */
    public int talla() { 
        // A completar        
        return talla; 
    }
    
    
    /**
     * Retorna el conjunt interseccio del conjunt i d'altre.
     *
     * @param altre ConjuntString.
     * @return el conjunt interseccio.
     */
    public ConjuntString interseccio(ConjuntString altre) {
        NodeString aux = this.primer;
        NodeString aux2 = altre.primer;
        NodeString ultResult = null;
        ConjuntString res = new ConjuntString();
        while(aux !=null && aux2 != null){
            if (aux.dada.compareTo(aux2.dada)< 0) aux = aux.seguent;
            else if (aux.dada.compareTo(aux2.dada)> 0) aux2 = aux2.seguent;
            else 
            if(aux.dada.compareTo(aux2.dada) == 0) {
                if(res.primer == null) {
                    ultResult = new NodeString(aux.dada);
                    res.primer = ultResult;
                } 
                else {
                    ultResult.seguent= new NodeString(aux.dada);
                    ultResult = ultResult.seguent;
                }
                aux2 = aux2.seguent;
                aux = aux.seguent;
                res.talla++;
            }
        }
     
        return res;
    }
    
    
    /**
     * Retorna el conjunt unio del conjunt i d'altre.
     *
     * @param altre ConjuntString.
     * @return el conjunt unio.
     */
    public ConjuntString unio(ConjuntString altre) {
        NodeString aux = this.primer;
        NodeString aux2 = altre.primer;
        NodeString ultResult = null;
        ConjuntString res = new ConjuntString();
        while (aux !=null && aux2 != null){
          if (aux.dada.compareTo(aux2.dada)< 0) {
              if(res.primer == null) {
                    ultResult = new NodeString(aux.dada);
                    res.primer = ultResult;
                    aux=aux.seguent;
                } else {
                    ultResult.seguent = new NodeString(aux.dada);
                    ultResult = ultResult.seguent;
                    aux=aux.seguent;
                }
            }
            else if (aux.dada.compareTo(aux2.dada)> 0) {
                if(res.primer == null) {
                    ultResult = new NodeString(aux2.dada);
                    res.primer = ultResult;
                    aux2=aux2.seguent;
                } else {
                    ultResult.seguent = new NodeString(aux2.dada);
                    ultResult = ultResult.seguent;
                    aux2=aux2.seguent;
                }
            }
            else if(aux.dada.compareTo(aux2.dada) == 0) {
                    if(res.primer == null) {
                    ultResult = new NodeString(aux.dada);
                    res.primer = ultResult;
                    }    
                    else {
                    ultResult.seguent= new NodeString(aux.dada);
                    ultResult = ultResult.seguent;
                }
                  aux2 = aux2.seguent;
                  aux = aux.seguent;
            }
            res.talla++;
          }

        while (aux !=null){
            if(res.primer == null) {
                    ultResult = new NodeString(aux.dada);
                    res.primer = ultResult;
                } 
                else {
                    ultResult.seguent = new NodeString(aux.dada);
                    ultResult = ultResult.seguent;
                }
            aux=aux.seguent;
            talla++;
        }
        
        while (aux2 !=null){
          if(res.primer == null) {
             ultResult = new NodeString(aux2.dada);
             res.primer = ultResult;
          } else {
              ultResult.seguent = new NodeString(aux2.dada);
              ultResult = ultResult.seguent;
          }
          aux2=aux2.seguent;     
          talla++;
            }
        return res;
    }
    
        
    /**
     * Retorna el llistat de les Strings en el conjunt, en ordre
     * lexicografic, i separats per canvis de linia.
     * Si el conjunt es buit retorna "", la String buida.
     *
     * @return llistat dels elements del conjunt.
     */
    public String toString() {
        String result = "";
        NodeString aux = this.primer;
        while (aux != null) {
            result += aux.dada + "\n"; 
            aux = aux.seguent; 
        }
        return result;
    }
    
}
