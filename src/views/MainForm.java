package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * @author donpedromz
 * @version 1.0.0
 * Form principal del sistema de manejo de figuras geometricas.
 */
public class MainForm extends JPanel {

    private static final Color BLANCO = new Color(255, 255, 255);
    private static final Color COLOR_HOVER = new Color(225, 238, 254);
    private static final Color COLOR_ENCABEZADO = new Color(236, 239, 245);
    private static final Color COLOR_GRILLA = new Color(214, 218, 226);
    private static final Cursor CURSOR_MANO =
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    private static final Font COLUMNAS_EN_NEGRITA =
            new Font(Font.SANS_SERIF, Font.BOLD, 12);
    private static final int ALTO_FILA = 28;
    private static final double PROPORCION_PANEL_TABLA = 0.40;
    private static final String[] COLUMNAS_FIGURAS =
            {"Tipo", "Dimensionamiento", "Área", "Perímetro", "X", "Y"};

    private JPanel panel1;
    private JTable tablaFiguras;
    private JButton escalarButton;
    private JButton desplazarButton;
    private JPanel panelPrincipal;
    private JPanel panelOpcionesFiguras;
    private Lienzo lienzo1;
    private JLabel cuadradoIcon;
    private JLabel pentagonoIcon;
    private JLabel circuloIcon;
    private JPanel panelIconoTriangulo;
    private JPanel panelIconoCuadrado;
    private JPanel panelIconoPentagono;
    private JPanel panelIconoCirculo;
    private JLabel trainguloIcon;
    private JButton compararButton;
    private JScrollPane scrollPanelTabla;
    private JPanel seccionGestionFiguras;
    private JPanel seccionBotonesFiguras;
    private JButton eliminarButton;
    private JSplitPane splitPanePrincipal;

    public MainForm() {
        initComponents();
    }

    /**
     * Coordina la inicializacion seccion por seccion.
     */
    private void initComponents() {
        initContenedor();
        initEncabezado();
        initSelectorFiguras();
        initAreaCentral();
        initTablaFiguras();
        initBotones();
    }

    private void initContenedor() {
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.CENTER);
    }

    /**
     * Titulo y aviso del encabezado. Solo requiere ajuste de estilos.
     */
    private void initEncabezado() {
        for (JLabel etiqueta : new JLabel[]{trainguloIcon, cuadradoIcon,
                pentagonoIcon, circuloIcon}) {
            etiqueta.setVerticalTextPosition(SwingConstants.BOTTOM);
            etiqueta.setHorizontalTextPosition(SwingConstants.CENTER);
            etiqueta.setForeground(Color.DARK_GRAY);
        }
    }

    /**
     * Los paneles de figura funcionan como botones: llevan su icono y
     * resaltan al pasar el mouse.
     */
    private void initSelectorFiguras() {
        aplicarIcono(trainguloIcon, "/assets/triangle.png");
        aplicarIcono(cuadradoIcon, "/assets/square.png");
        aplicarIcono(pentagonoIcon, "/assets/pentagon.png");
        aplicarIcono(circuloIcon, "/assets/circle.png");

        activarHover(panelIconoTriangulo, trainguloIcon);
        activarHover(panelIconoCuadrado, cuadradoIcon);
        activarHover(panelIconoPentagono, pentagonoIcon);
        activarHover(panelIconoCirculo, circuloIcon);
    }

    /**
     * Area central: listado de figuras a la izquierda, plano a la derecha.
     */
    private void initAreaCentral() {
        splitPanePrincipal.setContinuousLayout(true);
        splitPanePrincipal.setResizeWeight(0);
        splitPanePrincipal.setDividerLocation(PROPORCION_PANEL_TABLA);
        lienzo1.setPreferredSize(new Dimension(520, 520));
    }

    /**
     * Listado de figuras: columnas, formato y seleccion de una sola fila.
     */
    private void initTablaFiguras() {
        tablaFiguras.setModel(new ModeloTablaFiguras());
        tablaFiguras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaFiguras.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tablaFiguras.setFillsViewportHeight(true);
        tablaFiguras.setRowHeight(ALTO_FILA);
        tablaFiguras.setShowGrid(true);
        tablaFiguras.setGridColor(COLOR_GRILLA);
        tablaFiguras.getTableHeader().setReorderingAllowed(false);
        tablaFiguras.getTableHeader().setFont(COLUMNAS_EN_NEGRITA);
        tablaFiguras.getTableHeader().setBackground(COLOR_ENCABEZADO);
        tablaFiguras.getTableHeader().setForeground(Color.DARK_GRAY);
        tablaFiguras.setSelectionBackground(COLOR_HOVER);
        tablaFiguras.setSelectionForeground(Color.BLACK);
    }

    /**
     * Botones de operacion sobre la figura seleccionada.
     */
    private void initBotones() {
        for (JButton boton : new JButton[]{desplazarButton, escalarButton,
                compararButton, eliminarButton}) {
            boton.setCursor(CURSOR_MANO);
        }
    }

    /**
     * Modelo de solo lectura para el listado de figuras.
     */
    private static final class ModeloTablaFiguras extends DefaultTableModel {
        private static final long serialVersionUID = 1L;
        private ModeloTablaFiguras() {
            super(COLUMNAS_FIGURAS, 0);
        }
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    }

    /**
     * Resalta el panel y muestra la mano cuando el mouse entra, y vuelve al
     * estado normal cuando sale.
     */
    private void activarHover(JPanel panel, JComponent... iconos) {
        panel.setOpaque(true);
        panel.setBackground(BLANCO);
        panel.setCursor(CURSOR_MANO);

        MouseAdapter hover = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evento) {
                panel.setBackground(COLOR_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent evento) {
                panel.setBackground(BLANCO);
            }
        };

        panel.addMouseListener(hover);
        for (JComponent icono : iconos) {
            icono.addMouseListener(hover);
        }
    }

    /**
     * Asigna el icono de la figura
     */
    private void aplicarIcono(JLabel etiqueta, String ruta) {
        URL recurso = MainForm.class.getResource(ruta);
        if (recurso != null) {
            etiqueta.setIcon(new ImageIcon(recurso));
        }
    }
}