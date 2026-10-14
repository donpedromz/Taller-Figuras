package domain;

import java.awt.Color;
import java.util.List;

public final class Circulo extends Figura {
    /**
 * Segmentos con que se aproxima la circunferencia. El error de un poligono
 * de N lados es r * (1 - cos(pi/N)), que con 360 segmentos queda por debajo
 * de 0.003 px para un radio de 72 px, muy por debajo de un pixel.
 */
private static final int SEGMENTOS_APROXIMACION = 360;

    private static final Color COLOR = new Color(234, 67, 53);

    private float radio;

    public Circulo(float radio) {
        this.setRadio(radio);
    }

    @Override
    public String getTipo() {
        return "Circulo";
    }

    @Override
    public String getDimensiones() {
        return "Radio: " + this.radio;
    }

    @Override
    public float calcularArea() {
        return (float) (Math.PI * this.radio * this.radio);
    }

    @Override
    public float calcularPerimetro() {
        return (float) (2 * Math.PI * this.radio);
    }

    @Override
    public List<Punto> obtenerContorno() {
        return contornoRegular(SEGMENTOS_APROXIMACION, this.radio, 0);
    }

    @Override
    public Color obtenerColor() {
        return COLOR;
    }

    @Override
    protected float dimensionar() {
        return this.calcularArea();
    }

    @Override
    protected void escalarDimensiones(float factor) {
        this.setRadio(this.radio * factor);
    }

    public float getRadio() {
        return this.radio;
    }

    private void setRadio(float radio) {
        validarPositivo(radio, "radio del circulo");
        this.radio = radio;
    }
}