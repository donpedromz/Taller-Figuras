import controllers.FiguraController;
import domain.Circulo;
import domain.Cuadrado;
import domain.Figura;
import domain.PentagonoRegular;
import domain.Triangulo;
import exceptions.DimensionInvalidaException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Punto de entrada del sistema de manejo de figuras geometricas.
 */
public class Main {

    private static final String SEPARADOR = "--------------------------------------------------";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FiguraController controller = new FiguraController();

        System.out.println(SEPARADOR);
        System.out.println("Sistema de Manejo de Figuras Geometricas");
        System.out.println(SEPARADOR);

        cargarFiguras(controller);
        mostrarFiguras(controller);
        mostrarComparacion(controller);
        mostrarDesplazamiento(controller);
        mostrarEscalamiento(controller);
        mostrarDimensionesInvalidas(controller);

        menu(scanner, controller);
        scanner.close();
    }

    /**
     * Registra un Circulo, un Cuadrado, un Triangulo y un Pentagono regular.
     */
    private static void cargarFiguras(FiguraController controller) {
        controller.agregarFigura(new Circulo(5f));
        controller.agregarFigura(new Cuadrado(4f));
        controller.agregarFigura(new Triangulo(6f));
        controller.agregarFigura(new PentagonoRegular(3f, 2f));
        System.out.println("Figuras cargadas: " + controller.getCantidad());
    }

    /**
     * Muestra el detalle de cada figura almacenada.
     */
    private static void mostrarFiguras(FiguraController controller) {
        System.out.println(SEPARADOR);
        System.out.println("Detalle de las figuras almacenadas");
        System.out.println(SEPARADOR);
        controller.listarFiguras();
    }

    /**
     * Compara dos figuras del mismo tipo a partir de su dimensionamiento.
     */
    private static void mostrarComparacion(FiguraController controller) {
        System.out.println(SEPARADOR);
        System.out.println("Comparacion entre figuras del mismo tipo");
        System.out.println(SEPARADOR);

        Circulo circulo = (Circulo) controller.getFigura(0);
        Cuadrado cuadrado = (Cuadrado) controller.getFigura(1);

        System.out.println(circulo.getTipo() + " " + circulo.getDimensiones()
                + " contra otro igual: "
                + controller.compararFiguras(circulo, new Circulo(5f)));
        System.out.println(circulo.getTipo() + " " + circulo.getDimensiones()
                + " contra uno de radio 8: "
                + controller.compararFiguras(circulo, new Circulo(8f)));
        System.out.println(cuadrado.getTipo() + " " + cuadrado.getDimensiones()
                + " contra otro igual: "
                + controller.compararFiguras(cuadrado, new Cuadrado(4f)));
        System.out.println(cuadrado.getTipo() + " " + cuadrado.getDimensiones()
                + " contra uno de lado 9: "
                + controller.compararFiguras(cuadrado, new Cuadrado(9f)));
    }

    /**
     * Mueve una figura sobre el plano.
     */
    private static void mostrarDesplazamiento(FiguraController controller) {
        System.out.println(SEPARADOR);
        System.out.println("Desplazamiento");
        System.out.println(SEPARADOR);

        Figura circulo = controller.getFigura(0);
        System.out.println("Posicion inicial: " + circulo.getPosicion());
        controller.desplazarFigura(circulo, 12f, 7f);
        System.out.println("Posicion al desplazar 12 en X y 7 en Y: " + circulo.getPosicion());
        System.out.println("Distancia al centro del plano: " + circulo.getDistanciaAlCentro());
    }

    /**
     * Cambia el tamano de una figura aplicando un factor.
     */
    private static void mostrarEscalamiento(FiguraController controller) {
        System.out.println(SEPARADOR);
        System.out.println("Escalamiento");
        System.out.println(SEPARADOR);

        Figura cuadrado = controller.getFigura(1);
        System.out.println("Antes:   " + cuadrado.getDimensiones()
                + ", area " + cuadrado.calcularArea());
        controller.escalarFigura(cuadrado, 2.5f);
        System.out.println("Con factor 2.5: " + cuadrado.getDimensiones()
                + ", area " + cuadrado.calcularArea());
    }

    /**
     * Intenta crear y modificar figuras con dimensiones que no estan permitidas.
     */
    private static void mostrarDimensionesInvalidas(FiguraController controller) {
        System.out.println(SEPARADOR);
        System.out.println("Dimensiones no validas");
        System.out.println(SEPARADOR);

        intentarCrear("Circulo con radio cero", () -> new Circulo(0f));
        intentarCrear("Circulo con radio negativo", () -> new Circulo(-3f));
        intentarCrear("Cuadrado con lado cero", () -> new Cuadrado(0f));
        intentarCrear("Triangulo con lado negativo", () -> new Triangulo(-2f));
        intentarCrear("Pentagono con apotema cero", () -> new PentagonoRegular(0f, 2f));
        intentarCrear("Pentagono con lado negativo", () -> new PentagonoRegular(2f, -1f));
        intentarEscalar("Escalar un cuadrado con factor cero",
                () -> new Cuadrado(4f).escalar(0f));
        intentarEscalar("Escalar un circulo con factor negativo",
                () -> new Circulo(4f).escalar(-1.5f));

        System.out.println("Las figuras siguen siendo " + controller.getCantidad()
                + ", ninguna dimension invalida se guardo.");
    }

    /**
     * Ejecuta la creacion de una figura e informa el rechazo.
     */
    private static void intentarCrear(String descripcion, Supplier<Figura> creacion) {
        try {
            creacion.get();
            System.out.println(descripcion + ": se acepto, revise el caso.");
        } catch (DimensionInvalidaException exception) {
            System.out.println(descripcion + " -> " + exception.getMessage());
        }
    }

    /**
     * Ejecuta una operacion de escalamiento e informa el rechazo.
     */
    private static void intentarEscalar(String descripcion, Runnable operacion) {
        try {
            operacion.run();
            System.out.println(descripcion + ": se acepto, revise el caso.");
        } catch (DimensionInvalidaException exception) {
            System.out.println(descripcion + " -> " + exception.getMessage());
        }
    }

    /**
     * Menu de operacion del sistema.
     */
    private static void menu(Scanner scanner, FiguraController controller) {
        while (true) {
            System.out.println(SEPARADOR);
            System.out.println("1. Crear una figura");
            System.out.println("2. Ver todas las figuras");
            System.out.println("3. Salir del sistema");
            System.out.print("Ingrese una opcion: ");

            int opcion;
            try {
                opcion = leerEntero(scanner);
            } catch (NoSuchElementException exception) {
                System.out.println("Saliendo del sistema.");
                return;
            }

            if (opcion == 1) {
                Figura figura = crearFigura(scanner);
                if (figura != null) {
                    controller.agregarFigura(figura);
                    figura.imprimirInformacion();
                }
            } else if (opcion == 2) {
                if (controller.estaVacio()) {
                    System.out.println("No hay figuras registradas.");
                } else {
                    controller.listarFiguras();
                }
            } else if (opcion == 3) {
                System.out.println("Saliendo del sistema.");
                return;
            } else {
                System.out.println("Opcion invalida, por favor ingrese una opcion valida.");
            }
        }
    }

    /**
     * Solicita el tipo de figura y sus dimensiones.
     */
    private static Figura crearFigura(Scanner scanner) {
        System.out.println("1. Triangulo");
        System.out.println("2. Cuadrado");
        System.out.println("3. Circulo");
        System.out.println("4. Pentagono");
        System.out.print("Ingrese el tipo de figura: ");

        int opcion;
        try {
            opcion = leerEntero(scanner);
        } catch (NoSuchElementException exception) {
            return null;
        }

        try {
            switch (opcion) {
                case 1:
                    return new Triangulo(leerDimension(scanner, "Ingrese el tamano del lado: "));
                case 2:
                    return new Cuadrado(leerDimension(scanner, "Ingrese el tamano del lado: "));
                case 3:
                    return new Circulo(leerDimension(scanner, "Ingrese el radio del circulo: "));
                case 4:
                    float apotema = leerDimension(scanner, "Ingrese el apotema del pentagono: ");
                    float lado = leerDimension(scanner, "Ingrese el lado del pentagono: ");
                    return new PentagonoRegular(apotema, lado);
                default:
                    System.out.println("Tipo de figura invalido, por favor ingrese uno valido.");
                    return null;
            }
        } catch (DimensionInvalidaException exception) {
            System.out.println(exception.getMessage());
            System.out.println("La figura no fue creada.");
            return null;
        }
    }

    /**
     * Lee una dimension escrita por el usuario.
     */
    private static float leerDimension(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        while (true) {
            String linea = scanner.nextLine().trim();
            try {
                return Float.parseFloat(linea);
            } catch (NumberFormatException exception) {
                System.out.println("Valor invalido, ingrese un numero. " + mensaje);
            }
        }
    }

    /**
     * Lee un numero entero escrito por el usuario.
     */
    private static int leerEntero(Scanner scanner) {
        while (true) {
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException exception) {
                System.out.println("Valor invalido, ingrese un numero entero.");
            }
        }
    }
}