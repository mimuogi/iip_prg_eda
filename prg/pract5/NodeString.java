
/**
 * Write a description of class NodeString here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NodeString {
    
    String dada;
    NodeString seguent;
    /**
     * Constructor for objects of class NodeString
     */
    public NodeString(String x) {
        dada = x;
        seguent = null;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public NodeString(String x, NodeString s) {
        dada= x;
        seguent = s;  
    }
}
