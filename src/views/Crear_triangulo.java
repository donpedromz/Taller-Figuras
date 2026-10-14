package views;

import controllers.FiguraController;
import domain.Figura;
import domain.Triangulo;
import exceptions.DimensionInvalidaException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Window;

/**
 * Dialogo para crear triangulos. Diseno en Crear_triangulo.form
 * (Swing UI Designer), aqui solo logica.
 */
public class Crear_triangulo extends JPanel {

    private JPanel panelPrincipal;
    private JTextField textLado;
    private JButton btnLimpiar;
    private JButton btnCrear;

    private final FiguraController controller;
    private final Runnable alCrear;

    public Crear_triangulo(FiguraController controller, Runnable alCrear) {
        this.controller = controller;
        this.alCrear = alCrear;
        initComponents();
    }

    private void initComponents() {
        initContenedor();
        initBotones();
    }

    /**
     * El GUI Designer arma panelPrincipal y sus hijos, pero no lo agrega a
     * este panel: la raiz del form hay que incorporarla a mano.
     */
    private void initContenedor() {
        setLayout(new BorderLayout());
        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void initBotones() {
        btnCrear.addActionListener(evento -> crear());
        btnLimpiar.addActionListener(evento -> textLado.setText(""));
    }

    private void crear() {
        float lado;
        try {
            lado = Float.parseFloat(textLado.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("Ingrese un lado numérico válido.");
            return;
        }
        Figura figura;
        try {
            figura = new Triangulo(lado);
        } catch (DimensionInvalidaException e) {
            mostrarError(e.getMessage());
            return;
        }
        controller.agregarFigura(figura);
        if (alCrear != null) {
            alCrear.run();
        }
        cerrar();
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje,
                "Dato inválido", JOptionPane.ERROR_MESSAGE);
    }

    private void cerrar() {
        Window ventana = SwingUtilities.getWindowAncestor(this);
        if (ventana != null) {
            ventana.dispose();
        }
    }
}
