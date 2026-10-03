package domain;

import exceptions.DimensionInvalidaException;

public abstract class Figura {
    private final Punto punto;

    protected Figura() {
        this.punto = new Punto(0.0f, 0.0f);
    }

    public abstract String getTipo();

    public abstract String getDimensiones();

    public abstract float calcularArea();

    public abstract float calcularPerimetro();

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
        this.punto.escalar(factor);
        this.escalarDimensiones(factor);
    }

    public String getPosicion() {
        return this.punto.toString();
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
        if (valor <= 0) {
            throw new DimensionInvalidaException(
                    "La dimension " + nombre + " debe ser mayor que cero, se recibio: " + valor);
        }
    }

    @Override
    public String toString() {
        return this.getTipo() + " " + this.getDimensiones();
    }
}