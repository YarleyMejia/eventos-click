package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CrearOrdenDTO;
import co.edu.uniquindio.proyecto.dto.FiltroEventoDTO;
import co.edu.uniquindio.proyecto.dto.ItemOrdenDTO;
import com.mercadopago.resources.preference.Preference;

import java.util.List;
import java.util.Map;

public interface OrdenServicio {
    //editar no va aquí
    //eliminar tampoco
    String crearOrden(CrearOrdenDTO crearOrdenDTO);
    List <ItemOrdenDTO> obtenerOrdenes();//administrador
    List <ItemOrdenDTO> filtrarOrdenes(FiltroEventoDTO filtroDTO);//clinteFinal

    //Preference realizarPago(String idOrden) throws Exception;
    void recibirNotificacionMercadoPago(Map<String, Object> request);

}
