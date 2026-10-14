package views;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import domain.Circulo;
import domain.Cuadrado;
import domain.Figura;
import domain.PentagonoRegular;
import domain.Triangulo;

/**
 * @author donpedromz
 * @version 1.0.0
 * Panel que dibuja una cuadrícula con centro en (0,0), y dos ejes de
 * coordenadas X e Y. El origen queda en el centro del panel para que las
 * coordenadas negativas tengan lugar.
 */
public class Lienzo extends JPanel {

    /**
     * Píxeles por unidad del plano.
     */
    private static final int ESCALA = 24;

    /**
     * Cada cuántas unidades se dibuja una línea más gruesa.
     */
    private static final int UNIDADES_POR_LINEA_GRUESA = 5;
    /**
     * Medida preferida del panel.
     */
    private static final int MEDIDA_PREFERIDA = 420;
    /**
     * Radio del punto que marca el origen, en las coordenadas (0,0).
     */
    private static final int RADIO_ORIGEN = 3;

    private static final Color COLOR_GRILLA_FINA = new Color(224, 228, 235);
    private static final Color COLOR_GRILLA_GRUESA = new Color(178, 186, 199);
    private static final Color COLOR_EJES = new Color(80, 88, 104);
    private static final Color COLOR_ORIGEN = new Color(200, 90, 70);

    private static final Stroke TRAZO_FINO = new BasicStroke(1f);
    private static final Stroke TRAZO_GRUESO = new BasicStroke(1.4f);
    private static final Stroke TRAZO_EJE = new BasicStroke(1.6f);
    private static final Stroke TRAZO_FIGURA = new BasicStroke(1.8f);
    private static final Stroke TRAZO_SELECCIONADA = new BasicStroke(3f);

    private static final Color COLOR_TRIANGULO = new Color(66, 133, 244);
    private static final Color COLOR_CUADRADO = new Color(52, 168, 83);
    private static final Color COLOR_PENTAGONO = new Color(244, 160, 0);
    private static final Color COLOR_CIRCULO = new Color(234, 67, 53);

    private List<Figura> figuras = new ArrayList<>();
    private Figura seleccionada;

    public Lienzo() {
        setBackground(Color.WHITE);
        setOpaque(true);
        setPreferredSize(new Dimension(MEDIDA_PREFERIDA, MEDIDA_PREFERIDA));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        try {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int ancho = getWidth();
            int alto = getHeight();
            int origenX = ancho / 2;
            int origenY = alto / 2;

            dibujarGrilla(g2d, ancho, alto, origenX, origenY);
            dibujarEjes(g2d, ancho, alto, origenX, origenY);
            dibujarOrigen(g2d, origenX, origenY);
            dibujarFiguras(g2d, origenX, origenY);
        } finally {
            g2d.dispose();
        }
    }

    /**
     * Dibuja la grilla fina y la gruesa.
     */
    private void dibujarGrilla(Graphics2D g2d, int ancho, int alto,
                               int origenX, int origenY) {
        dibujarLineas(g2d, ancho, alto, origenX, origenY,
                TRAZO_FINO, COLOR_GRILLA_FINA, false);
        dibujarLineas(g2d, ancho, alto, origenX, origenY,
                TRAZO_GRUESO, COLOR_GRILLA_GRUESA, true);
    }

    /**
     * Dibuja en los dos ejes las líneas del estilo recibido. Las gruesas son
     * las que caen en múltiplo de UNIDADES_POR_LINEA_GRUESA.
     */
    private void dibujarLineas(Graphics2D g2d, int ancho, int alto, int origenX,
                               int origenY, Stroke trazo, Color color,
                               boolean esGruesa) {
        g2d.setStroke(trazo);
        g2d.setColor(color);

        for (int x = -unidadesEntre(0, origenX);
             x <= unidadesEntre(origenX, ancho); x++) {
            if (esGruesa != esLineaGruesa(x)) {
                continue;
            }
            int px = aPixel(x, origenX);
            if (px >= 0 && px <= ancho) {
                g2d.drawLine(px, 0, px, alto);
            }
        }

        for (int y = -unidadesEntre(0, origenY);
             y <= unidadesEntre(origenY, alto); y++) {
            if (esGruesa != esLineaGruesa(y)) {
                continue;
            }
            int py = aPixel(y, origenY);
            if (py >= 0 && py <= alto) {
                g2d.drawLine(0, py, ancho, py);
            }
        }
    }

    /**
     * Convierte una coordenada del plano a píxeles sobre un eje.
     */
    private int aPixel(int unidades, int origen) {
        return origen + (unidades * ESCALA);
    }

    /**
     * Cantidad de líneas de la grilla que entran entre dos píxeles
     */
    private int unidadesEntre(int desde, int hasta) {
        return (int) Math.ceil((double) (hasta - desde) / ESCALA);
    }

