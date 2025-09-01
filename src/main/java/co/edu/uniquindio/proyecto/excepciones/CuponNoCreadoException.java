package co.edu.uniquindio.proyecto.excepciones;

import co.edu.uniquindio.proyecto.modelo.enums.TipoError;

public class CuponNoCreadoException extends Exception{

    private final TipoError tipoError;

    public CuponNoCreadoException(String mensaje){
        super(mensaje);
        this.tipoError = TipoError.UNKNOWN_ERROR;
    }

    public CuponNoCreadoException(String mensaje, TipoError tipoError) {
        super(mensaje);
        this.tipoError = tipoError;
    }

    public CuponNoCreadoException(String mensaje, TipoError tipoError, Throwable causa) {
        super(mensaje, causa);
        this.tipoError = tipoError;
    }

    public TipoError obtenerTipoError(){
        return tipoError;
    }
}
