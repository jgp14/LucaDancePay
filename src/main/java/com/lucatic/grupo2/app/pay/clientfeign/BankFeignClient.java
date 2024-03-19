package com.lucatic.grupo2.app.pay.clientfeign;


import com.lucatic.grupo2.app.pay.models.dto.BankRequest;
import com.lucatic.grupo2.app.pay.models.dto.BankResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Cliente Feign para la comunicación con el servicio bancario.
 *
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 19-03-2024
 */
@FeignClient(name ="pago", url = "http://banco-env.eba-ui2d2xf3.us-east-1.elasticbeanstalk.com")
public interface BankFeignClient {
	
	/**
     * Envía una solicitud al servicio bancario para procesar una transacción de compra.
     *
     * @param bankRequest La solicitud de compra que contiene los detalles de la transacción.
     * @return BankResponse La respuesta del servicio bancario a la transacción de compra.
     */

    @PostMapping("/pasarela/compra/")
    public BankResponse processBank(@RequestBody BankRequest bankRequest);
}
