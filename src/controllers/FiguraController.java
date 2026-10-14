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
    private Figura figuraSeleccionada;

    public FiguraController(){
        this.figuras = new ArrayList<>();
        this.figuraSeleccionada = null;
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

    /**
     * Guarda la figura que el usuario selecciono en la interfaz.
     * @author donpedromz
     * @version 1.0.0
     * @param figura Figura seleccionada, o null para vaciar la seleccion
     */
    public void seleccionarFigura(Figura figura){
        this.figuraSeleccionada = figura;
    }

    /**
     * Consulta la figura que el usuario selecciono.
     * @author donpedromz
     * @version 1.0.0
     * @return La figura seleccionada, o null si no hay ninguna
     */
    public Figura getFiguraSeleccionada(){
        return this.figuraSeleccionada;
    }

    /**
     * Indica si el usuario tiene una figura seleccionada.
     * @author donpedromz
     * @version 1.0.0
     * @return true si hay una figura seleccionada
     */
    public boolean hayFiguraSeleccionada(){
        return this.figuraSeleccionada != null;
    }

    /**
     * Devuelve las figuras del mismo tipo que la recibida, ya que solo se
     * comparan entre figuras de la misma clase. El filtro compara el tipo
     * de instancia en lugar de getTipo(), que devuelve texto suelto.
     * @author donpedromz
     * @version 1.0.0
     * @param referencia Figura con la que se va a comparar
     * @return Figuras comparables con la referencia, excluyendola
     */
    public List<Figura> getFigurasComparables(Figura referencia){
        return this.figuras.stream()
                .filter(figura -> figura.getClass() == referencia.getClass())
                .filter(figura -> figura != referencia)
                .toList();
    }

    /**
     * Devuelve una copia de la lista de figuras.
     * @return Copia inmodificable de las figuras registradas
     */
    public List<Figura> getFiguras(){
        return List.copyOf(this.figuras);
     * Elimina una figura del arreglo. Si era la seleccionada,
     * vacia la seleccion.
     * @param figura La figura a eliminar
     */
    public void eliminarFigura(Figura figura){
        this.figuras.remove(figura);
        if (figura == this.figuraSeleccionada) {
            this.figuraSeleccionada = null;
        }
    }
}
