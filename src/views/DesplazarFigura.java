package views;

import controllers.FiguraController;
import domain.Figura;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Window;
import java.util.Locale;

/**
 * Dialogo para desplazar la figura seleccionada.
 * Diseno en DesplazarFigura.form (Swing UI Designer), aqui solo logica.
 */
public class DesplazarFigura extends JPanel {

    private final FiguraController controller;
    private final Figura figura;
    private final Runnable alDesplazar;

    private JPanel panelRaiz;
    private JLabel labelInfoFigura;
    private JTextField txtDx;
    private JTextField txtDy;
    private JButton btnDesplazar;
    private JButton btnLimpiar;

    public DesplazarFigura(FiguraController controller, Figura figura, Runnable alDesplazar) {
        this.controller = controller;
        this.figura = figura;
        this.alDesplazar = alDesplazar;
        initComponents();
    }

    private void initComponents() {
        initContenedor();
        initInfo();
        initBotones();
    }

    /**
     * El GUI Designer arma panelRaiz y sus hijos pero no lo agrega a este
     *
     */
    private void initContenedor() {
        setLayout(new BorderLayout());
        add(panelRaiz, BorderLayout.CENTER);
    }

    private void initInfo() {
        labelInfoFigura.setText(String.format(Locale.ROOT,
                "%s (%s) - Posición: (%.2f, %.2f)",
                figura.getTipo(), figura.getDimensiones(),
                figura.getX(), figura.getY()));
    }

    private void initBotones() {
        btnDesplazar.addActionListener(evento -> desplazar());
        btnLimpiar.addActionListener(evento -> limpiar());
    }

    private void desplazar() {
        float dx;
        float dy;
        try {
            dx = Float.parseFloat(txtDx.getText().trim());
            dy = Float.parseFloat(txtDy.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese desplazamientos numéricos válidos.",
                    "Dato inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        controller.desplazarFigura(figura, dx, dy);
        if (alDesplazar != null) {
            alDesplazar.run();
        }
        Window ventana = SwingUtilities.getWindowAncestor(this);
        if (ventana != null) {
            ventana.dispose();
        }
    }

    private void limpiar() {
        txtDx.setText("");
        txtDy.setText("");
    }
}
