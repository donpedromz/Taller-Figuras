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
        for(Figura figura : this.figuras){
            figura.imprimirInformacion();
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
}
