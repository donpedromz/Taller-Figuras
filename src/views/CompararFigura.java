package views;

import controllers.*;
import domain.*;

import java.awt.BorderLayout;
import java.awt.Color;
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
    private static final Color COLOR_IGUALES = new Color(21, 101, 61);
    private static final Color COLOR_DISTINTAS = new Color(173, 28, 28);
    private static final String[] COLUMNAS_FIGURAS =
            {"Tipo", "Dimensionamiento", "X", "Y"};

    private final FiguraController controller;
    private final Figura original;
    private final List<Figura> comparables;

    private JPanel panelRaiz;
    private JTable tablaFigurasFiltradas;
    private JLabel labelDimensionamientoOriginal;
    private JLabel labelDimensionamientoSeleccionada;
    private JLabel labelResultado;

    public CompararFigura(FiguraController controller, Figura original) {
        this.controller = controller;
        this.original = original;
        this.comparables = controller.getFigurasComparables(original);
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
        conectarSeleccionDeTabla();
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
        for (Figura figura : this.comparables) {
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
     * Completa el dimensionamiento de la figura elegida y el veredicto al
     * seleccionar una fila. La fila i corresponde a la posicion i de la
     * lista de comparables. El filtro de getValueIsAdjusting evita
     * responder dos veces a un mismo cambio.
     */
    private void conectarSeleccionDeTabla() {
        this.tablaFigurasFiltradas.getSelectionModel().addListSelectionListener(evento -> {
            if (evento.getValueIsAdjusting()) {
                return;
            }
            int fila = this.tablaFigurasFiltradas.getSelectedRow();
            if (fila < 0) {
                this.labelDimensionamientoSeleccionada.setText("");
                this.labelResultado.setText("");
                return;
            }
            Figura elegida = this.comparables.get(fila);
            this.labelDimensionamientoSeleccionada.setText(formatear(elegida.getDimensionar()));

            boolean iguales = this.controller.compararFiguras(this.original, elegida);
            this.labelResultado.setText(veredicto(iguales));
            this.labelResultado.setForeground(iguales ? COLOR_IGUALES : COLOR_DISTINTAS);
        });
    }

    /**
     * Describe el resultado de la comparacion.
     */
    private String veredicto(boolean iguales) {
        return iguales ? "Tienen el mismo dimensionamiento"
                       : "Tienen dimensionamiento diferente";
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
