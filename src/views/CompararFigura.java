package views;

import controllers.*;
import domain.*;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author donpedromz
 * @version 1.0.0
 * Panel del dialogo de comparacion de figuras. Muestra las figuras del
 * mismo tipo que la original y la deja elegir la segunda.
 */
public class CompararFigura extends JPanel {

    private static final int ALTO_FILA = 28;
    private static final String[] COLUMNAS_FIGURAS =
            {"Tipo", "Dimensionamiento", "X", "Y"};

    private final FiguraController controller;
    private final Figura original;

    private JPanel panelRaiz;
    private JTable tablaFigurasFiltradas;
    private JLabel labelDimensionamientoOriginal;
    private JLabel labelDimensionamientoSeleccionada;
    private JLabel labelResultado;

    public CompararFigura(FiguraController controller, Figura original) {
        this.controller = controller;
        this.original = original;
        initComponents();
    }

    /**
     * Inicializa cada seccion del panel. Corre despues de que el GUI
     * Designer creo los componentes declarados en CompararFigura.form.
     */
    private void initComponents() {
        initContenedor();
        initTabla();
        initLabels();
    }

    /**
     * El GUI Designer arma panelRaiz y sus hijos, pero no lo agrega a este
     * panel: la raiz del form hay que incorporarla a mano.
     */
    private void initContenedor() {
        setLayout(new BorderLayout());
        add(panelRaiz, BorderLayout.CENTER);
    }

    /**
     * Llena la tabla con las figuras comparables con la original. La
     * fila i corresponde a la posicion i dentro de esa lista.
     */
    private void initTabla() {
        DefaultTableModel modelo = new ModeloTabla();
        List<Figura> comparables = this.controller.getFigurasComparables(this.original);
        for (Figura figura : comparables) {
            modelo.addRow(new Object[]{figura.getTipo(),
                    formatear(figura.getDimensionar()),
                    formatear(figura.getX()),
                    formatear(figura.getY())});
        }
        this.tablaFigurasFiltradas.setModel(modelo);
        this.tablaFigurasFiltradas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tablaFigurasFiltradas.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        this.tablaFigurasFiltradas.setFillsViewportHeight(true);
        this.tablaFigurasFiltradas.setRowHeight(ALTO_FILA);
        this.tablaFigurasFiltradas.setShowGrid(true);
        this.tablaFigurasFiltradas.getTableHeader().setReorderingAllowed(false);
    }

    /**
     * Muestra el dimensionamiento de la figura original. El de la
     * seleccionada y el resultado se completan al elegir una fila.
     */
    private void initLabels() {
        this.labelDimensionamientoOriginal.setText(formatear(this.original.getDimensionar()));
        this.labelDimensionamientoSeleccionada.setText("");
        this.labelResultado.setText("");
    }

    /**
     * Formato de dos decimales para las columnas numericas.
     */
    private String formatear(float valor) {
        return String.format(java.util.Locale.ROOT, "%.2f", valor);
    }

    /**
     * Modelo de solo lectura para la tabla de figuras comparables.
     */
    private static final class ModeloTabla extends DefaultTableModel {

        private static final long serialVersionUID = 1L;

        private ModeloTabla() {
            super(COLUMNAS_FIGURAS, 0);
        }

        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    }
}
