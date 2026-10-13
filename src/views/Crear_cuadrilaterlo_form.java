package views;

import controllers.FiguraController;
import domain.Cuadrado;
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
 * Dialogo para crear cuadrilateros. Diseno en
 * Crear_cuadrilatero_form.form (Swing UI Designer), aqui solo logica.
 * Pide el lado y la ubicacion inicial (X, Y) de la figura.
 */
public class Crear_cuadrilaterlo_form extends JPanel {

    private JTextField txtY;
    private JTextField txtX;
    private JButton btnCrear;
    private JButton btnLimpiar;
    private JTextField txtLado;
    private JPanel panelPrincipal;

    private final FiguraController controller;
    private final Runnable alCrear;

    public Crear_cuadrilaterlo_form(FiguraController controller, Runnable alCrear) {
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
        float lado;
        try {
            lado = Float.parseFloat(txtLado.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("Ingrese un lado numérico válido.");
            return;
        }
        float x = leerCoordenada(txtX);
        float y = leerCoordenada(txtY);
        if (Float.isNaN(x) || Float.isNaN(y)) {
            mostrarError("Las coordenadas X y Y deben ser numéricas.");
            return;
        }
        Figura figura;
        try {
            figura = new Cuadrado(lado);
        } catch (DimensionInvalidaException e) {
            mostrarError(e.getMessage());
            return;
        }
        if (x != 0 || y != 0) {
            controller.desplazarFigura(figura, x, y);
        }
        controller.agregarFigura(figura);
        if (alCrear != null) {
            alCrear.run();
        }
        cerrar();
    }

    /**
     * Lee una coordenada opcional: vacio equivale a cero.
     * Retorna NaN si el texto no es numerico.
     */
    private float leerCoordenada(JTextField campo) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            return 0;
        }
        try {
            return Float.parseFloat(texto);
        } catch (NumberFormatException e) {
            return Float.NaN;
        }
    }

    private void limpiar() {
        txtLado.setText("");
        txtX.setText("");
        txtY.setText("");
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
