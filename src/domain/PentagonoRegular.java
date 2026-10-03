package domain;

public final class PentagonoRegular extends Figura {
    private static final int CANTIDAD_DE_LADOS = 5;

    private float apotema;
    private float lado;

    public PentagonoRegular(float apotema, float lado) {
        this.setApotema(apotema);
        this.setLado(lado);
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
    protected float dimensionar() {
        return this.calcularPerimetro();
    }

    @Override
    protected void escalarDimensiones(float factor) {
        this.setLado(this.lado * factor);
        this.setApotema(this.apotema * factor);
    }

    private void setApotema(float apotema) {
        validarPositivo(apotema, "apotema del pentagono");
        this.apotema = apotema;
    }

    private void setLado(float lado) {
        validarPositivo(lado, "lado del pentagono");
        this.lado = lado;
    }
}