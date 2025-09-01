package co.edu.uniquindio.proyecto.excepciones.dataError;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}