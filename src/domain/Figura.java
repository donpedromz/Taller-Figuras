package domain;

import exceptions.DimensionInvalidaException;

import java.util.ArrayList;
import java.util.List;

public abstract class Figura {
    /**
     * Dimension maxima de una figura, en unidades del plano. Con la escala
     * del lienzo de 24 px por unidad, 20 unidades ocupan 480 px, que entra
     * completo en el lienzo de 520 px.
     */
    protected static final float DIMENSION_MAXIMA = 20f;

    /**
     * Distancia maxima del punto de referencia al origen. El lienzo llega
     * a unos 770 px de ancho, o sea 32 unidades de radio, asi que 40 deja
     * margen para que una figura no se vaya del todo.
     */
    protected static final float POSICION_MAXIMA = 40f;

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
        validarPosicion(this.punto.getX() + desplazamientoX,
                this.punto.getY() + desplazamientoY);
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
        if (valor > DIMENSION_MAXIMA) {
            throw new DimensionInvalidaException(
                    "La dimension " + nombre + " no puede superar " + DIMENSION_MAXIMA
                            + ", se recibio: " + valor);
        }
    }

    /**
     * Verifica que un punto quede dentro del area visible del plano. Se usa
     * despues de desplazar, porque sumar varias veces puede salirse.
     */
    protected void validarPosicion(float x, float y) {
        if (!Float.isFinite(x) || !Float.isFinite(y)) {
            throw new DimensionInvalidaException(
                    "Las coordenadas deben ser numeros finitos, se recibio: ("
                            + x + ", " + y + ")");
        }
        if (Math.abs(x) > POSICION_MAXIMA || Math.abs(y) > POSICION_MAXIMA) {
            throw new DimensionInvalidaException(
                    "Las coordenadas no pueden superar " + POSICION_MAXIMA
                            + " en valor absoluto, se recibio: (" + x + ", " + y + ")");
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