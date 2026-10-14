package exceptions;

/**
 * @author donpedromz
 * @version 1.0.0
 * Representa cuando se ingresa un tamaño incorrecto para
 * la creación de una figura.
 */
public class DimensionInvalidaException extends RuntimeException {
    public DimensionInvalidaException(String message) {
        super(message);
    }
}
