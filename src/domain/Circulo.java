package domain;

public final class Circulo extends Figura {
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
    protected float dimensionar() {
        return this.calcularArea();
    }

    @Override
    protected void escalarDimensiones(float factor) {
        this.setRadio(this.radio * factor);
    }

    private void setRadio(float radio) {
        validarPositivo(radio, "radio del circulo");
        this.radio = radio;
    }
}