package domain;

import exceptions.DimensionInvalidaException;

import java.util.ArrayList;
import java.util.List;

public abstract class Figura {
    protected final Punto punto;

    protected Figura() {
        this.punto = new Punto(0.0f, 0.0f);
    }

    public abstract String getTipo();

    public abstract String getDimensiones();

    public abstract float calcularArea();

    public abstract float calcularPerimetro();

    /**
     * Puntos del contorno de la figura, en coordenadas del plano. Cada
     * subclase dice como se dibuja, asi la vista no necesita preguntar por
     * el tipo de instancia.
     */
    public abstract List<Punto> obtenerContorno();

    protected abstract float dimensionar();

    protected abstract void escalarDimensiones(float factor);

    public final float getDimensionar() {
        return this.dimensionar();
    }

    public void desplazar(float desplazamientoX, float desplazamientoY) {
        this.punto.desplazar(desplazamientoX, desplazamientoY);
    }

    public void escalar(float factor) {
        validarPositivo(factor, "factor de escalamiento");
        this.escalarDimensiones(factor);
    }

    public String getPosicion() {
        return this.punto.toString();
    }

    /**
     * Coordenada X del punto de referencia de la figura.
     */
    public float getX() {
        return this.punto.getX();
    }

    /**
     * Coordenada Y del punto de referencia de la figura.
     */
    public float getY() {
        return this.punto.getY();
    }

    public float getDistanciaAlCentro() {
        return this.punto.distanciaAlOrigen();
    }

    public void imprimirInformacion() {
        System.out.println("Tipo de figura: " + this.getTipo());
        System.out.println("Dimensiones: " + this.getDimensiones());
        System.out.println("Area: " + this.calcularArea());
        System.out.println("Perimetro: " + this.calcularPerimetro());
        System.out.println("Dimensionamiento: " + this.dimensionar());
        System.out.println("Posicion: " + this.getPosicion());
    }

    protected static void validarPositivo(float valor, String nombre) {
        if (!Float.isFinite(valor) || valor <= 0) {
            throw new DimensionInvalidaException(
                    "La dimension " + nombre + " debe ser un numero positivo, se recibio: " + valor);
        }
    }

    /**
     * Genera el contorno de un poligono regular de lados indicados, con el
     * radio circunscrito dado y arrancando en el angulo inicial recibido.
     */
    protected List<Punto> contornoRegular(int lados, float radio,
                                      double anguloInicial) {
        List<Punto> contorno = new ArrayList<>(lados);
        for (int i = 0; i < lados; i++) {
            double angulo = anguloInicial + (i * 2.0 * Math.PI / lados);
            contorno.add(new Punto(
                    this.punto.getX() + (float) (radio * Math.cos(angulo)),
                    this.punto.getY() + (float) (radio * Math.sin(angulo))));
        }
        return contorno;
    }

    @Override
    public String toString() {
        return this.getTipo() + " " + this.getDimensiones();
    }
}