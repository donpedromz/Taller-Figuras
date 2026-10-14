package views;

import controllers.FiguraController;
import domain.Figura;
import exceptions.DimensionInvalidaException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Window;
import java.util.Locale;

/**
 * Dialogo para escalar la figura seleccionada.
 *
 */
public class EscalarFigura extends JPanel {

    private final FiguraController controller;
    private final Figura figura;
    private final Runnable alEscalar;

    private JPanel panelRaiz;
    private JLabel labelInfoFigura;
    private JTextField txtFactor;
    private JButton btnEscalar;
    private JButton btnLimpiar;

    public EscalarFigura(FiguraController controller, Figura figura, Runnable alEscalar) {
        this.controller = controller;
        this.figura = figura;
        this.alEscalar = alEscalar;
        initComponents();
    }

    private void initComponents() {
        initContenedor();
        initInfo();
        initBotones();
    }

    /**
     * El GUI Designer arma panelRaiz y sus hijos, pero no lo agrega a este
     * panel: la raiz del form hay que incorporarla a mano.
     */
    private void initContenedor() {
        setLayout(new BorderLayout());
        add(panelRaiz, BorderLayout.CENTER);
    }

    private void initInfo() {
        labelInfoFigura.setText(String.format(Locale.ROOT,
                "%s (%s) - Área: %.2f - Perímetro: %.2f",
                figura.getTipo(), figura.getDimensiones(),
                figura.calcularArea(), figura.calcularPerimetro()));
    }

    private void initBotones() {
        btnEscalar.addActionListener(evento -> escalar());
        btnLimpiar.addActionListener(evento -> txtFactor.setText(""));
    }

    private void escalar() {
        float factor;
        try {
            factor = Float.parseFloat(txtFactor.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese un factor numérico válido.",
                    "Factor inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            controller.escalarFigura(figura, factor);
        } catch (DimensionInvalidaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Factor inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (alEscalar != null) {
            alEscalar.run();
        }
        Window ventana = SwingUtilities.getWindowAncestor(this);
        if (ventana != null) {
            ventana.dispose();
        }
    }
}
