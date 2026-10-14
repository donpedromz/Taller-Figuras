import controllers.FiguraController;
import views.MainForm;

import domain.Circulo;
import domain.Cuadrado;
import domain.Figura;
import domain.PentagonoRegular;
import domain.Triangulo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Dimension;

/**
 * Punto de entrada del sistema de manejo de figuras geometricas.
 */
public class Main {

    private static final int ANCHO_VENTANA = 1280;
    private static final int ALTO_VENTANA = 820;
    private static final Dimension TAMANO_MINIMO = new Dimension(900, 620);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Sistema de Manejo de Figuras Geometricas");
            ventana.setContentPane(new MainForm(crearControllerDePrueba()));
            ventana.setSize(ANCHO_VENTANA, ALTO_VENTANA);
            ventana.setMinimumSize(TAMANO_MINIMO);
            ventana.setLocationRelativeTo(null);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setVisible(true);
        });
    }

    /**
     * Arma un controller con figuras inventadas. Datos de prueba para
     * revisar la seleccion y la comparacion, se van cuando exista la
     * creacion de figuras desde la interfaz.
     */
    private static FiguraController crearControllerDePrueba() {
        FiguraController controller = new FiguraController();

        Triangulo trianguloChico = new Triangulo(4);
        Triangulo trianguloIgual = new Triangulo(4);
        Triangulo trianguloGrande = new Triangulo(5);
        Cuadrado cuadradoChico = new Cuadrado(2);
        Cuadrado cuadradoIgual = new Cuadrado(2);
        Circulo circulo = new Circulo(3);
        PentagonoRegular pentagono = new PentagonoRegular(2.7528f, 4);

        trianguloChico.desplazar(3, 2);
        trianguloIgual.desplazar(-4, 1);
        trianguloGrande.desplazar(0, -5);
        cuadradoChico.desplazar(-2, -2);
        cuadradoIgual.desplazar(6, 4);
        circulo.desplazar(1, 0);
        pentagono.desplazar(-6, 3);

        for (Figura figura : new Figura[]{trianguloChico, trianguloIgual,
                trianguloGrande, cuadradoChico, cuadradoIgual, circulo,
                pentagono}) {
            controller.agregarFigura(figura);
        }
        return controller;
    }
}
