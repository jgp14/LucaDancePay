package com.lucatic.grupo2.app.pay.clientfeign;

import com.lucatic.grupo2.app.pay.models.dto.BoolResponseWithError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Cliente Feign para la comunicación con el servicio de gestión de eventos y usuarios.
 *
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 19-03-2024
 */
@FeignClient(name = "eventmanager")
public interface CheckUserEventFeignClient {
	
	/**
     * Verifica si un usuario está registrado en un evento específico.
     *
     * @param idUser  El id del usuario.
     * @param idEvent El id del evento.
     * @return BoolResponseWithError La respuesta del servicio de gestión de eventos y usuarios, indicando si el usuario está registrado en el evento.
     */
    @GetMapping("/eventmanager/checkExist/{idUser}/{idEvent}")
    BoolResponseWithError checkUserEvent(@PathVariable Long idUser, @PathVariable Long idEvent);
    
    /**
     * Obtiene el nombre de un usuario dado su identificador.
     *
     * @param idUser El id del usuario.
     * @return String El nombre del usuario.
     */
    @GetMapping("/eventmanager/getUser/{idUser}")
    String getNameUser(@PathVariable Long idUser);
}
