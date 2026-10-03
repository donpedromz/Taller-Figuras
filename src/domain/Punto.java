package domain;

import exceptions.DimensionInvalidaException;

public class Punto {
    private float x;
    private float y;

    public Punto(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void desplazar(float desplazamientoX, float desplazamientoY) {
        this.x += desplazamientoX;
        this.y += desplazamientoY;
    }

    public void escalar(float factor) {
        validarFactor(factor);
        this.x *= factor;
        this.y *= factor;
    }

    public float distanciaAlOrigen() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    private static void validarFactor(float factor) {
        if (factor <= 0) {
            throw new DimensionInvalidaException("El factor de escalamiento debe ser mayor que cero, se recibio: " + factor);
        }
    }

    @Override
    public String toString() {
        return "Punto{" + this.x + ", " + this.y + "}";
    }
}