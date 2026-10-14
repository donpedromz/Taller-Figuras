package views;

import controllers.FiguraController;
import domain.Figura;
import domain.PentagonoRegular;
import exceptions.DimensionInvalidaException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Window;

/**
 * Dialogo para crear pentagonos. Diseno en Crear_pentagono.form
 * (Swing UI Designer), aqui solo logica.
 */
public class Crear_pentagono extends JPanel {

    private JPanel panelPrincipal;
    private JTextField txtApotema;
    private JTextField txtLado;
    private JButton btnCrear;
    private JButton btnLimpiar;

    private final FiguraController controller;
    private final Runnable alCrear;

    public Crear_pentagono(FiguraController controller, Runnable alCrear) {
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
        btnLimpiar.addActionListener(evento -> limpiar());
    }

    private void crear() {
        float apotema;
        float lado;
        try {
            apotema = Float.parseFloat(txtApotema.getText().trim());
            lado = Float.parseFloat(txtLado.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("Ingrese apotema y lado numéricos válidos.");
            return;
        }
        Figura figura;
        try {
            figura = new PentagonoRegular(apotema, lado);
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

    private void limpiar() {
        txtApotema.setText("");
        txtLado.setText("");
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
