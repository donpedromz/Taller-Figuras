package views;

import controllers.FiguraController;
import domain.Circulo;
import domain.Figura;
import exceptions.DimensionInvalidaException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Window;

/**
 * Dialogo para crear circulos. Diseno en Crear_circulo.form
 *
 */
public class Crear_circulo extends JPanel {

    private JPanel panelPrincipal;
    private JTextField txtRadio;
    private JButton btnCrear;
    private JButton btnLimpiar;

    private final FiguraController controller;
    private final Runnable alCrear;

    public Crear_circulo(FiguraController controller, Runnable alCrear) {
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
     * este panel la raiz del form hay que incorporarla a mano ??
     */
    private void initContenedor() {
        setLayout(new BorderLayout());
        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void initBotones() {
        btnCrear.addActionListener(evento -> crear());
        btnLimpiar.addActionListener(evento -> txtRadio.setText(""));
    }

    private void crear() {
        float radio;
        try {
            radio = Float.parseFloat(txtRadio.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("Ingrese un radio numérico válido.");
            return;
        }
        Figura figura;
        try {
            figura = new Circulo(radio);
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
