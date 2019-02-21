package aplicaciones.paginasWeb;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.lineales.LPIColaPrioridad;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.io.IOException;

/**
 * Clase que gestione la información procedente del análisis de un
 * número cualquiera de páginas Web con el fin de servir como
 * sistema de recomendación.
 * Permite la siguiente funcionalidad:
 * 1.- Analizar una lista de direcciones url que se proporcionan en un fichero de texto
 * 2.- Analizar una dirección url
 * 3.- Recomendar la página que contenga más enlaces externos, y,
 *     a igualdad de enlaces, menor número de líneas
 * 4.- Recomendar las k páginas que contengan más enlaces externos, y,
 *     a igualdad de enlaces, menor número de líneas.
 * 
 * @author (EDA) 
 * @version (Curso 2016-2017)
 */
public class Recomendacion {
    public static String DIR_FICH_PAGS = "aplicaciones" + File.separator
        + "paginasWeb" + File.separator;
    protected int noPags;   
    //COMPLETAR: Declarar como atributo protected la Cola de Prioridad (qp) de ReferenciasWeb
    ColaPrioridad<ReferenciasWeb> qp;

    /**
     * Construye el sistema de recomendación sin ninguna página analizada hasta el momento
     */
    public Recomendacion() {
        noPags = 0;
        qp = new LPIColaPrioridad();
    }

    /**
     * Construye el sistema de recomendación a partir del análisis de las 
     * páginas cuyas url se encuentran en el fichero de texto nomFile
     * @param nomFile, nombre del fichero que contiene las urls
     * Si se produce algún problema en la lectura del fichero devuelve el sistema vacio
     */
    public Recomendacion(String nomFile) {
        this();
        try {
            Scanner pagsWebs = new Scanner(new File(DIR_FICH_PAGS + nomFile));            
            while (pagsWebs.hasNext()) {
                String nomPag = pagsWebs.nextLine();
                ReferenciasWeb pweb = anyadirPagina(nomPag);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Problemas al abrir el fichero " + nomFile);
        }
    }

    /**
     * Analiza la página nomPagina y la incorpora al sistema de recomendación
     * 
     * @param  nomPagina, la página a añadir
     * @return el resultado del análisis de la página o null si no se ha podido analizar 
     */
    public ReferenciasWeb anyadirPagina(String nomPagina) {
        ReferenciasWeb res = null;
        try{
            res = new ReferenciasWeb(nomPagina);
            qp.insertar(res);
            noPags++;
        } catch (MalformedURLException e) {
            System.out.println("La URL no es válida " + nomPagina);
        } catch (IOException e) { 
            System.out.println("No se puede conectar o acceder a la url " + nomPagina);
        } finally {
            return res;
        }
    }

    /**
     * Devuelve la página recomendada atendiendo al criterio
     * La elimina del sistema para no volver a recomendarla.
     * Precondición: el sistema debe contener al menos una página para recomendar
     * @return la página recomendada
     */
    public ReferenciasWeb maxReferencias() {
        ReferenciasWeb res = (ReferenciasWeb) qp.eliminarMin();
        noPags--;
        return res;
    }

    /**
     * Devuelve un array con las k páginas recomendadas atendiendo al criterio
     * Las elimina del sistema para no volver a recomendarlas.
     * Si el sistema no tiene k páginas devuelve todas las que tiene.
     * Precondición: el sistema debe contener al menos una página para recomendar
     * @return el array de páginas recomendadas de tamaño minimo(k, noPags)
     */
    public ReferenciasWeb[] maxKReferencias(int k) {
        if (noPags < k) k = noPags;
        ReferenciasWeb[] res = new ReferenciasWeb[k];
        for (int i = 0; i < k; i++) {
            res[i] = qp.eliminarMin();
        }
        noPags -= k;
        return res;
    }

    /** Devuelve la representación textual de la información 
     * que contiene el sistema de recomendación
     */
    public String toString() {
        return qp.toString();
    }
}
