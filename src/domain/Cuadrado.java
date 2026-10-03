package domain;

public final class Cuadrado extends Figura {
    private static final int CANTIDAD_DE_LADOS = 4;

    private float lado;

    public Cuadrado(float lado) {
        this.setLado(lado);
    }

    @Override
    public String getTipo() {
        return "Cuadrado";
    }

    @Override
    public String getDimensiones() {
        return "Lado: " + this.lado;
    }

    @Override
    public float calcularArea() {
        return this.lado * this.lado;
    }

    @Override
    public float calcularPerimetro() {
        return this.lado * CANTIDAD_DE_LADOS;
    }

    @Override
    protected float dimensionar() {
        return this.calcularDistanciaAlCentro();
    }

    @Override
    protected void escalarDimensiones(float factor) {
        this.setLado(this.lado * factor);
    }

    private void setLado(float lado) {
        validarPositivo(lado, "lado del cuadrado");
        this.lado = lado;
    }

    private float calcularDistanciaAlCentro() {
        return (float) (Math.sqrt(2) * this.lado) / 2;
    }
}