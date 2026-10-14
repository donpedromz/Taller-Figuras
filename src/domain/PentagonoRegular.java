package domain;

import exceptions.DimensionInvalidaException;
import java.awt.Color;
import java.util.List;
import java.util.Locale;

public final class PentagonoRegular extends Figura {
    private static final int CANTIDAD_DE_LADOS = 5;

    private static final Color COLOR = new Color(244, 160, 0);

    private float apotema;
    private float lado;

    public PentagonoRegular(float apotema, float lado) {
        this.setApotema(apotema);
        this.setLado(lado);
        validarApotemaYLado();
    }

    @Override
    public String getTipo() {
        return "Pentagono regular";
    }

    @Override
    public String getDimensiones() {
        return "Lado: " + this.lado + ", Apotema: " + this.apotema;
    }

    @Override
    public float calcularArea() {
        return (this.calcularPerimetro() * this.apotema) / 2;
    }

    @Override
    public float calcularPerimetro() {
        return this.lado * CANTIDAD_DE_LADOS;
    }

    @Override
    public List<Punto> obtenerContorno() {
        return contornoRegular(CANTIDAD_DE_LADOS,
                (float) (this.apotema / Math.cos(Math.PI / CANTIDAD_DE_LADOS)),
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
        this.setApotema(this.apotema * factor);
    }

    public float getLado() {
        return this.lado;
    }

    public float getApotema() {
        return this.apotema;
    }

    private void setApotema(float apotema) {
        validarPositivo(apotema, "apotema del pentagono");
        this.apotema = apotema;
    }

    private void setLado(float lado) {
        validarPositivo(lado, "lado del pentagono");
        this.lado = lado;
    }

    /**
     * Verifica que el apotema sea el correspondiente al lado en un pentagono
     * regular. La relacion es apotema = lado / (2 * tan(36 grados)).
     */
    private void validarApotemaYLado() {
        float esperado = (float) (this.lado
                / (2 * Math.tan(Math.PI / CANTIDAD_DE_LADOS)));
        if (Math.abs(esperado - this.apotema) > esperado * 0.01f) {
            throw new DimensionInvalidaException("Para un lado de " + this.lado
                    + ", el apotema debe ser " + String.format(Locale.ROOT, "%.2f", esperado)
                    + ", se recibio: " + this.apotema);
        }
    }
}