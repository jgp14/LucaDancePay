package com.lucatic.grupo2.app.pay.clientfeign;


import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="pago", url = "http://banco-env.eba-ui2d2xf3.us-east-1.elasticbeanstalk.com")
public interface BankFeignClient {

    @PostMapping("/pasarela/compra/")
    public BankResponse processBank(@RequestBody @Valid BankRequest bankRequest);
}
