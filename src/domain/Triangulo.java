package domain;

import java.awt.Color;
import java.util.List;

public final class Triangulo extends Figura {
    private static final int CANTIDAD_DE_LADOS = 3;

    private static final Color COLOR = new Color(66, 133, 244);

    private float lado;

    public Triangulo(float lado) {
        this.setLado(lado);
    }

    @Override
    public String getTipo() {
        return "Triangulo";
    }

    @Override
    public String getDimensiones() {
        return "Lado: " + this.lado;
    }

    @Override
    public float calcularArea() {
        return (this.lado * this.calcularAltura()) / 2;
    }

    @Override
    public float calcularPerimetro() {
        return this.lado * CANTIDAD_DE_LADOS;
    }

    @Override
    public List<Punto> obtenerContorno() {
        return contornoRegular(CANTIDAD_DE_LADOS, this.calcularAltura() * 2 / 3,
                Math.PI / 2);
    }

    @Override
    public Color obtenerColor() {
        return COLOR;
    }

    @Override
    protected float dimensionar() {
        return this.calcularPerimetro();
    }

    @Override
    protected void escalarDimensiones(float factor) {
        this.setLado(this.lado * factor);
    }

    public float getLado() {
        return this.lado;
    }

    private void setLado(float lado) {
        validarPositivo(lado, "lado del triangulo");
        this.lado = lado;
    }

    private float calcularAltura() {
        return (float) (Math.sqrt(3) / 2) * this.lado;
    }
}