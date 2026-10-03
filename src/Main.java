import controllers.FiguraController;
import domain.Circulo;
import domain.Cuadrado;
import domain.Figura;
import domain.PentagonoRegular;
import domain.Triangulo;
import exceptions.DimensionInvalidaException;

import java.util.NoSuchElementException;
import java.util.Scanner;

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

        menu(scanner, controller);
        scanner.close();
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
                agregarFigura(scanner, controller);
            } else if (opcion == 2) {
                listarFiguras(controller);
            } else if (opcion == 3) {
                System.out.println("Saliendo del sistema.");
                return;
            } else {
                System.out.println("Opcion invalida, por favor ingrese una opcion valida.");
            }
        }
    }

    /**
     * Crea una figura a partir de lo que indica el usuario.
     */
    private static void agregarFigura(Scanner scanner, FiguraController controller) {
        Figura figura = crearFigura(scanner);
        if (figura != null) {
            controller.agregarFigura(figura);
            figura.imprimirInformacion();
        }
    }

    /**
     * Muestra el detalle de todas las figuras almacenadas.
     */
    private static void listarFiguras(FiguraController controller) {
        if (controller.estaVacio()) {
            System.out.println("No hay figuras registradas.");
            return;
        }
        controller.listarFiguras();
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
                    return new Triangulo(leerDecimal(scanner, "Ingrese el tamano del lado: "));
                case 2:
                    return new Cuadrado(leerDecimal(scanner, "Ingrese el tamano del lado: "));
                case 3:
                    return new Circulo(leerDecimal(scanner, "Ingrese el radio del circulo: "));
                case 4:
                    float apotema = leerDecimal(scanner, "Ingrese el apotema del pentagono: ");
                    float lado = leerDecimal(scanner, "Ingrese el lado del pentagono: ");
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
     * Lee un numero decimal escrito por el usuario.
     */
    private static float leerDecimal(Scanner scanner, String mensaje) {
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