    /**
     * Las líneas que caen en múltiplo entero se dibujan gruesas.
     */
    private boolean esLineaGruesa(int unidades) {
        return unidades % UNIDADES_POR_LINEA_GRUESA == 0;
    }

    /**
     * Dibuja los ejes X e Y sobre las líneas gruesas de la grilla.
     */
    private void dibujarEjes(Graphics2D g2d, int ancho, int alto,
                             int origenX, int origenY) {
        g2d.setStroke(TRAZO_EJE);
        g2d.setColor(COLOR_EJES);
        g2d.drawLine(0, origenY, ancho, origenY);
        g2d.drawLine(origenX, 0, origenX, alto);
    }

    /**
     * Marca el punto 0,0 del plano.
     */
    private void dibujarOrigen(Graphics2D g2d, int origenX, int origenY) {
        int diametro = 2 * RADIO_ORIGEN;
        g2d.setColor(COLOR_ORIGEN);
        g2d.fillOval(origenX - RADIO_ORIGEN, origenY - RADIO_ORIGEN,
                diametro, diametro);
    }

    /**
     * Actualiza las figuras a dibujar.
     */
    public void setFiguras(List<Figura> figuras) {
        this.figuras = new ArrayList<>(figuras);
        repaint();
    }

    /**
     * Marca la figura resaltada en el plano.
     */
    public void setSeleccionada(Figura seleccionada) {
        this.seleccionada = seleccionada;
        repaint();
    }

    /**
     * Dibuja cada figura centrada en su posicion X Y.
     */
    private void dibujarFiguras(Graphics2D g2d, int origenX, int origenY) {
        for (Figura figura : this.figuras) {
            int px = origenX + Math.round(figura.getX() * ESCALA);
            int py = origenY - Math.round(figura.getY() * ESCALA);
            boolean esSeleccionada = figura == this.seleccionada;
            if (figura instanceof Circulo circulo) {
                dibujarCirculo(g2d, circulo.getRadio(), px, py,
                        COLOR_CIRCULO, esSeleccionada);
            } else if (figura instanceof Triangulo triangulo) {
                dibujarPoligono(g2d, 3, triangulo.getLado(), -Math.PI / 2.0,
                        px, py, COLOR_TRIANGULO, esSeleccionada);
            } else if (figura instanceof Cuadrado cuadrado) {
                dibujarPoligono(g2d, 4, cuadrado.getLado(), -Math.PI / 4.0,
                        px, py, COLOR_CUADRADO, esSeleccionada);
            } else if (figura instanceof PentagonoRegular pentagono) {
                dibujarPoligono(g2d, 5, pentagono.getLado(), -Math.PI / 2.0,
                        px, py, COLOR_PENTAGONO, esSeleccionada);
            }
        }
    }

    /**
     * Dibuja un circulo con el radio dado, en pixeles del plano.
     */
    private void dibujarCirculo(Graphics2D g2d, float radio, int cx, int cy,
                                Color color, boolean esSeleccionada) {
        int r = Math.max(2, Math.round(radio * ESCALA));
        Stroke trazoAnterior = g2d.getStroke();
        g2d.setColor(new Color(color.getRed(), color.getGreen(),
                color.getBlue(), esSeleccionada ? 80 : 40));
        g2d.fillOval(cx - r, cy - r, 2 * r, 2 * r);
        g2d.setColor(color);
        g2d.setStroke(esSeleccionada ? TRAZO_SELECCIONADA : TRAZO_FIGURA);
        g2d.drawOval(cx - r, cy - r, 2 * r, 2 * r);
        g2d.setStroke(trazoAnterior);
    }

    /**
     * Dibuja un poligono regular de lados y lado dados, rotado segun el
     * angulo inicial recibido.
     */
    private void dibujarPoligono(Graphics2D g2d, int lados, float lado,
                                 double anguloInicial, int cx, int cy,
                                 Color color, boolean esSeleccionada) {
        double radio = (lado * ESCALA) / (2.0 * Math.sin(Math.PI / lados));
        int[] xs = new int[lados];
        int[] ys = new int[lados];
        for (int i = 0; i < lados; i++) {
            double angulo = anguloInicial + i * 2.0 * Math.PI / lados;
            xs[i] = cx + (int) Math.round(radio * Math.cos(angulo));
            ys[i] = cy + (int) Math.round(radio * Math.sin(angulo));
        }
        Stroke trazoAnterior = g2d.getStroke();
        g2d.setColor(new Color(color.getRed(), color.getGreen(),
                color.getBlue(), esSeleccionada ? 80 : 40));
        g2d.fillPolygon(xs, ys, lados);
        g2d.setColor(color);
        g2d.setStroke(esSeleccionada ? TRAZO_SELECCIONADA : TRAZO_FIGURA);
        g2d.drawPolygon(xs, ys, lados);
        g2d.setStroke(trazoAnterior);
    }
}
