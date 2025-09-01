package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.documentos.*;
import co.edu.uniquindio.proyecto.modelo.vo.DetalleOrden;
import co.edu.uniquindio.proyecto.modelo.vo.Localidad;
import co.edu.uniquindio.proyecto.modelo.vo.Pago;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EventoServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.OrdenServicio;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenServicioImpl implements OrdenServicio {

    private final OrdenRepo ordenRepo;
    private final EventoRepo eventoRepo;
    private final CuponRepo cuponRepo;
    private final CuentaRepo cuentaRepo;
    private final CarritoRepo carritoRepo;

    private final EmailServicio emailService;
    private final EventoServicio eventoServicio;

    @Override
    public String crearOrden(CrearOrdenDTO crearOrdenDTO) {


        Optional<Carrito> carritoOptional = carritoRepo.findById(crearOrdenDTO.idCarrito());

        if (carritoOptional.isEmpty())
        {
            throw new RuntimeException("Carrito no encontrado");
        }

        Carrito carritoOpcional = carritoOptional.get();


        // Verificar la existencia del cliente
        Optional<Cuenta> cuentaOpt = cuentaRepo.findById(crearOrdenDTO.idCliente());

        //Cuenta cuenta = cuentaOpt.get();
        if (cuentaOpt.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado");
        }

        Cuenta cuenta = cuentaOpt.get();

        // Validar los eventos y localidades
        List<DetalleOrden> detalles = new ArrayList<>();

// Aquí, el método items() del DTO CrearOrdenDTO devuelve una lista de DetalleOrdenDTO: 
        for (DetalleOrdenDTO dto : crearOrdenDTO.items()) {

            Optional<Evento> eventoOpt = eventoRepo.findById(dto.idEvento());

            if (eventoOpt.isEmpty()) {
                throw new RuntimeException("Evento no encontrado");
            }

            Evento evento = eventoOpt.get();

            //Reemplazo de la expresión lambda con un ciclo for
            Localidad localidadEncontrada = null;

            for (Localidad localidad : evento.getLocalidades()) {

                if (localidad.getNombre().equals(dto.nombreLocalidad())) {
                    localidadEncontrada = localidad;
                    break;
                }
            }

            if (localidadEncontrada == null) {
                throw new RuntimeException("Localidad no encontrada");
            }

            if (localidadEncontrada.getCapacidadMaxima() < dto.cantidad()) {

                throw new RuntimeException("No hay suficiente capacidad en la localidad");
            }

            //Reducir capacidad de la localidad con la cantidad comprada que esta en el detalle
            localidadEncontrada.setCapacidadMaxima(localidadEncontrada.getCapacidadMaxima() - dto.cantidad());

            // Crear el detalle de la orden
            DetalleOrden detalle = new DetalleOrden();
            detalle.setIdEvento(new ObjectId(dto.idEvento()));
            detalle.setNombreLocalidad(dto.nombreLocalidad());
            detalle.setCantidad(dto.cantidad());
            detalle.setPrecio(dto.precio());
            detalles.add(detalle);
        }

        //Validar el cupón si está presente
        float descuento = 0;

        if (crearOrdenDTO.idCupon() != null) {

            Optional<Cupon> cuponOpt = cuponRepo.findById(crearOrdenDTO.idCupon());

            if (cuponOpt.isEmpty()) {
                throw new RuntimeException("Cupón no encontrado");
            }

            Cupon cupon = cuponOpt.get();
            descuento = cupon.getDescuento(); // Aplicar el descuento
        }

        //Crear la orden
        Orden orden = new Orden();
        orden.setIdCliente(new ObjectId(crearOrdenDTO.idCliente()));
        orden.setFecha(LocalDateTime.now());
        orden.setCodigoPasarela(crearOrdenDTO.codigoPasarela());

        if (crearOrdenDTO.idCupon() != null) {
            orden.setIdCupon(new ObjectId(crearOrdenDTO.idCupon()));
        }

        orden.setTotal(crearOrdenDTO.total() - descuento);
        orden.setItems(detalles);
       // orden.setPago(pago); pendiente

        //Guardar la orden en MongoDB
        Orden ordenGuardada = ordenRepo.save(orden);

        //Retornar el ID de la orden como String
        return "Orden creada con éxito. ID: " + ordenGuardada.getId();
    }


    @Override
    public List<ItemOrdenDTO> obtenerOrdenes() {
        List<Orden> ordenes = ordenRepo.findAll();
        return ordenes.stream()
                .map(this::convertirAItemOrdenDTO)
                .collect(Collectors.toList());
    }

    private ItemOrdenDTO convertirAItemOrdenDTO(Orden orden) {
        List<DetalleOrdenResumenDTO> detalles = orden.getItems().stream()
                .map(this::convertirADetalleOrdenResumenDTO)
                .collect(Collectors.toList());

        return new ItemOrdenDTO(
                orden.getId().toString(),
                orden.getIdCliente().toString(),
                orden.getFecha(),
                orden.getCodigoPasarela(),
                orden.getIdCupon() != null ? orden.getIdCupon().toString() : null,
                orden.getTotal(),
                detalles
        );
    }

    private DetalleOrdenResumenDTO convertirADetalleOrdenResumenDTO(DetalleOrden detalle) {
        Evento evento = eventoRepo.findById(String.valueOf(detalle.getIdEvento())).orElseThrow(() ->
                new RuntimeException("Evento no encontrado"));

        return new DetalleOrdenResumenDTO(
                detalle.getIdEvento().toString(),
                evento.getNombre(),
                detalle.getNombreLocalidad(),
                detalle.getCantidad(),
                detalle.getPrecio()
        );
    }

    @Override
    public List<ItemOrdenDTO> filtrarOrdenes(FiltroEventoDTO filtroDTO) {
        return List.of();
    }

   //------------------------------------------------------------------------

    private Orden obtenerOrden(String idOrden) {
        return ordenRepo.buscaOrden(idOrden);
    }

/*   @Override
    public Preference realizarPago(String idOrden) throws Exception {
// Obtener la orden guardada en la base de datos y los ítems de la orden
        Orden ordenGuardada = obtenerOrden(idOrden);
        List<PreferenceItemRequest> itemsPasarela = new ArrayList<>();
// Recorrer los items de la orden y crea los ítems de la pasarela
        for(DetalleOrden item : ordenGuardada.getItems()){
// Obtener el evento y la localidad del ítem
            Evento evento = eventoServicio.obtenerEvento(item.getCodigoEvento().toString());
            Localidad localidad = evento.obtenerLocalidad(item.getNombreLocalidad());
// Crear el item de la pasarela
            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id(evento.getCodigo())
                            .title(evento.getNombre())
                            .pictureUrl(evento.getImagenPortada())
                            .categoryId(evento.getTipo().name())
                            .quantity(item.getCantidad())
                            .currencyId("COP")
                            .unitPrice(BigDecimal.valueOf(localidad.getPrecio()))
                            .build();

            itemsPasarela.add(itemRequest);
        }
// Configurar las credenciales de MercadoPago
        MercadoPagoConfig.setAccessToken("ACCESS_TOKEN");
// Configurar las urls de retorno de la pasarela (Frontend)
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("URL PAGO EXITOSO")
                .failure("URL PAGO FALLIDO")
                .pending("URL PAGO PENDIENTE")
                .build();
// Construir la preferencia de la pasarela con los ítems, metadatos y urls de retorno
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .backUrls(backUrls)
                .items(itemsPasarela)
                .metadata(Map.of("id_orden", ordenGuardada.getCodigo()))
                .notificationUrl("URL NOTIFICACION")
                .build();
// Crear la preferencia en la pasarela de MercadoPago
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);
// Guardar el código de la pasarela en la orden
        ordenGuardada.setCodigoPasarela( preference.getId() );
        ordenRepo.save(ordenGuardada);

        return preference;
    }
*/


    /*
se encarga de manejar las notificaciones
enviadas por MercadoPago a nuestro servidor. Cuando MercadoPago envía una notificación sobre
el estado de un pago, este método procesa esa notificación y actualiza la orden correspondiente en
la base de datos.
     */
    @Override
    public void recibirNotificacionMercadoPago(Map<String, Object> request) {
        try {
// Obtener el tipo de notificación
            Object tipo = request.get("type");
// Si la notificación es de un pago entonces obtener el pago y la orden asociada
            if ("payment".equals(tipo)) {
// Capturamos el JSON que viene en el request y lo convertimos a un String
                String input = request.get("data").toString();
// Extraemos los números de la cadena, es decir, el id del pago
                String idPago = input.replaceAll("\\D+", "");
// Se crea el cliente de MercadoPago y se obtiene el pago con el id
                PaymentClient client = new PaymentClient();
                Payment payment = client.get( Long.parseLong(idPago) );
// Obtener el id de la orden asociada al pago que viene en los metadatos
                String idOrden = payment.getMetadata().get("id_orden").toString();
// Se obtiene la orden guardada en la base de datos y se le asigna el pago
                Orden orden = obtenerOrden(idOrden);
                Pago pago = crearPago(payment);
                orden.setPago(pago);
                ordenRepo.save(orden);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//---------------------------------------------------------------------------------------------------
    private Pago crearPago(Payment payment) {
        Pago pago = new Pago();
        pago.setCodigoAutorizacion(payment.getId().toString());
        pago.setFecha( payment.getDateCreated().toLocalDateTime() );
        pago.setEstado(payment.getStatus());
        pago.setDetalleEstado(payment.getStatusDetail());
        pago.setTipoPago(payment.getPaymentTypeId());
        pago.setMoneda(payment.getCurrencyId());
        pago.setCodigoAutorizacion(payment.getAuthorizationCode());
        pago.setValorTransaccion(payment.getTransactionAmount().floatValue());
        return pago;
    }


    /**
     *Método que me permite generar un código aleatorio:
     */
    private String generarCodigoDeOrden(){
        String caracteres = "ABCDEFGHIJKLMNOPQRST1234567890";
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int indice = (int)(caracteres.length() * Math.random());
            codigo.append(caracteres.charAt(indice));
        }
        return codigo.toString();
    }
}
