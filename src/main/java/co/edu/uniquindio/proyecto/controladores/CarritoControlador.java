package co.edu.uniquindio.proyecto.controladores;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para la gestión de carritos de compras.
 *
 * Actualmente está vacío, pero se puede extender para:
 * - Agregar productos al carrito
 * - Eliminar productos
 * - Ver el contenido del carrito
 * - Procesar compras
 */
@RestController // Marca la clase como controlador REST
@RequiredArgsConstructor // Genera constructor con campos final automáticamente
@RequestMapping("/api/carritos") // Prefijo común para todos los endpoints de carrito
public class CarritoControlador {
    // Aquí se inyectarán servicios de carrito en el futuro
}
