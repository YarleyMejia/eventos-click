package co.edu.uniquindio.proyecto.excepciones.dataError;

public class EmailNotFound extends RuntimeException{
    public EmailNotFound(String message) {
        super(message);
    }
}
