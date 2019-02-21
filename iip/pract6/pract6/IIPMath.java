/**
 * La classe IIPMath implementa algunes operacions matematiques:
 * IIPMath.sqrt(double) i IIPMath.log(double).
 *
 * @author (IIP-PRG-DSIC-ETSINF)
 * @version (Year 2016-17)
 */
public class IIPMath {
    
    // A COMPLETAR
    public final static double LOG_2 = 0.6931471805599453;
        
    /**
     * Torna l'arrel quadrada de x >= 0, amb error epsilon > 0. 
     * A COMPLETAR COMENTARI
     */
    
    public static double sqrt(double x, double epsilon) {
        // A COMPLETAR
       
        double tact = (x+1)/2;
        double tant = tact + 1;
       
        while ((tant-tact) >= epsilon) {
            tant = tact;
            tact = (tant + (x/tant))/2.0;
            
        }
            
        return tact;
    }            

    /**
     * Torna l'arrel quadrada de x >= 0, amb error 1e-15. 
     * A COMPLETAR COMENTARI
     * @param x. El valor, que ha de ser igual o major que zero.
     * @return double. L’arrel de x amb error màxim 1e-15.
     */
    public static double sqrt(double x) {    
        // A COMPLETAR
        return sqrt(x,1e-15);
        //double tact = (x+1)/2;
        //double tant = tact + 1;
       
        //while ((tant-tact) > 1e-15) {
            //tant = tact;
            //tact = (tant + (x/tant))/2.0;
            
        //}
            
        //return tact;
        
    }  
                    
    /* ******************************************************** */
    /* ******************************************************** */
    /* ******************************************************** */
                
    /**
     * Torna log(z), 1/2 <= z < 1, amb error epsilon > 0.
     * A COMPLETAR COMENTARI
     */
    public static double logBase(double z, double epsilon) {
        // A COMPLETAR
        double y = (1-z)/(1+z);
        double termAct = y;
        double sum = termAct;
        double termAnt = termAct +1;
        double k=1; 
        double error = epsilon +1;
        while (  termAct >= epsilon) {
            termAnt = termAct;
            termAct = termAnt*(y*y)*(2*k-1)/(2*k+1);
            k++;
            sum += termAct;
       
        }
        return sum * (-2);  
    }
          
    /**
     * Torna log(x), x > 0, amb error epsilon > 0.
     * A COMPLETAR COMENTARI
     */
    public static double log(double x, double epsilon) {
        // A COMPLETAR
        int m = 0;
        if (x>=1) { 
            while (x>= 1) {x= x/2; m++;}
            return m * LOG_2 + logBase (x, epsilon);
        }
        if (x< 1/2) {
            while (x < 1/2) {x= x*2; m++;}
            return  -m * LOG_2 + logBase (x, epsilon);
        } 
        else {return logBase(x,epsilon);}
    }
    
    
    /**
     * Torna log(x), x > 0, amb error 1e-15.
     * A COMPLETAR COMENTARI
     */
    public static double log(double x) {    
        return log(x,1e-15);
        
    }
                        
}