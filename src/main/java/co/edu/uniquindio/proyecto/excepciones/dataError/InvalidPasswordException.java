package co.edu.uniquindio.proyecto.excepciones.dataError;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
