import views.MainForm;

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
            ventana.setContentPane(new MainForm());
            ventana.setSize(ANCHO_VENTANA, ALTO_VENTANA);
            ventana.setMinimumSize(TAMANO_MINIMO);
            ventana.setLocationRelativeTo(null);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setVisible(true);
        });
    }
}