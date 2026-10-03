package controllers;

import domain.Figura;
import java.util.ArrayList;
import java.util.List;

/**
 * @author donpedromz
 * @version 1.0.0
 * Controlador de Figuras. Orquesta y maneja el estado de la lista
 * de figuras y la manipulación de este modelo y su información.
 */
public class  FiguraController {
    private List<Figura> figuras;
    public FiguraController(){
        this.figuras = new ArrayList<>();
    }

    public void agregarFigura(Figura figura){
        this.figuras.add(figura);
    }
    public void listarFiguras(){
        for(int i = 0; i < this.figuras.size(); i++){
            System.out.println("[" + i + "]");
            this.figuras.get(i).imprimirInformacion();
        }
    }
    
    /**
     * Compara dos figuras mediante su dimensionamiento
     * @param figura1
     * @param figura2
     * @return
     */
    public boolean compararFiguras(Figura figura1, Figura figura2){
        return figura1.getDimensionar() == figura2.getDimensionar();
    }

    /**
     * Escala una figura específica.
     * @author donpedromz
     * @version 1.0.0
     * @param figura La figura a escalar
     * @param factor Factor de escalamiento (debe ser positivo)
     */
    public void escalarFigura(Figura figura, float factor){
        figura.escalar(factor);
    }

    /**
     * Desplaza una figura específica.
     * @author donpedromz
     * @version 1.0.0
     * @param figura La figura a desplazar
     * @param desplazamientoX Desplazamiento en el eje X
     * @param desplazamientoY Desplazamiento en el eje Y
     */
    public void desplazarFigura(Figura figura, float desplazamientoX, float desplazamientoY){
        figura.desplazar(desplazamientoX, desplazamientoY);
    }

    /**
     * Obtiene una figura por su índice en el arreglo.
     * @author donpedromz
     * @version 1.0.0
     * @param indice Índice de la figura en el arreglo
     * @return La figura en el índice especificado
     */
    public Figura getFigura(int indice){
        if (indice < 0 || indice >= this.figuras.size()) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }
        return this.figuras.get(indice);
    }

    /**
     * Verifica si el arreglo de figuras está vacío.
     * @author donpedromz
     * @version 1.0.0
     * @return true si no hay figuras, false en caso contrario
     */
    public boolean estaVacio(){
        return this.figuras.isEmpty();
    }

    /**
     * Obtiene la cantidad de figuras en el arreglo.
     * @author donpedromz
     * @version 1.0.0
     * @return Cantidad de figuras
     */
    public int getCantidad(){
        return this.figuras.size();
    }
}
